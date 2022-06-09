package controller.Actions;

import java.io.IOException;

import controller.ImageCommands;
import model.ImageModel;
import view.ImageView;

public class AdjustBrightness implements ImageCommands {

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

    try {
      model.adjustBrightness(increment, fileName, newFileName);
    } catch (NullPointerException npe) {
      view.renderMessage(fileName + " not loaded!");
    }
  }
}
