package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ImageProgram {
  public static void main(String[] args) throws FileNotFoundException {
    ImageControllerImpl controller = new ImageControllerImpl(args);
    controller.go();
  }

}
