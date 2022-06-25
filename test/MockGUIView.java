import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import model.Pixel;
import view.GUIView;

public class MockGUIView implements GUIView {

  private Appendable log;

  public MockGUIView(Appendable ap){
    this.log = ap;
  }

  /**
   * Shows an error message.
   *
   * @param s the message
   */
  @Override
  public void showErrorPopup(String s)  {
    try {
      log.append("Error: ").append(s).append("\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Displays a histogram.
   *
   * @param pixels the pixels required to calculate the histogram.
   */
  @Override
  public void displayHistogram(Pixel[][] pixels) {
    try {
      log.append("Displayed histogram\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

  /**
   * Creates a buffered image out of an inputted array of pixels.
   *
   * @param pixels the inputted 2D array of pixels
   * @return a buffered image that stores each rgb value and can be displayed by the gui
   */
  @Override
  public BufferedImage getBufferedImage(Pixel[][] pixels) {
    try {
      log.append("Created buffered image\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return null;
  }

  /**
   * Renders a message.
   *
   * @param s the inputted message
   */
  @Override
  public void renderMessage(String s) {

    try {
      log.append("Rendered message: ").append(s).append("\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Adds an edit to the list of edits to be preformed on an image.
   *
   * @param command       the specific edit
   * @param inputtedEdits the list of edits to be preformed
   */
  @Override
  public void addEdit(String command, ArrayList<String> inputtedEdits) {

    try {
      log.append("Added edit: ").append(command).append("\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Changes the text on a label.
   *
   * @param color the text
   */
  @Override
  public void changeLabelText(String color) {
    try {
      log.append("Changed label text: ").append(color).append("\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Sets the increment to brighten an image.
   */
  @Override
  public void setIncrement() {
    try {
      log.append("Set increment\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

  /**
   * Returns the current brightness increment.
   *
   * @return the increment
   */
  @Override
  public int getIncrement() {
    try {
      log.append("Got increment\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return 0;
  }

  /**
   * Lets the user select a file to save to.
   *
   * @return the file to be saved
   */
  @Override
  public File getSaveFile(){
    try {
      log.append("Got save file\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return null;
  }

  /**
   * Lets the user select a file to load.
   *
   * @return the file to be loaded
   */
  @Override
  public File GetLoadFile() {
    try {
      log.append("Got load file\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return null;
  }

  /**
   * Changes the image shown by the GUI.
   *
   * @param image the image to be displayed
   */
  @Override
  public void changeImage(ImageIcon image) {
    try {
      log.append("Changed image\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

  /**
   * Sets the action listeners of the GUI.
   *
   * @param listener the listener to be set
   */
  @Override
  public void setActionListeners(ActionListener listener)  {

    try {
      log.append("Set action listeners\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

  /**
   * Resets the GUI to its default state.
   */
  @Override
  public void reset() {

    try {
      log.append("Reset\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Sets the height for a downscaled image.
   *
   * @param imageHeight the height of the downscaled image
   */
  @Override
  public void setDownScaleHeight(int imageHeight){

    try {
      log.append("Set downscale height\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Sets the width for a downscaled image.
   *
   * @param imageWidth the width of the downscaled image
   */
  @Override
  public void setDownScaleWidth(int imageWidth) {

    try {
      log.append("Set downscale width\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Gets the new height for an image to be downscaled.
   *
   * @return the new height for an image to be downscaled
   */
  @Override
  public int getDownScaleHeight() {
    try {
      log.append("Got downscale height\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return 0;
  }

  /**
   * Gets the new width for an image to be downscaled.
   *
   * @return the new width for an image to be downscaled
   */
  @Override
  public int getDownScaleWidth() {
    try {
      log.append("Got downscale width\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return 0;
  }

  /**
   * Displays the welcome message.
   */
  @Override
  public void displayWelcomeMessage()  {

    try {
      log.append("Displayed welcome message\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
