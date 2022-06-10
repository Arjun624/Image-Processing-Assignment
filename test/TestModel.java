import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

import model.ImageEditor;
import model.ImageModel;
import model.Pixel;
import view.ImageDisplay;
import view.ImageView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests the {@code ImageModel}.
 */
public class TestModel {

  @Test
  public void testValidInitialization() {
    ImageModel m = new ImageModel();
    ImageView v = new ImageDisplay(System.out);
    assertFalse(m.quit);
    assertTrue(m.images.isEmpty());

    HashMap<String, Pixel[][]> testMap = new HashMap<>();
    String file = "test";
    Pixel[][] arr = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr[i][j] = new Pixel(i, j, 1);
      }
    }
    testMap.put(file, arr);
    ImageModel m1 = new ImageModel(testMap, v);
    assertFalse(m1.quit);
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(m1.images.get(file)[i][j], new Pixel(i, j, 1));
      }
    }
  }

  @Test
  public void testNullMap() {
    ImageView v = new ImageDisplay(System.out);
    try {
      new ImageModel(null, v);
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "HashMap or ImageView cannot be null");
    }
  }

  @Test
  public void testNullView() {
    HashMap<String, Pixel[][]> testMap = new HashMap<>();
    try {
      new ImageModel(testMap, null);
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "HashMap or ImageView cannot be null");
    }
  }

  @Test
  public void testFlipVertically() throws IOException {
    ImageView v = new ImageDisplay(new StringBuilder());
    HashMap<String, Pixel[][]> testMap = new HashMap<>();
    String file = "test";
    Pixel[][] arr = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr[i][j] = new Pixel(i, j, 1);
      }
    }
    testMap.put(file, arr);
    ImageModel m1 = new ImageModel(testMap, v);
    m1.flipVertically("test", "test-vf");
    Pixel[][] arr1 = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr1[2 - i][j] = new Pixel(i, j, 1);
      }
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(m1.images.get("test-vf")[i][j], arr1[i][j]);
      }
    }

    try{
      m1.flipVertically("yes", "no");
      fail("Should have thrown an exception");
    } catch (NullPointerException e){
      assertEquals(e.getMessage(), "file doesn't exist");
    }
  }

  @Test
  public void testFLipHorizontally() throws IOException {
    ImageView v = new ImageDisplay(new StringBuilder());
    HashMap<String, Pixel[][]> testMap = new HashMap<>();
    String file = "test";
    Pixel[][] arr = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr[i][j] = new Pixel(i, j, 1);
      }
    }
    testMap.put(file, arr);
    ImageModel m1 = new ImageModel(testMap, v);
    m1.flipHorizontally("test", "test-hf");
    Pixel[][] arr1 = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr1[i][2 - j] = new Pixel(i, j, 1);
      }
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(m1.images.get("test-hf")[i][j], arr1[i][j]);
      }
    }

    try{
      m1.flipHorizontally("yes", "no");
      fail("Should have thrown an exception");
    } catch (NullPointerException e){
      assertEquals(e.getMessage(), "file doesn't exist");
    }
  }

  @Test
  public void testRedGreyscale() throws IOException {
    ImageView v = new ImageDisplay(new StringBuilder());
    HashMap<String, Pixel[][]> testMap = new HashMap<>();
    String file = "test";
    Pixel[][] arr = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr[i][j] = new Pixel(110 + i, 120, 130);
      }
    }
    testMap.put(file, arr);
    ImageModel m1 = new ImageModel(testMap, v);
    m1.redGreyscale("test", "test-r");
    Pixel[][] arr1 = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr1[i][j] = new Pixel(110 + i, 110 + i, 110 + i);
      }
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(m1.images.get("test-r")[i][j], arr1[i][j]);
      }
    }

    try{
      m1.redGreyscale("yes", "no");
      fail("Should have thrown an exception");
    } catch (NullPointerException e){
      assertEquals(e.getMessage(), "file doesn't exist");
    }
  }

  @Test
  public void testGreenGreyscale() throws IOException {
    ImageView v = new ImageDisplay(new StringBuilder());
    HashMap<String, Pixel[][]> testMap = new HashMap<>();
    String file = "test";
    Pixel[][] arr = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr[i][j] = new Pixel(110, 120 + j, 130);
      }
    }
    testMap.put(file, arr);
    ImageModel m1 = new ImageModel(testMap, v);
    m1.greenGreyscale("test", "test-g");
    Pixel[][] arr1 = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr1[i][j] = new Pixel(120 + j, 120 + j, 120 + j);
      }
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(m1.images.get("test-g")[i][j], arr1[i][j]);
      }
    }

    try{
      m1.greenGreyscale("yes", "no");
      fail("Should have thrown an exception");
    } catch (NullPointerException e){
      assertEquals(e.getMessage(), "file doesn't exist");
    }
  }

  @Test
  public void testBlueGreyscale() throws IOException {
    ImageView v = new ImageDisplay(new StringBuilder());
    HashMap<String, Pixel[][]> testMap = new HashMap<>();
    String file = "test";
    Pixel[][] arr = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr[i][j] = new Pixel(110, 120, 130 + i + j);
      }
    }
    testMap.put(file, arr);
    ImageModel m1 = new ImageModel(testMap, v);
    m1.blueGreyscale("test", "test-b");
    Pixel[][] arr1 = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr1[i][j] = new Pixel(130 + i + j, 130 + i + j, 130 + i + j);
      }
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(m1.images.get("test-b")[i][j], arr1[i][j]);
      }
    }

    try{
      m1.blueGreyscale("yes", "no");
      fail("Should have thrown an exception");
    } catch (NullPointerException e){
      assertEquals(e.getMessage(), "file doesn't exist");
    }
  }

  @Test
  public void testAdjustBrightness() throws IOException {
    ImageView v = new ImageDisplay(new StringBuilder());
    HashMap<String, Pixel[][]> testMap = new HashMap<>();
    String file = "test";
    Pixel[][] arr = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr[i][j] = new Pixel(i, j, i + j);
      }
    }
    testMap.put(file, arr);
    ImageModel m1 = new ImageModel(testMap, v);
    m1.adjustBrightness(10, "test", "test-b1");
    Pixel[][] arr1 = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr1[i][j] = new Pixel(i + 10, j + 10, i + j + 10);
      }
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(m1.images.get("test-b1")[i][j], arr1[i][j]);
      }
    }

    ImageView v2 = new ImageDisplay(new StringBuilder());
    HashMap<String, Pixel[][]> testMap2 = new HashMap<>();
    String file2 = "test";
    Pixel[][] arr2 = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr2[i][j] = new Pixel(100, 100, 100);
      }
    }
    testMap2.put(file2, arr2);
    ImageModel m2 = new ImageModel(testMap2, v2);
    m2.adjustBrightness(-10, "test", "test-b2");
    Pixel[][] testArr2 = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        testArr2[i][j] = new Pixel(90, 90, 90);
      }
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(m2.images.get("test-b2")[i][j], testArr2[i][j]);
      }
    }

    ImageView v3 = new ImageDisplay(new StringBuilder());
    HashMap<String, Pixel[][]> testMap3 = new HashMap<>();
    String file3 = "test";
    Pixel[][] arr3 = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr3[i][j] = new Pixel(250, 250, 250);
      }
    }
    testMap3.put(file3, arr3);
    ImageModel m3 = new ImageModel(testMap3, v3);
    m3.adjustBrightness(10, "test", "test-b3");
    Pixel[][] testArr3 = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        testArr3[i][j] = new Pixel(255, 255, 255);
      }
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(m3.images.get("test-b3")[i][j], testArr3[i][j]);
      }
    }

    ImageView v4 = new ImageDisplay(new StringBuilder());
    HashMap<String, Pixel[][]> testMap4 = new HashMap<>();
    String file4 = "test";
    Pixel[][] arr4 = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr4[i][j] = new Pixel(5, 5, 5);
      }
    }
    testMap4.put(file4, arr4);
    ImageModel m4 = new ImageModel(testMap4, v4);
    m4.adjustBrightness(-10, "test", "test-b4");
    Pixel[][] testArr4 = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        testArr4[i][j] = new Pixel(0, 0, 0);
      }
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(m4.images.get("test-b4")[i][j], testArr4[i][j]);
      }
    }

    try{
      m4.adjustBrightness(40, "yes", "no");
      fail("Should have thrown an exception");
    } catch (NullPointerException e){
      assertEquals(e.getMessage(), "file doesn't exist");
    }
  }

  @Test
  public void testLumaGreyscale() throws IOException {
    ImageView v = new ImageDisplay(new StringBuilder());
    HashMap<String, Pixel[][]> testMap = new HashMap<>();
    String file = "test";
    Pixel[][] arr = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr[i][j] = new Pixel(i, j, i + j);
      }
    }
    testMap.put(file, arr);
    ImageModel m1 = new ImageModel(testMap, v);
    m1.lumaGreyscale("test", "test-lg");
    Pixel[][] arr1 = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        int luma = (int) ((0.2126 * i) + (0.7152 * j) + (0.0722 * (i + j)));
        arr1[i][j] = new Pixel(luma,luma,luma);
      }
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(m1.images.get("test-lg")[i][j], arr1[i][j]);
      }
    }

    try{
      m1.lumaGreyscale("yes", "no");
      fail("Should have thrown an exception");
    } catch (NullPointerException e){
      assertEquals(e.getMessage(), "file doesn't exist");
    }
  }

  @Test
  public void testIntensityGreyscale() throws IOException {
    ImageView v = new ImageDisplay(new StringBuilder());
    HashMap<String, Pixel[][]> testMap = new HashMap<>();
    String file = "test";
    Pixel[][] arr = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr[i][j] = new Pixel(i, j, i + j);
      }
    }
    testMap.put(file, arr);
    ImageModel m1 = new ImageModel(testMap, v);
    m1.intensityGreyscale("test", "test-ig");
    Pixel[][] arr1 = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        int intensity = (2 * i + 2 * j) / 3;
        arr1[i][j] = new Pixel(intensity,intensity,intensity);
      }
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(m1.images.get("test-ig")[i][j], arr1[i][j]);
      }
    }
    try{
      m1.intensityGreyscale("yes", "no");
      fail("Should have thrown an exception");
    } catch (NullPointerException e){
      assertEquals(e.getMessage(), "file doesn't exist");
    }
  }

  @Test
  public void testValueGreyscale() throws IOException {
    ImageView v = new ImageDisplay(new StringBuilder());
    HashMap<String, Pixel[][]> testMap = new HashMap<>();
    String file = "test";
    Pixel[][] arr = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr[i][j] = new Pixel(i, j, i + j);
      }
    }
    testMap.put(file, arr);
    ImageModel m1 = new ImageModel(testMap, v);
    m1.valueGreyscale("test", "test-vg");
    Pixel[][] arr1 = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr1[i][j] = new Pixel(i + j,i + j,i + j);
      }
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(m1.images.get("test-vg")[i][j], arr1[i][j]);
      }
    }

    try{
      m1.valueGreyscale("yes", "no");
      fail("Should have thrown an exception");
    } catch (NullPointerException e){
      assertEquals(e.getMessage(), "file doesn't exist");
    }
  }

  @Test
  public void testLoadImage() throws IOException {
    ImageModel m = new ImageModel();
    m.loadImage("/Users/noamgreenstein/Documents/OOD" +
            "/Image-Processing-Assignment/images/Koala.ppm","test");

    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream("/Users/noamgreenstein/Documents/OOD" +
              "/Image-Processing-Assignment/images/Koala.ppm"));
    }
    catch (FileNotFoundException e) {
      return;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0)!='#') {
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
    for (int row=0;row<height;row++) {
      for (int col=0;col<width;col++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        pixels[row][col] = new Pixel(r,g,b);
      }
    }

    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[0].length; j++) {
        assertEquals(m.images.get("test")[i][j], pixels[i][j]);
      }
    }

    try {
      m.loadImage("noam", "ood");
      fail("should have thrown an exception");
    } catch (NoSuchElementException e){
      assertEquals(e.getMessage(), "File noam not found!");
    }
  }

  @Test
  public void testSaveImage() throws IOException {
    ImageModel m = new ImageModel();
    m.loadImage("/Users/noamgreenstein/Documents/OOD" +
            "/Image-Processing-Assignment/images/Koala.ppm","test");
    m.saveImage("/Users/noamgreenstein/Documents/OOD" +
            "/Image-Processing-Assignment/images/Test.ppm","test");

    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream("/Users/noamgreenstein/Documents/OOD" +
              "/Image-Processing-Assignment/images/Test.ppm"));
    }
    catch (FileNotFoundException e) {
      return;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0)!='#') {
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
    for (int row=0;row<height;row++) {
      for (int col=0;col<width;col++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        pixels[row][col] = new Pixel(r,g,b);
      }
    }

    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[0].length; j++) {
        assertEquals(m.images.get("test")[i][j], pixels[i][j]);
      }
    }
  }
}
