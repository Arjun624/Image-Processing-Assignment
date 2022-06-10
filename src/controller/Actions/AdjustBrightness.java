package controller.Actions;

import java.io.IOException;

import controller.ImageCommands;
import model.ImageEditor;
import model.ImageModel;
import view.ImageView;

/**
 * Represents an object that can adjust the brightness of an image.
 */
public class AdjustBrightness implements ImageCommands {

  int increment;
  String fileName;
  String newFileName;

  /**
   * Constructs an {@code AdjustBrightness} that can brighten the filename by the increment.
   * @param increment the increment value of brightness
   * @param fileName the file to be brightened
   * @param newFileName the name of the new brightened image
   */
  public AdjustBrightness(int increment, String fileName, String newFileName) {
    this.increment = increment;
    this.fileName = fileName;
    this.newFileName = newFileName;
  }

  /**
   * Brightens the image by the increment.
   * @param model the model to be edited
   * @param view the view
   * @throws IOException if the program cannot read the input or write the output
   */
  @Override
  public void execute(ImageEditor model, ImageView view) throws IOException {

    try {
      model.adjustBrightness(increment, fileName, newFileName);
    } catch (NullPointerException npe) {
      view.renderMessage(fileName + " not loaded!");
    }
  }
}
