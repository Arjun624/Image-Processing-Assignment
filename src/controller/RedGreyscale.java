package controller;

import java.io.FileNotFoundException;

import model.ImageModel;

public class RedGreyscale implements ImageCommands {
  String fileName;
  String newFileName;

  public RedGreyscale(String fileName, String newFileName) {
    this.fileName = fileName;
    this.newFileName = newFileName;
  }


  @Override
  public void execute(ImageModel model) {
    model.redGreyscale(fileName, newFileName);
  }
}
