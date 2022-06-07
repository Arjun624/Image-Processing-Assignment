package controller;

public class ImageProgram {
  public static void main(String[] args) {
    String instruction = args[0];
    String fileName;
    int increment;
    ImageController c;
    try {
      increment = Integer.parseInt(args[1]);
      fileName = args[2];
    } catch (NumberFormatException e){
      increment = 0;
      fileName = args[1];
    }
    c = new ImageControllerImpl(instruction, fileName, increment);
    c.editImage();
  }

}
