import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.NoSuchElementException;

import controller.ImageController;
import controller.ImageControllerImpl;
import model.ImageEditor;
import model.ImageModel;
import view.ImageDisplay;
import view.ImageView;

import static org.junit.Assert.assertEquals;

/**
 * Test that the {@code ImageControllerImpl} handles inout correctly
 */
public class TestController {

  ImageView v;
  ImageEditor m;
  Appendable ap;
  Appendable apView;
  @Before
  public void init(){
    ap = new StringBuilder();
    apView = new StringBuilder();
    m = new MockImageModel(ap);
    v = new ImageDisplay(apView);
  }

  @Test
  public void testValidInitialization() throws IOException {
    Readable r = new StringReader("q");
    ImageController c = new ImageControllerImpl(v,r);
    c.go(m);
    assertEquals("program quit",ap.toString());
  }

  @Test
  public void testInvalidInitialization(){
    try {
      new ImageControllerImpl(null, new StringReader(""));

    } catch (IllegalArgumentException e){
      assertEquals(e.getMessage(), "View or readable cannot be null");
    }

    try {
      new ImageControllerImpl(v, null);

    } catch (IllegalArgumentException e){
      assertEquals(e.getMessage(), "View or readable cannot be null");
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
    ImageController c = new ImageControllerImpl(v,r);
    c.go(m);
    assertEquals("loaded " +
            "/Users/noamgreenstein/Documents/OOD/Image-Processing-Assignment/images/Koala.ppm" +
            " as test",ap.toString().split("\n")[0]);
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
    assertEquals("preformed a value greyscale test. Is now test-m",
            ap.toString().split("\n")[9]);
    assertEquals("program quit",
            ap.toString().split("\n")[10]);
  }

  @Test
  public void testInvalidInput() throws IOException {
    Readable r = new StringReader("hello q");
    ImageController c = new ImageControllerImpl(v,r);
    c.go(m);
    assertEquals("Enter a command: \n" +
            "Invalid command!\n" +
            "Enter a command: \n" +
            "Thanks!\n",apView.toString());

    ImageEditor m2 = new ImageModel();
    Readable r2 = new StringReader("load hello q");
    ImageController c2 = new ImageControllerImpl(v,r2);
    try {
      c2.go(m2);
    } catch (NoSuchElementException e){
      assertEquals(e.getMessage(), "File hello not found");
    }
    assertEquals("Enter a command: \n" +
            "Invalid command!\n" +
            "Enter a command: \n" +
            "Thanks!\n",apView.toString());
  }
}
