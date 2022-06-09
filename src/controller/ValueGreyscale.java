package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.ImageModel;
import view.ImageView;

public class ValueGreyscale implements ImageCommands {

  String fileName;
  String newFileName;

  public ValueGreyscale(String fileName, String newFileName) {
    this.fileName = fileName;
    this.newFileName = newFileName;
  }


  @Override
  public void execute(ImageModel model, ImageView view) throws IOException {
    model.valueGreyscale(fileName, newFileName);
    view.renderMessage("Image changed to greyscale");
  }
}
