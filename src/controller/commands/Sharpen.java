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
    double[][] kernal = new double[][]{
            { 0.0625, 0.125, 0.0625 },
            { 0.125, 0.25, 0.125 },
            { 0.0625, 0.125, 0.0625 },
    };

    double[][] kernal2 = new double[][]{
            {-0.125, -0.125, -0.125, -0.125, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, 0.25, 1, 0.25, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, -0.125, -0.125, -0.125, -0.125},
    };


      model.blurImage(fileName, newFileName, kernal);

  }
}
