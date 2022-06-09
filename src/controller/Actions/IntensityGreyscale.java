package controller.Actions;

import java.io.IOException;

import controller.ImageCommands;
import model.ImageModel;
import view.ImageView;

public class IntensityGreyscale implements ImageCommands {

  String fileName;
  String newFileName;

  public IntensityGreyscale(String fileName, String newFileName) {
    this.fileName = fileName;
    this.newFileName = newFileName;
  }

  @Override
  public void execute(ImageModel model, ImageView view) throws IOException {


    try {
      model.intensityGreyscale(fileName, newFileName);
    } catch (NullPointerException npe) {
      view.renderMessage("No image loaded!");
    }
  }
}
