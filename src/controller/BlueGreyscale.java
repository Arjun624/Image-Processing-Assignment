package controller;

import java.io.IOException;

import model.ImageModel;
import view.ImageView;

public class BlueGreyscale implements ImageCommands {
  String fileName;
  String newFileName;
  ImageView view;

  public BlueGreyscale(String fileName, String newFileName, ImageView view) {
    this.fileName = fileName;
    this.newFileName = newFileName;
    this.view = view;
  }

  @Override
  public void execute(ImageModel model) throws IOException {
    model.blueGreyscale(fileName, newFileName);
    view.renderMessage("Created blue greyscale of " + fileName);
  }
}
