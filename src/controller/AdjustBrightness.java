package controller;

import model.ImageModel;

public class AdjustBrightness implements ImageCommands{

  int increment;
  String fileName;
  String newFileName;

  public AdjustBrightness(int increment, String fileName, String newFileName) {
    this.increment = increment;
    this.fileName = fileName;
    this.newFileName = newFileName;
  }

  @Override
  public void execute(ImageModel model) {
    model.adjustBrightness(increment, fileName, newFileName);
  }
}
