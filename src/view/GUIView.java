package view;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import model.Pixel;

/**
 * Represents the interface for a GUI for an Image processor.
 */
public interface GUIView extends ImageView {

  /**
   * Shows an error message.
   *
   * @param s the message
   */
  void showErrorPopup(String s) ;

  /**
   * Displays a histogram.
   *
   * @param pixels the pixels required to calculate the histogram.
   */
  void displayHistogram(Pixel[][] pixels) ;

  /**
   * Creates a buffered image out of an inputted array of pixels.
   *
   * @param pixels the inputted 2D array of pixels
   * @return a buffered image that stores each rgb value and can be displayed by the gui
   */
  BufferedImage getBufferedImage(Pixel[][] pixels) ;

  /**
   * Renders a message.
   *
   * @param s the inputted message
   */
  void renderMessage(String s) ;

  /**
   * Adds an edit to the list of edits to be preformed on an image.
   *
   * @param command       the specific edit
   * @param inputtedEdits the list of edits to be preformed
   */
  void addEdit(String command, ArrayList<String> inputtedEdits) ;

  /**
   * Changes the text on a label.
   *
   * @param type type of the text
   */
  void changeLabelText(String type) ;

  /**
   * Sets the increment to brighten an image.
   */
  void setIncrement() ;

  /**
   * Returns the current brightness increment.
   *
   * @return the increment
   */
  int getIncrement() ;

  /**
   * Lets the user select a file to save to.
   *
   * @return the file to be saved
   */
  File getSaveFile() ;

  /**
   * Lets the user select a file to load.
   *
   * @return the file to be loaded
   */
  File GetLoadFile() ;

  /**
   * Changes the image shown by the GUI.
   *
   * @param image the image to be displayed
   */
  void changeImage(ImageIcon image) ;

  /**
   * Sets the action listeners of the GUI.
   *
   * @param listener the listener to be set
   */
  void setActionListeners(ActionListener listener) ;

  /**
   * Resets the GUI to its default state.
   */
  void reset() ;

  /**
   * Sets the height for a downscaled image.
   *
   * @param imageHeight the height of the downscaled image
   */
  void setDownScaleHeight(int imageHeight) ;

  /**
   * Sets the width for a downscaled image.
   *
   * @param imageWidth the width of the downscaled image
   */
  void setDownScaleWidth(int imageWidth);

  /**
   * Gets the new height for an image to be downscaled.
   *
   * @return the new height for an image to be downscaled
   */
  int getDownScaleHeight() ;

  /**
   * Gets the new width for an image to be downscaled.
   *
   * @return the new width for an image to be downscaled
   */
  int getDownScaleWidth() ;

  /**
   * Displays the welcome message.
   */
  void displayWelcomeMessage() ;
}



