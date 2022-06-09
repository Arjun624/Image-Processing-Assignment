package controller;

import java.io.FileNotFoundException;

import model.ImageModel;

public class LoadImage implements ImageCommands{

  String pathName;
  String fileName;

  public LoadImage(String pathName, String fileName) {
    this.pathName = pathName;
    this.fileName = fileName;
  }

  @Override
  public void execute(ImageModel model) throws FileNotFoundException {
    model.loadImage(pathName, fileName);
  }
}
