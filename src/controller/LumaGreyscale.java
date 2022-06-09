package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.ImageModel;
import view.ImageView;

public class LumaGreyscale implements ImageCommands {
  String fileName;
  String newFileName;

  public LumaGreyscale(String fileName, String newFileName) {
    this.fileName = fileName;
    this.newFileName = newFileName;
  }

  @Override
  public void execute(ImageModel model, ImageView view) throws IOException {
    model.lumaGreyscale(fileName, newFileName);
    view.renderMessage("Image " + fileName + " changed to luma");
  }
}