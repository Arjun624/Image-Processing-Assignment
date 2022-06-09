package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.ImageModel;
import view.ImageView;

public class LoadImage implements ImageCommands{

  String pathName;
  String fileName;

  public LoadImage(String pathName, String fileName) {
    this.pathName = pathName;
    this.fileName = fileName;
  }

  @Override
  public void execute(ImageModel model, ImageView view) throws IOException {
    model.loadImage(pathName, fileName);
    view.renderMessage("Image: " + pathName + "\nloaded as: " + fileName);
  }
}