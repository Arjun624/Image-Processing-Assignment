package controller;

import java.io.IOException;

import model.ImageModel;

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
//    ImageControllerImpl controller;
//    ImageView view = new ImageDisplay(System.out);
//    ImageModel model = new ImageModel();
//    if (args.length > 0) {
//      for (int i = 0; i < args.length - 1; i++) {
//        if (args[i].contains("-file")) {
//          try {
//            InputStreamReader in = new FileReader(args[i + 1]);
//            controller = new ImageControllerImpl(view, in, model);
//            controller.start();
//            return;
//          } catch (IOException e) {
//            view.renderMessage("Error: Cannot read file, please check arguments.");
//            return;
//          }
//
//        }
//      }
//    }
//    InputStreamReader in = new InputStreamReader(System.in);
//    view.displayWelcomeMessage();
//    controller = new ImageControllerImpl(view, in, model);
//    controller.start();
//

    ImageModel model = new ImageModel();


    ImageControllerGUI controller = new ImageControllerGUI(model);

  }
}
