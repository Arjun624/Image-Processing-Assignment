package controller.commands;

import java.io.IOException;
import java.util.NoSuchElementException;

import controller.ImageCommands;
import controller.ImageController;
import model.ImageEditor;
import view.ImageView;

/**
 * Represents an object that can load an image into the program.
 */
public class LoadImage implements ImageCommands {

  private final String pathName;
  private final String fileName;
  private final ImageController controller;

  /**
   * Constructs a {@code LoadImage} that loads the pathname as the filename to the program.
   *
   * @param pathName the path to save the file
   * @param fileName the name of the file to be saved
   */
  public LoadImage(String pathName, String fileName, ImageController controller) {
    this.pathName = pathName;
    this.fileName = fileName;
    this.controller = controller;
  }

  /**
   * load an image into the model.
   *
   * @param model the model to be edited
   * @param view  the view
   * @throws IOException if the program cannot read the input or write the output
   */
  @Override
  public void execute(ImageEditor model, ImageView view) throws IOException {
    try {
      controller.loadImage(pathName, fileName);
    } catch (NoSuchElementException npe) {
      view.renderMessage(pathName + " does not exist!");
    }
  }
}
