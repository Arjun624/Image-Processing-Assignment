import org.junit.Before;
import org.junit.Test;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.StringReader;
import java.util.NoSuchElementException;

import javax.swing.*;

import controller.ImageController;
import controller.ImageControllerGUI;
import controller.ImageControllerText;
import model.ImageEditor;
import view.GUIView;
import view.ImageDisplay;
import view.ImageView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

public class TestControllerGUI {

  GUIView v;
  ImageEditor m;
  Appendable ap;
  Appendable apView;

  @Before
  public void init() {
    ap = new StringBuilder();
    apView = new StringBuilder();
    m = new MockImageModel(ap);
    v = new MockGUIView(apView);
  }

  @Test
  public void testValidInitialization() throws IOException {
    MockGUIView v = new MockGUIView(apView);
    ImageController c = new MockControllerGUI(ap, v);
    c.start();
    assertEquals("Started The Program\n", ap.toString());
    assertEquals("Displayed welcome message\n", apView.toString());
  }

  @Test
  public void testLoadButton() throws IOException {
    MockGUIView v = new MockGUIView(apView);
    ImageController c = new MockControllerGUI(ap, v);
    c.loadImage("", "");
    assertEquals("Loaded image\n", ap.toString());
    assertEquals("Got load file\n"
            + "Changed image\n", apView.toString());

  }

  @Test
  public void testSaveButton() throws IOException {
    MockGUIView v = new MockGUIView(apView);
    ImageController c = new MockControllerGUI(ap, v);
    c.saveImage("", "");
    assertEquals("Saved image\n", ap.toString());
  }

  @Test
  public void testActionPerformed() throws IOException {
    MockGUIView v = new MockGUIView(apView);
    MockControllerGUI c = new MockControllerGUI(ap, v);
    ActionEvent e = new ActionEvent(new JButton(), 0, "load");
     c.actionPerformed(e);
    assertEquals("Action performed: load\n", ap.toString());
  }

  @Test
  public void testInvalidInitialization() throws IOException {
    try {
      new ImageControllerText(null, new StringReader(""), m);
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "View, readable, or model cannot be null");
    }

    try {
      new ImageControllerText(v, null, m);
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "View, readable, or model cannot be null");
    }

    try {
      new ImageControllerText(v, new StringReader(""), null);
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "View, readable, or model cannot be null");
    }

    Readable r = new StringReader("");
    ImageController c = new ImageControllerText(v, r, m);
    try {
      c.start();
      fail("Should have thrown an exception");
    } catch (NoSuchElementException e) {
      assertNull(e.getMessage());
    }

  }

  @Test
  public void testValidInput() throws IOException {
    Readable r = new StringReader("load "
            + "res/test.ppm "
            + "test "
            + "adjust-brightness 10 test test-ab "
            + "vertical-flip test test-vf "
            + "horizontal-flip test test-hf "
            + "greyscale-red test test-rg "
            + "greyscale-green test test-gg "
            + "greyscale-blue test test-bg "
            + "luma test test-l "
            + "intensity test test-i  "
            + "value test test-v  q");
    ImageController c = new ImageControllerText(v, r, m);
    c.start();
    assertEquals("added test to hashmap", ap.toString().split("\n")[0]);
    assertEquals("adjusted test by 10. Is now test-ab", ap.toString().split("\n")[1]);
    assertEquals("flipped test vertically. Is now test-vf", ap.toString().split("\n")[2]);
    assertEquals("flipped test horizontally. Is now test-hf", ap.toString().split("\n")[3]);
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
    assertEquals("program quit", ap.toString().split("\n")[10]);
  }
}
