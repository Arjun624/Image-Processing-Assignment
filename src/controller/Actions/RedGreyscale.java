package controller.Actions;

import java.io.IOException;

import controller.ImageCommands;
import model.ImageModel;
import view.ImageView;

public class RedGreyscale implements ImageCommands {
  String fileName;
  String newFileName;

  public RedGreyscale(String fileName, String newFileName) {
    this.fileName = fileName;
    this.newFileName = newFileName;
  }


  @Override
  public void execute(ImageModel model, ImageView view) throws IOException {
    model.redGreyscale(fileName, newFileName);
    view.renderMessage("Imaged " + fileName + " changed to red greyscale");
  }
}
