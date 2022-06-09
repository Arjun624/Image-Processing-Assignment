package controller.Actions;

import java.io.IOException;

import controller.ImageCommands;
import model.ImageModel;
import view.ImageView;

public class BlueGreyscale implements ImageCommands {
  String fileName;
  String newFileName;

  public BlueGreyscale(String fileName, String newFileName) {
    this.fileName = fileName;
    this.newFileName = newFileName;
  }

  @Override
  public void execute(ImageModel model, ImageView view) throws IOException {
    try {
      model.blueGreyscale(fileName, newFileName);
      view.renderMessage("image " + fileName + " changed to blue greyscale");
    } catch (NullPointerException npe) {
      view.renderMessage("No image loaded!");
    }
  }
}
