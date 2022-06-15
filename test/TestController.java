import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.NoSuchElementException;

import controller.ImageController;
import controller.ImageControllerImpl;
import model.ImageEditor;
import model.ImageModel;
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
      new ImageControllerImpl(v, null,m);
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "View, readable, or model cannot be null");
    }

    try {
      new ImageControllerImpl(v, new StringReader(""),null);
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
            "/Users/noamgreenstein/Documents/OOD/Image-Processing-Assignment/images/Koala.ppm " +
            "test " +
            " adjust-brightness 10 test test-ab " +
            "vertical-flip test test-vf " +
            "horizontal-flip test test-hf " +
            "greyscale-red test test-rg " +
            "greyscale-green test test-gg " +
            "greyscale-blue test test-bg " +
            "luma test test-l " +
            "intensity test test-i  " +
            "max test test-m  q");
    ImageController c = new ImageControllerImpl(v, r,m);
    c.start();
    assertEquals("loaded " +
            "/Users/noamgreenstein/Documents/OOD/Image-Processing-Assignment/images/Koala.ppm" +
            " as test", ap.toString().split("\n")[0]);
    assertEquals("adjusted test by 10. Is now test-ab",
            ap.toString().split("\n")[1]);
    assertEquals("flipped test vertically. Is now test-vf",
            ap.toString().split("\n")[2]);
    assertEquals("flipped test horizontally. Is now test-hf",
            ap.toString().split("\n")[3]);
    assertEquals("preformed a red greyscale test. Is now test-rg",
            ap.toString().split("\n")[4]);
    assertEquals("preformed a red greyscale test. Is now test-rg",
            ap.toString().split("\n")[4]);
    assertEquals("preformed a green greyscale test. Is now test-gg",
            ap.toString().split("\n")[5]);
    assertEquals("preformed a blue greyscale test. Is now test-bg",
            ap.toString().split("\n")[6]);
    assertEquals("preformed a luma greyscale test. Is now test-l",
            ap.toString().split("\n")[7]);
    assertEquals("preformed an intensity greyscale test. Is now test-i",
            ap.toString().split("\n")[8]);
    assertEquals("program quit",
            ap.toString().split("\n")[9]);
  }

  @Test
  public void testInvalidInput() throws IOException {
    Readable r = new StringReader("hello q");
    ImageController c = new ImageControllerImpl(v, r,m);
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
    ImageController c2 = new ImageControllerImpl(v2, r2,m2);
    c2.start();

    Appendable ap3 = new StringBuilder();
    ImageView vModel2 = new ImageDisplay(ap3);
    ImageEditor m3 = new ImageModel(new HashMap<>(), vModel2);
    Readable r3 = new StringReader("load " +
            "/Users/noamgreenstein/Documents/OOD/Image-Processing-Assignment/images/Test.ppm " +
            "test max hello test-max q");
    Appendable apView3 = new StringBuilder();
    ImageView v3 = new ImageDisplay(apView3);
    ImageController c3 = new ImageControllerImpl(v3, r3,m3);
    c3.start();
    assertEquals("Enter a command: ", apView3.toString().split("\n")[2]);


  }

  @Test
  public void testLoadPPM(){

  }

  @Test
  public void testLoadOther(){

  }
  @Test
  public void testSavePPM(){

  }

  @Test
  public void testSaveOther(){

  }
}
