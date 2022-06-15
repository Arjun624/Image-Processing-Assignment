package controller.commands;

import java.io.IOException;

import controller.ImageCommands;
import model.ImageEditor;
import view.ImageView;

public class Sharpen implements ImageCommands {

  private final String fileName;
  private final String newFileName;


  public Sharpen(String fileName, String newFileName) {
    this.fileName = fileName;
    this.newFileName = newFileName;
  }



  @Override
  public void execute(ImageEditor model, ImageView view) throws IOException {


      model.sharpenImage(fileName, newFileName);

  }
}
