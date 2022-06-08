package model;

/**
 * Represents all the actions that can be performed on an image.
 */
public interface ImageEditor {

   /**
    * Flips an image vertically.
    * @param filename the file reference
    */
   void flipVertically(String filename, String newFilename);

   /**
    * Flips an image horizontally.
    * @param filename the file reference
    */
   void flipHorizontally(String filename, String newFilename);

   /**
    * Preforms a red grayscale on an image.
    * @param filename the file reference
    */
   void redGreyscale(String filename, String newFilename);

   /**
    * Preforms a green grayscale on an image.
    * @param filename the file reference
    */
   void greenGreyscale(String filename, String newFilename);

   /**
    * Preforms a blue grayscale on an image.
    * @param filename the file reference
    */
   void blueGreyscale(String filename, String newFilename);

   /**
    * Finds the luma of each pixel using its RGB value.
    * @param filename the file reference
    */
   void lumaGreyscale(String filename, String newFilename);

   /**
    * Preforms a grayscale on an image.
    * @param filename the file reference
    */
   void intensityGreyscale(String filename, String newFilename);

   /**
    * Preforms a max value grayscale on an image.
    * @param filename the file reference
    */
   void valueGreyscale(String filename, String newFilename);

   /**
    * Adjusts the brightness of an image.
    * @param filename the file reference
    */
   void adjustBrightness(int increment, String filename);

   void load(String pathname, String filename);

   void saveImage(String pathname, String imageName);



}
