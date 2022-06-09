package controller;

import model.ImageModel;

public class GreenGreyscale implements ImageCommands {

  String fileName;
  String newFileName;

  public GreenGreyscale(String fileName, String newFileName) {
    this.fileName = fileName;
    this.newFileName = newFileName;
  }


  @Override
  public void execute(ImageModel model) {
    model.greenGreyscale(fileName, newFileName);
  }
}
