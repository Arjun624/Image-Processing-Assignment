package controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;

import javax.imageio.ImageIO;

import controller.commands.AdjustBrightness;
import controller.commands.BlueGreyscale;
import controller.commands.Blur;
import controller.commands.GreenGreyscale;
import controller.commands.Greyscale;
import controller.commands.HorizontalFlip;
import controller.commands.ImageDownscale;
import controller.commands.IntensityGreyscale;
import controller.commands.LoadImage;
import controller.commands.LumaGreyscale;
import controller.commands.Quit;
import controller.commands.RedGreyscale;
import controller.commands.SaveImage;
import controller.commands.Sepia;
import controller.commands.Sharpen;
import controller.commands.ValueGreyscale;
import controller.commands.VerticalFlip;
import model.ImageEditor;
import view.ImageView;

/**
 * Implementation of an image controller for text based commands.
 */
public class ImageControllerText extends ALoadSave implements ImageController {

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
  public ImageControllerText(ImageView view, Readable rd, ImageEditor model)
          throws IllegalArgumentException {
    if (view == null || rd == null || model == null) {
      throw new IllegalArgumentException("View, readable, or model cannot be null");
    }

    this.view = view;
    this.rd = rd;
    this.model = model;

    commands = new HashMap<>();
    commands.put("adjust-brightness", s -> new AdjustBrightness(s.nextInt(), s.next(), s.next()));
    commands.put("load", s -> new LoadImage(s.next(), s.next(), this));
    commands.put("save", s -> new SaveImage(s.next(), s.next(), this));
    commands.put("vertical-flip", s -> new VerticalFlip(s.next(), s.next()));
    commands.put("horizontal-flip", s -> new HorizontalFlip(s.next(), s.next()));
    commands.put("greyscale-red", s -> new RedGreyscale(s.next(), s.next()));
    commands.put("greyscale-green", s -> new GreenGreyscale(s.next(), s.next()));
    commands.put("greyscale-blue", s -> new BlueGreyscale(s.next(), s.next()));
    commands.put("luma", s -> new LumaGreyscale(s.next(), s.next()));
    commands.put("intensity", s -> new IntensityGreyscale(s.next(), s.next()));
    commands.put("value", s -> new ValueGreyscale(s.next(), s.next()));
    commands.put("sharpen", s -> new Sharpen(s.next(), s.next()));
    commands.put("blur", s -> new Blur(s.next(), s.next()));
    commands.put("sepia", s -> new Sepia(s.next(), s.next()));
    commands.put("greyscale", s -> new Greyscale(s.next(), s.next()));
    commands.put("downscale", s -> new ImageDownscale(s.nextInt(), s.nextInt(), s.next(), s.next()));
    commands.put("q", s -> new Quit());

  }

  @Override
  public void start() throws IllegalArgumentException, IOException {
    Scanner s = new Scanner(rd);

    while (!model.getStatus()) {
      view.renderMessage("Enter a command: ");
      Function<Scanner, ImageCommands> givenCommand = commands.getOrDefault(s.next(),
              null);
      if (givenCommand == null) {
        view.renderMessage("Invalid command!");
      } else {
        try {
          givenCommand.apply(s).execute(model, view);
        } catch (InputMismatchException e) {
          view.renderMessage("Invalid input!");
        }
      }
    }

  }

  @Override
  public void loadImage(String pathname, String filename) throws IOException,
          NoSuchElementException {
    if (pathname.length() >= 4 && pathname.substring(pathname.length()
            - 4).equalsIgnoreCase(".ppm")) {
      loadPPM(pathname, filename);
    } else {
      loadOther(pathname, filename);
    }

  }

  private void loadPPM(String pathname, String filename) throws IOException,
          NoSuchElementException {
    model.add(filename, this.getPPMPixelArray(new File(pathname), view));
    view.renderMessage("Image: " + pathname + "\nloaded as: " + filename);

  }

  private void loadOther(String pathname, String filename) throws IOException,
          NoSuchElementException {
    BufferedImage b;
    try {
      b = ImageIO.read(new File(pathname));
    } catch (IOException e) {
      throw new NoSuchElementException("File " + pathname + " not found!");
    }
    model.add(filename, this.getOtherPixelArray(b));
    view.renderMessage("Image: " + pathname + "\nloaded as: " + filename);
  }

  @Override
  public void saveImage(String pathname, String filename) throws IOException,
          NoSuchElementException {
    if (pathname.length() >= 4
            && pathname.substring(pathname.length() - 4)
            .equalsIgnoreCase(".ppm")) {
      savePPM(pathname, filename);
    } else {
      saveOther(pathname, filename);
    }

  }

  private void savePPM(String pathname, String filename) throws IOException {

    try {
      if (!model.getMap().containsKey(filename)) {
        view.renderMessage("Image " + filename + " does not exist or has not been loaded!");
        return;
      }
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("Invalid image name: " + filename);
    }
    try {
      File newFile = new File(pathname);
      view.renderMessage("Image: " + filename + "\nsaved as: " + pathname);
      if (newFile.createNewFile()) {
        view.renderMessage("File created: " + newFile.getName());
      } else {
        view.renderMessage("File already exists.");
      }

    } catch (IOException e) {
      view.renderMessage("An error occurred.");
      e.printStackTrace();
    }

    try {
      this.savePPM(model, filename, pathname);
      view.renderMessage("Successfully wrote to the file.");
    } catch (IOException e) {
      view.renderMessage("An error occurred.");
      e.printStackTrace();
    }

  }

  private void saveOther(String pathname, String filename) throws IOException {

    if (!model.getMap().containsKey(filename)) {
      view.renderMessage("Image " + filename + " does not exist or has not been loaded!");
      return;
    }

    int length = model.getMap().get(filename).length;
    int width = model.getMap().get(filename)[0].length;
    BufferedImage bufferedImage = new BufferedImage(width,
            length, BufferedImage.TYPE_INT_RGB);
    for (int row = 0; row < length; row++) {
      for (int col = 0; col < width; col++) {
        Color c = new Color(model.getMap().get(filename)[row][col].getRed(),
                model.getMap().get(filename)[row][col].getGreen(),
                model.getMap().get(filename)[row][col].getBlue(),
                model.getMap().get(filename)[row][col].getAlpha());
        bufferedImage.setRGB(col, row, c.getRGB());
      }
    }


    saveOther(bufferedImage, filename, pathname, view);


  }
}
