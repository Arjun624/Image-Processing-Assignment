package controller;

import java.io.IOException;

import model.ImageEditor;

/**
 * Interface for a controller that can take user input to edit an Image.
 */
public interface ImageController {
  /**
   * Method that takes in the user arguments and preforms any action.
   *
   * @param model the image model to be edited
   * @throws IOException if the program cannot read the input or write the output
   */
  void start(ImageEditor model) throws IOException;
}
