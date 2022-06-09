package controller;

import model.ImageModel;

public class VerticalFlip implements ImageCommands{
  String filepath;
  String filename;

  public VerticalFlip(String filepath, String filename){
    this.filepath = filepath;
    this.filename = filename;
  }

  @Override
  public void execute(ImageModel model) {
    model.flipVertically(filepath, filename);
  }
}
