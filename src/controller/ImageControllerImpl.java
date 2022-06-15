package controller;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;

import javax.imageio.ImageIO;

import controller.commands.AdjustBrightness;
import controller.commands.BlueGreyscale;
import controller.commands.GreenGreyscale;
import controller.commands.HorizontalFlip;
import controller.commands.IntensityGreyscale;
import controller.commands.LoadImage;
import controller.commands.LumaGreyscale;
import controller.commands.Quit;
import controller.commands.RedGreyscale;
import controller.commands.SaveImage;
import controller.commands.ValueGreyscale;
import controller.commands.VerticalFlip;
import model.ImageEditor;
import model.Pixel;
import view.ImageView;

/**
 * Implementation of an image controller.
 */
public class ImageControllerImpl implements ImageController {

  private final ImageView view;

  private final Readable rd;

  private final Map<String, Function<Scanner, ImageCommands>> commands;

  private final ImageEditor model;

  /**
   * Constructs an {@code ImageControllerImpl} where the user can set the view and readable object.
   *
   * @param view the view
   * @param rd   the readable object to be read by the scanner
   */
  public ImageControllerImpl(ImageView view, Readable rd, ImageEditor model) throws IllegalArgumentException {
    if (view == null || rd == null || model == null) {
      throw new IllegalArgumentException("View, readable, or model cannot be null");
    }

    this.view = view;
    this.rd = rd;
    this.model = model;

    commands = new HashMap<>();
    commands.put("adjust-brightness", s -> new AdjustBrightness(s.nextInt(), s.next(), s.next()));
    commands.put("load", s -> new LoadImage(s.next(), s.next()));
    commands.put("save", s -> new SaveImage(s.next(), s.next()));
    commands.put("vertical-flip", s -> new VerticalFlip(s.next(), s.next()));
    commands.put("horizontal-flip", s -> new HorizontalFlip(s.next(), s.next()));
    commands.put("greyscale-red", s -> new RedGreyscale(s.next(), s.next()));
    commands.put("greyscale-green", s -> new GreenGreyscale(s.next(), s.next()));
    commands.put("greyscale-blue", s -> new BlueGreyscale(s.next(), s.next()));
    commands.put("luma", s -> new LumaGreyscale(s.next(), s.next()));
    commands.put("intensity", s -> new IntensityGreyscale(s.next(), s.next()));
    commands.put("value", s -> new ValueGreyscale(s.next(), s.next()));
    commands.put("q", s -> new Quit());

  }

  @Override
  public void start() throws IllegalArgumentException, IOException {
    Scanner s = new Scanner(rd);

    while (!model.getStatus()) {
      view.renderMessage("Enter a command: ");
      Function<Scanner, ImageCommands> givenCommand = commands.getOrDefault(s.next(), null);
      if (givenCommand == null) {
        view.renderMessage("Invalid command!");
      } else {
        givenCommand.apply(s).execute(model, view);
      }
    }

  }

  public void loadImage(String pathname, String filename) throws IOException,
          NoSuchElementException {
    if(pathname.substring(pathname.length() -4).equalsIgnoreCase(".ppm")){
      loadPPM(pathname, filename);
    }
    else {
      loadOther(pathname, filename);
    }

  }

  private void loadPPM(String pathname, String filename) throws IOException,
          NoSuchElementException {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(pathname));
    } catch (FileNotFoundException e) {
      throw new NoSuchElementException("File " + pathname + " not found!");
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
    view.renderMessage("Width of image: " + width);
    int height = sc.nextInt();
    view.renderMessage("Height of image: " + height);
    int maxValue = sc.nextInt();
    view.renderMessage("Maximum value of a color in this file (usually 255): " + maxValue);

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
    model.add(filename, pixels);
    view.renderMessage("Image: " + pathname + "\nloaded as: " + filename);

  }

  private void loadOther(String pathname, String filename) throws IOException,
          NoSuchElementException {
    BufferedImage b = null;
    try {
      b = ImageIO.read(new File(pathname));
    }
    catch (IOException e) {
      throw new NoSuchElementException("File " + pathname + " not found!");
    }
    int width = b.getWidth();
    int height = b.getHeight();
    Pixel[][] arr = new Pixel[width][height];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        Color c = new Color(b.getRGB(i,j));
        arr[i][j] = new Pixel(c.getRed(), c.getGreen(), c.getBlue());
      }
    }
    model.add(filename,arr);
    view.renderMessage("Image: " + pathname + "\nloaded as: " + filename);
  }
}
