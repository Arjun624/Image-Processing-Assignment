package controller.commands;

import java.io.IOException;

import controller.ImageCommands;
import model.ImageEditor;
import view.ImageView;

/**
 * Represents an object that can preform a red greyscale on an image.
 */
public class RedGreyscale implements ImageCommands {
  private final String fileName;
  private final String newFileName;

  /**
   * Constructs a {@code RedGreyscale} that preforms a red greyscale on the filename.
   *
   * @param fileName    the file to be edited
   * @param newFileName the new file name
   */
  public RedGreyscale(String fileName, String newFileName) {
    this.fileName = fileName;
    this.newFileName = newFileName;
  }


  /**
   * preforms a red greyscale on an image.
   *
   * @param model the model to be edited
   * @param view  the view
   * @throws IOException if the program cannot read the input or write the output
   */
  @Override
  public void execute(ImageEditor model, ImageView view) throws IOException {

    try {
      model.redGreyscale(fileName, newFileName);
    } catch (IllegalArgumentException e) {
      view.renderMessage(fileName + " not loaded!");
    }
  }
}
