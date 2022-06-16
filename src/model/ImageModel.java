package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

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
        if (newRed > 255) {
          newRed = 255;
        }
        if (newGreen > 255) {
          newGreen = 255;
        }
        if (newBlue > 255) {
          newBlue = 255;
        }
        if (newRed < 0) {
          newRed = 0;
        }
        if (newGreen < 0) {
          newGreen = 0;
        }
        if (newBlue < 0) {
          newBlue = 0;
        }
        arr[row][col] = new Pixel(newRed, newGreen, newBlue);
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



  public void blurImage(String filename, String newFilename, double[][] kernal) throws IOException,
          IllegalArgumentException {

    int length = kernal.length;
    int width = kernal[0].length;
    List<Double> newRs = new ArrayList<Double>();
    List<Double> newGs = new ArrayList<Double>();
    List<Double> newBs = new ArrayList<Double>();

    Pixel[][] arr = new Pixel[images.get(filename).length][images.get(filename)[0].length];
    for (int row = 0; row < this.images.get(filename).length; row++) {
      for (int col = 0; col < this.images.get(filename)[0].length; col++) {

        for (int kernalRow = row-(length/2); kernalRow < (row-(length/2)) + length; kernalRow++) {
          for (int kernalCol = col-(length/2); kernalCol < (col-(length/2)) + width; kernalCol++) {
            try{
              newRs.add(images.get(filename)[kernalRow][kernalCol].getRed()*kernal[kernalRow-(row-(length/2))][kernalCol-(col-(length/2))]);
              newGs.add(images.get(filename)[kernalRow][kernalCol].getGreen()*kernal[kernalRow-(row-(length/2))][kernalCol-(col-(length/2))]);
              newBs.add(images.get(filename)[kernalRow][kernalCol].getBlue()*kernal[kernalRow-(row-(length/2))][kernalCol-(col-(length/2))]);
            } catch(ArrayIndexOutOfBoundsException e){
              continue;
            }

          }
        }

        int rSum = newRs.stream()
                .mapToInt(Double::intValue)
                .sum();

        int gSum = newGs.stream()
                .mapToInt(Double::intValue)
                .sum();

        int bSum = newBs.stream()
                .mapToInt(Double::intValue)
                .sum();


        if(rSum > 255){
          rSum = 255;
        }
        if(gSum > 255){
          gSum = 255;
        }
        if(bSum > 255){
          bSum = 255;
        }
        if(rSum < 0){
          rSum = 0;
        }
        if(gSum < 0){
          gSum = 0;
        }
        if(bSum < 0){
          bSum = 0;
        }

        arr[row][col] = new Pixel(rSum, gSum, bSum);
        newRs.clear();
        newGs.clear();
        newBs.clear();


      }
    }
    images.put(newFilename, arr);
  }






  @Override
  public void sepia(String filename, String newFilename){
    Pixel[][] arr = new Pixel[images.get(filename).length][images.get(filename)[0].length];
    for (int i = 0; i < images.get(filename).length; i++) {
      for (int j = 0; j < images.get(filename)[0].length; j++) {
        arr[i][j] = images.get(filename)[i][j].getSepia();
      }
    }
    images.put(newFilename,arr);
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



}
