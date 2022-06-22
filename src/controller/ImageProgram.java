package controller;

import java.io.FileReader;
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
    ImageModel model = new ImageModel();
    ImageControllerImpl controller;
    ImageView view = new ImageDisplay(System.out);
    if (args.length > 0) {
        if (args[0].equalsIgnoreCase("-file")) {
          try {
            InputStreamReader in = new FileReader(args[1]);
            controller = new ImageControllerImpl(view, in, model);
            controller.start();
            return;
          } catch (IOException e) {
            view.renderMessage("Error: Cannot read file, please check arguments.");
            return;
          }
          catch (NullPointerException e) {
            view.renderMessage("Error: No file inputted!");
            return;
          }
        } else if (args[0].equalsIgnoreCase("-text")) {
          InputStreamReader in = new InputStreamReader(System.in);
          view.displayWelcomeMessage();
          controller = new ImageControllerImpl(view, in, model);
          controller.start();
          return;
        }

    }
    ImageControllerGUI controllerGUI = new ImageControllerGUI(model);

  }
}
