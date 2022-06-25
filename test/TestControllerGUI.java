import org.junit.Before;
import org.junit.Test;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JButton;

import controller.ImageController;
import model.ImageEditor;
import view.GUIView;

import static org.junit.Assert.assertEquals;

/**
 * Tests that the GUI controller can handle input.
 */
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
}
