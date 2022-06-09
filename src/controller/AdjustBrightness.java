package controller;

import java.io.IOException;

import model.ImageModel;
import view.ImageView;

public class AdjustBrightness implements ImageCommands{

  int increment;
  String fileName;
  String newFileName;

  public AdjustBrightness(int increment, String fileName, String newFileName) {
    this.increment = increment;
    this.fileName = fileName;
    this.newFileName = newFileName;
  }

  @Override
  public void execute(ImageModel model, ImageView view) throws IOException {
    model.adjustBrightness(increment, fileName, newFileName);
    view.renderMessage("Image: " + fileName + " adjusted brightness by a factor of " + increment);
  }
}
