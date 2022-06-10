package controller.Actions;

import java.io.IOException;

import controller.ImageCommands;
import model.ImageEditor;
import model.ImageModel;
import view.ImageView;

/**
 * Represents an object that can preform a value greyscale on an image.
 */
public class ValueGreyscale implements ImageCommands {

  String fileName;
  String newFileName;

  /**
   * Constructs a {@code ValueGreyscale} that preforms a value greyscale on the filename.
   * @param fileName the file to be edited
   * @param newFileName the new file name
   */
  public ValueGreyscale(String fileName, String newFileName) {
    this.fileName = fileName;
    this.newFileName = newFileName;
  }


  /**
   * preforms a value greyscale on an image.
   * @param model the model to be edited
   * @param view the view
   * @throws IOException if the program cannot read the input or write the output
   */
  @Override
  public void execute(ImageEditor model, ImageView view) throws IOException {

    try {
      model.valueGreyscale(fileName, newFileName);
    } catch (NullPointerException npe) {
      view.renderMessage(fileName + " not loaded!");
    }
  }
}
