package controller;

import java.io.IOException;

import model.ImageModel;
import view.ImageView;

public class VerticalFlip implements ImageCommands{
  String filepath;
  String filename;

  public VerticalFlip(String filepath, String filename){
    this.filepath = filepath;
    this.filename = filename;
  }

  @Override
  public void execute(ImageModel model, ImageView view) throws IOException {
    model.flipVertically(filepath, filename);
    view.renderMessage("Image: " + filepath + " flipped vertically");
  }
}
