package controller;

import java.io.FileNotFoundException;

import model.ImageModel;

public class LumaGreyscale implements ImageCommands {
  String fileName;
  String newFileName;

  public LumaGreyscale(String fileName, String newFileName) {
    this.fileName = fileName;
    this.newFileName = newFileName;
  }

  @Override
  public void execute(ImageModel model) {
    model.lumaGreyscale(fileName, newFileName);
  }
}
