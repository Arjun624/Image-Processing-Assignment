package model;

/**
 * Represents all the actions that can be performed on an image.
 */
public interface ImageEditor {

   void flipVertically();

   void flipHorizontally();

   void redGreyscale();

   void greenGreyscale();

   void blueGreyscale();

   void lumaGreyscale();

   void intensityGreyscale();

   void valueGreyscale();

   void adjustBrightness(int increment);





}
