package controller;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.imageio.ImageIO;

import model.ImageEditor;
import model.Pixel;
import view.ImageView;

/**
 * Represents an abstract class for loading and saving an image.
 */
public abstract class ALoadSave {


  /**
   * Reads a ppm file and returns it as a 2D array of Pixels.
   *
   * @param file the file to be read
   * @param view the view
   * @return the pixel array of a ppm image
   * @throws IOException if the program cannot read the input or write the output
   */
  public Pixel[][] getPPMPixelArray(File file, ImageView view) throws IOException {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(file));
    } catch (FileNotFoundException e) {

      view.renderMessage("File " + file.getName() + " not found!");
      return null;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      view.renderMessage("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    //now read the image data
    Pixel[][] pixels = new Pixel[height][width];
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        pixels[row][col] = new Pixel(r, g, b);
      }
    }
    return pixels;

  }

  /**
   * Reads a file that is not a ppm and returns it as a 2D array of Pixels.
   *
   * @param b the buffered image to be converted
   * @return the pixel array of an image
   */
  public Pixel[][] getOtherPixelArray(BufferedImage b) {


    int height = b.getHeight();
    int width = b.getWidth();

    Pixel[][] arr = new Pixel[height][width];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        Color c = new Color(b.getRGB(i, j));
        arr[j][i] = new Pixel(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
      }
    }
    return arr;
  }

  /**
   * Saves a ppm image to the local disk.
   *
   * @param model    the model
   * @param filename the name of the file in the model to be saved
   * @param pathname where the image will be saved
   * @throws IOException if the program cannot read the input or write the output
   */
  public void savePPM(ImageEditor model, String filename, String pathname) throws IOException {
    StringBuilder sb = new StringBuilder();

    FileWriter writer = new FileWriter(pathname);
    sb.append("P3");
    sb.append((System.lineSeparator()));
    sb.append(model.getMap().get(filename)[0].length);
    sb.append(" ");
    sb.append(model.getMap().get(filename).length);
    sb.append((System.lineSeparator()));
    sb.append(model.findTotalValue(filename));
    sb.append(System.lineSeparator());
    for (int row = 0; row < model.getMap().get(filename).length; row++) {
      for (int col = 0; col < model.getMap().get(filename)[0].length; col++) {
        sb.append(model.getMap().get(filename)[row][col].getRed());
        sb.append(" ");
        sb.append((model.getMap().get(filename)[row][col].getGreen()));
        sb.append(" ");
        sb.append((model.getMap().get(filename)[row][col].getBlue()));
        sb.append(" ");
      }
      sb.append((System.lineSeparator()));
    }
    writer.write(sb.toString());
    writer.close();
  }

  /**
   * Saves an image that is not a ppm to the local disk.
   *
   * @param bufferedImage the buffered image that will represent the new image
   * @param filename      the name of the file in the model to be saved
   * @param pathname      where the image will be saved
   * @throws IOException if the program cannot read the input or write the output
   */
  public void saveOther(BufferedImage bufferedImage,
                        String filename, String pathname, ImageView view) throws IOException {
    ArrayList<String> formats = new ArrayList<>(Arrays.asList(ImageIO.getWriterFormatNames()));
    String type2 = pathname.split("\\.")[1];

    if (formats.contains(type2)) {
      File file = new File(pathname);
      ImageIO.write(bufferedImage, type2, file);
      view.renderMessage("Image: " + filename + "\nsaved as: " + pathname);
    } else {
      view.renderMessage("Image type not supported");
    }
  }
}
