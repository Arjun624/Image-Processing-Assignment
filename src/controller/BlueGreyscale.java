package controller;

import model.ImageModel;

public class BlueGreyscale implements ImageCommands {
  String fileName;
  String newFileName;

  public BlueGreyscale(String fileName, String newFileName) {
    this.fileName = fileName;
    this.newFileName = newFileName;
  }

  @Override
  public void execute(ImageModel model) {
    model.blueGreyscale(fileName, newFileName);
  }
}
