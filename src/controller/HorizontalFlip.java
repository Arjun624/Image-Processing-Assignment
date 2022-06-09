package controller;

import model.ImageModel;

public class HorizontalFlip implements ImageCommands{
  String filepath;
  String filename;

  public HorizontalFlip(String filepath, String filename){
    this.filepath = filepath;
    this.filename = filename;
  }

  @Override
  public void execute(ImageModel model) {
    model.flipHorizontally(filepath, filename);
  }
}