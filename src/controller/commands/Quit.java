package controller.commands;

import java.io.IOException;

import controller.ImageCommands;
import controller.model.ImageEditor;
import view.ImageView;

/**
 * Represents an object that can quit the program.
 */
public class Quit implements ImageCommands {

  /**
   * Quits the program using the model.
   *
   * @param model the model to be edited
   * @param view  the view
   * @throws IOException if the program cannot read the input or write the output
   */
  @Override
  public void execute(ImageEditor model, ImageView view) throws IOException {
    view.renderMessage("Thanks!");
    model.quit();
  }
}
