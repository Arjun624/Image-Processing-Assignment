import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import controller.ImageController;
import controller.ImageControllerImpl;
import model.ImageEditor;
import model.ImageModel;
import model.Pixel;
import view.ImageDisplay;
import view.ImageView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * Test that the {@code ImageControllerImpl} handles inout correctly.
 */
public class TestController {

  ImageView v;
  ImageEditor m;
  Appendable ap;
  Appendable apView;

  @Before
  public void init() {
    ap = new StringBuilder();
    apView = new StringBuilder();
    m = new MockImageModel(ap);
    v = new ImageDisplay(apView);
  }

  @Test
  public void testValidInitialization() throws IOException {
    Readable r = new StringReader("q");
    ImageController c = new ImageControllerImpl(v, r, m);
    c.start();
    assertEquals("program quit", ap.toString());
  }

  @Test
  public void testInvalidInitialization() throws IOException {
    try {
      new ImageControllerImpl(null, new StringReader(""), m);
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "View, readable, or model cannot be null");
    }

    try {
      new ImageControllerImpl(v, null, m);
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "View, readable, or model cannot be null");
    }

    try {
      new ImageControllerImpl(v, new StringReader(""), null);
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "View, readable, or model cannot be null");
    }

    Readable r = new StringReader("");
    ImageController c = new ImageControllerImpl(v, r, m);
    try {
      c.start();
      fail("Should have thrown an exception");
    } catch (NoSuchElementException e) {
      assertNull(e.getMessage());
    }

  }

  @Test
  public void testValidInput() throws IOException {
    Readable r = new StringReader("load " +
            "res/test.ppm " +
            "test " +
            "adjust-brightness 10 test test-ab " +
            "vertical-flip test test-vf " +
            "horizontal-flip test test-hf " +
            "greyscale-red test test-rg " +
            "greyscale-green test test-gg " +
            "greyscale-blue test test-bg " +
            "luma test test-l " +
            "intensity test test-i  " +
            "value test test-v  q");
    ImageController c = new ImageControllerImpl(v, r, m);
    c.start();
    assertEquals("added test to hashmap", ap.toString().split("\n")[0]);
    assertEquals("adjusted test by 10. Is now test-ab",
            ap.toString().split("\n")[1]);
    assertEquals("flipped test vertically. Is now test-vf",
            ap.toString().split("\n")[2]);
    assertEquals("flipped test horizontally. Is now test-hf",
            ap.toString().split("\n")[3]);
    assertEquals("preformed a greyscale of type red on test. Is now test-rg",
            ap.toString().split("\n")[4]);
    assertEquals("preformed a greyscale of type red on test. Is now test-rg",
            ap.toString().split("\n")[4]);
    assertEquals("preformed a greyscale of type green on test. Is now test-gg",
            ap.toString().split("\n")[5]);
    assertEquals("preformed a greyscale of type blue on test. Is now test-bg",
            ap.toString().split("\n")[6]);
    assertEquals("preformed a greyscale of type luma on test. Is now test-l",
            ap.toString().split("\n")[7]);
    assertEquals("preformed a greyscale of type intensity on test. Is now test-i",
            ap.toString().split("\n")[8]);
    assertEquals("preformed a greyscale of type value on test. Is now test-v",
            ap.toString().split("\n")[9]);
    assertEquals("program quit",
            ap.toString().split("\n")[10]);
  }

  @Test
  public void testInvalidInput() throws IOException {
    Readable r = new StringReader("hello q");
    ImageController c = new ImageControllerImpl(v, r, m);
    c.start();
    assertEquals("Enter a command: \n" +
            "Invalid command!\n" +
            "Enter a command: \n" +
            "Thanks!\n", apView.toString());

    Appendable ap2 = new StringBuilder();
    ImageView vModel = new ImageDisplay(ap2);
    ImageEditor m2 = new ImageModel(new HashMap<>(), vModel);
    Readable r2 = new StringReader("load hello1 test q");
    Appendable apView2 = new StringBuilder();
    ImageView v2 = new ImageDisplay(apView2);
    ImageController c2 = new ImageControllerImpl(v2, r2, m2);
    c2.start();

    Appendable ap3 = new StringBuilder();
    ImageView vModel2 = new ImageDisplay(ap3);
    ImageEditor m3 = new ImageModel(new HashMap<>(), vModel2);
    Readable r3 = new StringReader("load " +
            "res/doesNotExist.ppm " +
            "test max hello test-max q");
    Appendable apView3 = new StringBuilder();
    ImageView v3 = new ImageDisplay(apView3);
    ImageController c3 = new ImageControllerImpl(v3, r3, m3);
    c3.start();
    assertEquals("Enter a command: ", apView3.toString().split("\n")[2]);


  }

  @Test
  public void testLoadPPM() throws IOException {
    ImageModel m = new ImageModel();
    ImageView view = new ImageDisplay(new StringBuilder());
    ImageController c = new ImageControllerImpl(view, new InputStreamReader(System.in), m);
    c.loadImage("res/test.ppm", "test");


    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream("res/colors.ppm"));
    } catch (FileNotFoundException e) {
      return;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("what?");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    //now read the image data
    Pixel[][] pixels = new Pixel[height][width];
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        pixels[row][col] = new Pixel(r, g, b);
      }
    }

    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[0].length; j++) {
        assertEquals(m.images.get("test")[i][j], pixels[i][j]);
      }
    }

    try {
      c.loadImage("noam.ppm", "ood");
      fail("should have thrown an exception");
    } catch (NoSuchElementException e) {
      assertEquals(e.getMessage(), "File noam.ppm not found!");
    }
  }

  @Test
  public void testLoadOther() throws IOException {
    ImageModel m = new ImageModel();
    ImageView view = new ImageDisplay(new StringBuilder());
    ImageController c = new ImageControllerImpl(view, new InputStreamReader(System.in), m);
    c.loadImage("res/ClassDiagram.png", "test");

    BufferedImage b;
    try {
      b = ImageIO.read(new File("res/ClassDiagram.png"));
    } catch (IOException e) {
      throw new NoSuchElementException();
    }
    int width = b.getWidth();
    int height = b.getHeight();
    Pixel[][] arr = new Pixel[height][width];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        Color co = new Color(b.getRGB(i, j));
        arr[j][i] = new Pixel(co.getRed(), co.getGreen(), co.getBlue(), co.getAlpha());
      }
    }

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        assertEquals(m.images.get("test")[i][j], arr[i][j]);
      }
    }

    try {
      c.loadImage("fake.png", "test");
    } catch (NoSuchElementException e) {
      assertEquals(e.getMessage(), "File fake.png not found!");
    }
  }

  @Test
  public void testSavePPM() throws IOException {
    ImageModel m = new ImageModel();
    ImageView view = new ImageDisplay(new StringBuilder());
    ImageController c = new ImageControllerImpl(view, new InputStreamReader(System.in), m);
    c.loadImage("res/test.ppm", "test");
    c.saveImage("res/SaveTest.ppm", "test");

    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream("res/SaveTest.ppm"));
    } catch (FileNotFoundException e) {
      return;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("what?");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    //now read the image data
    Pixel[][] pixels = new Pixel[height][width];
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        pixels[row][col] = new Pixel(r, g, b);
      }
    }

    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[0].length; j++) {
        assertEquals(m.images.get("test")[i][j], pixels[i][j]);
      }
    }
  }

  @Test
  public void testSaveOther() throws IOException {
    ImageModel m = new ImageModel();
    ImageView view = new ImageDisplay(new StringBuilder());
    ImageController c = new ImageControllerImpl(view, new InputStreamReader(System.in), m);
    c.loadImage("res/ClassDiagram.png", "test");
    c.saveImage("res/ClassDiagram.png", "test");

    BufferedImage b;
    try {
      b = ImageIO.read(new File("res/ClassDiagram.png"));
    } catch (IOException e) {
      throw new NoSuchElementException();
    }
    int width = b.getWidth();
    int height = b.getHeight();
    Pixel[][] arr = new Pixel[height][width];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        Color co = new Color(b.getRGB(i, j));
        arr[j][i] = new Pixel(co.getRed(), co.getGreen(), co.getBlue(), co.getAlpha());
      }
    }

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        assertEquals(m.images.get("test")[i][j], arr[i][j]);
      }
    }
  }

  @Test
  public void testScript() throws IOException {
    Appendable ap = new StringBuilder();
    ImageView view = new ImageDisplay(new StringBuilder());
    ImageEditor model = new MockImageModel(ap);
    InputStreamReader in = new FileReader("res/testScript.txt");
    ImageControllerImpl controller = new ImageControllerImpl(view, in, model);
    controller.start();
    assertEquals("added test to hashmap\n" +
            "flipped test vertically. Is now test-vf\n" +
            "flipped test horizontally. Is now test-hf\n" +
            "preformed a greyscale of type red on test. Is now test-rg\n" +
            "preformed a greyscale of type green on test. Is now test-gg\n" +
            "preformed a greyscale of type blue on test. Is now test-bg\n" +
            "preformed a greyscale of type luma on test. Is now test-lg\n" +
            "preformed a greyscale of type intensity on test. Is now test-ig\n" +
            "preformed a greyscale of type value on test. Is now test-vg\n" +
            "adjusted test by 30. Is now test-abb\n" +
            "adjusted test by -30. Is now test-abd\n" +
            "filtered test. Is now test-sh\n" +
            "filtered test. Is now test-bl\n" +
            "transformed test. Is now test-sp\n" +
            "transformed test. Is now test-gs\n" +
            "program quit",ap.toString());
  }
}
