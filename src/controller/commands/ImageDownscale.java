package controller.commands;

import java.io.IOException;

import controller.ImageCommands;
import model.ImageEditor;
import model.ImageModel;
import view.ImageView;

public class ImageDownscale implements ImageCommands {
  String filename;
  String newFilename;
  int width;
  int height;

  public ImageDownscale(int width, int height, String filename, String newFilename){
    this.width = width;
    this.height = height;
    this.filename = filename;
    this.newFilename = newFilename;
  }

  @Override
  public void execute(ImageEditor model, ImageView view) throws IOException {
    try {
      model.imageDownscale(height, width, filename, newFilename);
    } catch (IllegalArgumentException e){
      view.renderMessage(e.getMessage() + " " + width + " " + height);
    }
  }
}
