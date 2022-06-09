package controller;

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
  public void execute(ImageModel model, ImageView view) {
    model.greenGreyscale(fileName, newFileName);
  }
}
