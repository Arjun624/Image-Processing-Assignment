package controller;

import java.io.FileNotFoundException;

import model.ImageModel;

public class SaveImage implements ImageCommands{

  String pathName;
  String fileName;

  public SaveImage(String pathName, String fileName) {
    this.pathName = pathName;
    this.fileName = fileName;
  }


  @Override
  public void execute(ImageModel model)  {
    model.saveImage(pathName, fileName);
  }
}
