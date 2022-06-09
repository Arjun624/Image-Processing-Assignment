package controller.Actions;

import java.io.IOException;

import controller.ImageCommands;
import model.ImageModel;
import view.ImageView;

/**
 * Represents an object that can load an image into the program.
 */
public class LoadImage implements ImageCommands {

  String pathName;
  String fileName;

  /**
   * Constructs a {@code LoadImage} that loads the pathname as the filename to the program
   * @param pathName the path to save the file
   * @param fileName the name of the file to be saved
   */
  public LoadImage(String pathName, String fileName) {
    this.pathName = pathName;
    this.fileName = fileName;
  }

  /**
   * load an image into the model.
   * @param model the model to be edited
   * @param view the view
   * @throws IOException if the program cannot read the input or write the output
   */
  @Override
  public void execute(ImageModel model, ImageView view) throws IOException {
    model.loadImage(pathName, fileName);
  }
}
