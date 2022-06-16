package controller.commands;

import java.io.IOException;

import controller.ImageCommands;
import model.ImageEditor;
import view.ImageView;

/**
 * Represents an object that can preform a blue greyscale on an image.
 */
public class BlueGreyscale implements ImageCommands {
  private final String fileName;
  private final String newFileName;

  /**
   * Constructs a {@code BlueGreyscale} that preforms a blue greyscale on the filename.
   *
   * @param fileName    the file to be edited
   * @param newFileName the new file name
   */
  public BlueGreyscale(String fileName, String newFileName) {
    this.fileName = fileName;
    this.newFileName = newFileName;
  }

  /**
   * preforms a blue greyscale on an image.
   *
   * @param model the model to be edited
   * @param view  the view
   * @throws IOException if the program cannot read the input or write the output
   */
  @Override
  public void execute(ImageEditor model, ImageView view) throws IOException {
    try {
      model.greyscale(fileName, newFileName, "blue");
    } catch (IllegalArgumentException e) {
      view.renderMessage(fileName + " not loaded!");
    }
  }
}
