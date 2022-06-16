package controller.commands;

import java.io.IOException;

import controller.ImageCommands;
import controller.model.ImageEditor;
import view.ImageView;

/**
 * Represents an object that can preform a sepia greyscale on an image.
 */
public class Sepia implements ImageCommands {
  private final String fileName;
  private final String newFileName;
  /**
   * Constructs a {@code Sepia} that preforms a sepia greyscale on the filename.
   *
   * @param fileName    the file to be edited
   * @param newFileName the new file name
   */
  public Sepia(String fileName, String newFileName) {
    this.fileName = fileName;
    this.newFileName = newFileName;
  }

  /**
   * preforms a sepia greyscale on an image.
   *
   * @param model the model to be edited
   * @param view  the view
   * @throws IOException if the program cannot read the input or write the output
   */
  @Override
  public void execute(ImageEditor model, ImageView view) throws IOException {
    float[][] colors = new float[3][3];
    colors[0][0] = (float) 0.393;
    colors[0][1] = (float) 0.769;
    colors[0][2] = (float) 0.189;
    colors[1][0] = (float) 0.349;
    colors[1][1] = (float) 0.686;
    colors[1][2] = (float) 0.168;
    colors[2][0] = (float) 0.272;
    colors[2][1] = (float) 0.534;
    colors[2][2] = (float) 0.131;
    try {
      model.colorTransform(colors,fileName, newFileName);
    } catch (IllegalArgumentException e) {
      view.renderMessage(fileName + " not loaded!");
    }
  }
}
