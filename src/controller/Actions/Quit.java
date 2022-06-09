package controller.Actions;

import java.io.IOException;

import controller.ImageCommands;
import model.ImageModel;
import view.ImageView;

public class Quit implements ImageCommands {
  @Override
  public void execute(ImageModel model, ImageView view) throws IOException {
    view.renderMessage("Thanks!");
    model.quit = true;
  }
}
