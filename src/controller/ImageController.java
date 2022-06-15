package controller;

import java.io.IOException;
import java.util.NoSuchElementException;

import model.ImageEditor;

/**
 * Interface for a controller that can take user input to edit an Image.
 */
public interface ImageController {
  /**
   * Method that takes in the user arguments and preforms any action.
   *
   * @throws IOException if the program cannot read the input or write the output
   */
  void start() throws IOException;

  /**
   * Loads an image from the inputted path and adds it to the map of images.
   *
   * @param pathname is the path to the image to be loaded.
   * @param filename is the name of the image to be loaded.
   * @throws IOException            if the image cannot be loaded.
   * @throws NoSuchElementException if the path does not exist
   */
  void loadImage(String pathname, String filename) throws IOException;

  /**
   * Saves the given filename to the given pathname on the local disk.
   *
   * @param pathname the pathname of the file to save.
   * @param filename is the name of the file to save.
   * @throws IOException if the file cannot be saved.
   */
  void saveImage(String pathname, String filename) throws IOException;
}
