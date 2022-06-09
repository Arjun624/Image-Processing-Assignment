package controller;

import java.io.IOException;

import model.ImageModel;
import view.ImageView;

public class IntensityGreyscale implements ImageCommands{

  String fileName;
  String newFileName;

  public IntensityGreyscale(String fileName, String newFileName) {
    this.fileName = fileName;
    this.newFileName = newFileName;
  }

  @Override
  public void execute(ImageModel model, ImageView view) throws IOException {
    model.intensityGreyscale(fileName, newFileName);
    view.renderMessage("Image changed to intensity greyscale");
  }
}
