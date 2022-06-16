package controller.commands;

import java.io.IOException;

import controller.ImageCommands;
import controller.model.ImageEditor;
import view.ImageView;

/**
 * Represents an object that Horizontally flips an image.
 */
public class HorizontalFlip implements ImageCommands {
  private final String fileName;
  private final String newFileName;

  /**
   * Constructs an {@code HorizontalFlip} that Horizontally flips the filename and sets it.
   *
   * @param fileName    the file to be edited
   * @param newFileName the new file name
   */
  public HorizontalFlip(String fileName, String newFileName) {
    this.fileName = fileName;
    this.newFileName = newFileName;
  }

  /**
   * Horizontally flips an image.
   *
   * @param model the model to be edited
   * @param view  the view
   * @throws IOException if the program cannot read the input or write the output
   */
  @Override
  public void execute(ImageEditor model, ImageView view) throws IOException {

    try {
      model.flipHorizontally(fileName, newFileName);
    } catch (IllegalArgumentException e) {
      view.renderMessage(fileName + " not loaded!");
    }
  }
}