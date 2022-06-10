package model;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * Represents all the actions that can be performed on an image.
 */
public interface ImageEditor {

   /**
    * Flips an image vertically.
    * @param filename the file reference
    * @param newFilename what the new file will be stored as
    * @throws IOException if the program cannot read the input or write the output
    * @throws NullPointerException if inputted file does not exist
    */
   void flipVertically(String filename, String newFilename) throws IOException,
           NullPointerException;

   /**
    * Flips an image horizontally.
    * @param filename the file reference
    * @param newFilename what the new file will be stored as
    * @throws IOException if the program cannot read the input or write the output
    * @throws NullPointerException if inputted file does not exist
    */
   void flipHorizontally(String filename, String newFilename) throws IOException,
           NullPointerException;

   /**
    * Preforms a red grayscale on an image.
    * @param filename the file reference
    * @param newFilename what the new file will be stored as
    * @throws IOException if the program cannot read the input or write the output
    * @throws NullPointerException if inputted file does not exist
    */
   void redGreyscale(String filename, String newFilename) throws IOException,
           NullPointerException;

   /**
    * Preforms a green grayscale on an image.
    * @param filename the file reference
    * @param newFilename what the new file will be stored as
    * @throws IOException if the program cannot read the input or write the output
    * @throws NullPointerException if inputted file does not exist
    */
   void greenGreyscale(String filename, String newFilename) throws IOException,
           NullPointerException;

   /**
    * Preforms a blue grayscale on an image.
    * @param filename the file reference
    * @param newFilename what the new file will be stored as
    * @throws IOException if the program cannot read the input or write the output
    * @throws NullPointerException if inputted file does not exist
    */
   void blueGreyscale(String filename, String newFilename) throws IOException,
           NullPointerException;

   /**
    * Finds the luma of each pixel using its RGB value.
    * @param filename the file reference
    * @param newFilename what the new file will be stored as
    * @throws IOException if the program cannot read the input or write the output
    * @throws NullPointerException if inputted file does not exist
    */
   void lumaGreyscale(String filename, String newFilename) throws IOException,
           NullPointerException;

   /**
    * Preforms a grayscale on an image.
    * @param filename the file reference
    * @param newFilename what the new file will be stored as
    * @throws IOException if the program cannot read the input or write the output
    * @throws NullPointerException if inputted file does not exist
    */
   void intensityGreyscale(String filename, String newFilename) throws IOException,
           NullPointerException;

   /**
    * Preforms a max value grayscale on an image.
    * @param filename the file reference
    * @param newFilename what the new file will be stored as
    * @throws IOException if the program cannot read the input or write the output
    * @throws NullPointerException if inputted file does not exist
    */
   void valueGreyscale(String filename, String newFilename) throws IOException,
           NullPointerException;

   /**
    * Adjusts the brightness of an image.
    * @param filename the file reference
    * @param newFilename what the new file will be stored as
    * @throws IOException if the program cannot read the input or write the output
    * @throws NullPointerException if inputted file does not exist
    */
   void adjustBrightness(int increment, String filename, String newFilename) throws IOException,
           NullPointerException;

   /**
    * Loads an image from the inputted path and adds it to the map of images.
    * @param pathname is the path to the image to be loaded.
    * @param filename is the name of the image to be loaded.
    * @throws IOException if the image cannot be loaded.
    * @throws NoSuchElementException if the path does not exist
    */
   void loadImage(String pathname, String filename) throws IOException, NoSuchElementException;

   /**
    * Saves the given filename to the given pathname on the local disk.
    * @param pathname the pathname of the file to save.
    * @param filename is the name of the file to save.
    * @throws IOException if the file cannot be saved.
    */
   void saveImage(String pathname, String filename) throws IOException;

   /**
    * Returns whether the program should be quit.
    * @return the quit field of the class
    */
   boolean getStatus();

   /**
    * changes the quit boolean in order to tell the program to quit
    */
   void quit() throws IOException;
}
