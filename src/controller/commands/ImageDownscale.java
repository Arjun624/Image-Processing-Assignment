package controller.commands;

import java.io.IOException;

import controller.ImageCommands;
import model.ImageEditor;
import view.ImageView;

/**
 * Represents an object that can downscale an image.
 */
public class ImageDownscale implements ImageCommands {
  private final String filename;
  private final String newFilename;
  private final int width;
  private final int height;

  /**
   * Constructs an {@code ImageDownscale} that downscales the filename and sets it as the new.
   *
   * @param width       the width of the new image
   * @param height      the height of the new image
   * @param filename    the file to be edited
   * @param newFilename the new file name
   */
  public ImageDownscale(int width, int height, String filename, String newFilename) {
    this.width = width;
    this.height = height;
    this.filename = filename;
    this.newFilename = newFilename;
  }

  /**
   * downscales an image.
   *
   * @param model the model to be edited
   * @param view  the view
   * @throws IOException if the program cannot read the input or write the output
   */
  @Override
  public void execute(ImageEditor model, ImageView view) throws IOException {
    try {
      model.imageDownscale(height, width, filename, newFilename);
    } catch (IllegalArgumentException e) {
      view.renderMessage(e.getMessage() + " " + width + " " + height);
    }
  }
}
