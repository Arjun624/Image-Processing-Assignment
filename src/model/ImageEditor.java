package model;

//methods to edit an image
public interface ImageEditor {

   Image flipVertically(Image image);

   Image flipHorizontally(Image image);

   Image showR(Image image);

   Image showG(Image image);

   Image showB(Image image);

   Image adjustBrightness(Image image);


}
