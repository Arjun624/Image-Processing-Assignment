package controller;

import java.io.IOException;
import java.io.InputStreamReader;

import model.ImageModel;
import view.ImageDisplay;
import view.ImageView;

public class ImageProgram {
  public static void main(String[] args) throws IOException {
    ImageControllerImpl controller;
    ImageView view = new ImageDisplay();
    ImageModel model = new ImageModel();
    InputStreamReader in = new InputStreamReader(System.in);
    while (!model.quit) {
      controller = new ImageControllerImpl(view, in);
      controller.go(model);
    }


  }

}
