package controller.Actions;

import java.io.IOException;

import controller.ImageCommands;
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

    try {
      model.valueGreyscale(fileName, newFileName);
      view.renderMessage("Image " + fileName + " changed to greyscale");
    } catch (NullPointerException npe) {
      view.renderMessage("No image loaded!");
    }
  }
}
