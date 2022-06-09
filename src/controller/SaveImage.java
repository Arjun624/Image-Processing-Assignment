package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.ImageModel;
import view.ImageView;

public class SaveImage implements ImageCommands{

  String pathName;
  String fileName;

  public SaveImage(String pathName, String fileName) {
    this.pathName = pathName;
    this.fileName = fileName;
  }


  @Override
  public void execute(ImageModel model, ImageView view) throws IOException {
    model.saveImage(pathName, fileName);
    view.renderMessage("Image: " + fileName + "\nsaved as: " + pathName);
  }
}
