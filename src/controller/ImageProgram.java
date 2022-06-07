package controller;

public class ImageProgram {
  public static void main(String[] args) {
    String instruction = args[0];
    String fileName = args[1];
    int increment;
    ImageController c;
    try {
      increment = Integer.parseInt(args[2]);
      c = new ImageControllerImpl(instruction, fileName, increment);
    } catch (NumberFormatException e){
      increment = 0;
      c = new ImageControllerImpl(instruction, fileName, increment);
    }
    c.editImage();
  }

}
