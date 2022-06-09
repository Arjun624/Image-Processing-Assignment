package controller.Actions;

import java.io.IOException;

import controller.ImageCommands;
import model.ImageModel;
import view.ImageView;

/**
 * Represents an object that vertically flips an image.
 */
public class VerticalFlip implements ImageCommands {
  String fileName;
  String newFileName;

  /**
   * Constructs an {@code VerticalFlip} that vertically flips the filename and sets it as the new.
   * @param fileName the file to be edited
   * @param newFileName the new file name
   */
  public VerticalFlip(String fileName, String newFileName){
    this.fileName = fileName;
    this.newFileName = newFileName;
  }

  /**
   * Vertically flips an image.
   * @param model the model to be edited
   * @param view the view
   * @throws IOException if the program cannot read the input or write the output
   */
  @Override
  public void execute(ImageModel model, ImageView view) throws IOException {
    try {
      model.flipVertically(fileName, newFileName);
    } catch (NullPointerException npe) {
      view.renderMessage(fileName + " not loaded!");
    }
  }
}
