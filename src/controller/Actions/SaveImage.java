package controller.Actions;

import java.io.IOException;

import controller.ImageCommands;
import model.ImageModel;
import view.ImageView;

/**
 * Represents an object that can save an image on the local disk
 */
public class SaveImage implements ImageCommands {

  String pathName;
  String fileName;

  /**
   * Constructs a {@code SaveImage} that saves the filename to the pathname on the local disk
   * @param pathName the path to save the file
   * @param fileName the name of the file to be saved
   */
  public SaveImage(String pathName, String fileName) {
    this.pathName = pathName;
    this.fileName = fileName;
  }


  /**
   * saves an image on the local disk.
   * @param model the model to be edited
   * @param view the view
   * @throws IOException if the program cannot read the input or write the output
   */
  @Override
  public void execute(ImageModel model, ImageView view) throws IOException {

    try {
      model.saveImage(pathName, fileName);
    } catch (NullPointerException npe) {
      view.renderMessage(fileName + " not loaded!");
    }
  }
}
