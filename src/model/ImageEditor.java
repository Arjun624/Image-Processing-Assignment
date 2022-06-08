package model;

/**
 * Represents all the actions that can be performed on an image.
 */
public interface ImageEditor {

   /**
    * Flips an image vertically.
    * @param filename the file reference
    */
   void flipVertically(String filename);

   /**
    * Flips an image horizontally.
    * @param filename the file reference
    */
   void flipHorizontally(String filename);

   /**
    * Preforms a red grayscale on an image.
    * @param filename the file reference
    */
   void redGreyscale(String filename);

   /**
    * Preforms a green grayscale on an image.
    * @param filename the file reference
    */
   void greenGreyscale(String filename);

   /**
    * Preforms a blue grayscale on an image.
    * @param filename the file reference
    */
   void blueGreyscale(String filename);

   /**
    * Finds the luma of each pixel using its RGB value.
    * @param filename the file reference
    */
   void lumaGreyscale(String filename);

   /**
    * Preforms a grayscale on an image.
    * @param filename the file reference
    */
   void intensityGreyscale(String filename);

   /**
    * Preforms a max value grayscale on an image.
    * @param filename the file reference
    */
   void valueGreyscale(String filename);

   /**
    * Adjusts the brightness of an image.
    * @param filename the file reference
    */
   void adjustBrightness(int increment, String filename);



}
