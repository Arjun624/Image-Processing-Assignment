package model;

import java.io.IOException;
import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * Represents all the actions that can be performed on an image.
 */
public interface ImageEditor {

  /**
   * Flips an image vertically.
   *
   * @param filename    the file reference
   * @param newFilename what the new file will be stored as
   * @throws IOException          if the program cannot read the input or write the output
   * @throws IllegalArgumentException if inputted file does not exist
   */
  void flipVertically(String filename, String newFilename) throws IOException,
          IllegalArgumentException;

  /**
   * Flips an image horizontally.
   *
   * @param filename    the file reference
   * @param newFilename what the new file will be stored as
   * @throws IOException          if the program cannot read the input or write the output
   * @throws IllegalArgumentException if inputted file does not exist
   */
  void flipHorizontally(String filename, String newFilename) throws IOException,
          IllegalArgumentException;

  /**
   * Preforms a red grayscale on an image.
   *
   * @param filename    the file reference
   * @param newFilename what the new file will be stored as
   * @throws IOException          if the program cannot read the input or write the output
   * @throws IllegalArgumentException if inputted file does not exist
   */
  void redGreyscale(String filename, String newFilename) throws IOException,
          IllegalArgumentException;

  /**
   * Preforms a green grayscale on an image.
   *
   * @param filename    the file reference
   * @param newFilename what the new file will be stored as
   * @throws IOException          if the program cannot read the input or write the output
   * @throws IllegalArgumentException if inputted file does not exist
   */
  void greenGreyscale(String filename, String newFilename) throws IOException,
          IllegalArgumentException;

  /**
   * Preforms a blue grayscale on an image.
   *
   * @param filename    the file reference
   * @param newFilename what the new file will be stored as
   * @throws IOException          if the program cannot read the input or write the output
   * @throws IllegalArgumentException if inputted file does not exist
   */
  void blueGreyscale(String filename, String newFilename) throws IOException,
          IllegalArgumentException;

  /**
   * Finds the luma of each pixel using its RGB value.
   *
   * @param filename    the file reference
   * @param newFilename what the new file will be stored as
   * @throws IOException          if the program cannot read the input or write the output
   * @throws IllegalArgumentException if inputted file does not exist
   */
  void lumaGreyscale(String filename, String newFilename) throws IOException,
          IllegalArgumentException;

  /**
   * Preforms a grayscale on an image.
   *
   * @param filename    the file reference
   * @param newFilename what the new file will be stored as
   * @throws IOException          if the program cannot read the input or write the output
   * @throws IllegalArgumentException if inputted file does not exist
   */
  void intensityGreyscale(String filename, String newFilename) throws IOException,
          IllegalArgumentException;

  /**
   * Preforms a max value grayscale on an image.
   *
   * @param filename    the file reference
   * @param newFilename what the new file will be stored as
   * @throws IOException          if the program cannot read the input or write the output
   * @throws IllegalArgumentException if inputted file does not exist
   */
  void valueGreyscale(String filename, String newFilename) throws IOException,
          IllegalArgumentException;

  /**
   * Adjusts the brightness of an image.
   *
   * @param filename    the file reference
   * @param newFilename what the new file will be stored as
   * @throws IOException          if the program cannot read the input or write the output
   * @throws IllegalArgumentException if inputted file does not exist
   */
  void adjustBrightness(int increment, String filename, String newFilename) throws IOException,
          IllegalArgumentException;



  /**
   * Returns whether the program should be quit.
   *
   * @return the quit field of the class
   */
  boolean getStatus();

  /**
   * changes the quit boolean in order to tell the program to quit.
   */
  void quit() throws IOException;

  /**
   * adds an image to the image hashmap.
   * @param imageName the name of the image
   * @param arr the array of pixels
   */
  void add(String imageName, Pixel[][] arr) throws IOException;

  /**
   * returns the hashmap of images.
   * @return the hashmap of images
   */
  HashMap<String,Pixel[][]> getMap();

  /**
   * Finds the total value of the image, which is the max value of each pixel.
   *
   * @param filename is the name of the file
   * @return an integer representing the total value of the image.
   */
    int findTotalValue(String filename);


  void filterImage(String fileName, String newFileName, double[][] kernal)
          throws IOException, IllegalArgumentException;

  void colorTransform(float[][] colors, String filename, String newFilename)
          throws IOException,  IllegalArgumentException;
}
