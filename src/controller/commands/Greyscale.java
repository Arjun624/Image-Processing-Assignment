package controller.commands;

import java.io.IOException;

import controller.ImageCommands;
import model.ImageEditor;
import view.ImageView;

/**
 * Represents an object that can preform a greyscale on an image.
 */
public class Greyscale implements ImageCommands {
  private final String fileName;
  private final String newFileName;
  /**
   * Constructs a {@code Greyscale} that preforms a greyscale on the filename.
   *
   * @param fileName    the file to be edited
   * @param newFileName the new file name
   */
  public Greyscale(String fileName, String newFileName) {
    this.fileName = fileName;
    this.newFileName = newFileName;
  }

  /**
   * preforms a greyscale on an image.
   *
   * @param model the model to be edited
   * @param view  the view
   * @throws IOException if the program cannot read the input or write the output
   */
  @Override
  public void execute(ImageEditor model, ImageView view) throws IOException {
    float[][] colors = new float[3][3];
    for (int i = 0; i < 3; i++) {
      colors[i][0] = (float) 0.2126;
      colors[i][1] = (float) 0.7152;
      colors[i][2] = (float) 0.0722;
    }
    try {
      model.colorTransform(colors,fileName, newFileName);
    } catch (IllegalArgumentException e) {
      view.renderMessage(fileName + " not loaded!");
    }
  }
}
