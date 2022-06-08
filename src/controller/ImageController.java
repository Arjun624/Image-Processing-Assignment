package controller;

import java.io.FileNotFoundException;

/**
 * Interface for a controller that can take user input to edit an Image.
 */
public interface ImageController {
  /**
   * Method that takes in the user arguments and preforms any action.
   */
   void go() throws FileNotFoundException;
}
