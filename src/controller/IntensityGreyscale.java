package controller;

import model.ImageModel;

public class IntensityGreyscale implements ImageCommands{

  String fileName;
  String newFileName;

  public IntensityGreyscale(String fileName, String newFileName) {
    this.fileName = fileName;
    this.newFileName = newFileName;
  }

  @Override
  public void execute(ImageModel model) {
    model.intensityGreyscale(fileName, newFileName);
  }
}
