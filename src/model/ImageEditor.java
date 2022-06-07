package model;

/**
 * Represents all the actions that can be performed on an image.
 */
public interface ImageEditor {

   Image flipVertically();

   Image flipHorizontally();

   Image showR();

   Image showG();

   Image showB();

   Image adjustBrightness(int increment);

   Image showLuma();

   Image showIntensity();

   Image showValue();


}
