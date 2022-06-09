package controller.Actions;

import java.io.IOException;

import controller.ImageCommands;
import model.ImageModel;
import view.ImageView;

public class GreenGreyscale implements ImageCommands {

  String fileName;
  String newFileName;

  public GreenGreyscale(String fileName, String newFileName) {
    this.fileName = fileName;
    this.newFileName = newFileName;
  }


  @Override
  public void execute(ImageModel model, ImageView view) throws IOException {
    model.greenGreyscale(fileName, newFileName);
    view.renderMessage("image " + fileName + " changed to green greyscale");
  }
}
