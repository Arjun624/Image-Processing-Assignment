package controller.commands;

import java.io.IOException;

import controller.ImageCommands;
import controller.ImageController;
import controller.model.ImageEditor;
import view.ImageView;

/**
 * Represents an object that can save an image on the local disk.
 */
public class SaveImage implements ImageCommands {

  private final String pathName;
  private final String fileName;

  private final ImageController controller;

  /**
   * Constructs a {@code SaveImage} that saves the filename to the pathname on the local disk.
   *
   * @param pathName the path to save the file
   * @param fileName the name of the file to be saved
   */
  public SaveImage(String pathName, String fileName, ImageController controller) {
    this.pathName = pathName;
    this.fileName = fileName;
    this.controller = controller;
  }


  /**
   * saves an image on the local disk.
   *
   * @param model the model to be edited
   * @param view  the view
   * @throws IOException if the program cannot read the input or write the output
   */
  @Override
  public void execute(ImageEditor model, ImageView view) throws IOException {

    try {
      controller.saveImage(pathName, fileName);
    } catch (IllegalArgumentException e) {
      view.renderMessage(pathName + " does not exist!");
    }
  }
}
