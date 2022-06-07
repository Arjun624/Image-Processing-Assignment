package model;

//methods to edit an image
public interface ImageEditor {

   Image flipVertically();

   Image flipHorizontally();

   Image showR();

   Image showG();

   Image showB();

   Image adjustBrightness(Image image);

   Image showLuma();

   Image showIntensity();

   Image showValue();


}
