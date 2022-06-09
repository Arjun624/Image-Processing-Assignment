package controller.Actions;

import java.io.IOException;

import controller.ImageCommands;
import model.ImageModel;
import view.ImageView;

/**
 * Represents an object that can preform a red greyscale on an image.
 */
public class RedGreyscale implements ImageCommands {
  String fileName;
  String newFileName;

  /**
   * Constructs a {@code RedGreyscale} that preforms a red greyscale on the filename.
   * @param fileName the file to be edited
   * @param newFileName the new file name
   */
  public RedGreyscale(String fileName, String newFileName) {
    this.fileName = fileName;
    this.newFileName = newFileName;
  }


  /**
   * preforms a red greyscale on an image.
   * @param model the model to be edited
   * @param view the view
   * @throws IOException if the program cannot read the input or write the output
   */
  @Override
  public void execute(ImageModel model, ImageView view) throws IOException {

    try {
      model.redGreyscale(fileName, newFileName);
    } catch (NullPointerException npe) {
      view.renderMessage(fileName + " not loaded!");
    }
  }
}
