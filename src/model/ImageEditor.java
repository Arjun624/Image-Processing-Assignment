package model;

//methods to edit an image
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
