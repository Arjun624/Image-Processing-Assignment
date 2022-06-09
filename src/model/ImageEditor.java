package model;

import java.io.IOException;

/**
 * Represents all the actions that can be performed on an image.
 */
public interface ImageEditor {

   /**
    * Flips an image vertically.
    * @param filename the file reference
    */
   void flipVertically(String filename, String newFilename) throws IOException;

   /**
    * Flips an image horizontally.
    * @param filename the file reference
    */
   void flipHorizontally(String filename, String newFilename) throws IOException;

   /**
    * Preforms a red grayscale on an image.
    * @param filename the file reference
    */
   void redGreyscale(String filename, String newFilename) throws IOException;

   /**
    * Preforms a green grayscale on an image.
    * @param filename the file reference
    */
   void greenGreyscale(String filename, String newFilename) throws IOException;

   /**
    * Preforms a blue grayscale on an image.
    * @param filename the file reference
    */
   void blueGreyscale(String filename, String newFilename) throws IOException;

   /**
    * Finds the luma of each pixel using its RGB value.
    * @param filename the file reference
    */
   void lumaGreyscale(String filename, String newFilename) throws IOException;

   /**
    * Preforms a grayscale on an image.
    * @param filename the file reference
    */
   void intensityGreyscale(String filename, String newFilename) throws IOException;

   /**
    * Preforms a max value grayscale on an image.
    * @param filename the file reference
    */
   void valueGreyscale(String filename, String newFilename) throws IOException;

   /**
    * Adjusts the brightness of an image.
    * @param filename the file reference
    */
   void adjustBrightness(int increment, String filename, String newFilename) throws IOException;

   void loadImage(String pathname, String filename) throws IOException;

   void saveImage(String pathname, String imageName) throws IOException;



}
