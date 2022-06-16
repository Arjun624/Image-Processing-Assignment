package controller.model;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;

import view.ImageDisplay;
import view.ImageView;

/**
 * Represents a way to edit, load, and save PPM images.
 */
public class ImageModel implements ImageEditor {
  public HashMap<String, Pixel[][]> images;
  public boolean quit;

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
  public void redGreyscale(String filename, String newFilename) throws IOException,
          IllegalArgumentException {
    if (images.get(filename) == null) {
      throw new IllegalArgumentException("file doesn't exist");
    }

    Pixel[][] arr = new Pixel[images.get(filename).length][images.get(filename)[0].length];
    for (int row = 0; row < this.images.get(filename).length; row++) {
      for (int col = 0; col < this.images.get(filename)[0].length; col++) {
        int red = this.images.get(filename)[row][col].getRed();
        arr[row][col] = new Pixel(red, red, red);
      }
    }
    images.put(newFilename, arr);
    view.renderMessage("Imaged " + filename + " changed to red greyscale");
  }

  @Override
  public void greenGreyscale(String filename, String newFilename) throws IOException,
          IllegalArgumentException {
    if (images.get(filename) == null) {
      throw new IllegalArgumentException("file doesn't exist");
    }

    Pixel[][] arr = new Pixel[images.get(filename).length][images.get(filename)[0].length];
    for (int row = 0; row < this.images.get(filename).length; row++) {
      for (int col = 0; col < this.images.get(filename)[0].length; col++) {
        int green = this.images.get(filename)[row][col].getGreen();
        arr[row][col] = new Pixel(green, green, green);
      }
    }
    images.put(newFilename, arr);
    view.renderMessage("image " + filename + " changed to green greyscale");
  }

  public void Agreyscale(String filename, String newFilename, Method c) throws Exception {
    if (images.get(filename) == null) {
      throw new IllegalArgumentException("file doesn't exist");
    }

    Pixel[][] arr = new Pixel[images.get(filename).length][images.get(filename)[0].length];
    for (int row = 0; row < this.images.get(filename).length; row++) {
      for (int col = 0; col < this.images.get(filename)[0].length; col++) {
        Pixel p = (Pixel) c.invoke(this.images.get(filename)[row][col]);
        arr[row][col] = p;
      }
    }
    images.put(newFilename, arr);
    view.renderMessage("image " + filename + " changed to inputted greyscale");
  }

  @Override
  public void blueGreyscale(String filename, String newFilename) throws IOException,
          IllegalArgumentException {
    if (images.get(filename) == null) {
      throw new IllegalArgumentException("file doesn't exist");
    }

    Pixel[][] arr = new Pixel[images.get(filename).length][images.get(filename)[0].length];
    for (int row = 0; row < this.images.get(filename).length; row++) {
      for (int col = 0; col < this.images.get(filename)[0].length; col++) {
        int blue = this.images.get(filename)[row][col].getBlue();
        arr[row][col] = new Pixel(blue, blue, blue);
      }
    }
    images.put(newFilename, arr);
    view.renderMessage("image " + filename + " changed to blue greyscale");
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
  public void lumaGreyscale(String filename, String newFilename) throws IOException,
          IllegalArgumentException {
    if (images.get(filename) == null) {
      throw new IllegalArgumentException("file doesn't exist");
    }

    Pixel[][] arr = new Pixel[images.get(filename).length][images.get(filename)[0].length];
    for (int row = 0; row < this.images.get(filename).length; row++) {
      for (int col = 0; col < this.images.get(filename)[0].length; col++) {
        int luma = this.images.get(filename)[row][col].findLuma();
        arr[row][col] = new Pixel(luma, luma, luma);
      }
    }
    images.put(newFilename, arr);
    view.renderMessage("Image " + filename + " changed to luma");
  }

  @Override
  public void intensityGreyscale(String filename, String newFilename) throws IOException,
          IllegalArgumentException {
    if (images.get(filename) == null) {
      throw new IllegalArgumentException("file doesn't exist");
    }

    Pixel[][] arr = new Pixel[images.get(filename).length][images.get(filename)[0].length];
    for (int row = 0; row < this.images.get(filename).length; row++) {
      for (int col = 0; col < this.images.get(filename)[0].length; col++) {
        int intensity = this.images.get(filename)[row][col].findIntensity();
        arr[row][col] = new Pixel(intensity, intensity, intensity);
      }
    }
    images.put(newFilename, arr);
    view.renderMessage("Image " + filename + " changed to intensity greyscale");
  }

  @Override
  public void valueGreyscale(String filename, String newFilename) throws IOException,
          IllegalArgumentException {
    if (images.get(filename) == null) {
      throw new IllegalArgumentException("file doesn't exist");
    }

    Pixel[][] arr = new Pixel[images.get(filename).length][images.get(filename)[0].length];
    for (int row = 0; row < this.images.get(filename).length; row++) {
      for (int col = 0; col < this.images.get(filename)[0].length; col++) {
        int value = this.images.get(filename)[row][col].findValue();
        arr[row][col] = new Pixel(value, value, value);
      }
    }
    images.put(newFilename, arr);
    view.renderMessage("Image " + filename + " changed to greyscale");
  }



  public void filterImage(String filename, String newFilename, double[][] kernal) throws IOException,
          IllegalArgumentException {
    if(kernal.length % 2 == 0 || kernal[0].length % 2 == 0) {
      throw new IllegalArgumentException("Kernal must have an odd number of rows and columns");
    }

    int length = kernal.length;
    int width = kernal[0].length;

    double newRed = 0;
    double newGreen = 0;
    double newBlue = 0;


    Pixel[][] arr = new Pixel[images.get(filename).length][images.get(filename)[0].length];
    for (int row = 0; row < this.images.get(filename).length; row++) {
      for (int col = 0; col < this.images.get(filename)[0].length; col++) {

        for (int kernalRow = row-(length/2); kernalRow < (row-(length/2)) + length; kernalRow++) {
          for (int kernalCol = col-(width/2); kernalCol < (col-(width/2)) + width; kernalCol++) {
            try{
              newRed += images.get(filename)[kernalRow][kernalCol].getRed()*kernal[kernalRow-(row-(length/2))][kernalCol-(col-(width/2))];
              newGreen += images.get(filename)[kernalRow][kernalCol].getGreen()*kernal[kernalRow-(row-(length/2))][kernalCol-(col-(width/2))];
              newBlue += images.get(filename)[kernalRow][kernalCol].getBlue()*kernal[kernalRow-(row-(length/2))][kernalCol-(col-(width/2))];
            } catch(ArrayIndexOutOfBoundsException ignored){}
          }
        }

        arr[row][col] = new Pixel(fixRGBRange(newRed), fixRGBRange(newGreen), fixRGBRange(newBlue));

        newRed = 0;
        newGreen = 0;
        newBlue = 0;

      }
    }
    view.renderMessage("Image " + filename + " filtered successfully");
    images.put(newFilename, arr);
  }

  @Override
  public void colorTransform(float[][] colors, String filename, String newFilename)
          throws IllegalArgumentException, IOException {
    if (colors.length != 3 || colors[0].length != 3){
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
        double newBlue=
                (colors[2][0] * p.getRed()
                        + colors[2][1] * p.getGreen()
                        + colors[2][2] * p.getBlue());

        arr[row][col] = new Pixel(fixRGBRange(newRed), fixRGBRange(newGreen), fixRGBRange(newBlue),p.getAlpha());
      }
    }
    images.put(newFilename,arr);
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
    images.put(imageName,arr);
  }

  public HashMap<String,Pixel[][]> getMap(){
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

  private int fixRGBRange(double value){
    if(value > 255){
      return 255;
    }
    if(value < 0){
      return 0;
    }

    return (int) Math.round(value);
  }



}
