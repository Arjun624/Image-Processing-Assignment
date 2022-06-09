package controller;

import java.io.FileNotFoundException;

import model.ImageModel;

public class ValueGreyscale implements ImageCommands {

  String fileName;
  String newFileName;

  public ValueGreyscale(String fileName, String newFileName) {
    this.fileName = fileName;
    this.newFileName = newFileName;
  }


  @Override
  public void execute(ImageModel model) {
    model.valueGreyscale(fileName, newFileName);
  }
}
