package model;

import java.io.IOException;
import java.util.HashMap;

import controller.ImageCommands;
import view.ImageDisplay;
import view.ImageView;

/**
 * Represents a way to edit, load, and save PPM images.
 */
public class ImageModel implements ImageEditor {
  private final HashMap<String, Pixel[][]> images;
  private boolean quit;

  ImageView view;

  /**
   * Constructs a {@code ImageModel} that sets the map of files and their pixels to an empty one.
   */
  public ImageModel() {
    this.images = new HashMap<>();
    this.quit = false;
    this.view = new ImageDisplay(System.out);
  }

  /**
   * Constructs a {@code ImageModel} where the user can input a map of files and their pixels.
   *
   * @param images the map of files and their pixels
   */
  public ImageModel(HashMap<String, Pixel[][]> images, ImageView view) {
    if (images == null || view == null) {
      throw new IllegalArgumentException("HashMap or ImageView cannot be null");
    }
    this.images = images;
    this.quit = false;
    this.view = view;
  }

  @Override
  public void flipVertically(String filename, String newFilename) throws IOException,
          IllegalArgumentException {
    if (images.get(filename) == null) {
      throw new IllegalArgumentException("file doesn't exist");
    }
    int yLength = images.get(filename).length - 1;

    Pixel[][] arr = new Pixel[images.get(filename).length][images.get(filename)[0].length];
    for (int row = 0; row < images.get(filename).length; row++) {
      for (int col = 0; col < images.get(filename)[0].length; col++) {
        arr[row][col] = images.get(filename)[yLength - row][col];
      }
    }

    images.put(newFilename, arr);
    view.renderMessage("Image: " + filename + " flipped vertically");
  }

  @Override
  public void flipHorizontally(String filename, String newFilename) throws IOException,
          IllegalArgumentException {
    if (images.get(filename) == null) {
      throw new IllegalArgumentException("file doesn't exist");
    }

    int xLength = images.get(filename)[0].length - 1;

    Pixel[][] arr = new Pixel[images.get(filename).length][images.get(filename)[0].length];
    for (int row = 0; row < images.get(filename).length; row++) {
      for (int col = 0; col < images.get(filename)[0].length; col++) {
        arr[row][col] = images.get(filename)[row][xLength - col];
      }
    }

    images.put(newFilename, arr);
    view.renderMessage("Image " + filename + " flipped horizontally");
  }

  @Override
  public void greyscale(String filename, String newFilename, String type) throws IOException,
          IllegalArgumentException {
    if (images.get(filename) == null) {
      throw new IllegalArgumentException("file doesn't exist");
    }

    Pixel[][] arr = new Pixel[images.get(filename).length][images.get(filename)[0].length];
    for (int row = 0; row < this.images.get(filename).length; row++) {
      for (int col = 0; col < this.images.get(filename)[0].length; col++) {
        arr[row][col] = getType(type, images.get(filename)[row][col]);
      }
    }
    images.put(newFilename, arr);
    view.renderMessage("image " + filename + " changed to " + type + " greyscale");
  }

  // gets the type of greyscale
  private Pixel getType(String type, Pixel pixel) {
    int val = -1;
    if (type.equalsIgnoreCase("red")) {
      val = pixel.getRed();
    } else if (type.equalsIgnoreCase("green")) {
      val = pixel.getGreen();
    } else if (type.equalsIgnoreCase("blue")) {
      val = pixel.getBlue();
    } else if (type.equalsIgnoreCase("luma")) {
      val = pixel.findLuma();
    } else if (type.equalsIgnoreCase("intensity")) {
      val = pixel.findIntensity();
    } else if (type.equalsIgnoreCase("value")) {
      val = pixel.findValue();
    }
    return new Pixel(val, val, val, pixel.getAlpha());
  }

  @Override
  public void adjustBrightness(int increment, String filename, String newFilename)
          throws IOException, IllegalArgumentException {
    if (images.get(filename) == null) {
      throw new IllegalArgumentException("file doesn't exist");
    }

    Pixel[][] arr = new Pixel[images.get(filename).length][images.get(filename)[0].length];
    for (int row = 0; row < images.get(filename).length; row++) {
      for (int col = 0; col < images.get(filename)[0].length; col++) {
        int newRed = this.images.get(filename)[row][col].getRed() + increment;
        int newGreen = this.images.get(filename)[row][col].getGreen() + increment;
        int newBlue = this.images.get(filename)[row][col].getBlue() + increment;

        arr[row][col] = new Pixel(fixRGBRange(newRed), fixRGBRange(newGreen), fixRGBRange(newBlue));
      }
    }
    images.put(newFilename, arr);
    view.renderMessage("Image: " + filename + " adjusted brightness by a factor of " + increment);
  }

  @Override
  public void filterImage(String filename, String newFilename, double[][] kernel)
          throws IOException, IllegalArgumentException {
    if (kernel == null) {
      throw new IllegalArgumentException("kernel is null");
    }
    if (images.get(filename) == null) {
      throw new IllegalArgumentException("file doesn't exist");
    }
    if (kernel.length % 2 == 0 || kernel[0].length % 2 == 0) {
      throw new IllegalArgumentException("Kernel must have an odd number of rows and columns");
    }
    int length = kernel.length;
    int width = kernel[0].length;

    double redCount = 0;
    double greenCount = 0;
    double blueCount = 0;


    Pixel[][] arr = new Pixel[images.get(filename).length][images.get(filename)[0].length];
    for (int row = 0; row < this.images.get(filename).length; row++) {
      for (int col = 0; col < this.images.get(filename)[0].length; col++) {

        for (int kernelRow = row - (length / 2); kernelRow
                < (row - (length / 2)) + length; kernelRow++) {
          for (int kernelCol = col - (width / 2); kernelCol
                  < (col - (width / 2)) + width; kernelCol++) {
            try {
              redCount += images.get(filename)[kernelRow][kernelCol].getRed() * kernel[kernelRow - (
                      row
                              - (length / 2))][kernelCol - (col - (width / 2))];
              greenCount += images.get(filename)[kernelRow][kernelCol].getGreen() * kernel[kernelRow
                      - (row - (length / 2))][kernelCol - (col - (width / 2))];
              blueCount += images.get(filename)[kernelRow][kernelCol].getBlue() * kernel[kernelRow
                      - (row - (length / 2))][kernelCol - (col - (width / 2))];
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
          }
        }

        arr[row][col] = new Pixel(fixRGBRange(redCount), fixRGBRange(greenCount),
                fixRGBRange(blueCount));

        redCount = 0;
        greenCount = 0;
        blueCount = 0;

      }
    }
    view.renderMessage("Image " + filename + " filtered successfully");
    images.put(newFilename, arr);
  }

  @Override
  public void colorTransform(float[][] colors, String filename, String newFilename)
          throws IllegalArgumentException, IOException {
    if (colors == null) {
      throw new IllegalArgumentException("colors is null");
    }
    if (images.get(filename) == null) {
      throw new IllegalArgumentException("file doesn't exist");
    }
    if (colors.length != 3 || colors[0].length != 3) {
      throw new IllegalArgumentException("color matrix not size 3x3!");
    }
    Pixel[][] arr = new Pixel[images.get(filename).length][images.get(filename)[0].length];
    for (int row = 0; row < images.get(filename).length; row++) {
      for (int col = 0; col < images.get(filename)[0].length; col++) {
        Pixel p = images.get(filename)[row][col];
        double newRed =
                (colors[0][0] * p.getRed()
                        + colors[0][1] * p.getGreen()
                        + colors[0][2] * p.getBlue());
        double newGreen =
                (colors[1][0] * p.getRed()
                        + colors[1][1] * p.getGreen()
                        + colors[1][2] * p.getBlue());
        double newBlue =
                (colors[2][0] * p.getRed()
                        + colors[2][1] * p.getGreen()
                        + colors[2][2] * p.getBlue());

        arr[row][col] = new Pixel(fixRGBRange(newRed), fixRGBRange(newGreen),
                fixRGBRange(newBlue), p.getAlpha());
      }
    }
    images.put(newFilename, arr);
    view.renderMessage("Image " + filename + " transformed successfully");
  }

  @Override
  public boolean getStatus() {
    return this.quit;
  }

  @Override
  public void quit() {
    this.quit = true;
  }

  @Override
  public void add(String imageName, Pixel[][] arr) {
    images.put(imageName, arr);
  }

  @Override
  public HashMap<String, Pixel[][]> getMap() {
    return this.images;
  }

  @Override
  public int findTotalValue(String filename) {
    int value = images.get(filename)[0][0].findValue();

    for (int row = 0; row < images.get(filename).length; row++) {
      for (int col = 0; col < images.get(filename)[0].length; col++) {
        value = Math.max(images.get(filename)[row][col].findValue(), value);
      }
    }
    return value;

  }

  //Makes sure not RGB value is out of range
  private int fixRGBRange(double value) {
    if (value > 255) {
      return 255;
    }
    if (value < 0) {
      return 0;
    }

    return (int) Math.round(value);
  }

  @Override
  public void imageDownscale(int height, int width, String filename, String newFilename) throws IOException,
          IllegalArgumentException {
    if (images.get(filename) == null) {
      throw new IllegalArgumentException("file doesn't exist");
    }
    if (height < 0 || width < 0 || width > images.get(filename)[0].length
            || height > images.get(filename).length) {
      throw new IllegalArgumentException("invalid size");
    }
    Pixel[][] arr = new Pixel[height][width];
    int oldHeight = images.get(filename).length;
    int oldWidth = images.get(filename)[0].length;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        double expHeight = (double) i / (double) height * (double) oldHeight;
        double expWidth = (double) j / (double) width * (double) oldWidth;
        if (expHeight - (int) expHeight != 0 && expWidth - (int) expWidth != 0) {
          Pixel a = images.get(filename)[(int) Math.floor(expHeight)][(int) Math.floor(expWidth)];
          Pixel b = images.get(filename)[(int) Math.ceil(expHeight)][(int) Math.floor(expWidth)];
          Pixel c = images.get(filename)[(int) Math.floor(expHeight)][(int) Math.ceil(expWidth)];
          Pixel d = images.get(filename)[(int) Math.ceil(expHeight)][(int) Math.ceil(expWidth)];

          double redM = (double) b.getRed() * (expHeight - Math.floor(expHeight)) +
                  (double) a.getRed() * (Math.ceil(expHeight) - expHeight);
          double greenM = (double) b.getGreen() * (expHeight - Math.floor(expHeight)) +
                  (double) a.getGreen() * (Math.ceil(expHeight) - expHeight);
          double blueM = (double) b.getBlue() * (expHeight - Math.floor(expHeight)) +
                  (double) a.getBlue() * (Math.ceil(expHeight) - expHeight);

          double redN = (double) d.getRed() * (expHeight - Math.floor(expHeight)) +
                  (double) c.getRed() * (Math.ceil(expHeight) - expHeight);
          double greenN = (double) d.getGreen() * (expHeight - Math.floor(expHeight)) +
                  (double) c.getGreen() * (Math.ceil(expHeight) - expHeight);
          double blueN = (double) d.getBlue() * (expHeight - Math.floor(expHeight)) +
                  (double) c.getBlue() * (Math.ceil(expHeight) - expHeight);

          int redP = (int) (redN * (expWidth - Math.floor(expWidth)) +
                  redM * (Math.ceil(expWidth) - expWidth));
          int greenP = (int) (greenN * (expWidth - Math.floor(expWidth)) +
                  greenM * (Math.ceil(expWidth) - expWidth));
          int blueP = (int) (blueN * (expWidth - Math.floor(expWidth)) +
                  blueM * (Math.ceil(expWidth) - expWidth));

          arr[i][j] = new Pixel(redP, greenP, blueP);
        } else {
          int newHeight = (int) expHeight;
          int newWidth = (int) expWidth;
          arr[i][j] = images.get(filename)[newHeight][newWidth];
        }
      }
    }
    images.put(newFilename, arr);
    view.renderMessage("downscale");
  }

  @Override
  public void partialImageManipulation(String maskName, String filename, String newFilename, ImageCommands c) throws IllegalArgumentException, IOException {
    Pixel[][] mask = images.get(maskName);
    if (mask.length > images.get(filename).length || mask[0].length > images.get(filename)[0].length) {
      throw new IllegalArgumentException("mask is invalid size");
    }
    Pixel[][] temp = new Pixel[images.get(filename).length][images.get(filename)[0].length];
    for (int i = 0; i < images.get(filename).length; i++) {
      for (int j = 0; j < images.get(filename)[0].length; j++) {
        Pixel p = images.get(filename)[i][j];
        temp[i][j] = new Pixel(p.getRed(), p.getGreen(), p.getBlue(), p.getAlpha());
      }
    }
    Pixel[][] arr = new Pixel[images.get(filename).length][images.get(filename)[0].length];
    for (int i = 0; i < mask.length; i++) {
      for (int j = 0; j < mask[0].length; j++) {
        if (mask[i][j].equals(new Pixel(0, 0, 0))) {
          Pixel p = temp[i][j];
          arr[i][j] = new Pixel(p.getRed(), p.getGreen(), p.getBlue(), p.getAlpha());
        } else {
          arr[i][j] = null;
        }
      }
    }
    images.remove(filename);
    images.put(filename, arr);
    c.execute(this, this.view);

    Pixel[][] newPic = new Pixel[temp.length][temp[0].length];
    for (int i = 0; i < temp.length; i++) {
      for (int j = 0; j < temp[0].length; j++) {
        if (images.get(newFilename)[i][j] != null) {
          Pixel p = images.get(newFilename)[i][j];
          newPic[i][j] = new Pixel(p.getRed(), p.getGreen(), p.getBlue(), p.getAlpha());
        } else {
          Pixel p = temp[i][j];
          newPic[i][j] = new Pixel(p.getRed(), p.getGreen(), p.getBlue(), p.getAlpha());
        }
      }
    }
    images.remove(filename);
    images.put(filename, temp);
    images.remove(newFilename);
    images.put(newFilename, newPic);
    view.renderMessage("image partially manipulated");
  }


}
