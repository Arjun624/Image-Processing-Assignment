package controller.commands;

import java.io.IOException;

import controller.ImageCommands;
import model.ImageEditor;
import view.ImageView;

public class Blur implements ImageCommands {

  private final String fileName;
  private final String newFileName;

  public Blur(String fileName, String newFileName) {
    this.fileName = fileName;
    this.newFileName = newFileName;
  }
  /**
   * Executes the specific action of the class.
   *
   * @param model the model to be edited
   * @param view  the view
   * @throws IOException if the program cannot read the input or write the output
   */
  @Override
  public void execute(ImageEditor model, ImageView view) throws IOException {
    double[][] kernal = new double[][]{
            { 0.0625, 0.125, 0.0625 },
            { 0.125, 0.25, 0.125 },
            { 0.0625, 0.125, 0.0625 },
    };

    double[][] kernal2 = new double[][]{
            { 0.0625, 0.125, 0.0625 },
            { 0.125, 0.25, 0.125 },
            { 0.25, 1, 0.25 },
            { 0.125, 0.25, 0.125 },
            { 0.0625, 0.125, 0.0625 },
    };

    double [][] kernal3 = new double[][]{
            {0.0625, 0.25, 0, 0.25, 0.0625},
            {0.25, 1, 0, 1, 0.25},
            {0, 0, 0, 0, 0},
            {0.25, 1, 0, 1, 0.25},
            {0.0625, 0.25, 0, 0.25, 0.0625},
    };

    try {
      model.filterImage(fileName, newFileName, kernal);
    } catch (IllegalArgumentException e) {
      view.renderMessage(fileName + " not loaded!");
    }
  }






}
