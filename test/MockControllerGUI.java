import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import controller.ImageController;
import view.GUIView;

public class MockControllerGUI implements ImageController, ActionListener {


  private Appendable log;
  private MockGUIView v;

  public MockControllerGUI(Appendable ap, MockGUIView v){
    this.log = ap;
    this.v = v;
  }

  /**
   * Method that takes in the user arguments and preforms any action.
   *
   * @throws IOException if the program cannot read the input or write the output
   */
  @Override
  public void start() throws IOException {

    log.append("Started The Program\n");
    v.displayWelcomeMessage();

  }

  /**
   * Loads an image from the inputted path and adds it to the map of images.
   *
   * @param pathname is the path to the image to be loaded.
   * @param filename is the name of the image to be loaded.
   * @throws IOException            if the image cannot be loaded.
   */
  @Override
  public void loadImage(String pathname, String filename) throws IOException {

    log.append("Loaded image\n");
    v.GetLoadFile();
    v.changeImage(new ImageIcon(pathname));

  }

  /**
   * Saves the given filename to the given pathname on the local disk.
   *
   * @param pathname the pathname of the file to save.
   * @param filename is the name of the file to save.
   * @throws IOException if the file cannot be saved.
   */
  @Override
  public void saveImage(String pathname, String filename) throws IOException {

    log.append("Saved image\n");

  }

  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {

    try{
      log.append("Action performed: ").append(e.getActionCommand()).append("\n");
    } catch (IOException e1) {
      throw new RuntimeException(e1);
    }

  }
}
