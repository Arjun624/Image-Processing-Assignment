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
    model.flipHorizontally(filepath, filename);
    view.renderMessage("Image " + filepath + " flipped horizontally");
  }
}