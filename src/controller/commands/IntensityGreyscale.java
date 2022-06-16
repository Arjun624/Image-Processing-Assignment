package controller.commands;

import java.io.IOException;

import controller.ImageCommands;
import model.ImageEditor;
import view.ImageView;

/**
 * Represents an object that can preform an intensity greyscale on an image.
 */
public class IntensityGreyscale implements ImageCommands {

  private final String fileName;
  private final String newFileName;

  /**
   * Constructs an {@code IntensityGreyscale} that preforms an intensity greyscale on the filename.
   *
   * @param fileName    the file to be edited
   * @param newFileName the new file name
   */
  public IntensityGreyscale(String fileName, String newFileName) {
    this.fileName = fileName;
    this.newFileName = newFileName;
  }

  /**
   * preforms an intensity greyscale on an image.
   *
   * @param model the model to be edited
   * @param view  the view
   * @throws IOException if the program cannot read the input or write the output
   */
  @Override
  public void execute(ImageEditor model, ImageView view) throws IOException {


    try {
      model.greyscale(fileName, newFileName, "intensity");
    } catch (IllegalArgumentException e) {
      view.renderMessage(fileName + " not loaded!");
    }
  }
}
