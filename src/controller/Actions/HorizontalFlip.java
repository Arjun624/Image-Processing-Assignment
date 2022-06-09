package controller.Actions;

import java.io.IOException;

import controller.ImageCommands;
import model.ImageModel;
import view.ImageView;

public class HorizontalFlip implements ImageCommands {
  String filepath;
  String filename;

  public HorizontalFlip(String filepath, String filename){
    this.filepath = filepath;
    this.filename = filename;
  }

  @Override
  public void execute(ImageModel model, ImageView view) throws IOException {

    try {
      model.flipHorizontally(filepath, filename);
    } catch (NullPointerException npe) {
      view.renderMessage("No image loaded!");
    }
  }
}