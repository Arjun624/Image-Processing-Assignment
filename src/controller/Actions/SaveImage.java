package controller.Actions;

import java.io.IOException;

import controller.ImageCommands;
import model.ImageModel;
import view.ImageView;

public class SaveImage implements ImageCommands {

  String pathName;
  String fileName;

  public SaveImage(String pathName, String fileName) {
    this.pathName = pathName;
    this.fileName = fileName;
  }


  @Override
  public void execute(ImageModel model, ImageView view) throws IOException {

    try {
      model.saveImage(pathName, fileName);
    } catch (NullPointerException npe) {
      view.renderMessage(fileName + " not loaded!");
    }
  }
}
