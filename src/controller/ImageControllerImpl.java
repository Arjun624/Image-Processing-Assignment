package controller;

/**
 * Implementation of an image controller
 */
public class ImageControllerImpl implements ImageController{

  String fileName;
  String instruction;
  int increment;

  public ImageControllerImpl(String fileName, String instruction, int increment){
    this.fileName = fileName;
    this.instruction = instruction;
    this.increment = increment;
  }

  @Override
  public void editImage() {

  }

}
