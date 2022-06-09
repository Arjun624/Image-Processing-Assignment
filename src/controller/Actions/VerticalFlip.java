package controller.Actions;

import java.io.IOException;

import controller.ImageCommands;
import model.ImageModel;
import view.ImageView;

public class VerticalFlip implements ImageCommands {
  String filepath;
  String filename;

  public VerticalFlip(String filepath, String filename){
    this.filepath = filepath;
    this.filename = filename;
  }

  @Override
  public void execute(ImageModel model, ImageView view) throws IOException {
    try {
      model.flipVertically(filepath, filename);
    } catch (NullPointerException npe) {
      view.renderMessage("No image loaded!");
    }
  }
}
