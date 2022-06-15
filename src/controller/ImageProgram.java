package controller;

import java.io.IOException;
import java.io.InputStreamReader;

import model.ImageModel;
import view.ImageDisplay;
import view.ImageView;

/**
 * The class that runs the program in its entirety.
 */
public class ImageProgram {
  /**
   * The main method.
   *
   * @param args the arguments
   * @throws IOException if the program cannot read the input or write the output
   */
  public static void main(String[] args) throws IOException {
    ImageControllerImpl controller;
    ImageView view = new ImageDisplay(System.out);
    ImageModel model = new ImageModel();
    InputStreamReader in = new InputStreamReader(System.in);
    view.displayWelcomeMessage();
    controller = new ImageControllerImpl(view, in, model);
    controller.start();
  }
}
