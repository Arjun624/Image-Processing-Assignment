package controller;

import java.io.IOException;

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
  public void execute(ImageModel model) throws IOException {
    model.blueGreyscale(fileName, newFileName);
  }
}
