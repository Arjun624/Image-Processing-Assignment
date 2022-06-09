package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class ImageModel {
  public HashMap<String, Pixel[][]> images;
  public boolean quit;

  public ImageModel(){
    images = new HashMap<>();
    quit = false;
  }

  public ImageModel(HashMap<String, Pixel[][]> images){
   this.images = images;
   quit = false;
  }

  public void flipVertically(String filename, String newFilename) {

    int yLength = images.get(filename).length-1;

    Pixel[][] arr = new Pixel[images.get(filename).length][images.get(filename)[0].length];
    for(int row = 0; row< images.get(filename).length; row++){
      for(int col = 0; col<images.get(filename)[0].length; col++){
        arr[row][col]=images.get(filename)[yLength-row][col];
      }
    }

    images.put(newFilename, arr);

  }

  public void flipHorizontally(String filename, String newFilename) {

    int xLength = images.get(filename)[0].length-1;

    Pixel[][] arr = new Pixel[images.get(filename).length][images.get(filename)[0].length];
    for(int row = 0; row< images.get(filename).length; row++){
      for(int col = 0; col<images.get(filename)[0].length; col++){
        arr[row][col]=images.get(filename)[row][xLength - col];
      }
    }

    images.put(newFilename, arr);

  }

  public void redGreyscale(String filename, String newFilename) {
    Pixel[][] arr = new Pixel[images.get(filename).length][images.get(filename)[0].length];
    for (int row = 0; row < this.images.get(filename).length; row++) {
      for (int col = 0; col < this.images.get(filename)[0].length; col++) {
        int red = this.images.get(filename)[row][col].r;
        arr[row][col]  = new Pixel(red,red,red);
      }
    }
    images.put(newFilename,arr);
  }

  public void greenGreyscale(String filename, String newFilename) {
    Pixel[][] arr = new Pixel[images.get(filename).length][images.get(filename)[0].length];
    for (int row = 0; row < this.images.get(filename).length; row++) {
      for (int col = 0; col < this.images.get(filename)[0].length; col++) {
        int green = this.images.get(filename)[row][col].g;
        arr[row][col]  = new Pixel(green,green,green);
      }
    }
    images.put(newFilename,arr);
  }

  public void blueGreyscale(String filename, String newFilename) {
    Pixel[][] arr = new Pixel[images.get(filename).length][images.get(filename)[0].length];
    for (int row = 0; row < this.images.get(filename).length; row++) {
      for (int col = 0; col < this.images.get(filename)[0].length; col++) {
        int blue = this.images.get(filename)[row][col].b;
        arr[row][col]  = new Pixel(blue,blue,blue);
      }
    }
    images.put(newFilename,arr);
  }

  public void adjustBrightness(int increment, String filename, String newFilename) {
    Pixel[][] arr = new Pixel[images.get(filename).length][images.get(filename)[0].length];
    for(int row = 0; row< images.get(filename).length; row++) {
      for (int col = 0; col < images.get(filename)[0].length; col++) {
        int newRed =  this.images.get(filename)[row][col].r + increment;
        int newGreen = this.images.get(filename)[row][col].g + increment;
        int newBlue = this.images.get(filename)[row][col].b + increment;
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
    images.put(newFilename,arr);
  }

  public void lumaGreyscale(String filename, String newFilename) {
    Pixel[][] arr = new Pixel[images.get(filename).length][images.get(filename)[0].length];
    for (int row = 0; row < this.images.get(filename).length; row++) {
      for (int col = 0; col < this.images.get(filename)[0].length; col++) {
        int luma= this.images.get(filename)[row][col].findLuma();
        arr[row][col]  = new Pixel(luma,luma,luma);
      }
    }
    images.put(newFilename,arr);
  }

  public void intensityGreyscale(String filename, String newFilename) {
    Pixel[][] arr = new Pixel[images.get(filename).length][images.get(filename)[0].length];
    for (int row = 0; row < this.images.get(filename).length; row++) {
      for (int col = 0; col < this.images.get(filename)[0].length; col++) {
        int intensity= this.images.get(filename)[row][col].findIntensity();
        arr[row][col]  = new Pixel(intensity,intensity,intensity);
      }
    }
    images.put(newFilename,arr);
  }

  public void valueGreyscale(String filename, String newFilename) {
    Pixel[][] arr = new Pixel[images.get(filename).length][images.get(filename)[0].length];
    for (int row = 0; row < this.images.get(filename).length; row++) {
      for (int col = 0; col < this.images.get(filename)[0].length; col++) {
        int value= this.images.get(filename)[row][col].findValue();
        images.get(filename)[row][col]  = new Pixel(value,value,value);
      }
    }
    images.put(newFilename,arr);
  }

  public void loadImage(String pathname, String filename) throws FileNotFoundException{
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(pathname));
    }
    catch (FileNotFoundException e) {
      System.out.println("File "+pathname+ " not found!");
      throw new FileNotFoundException("File "+pathname+ " not found!");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0)!='#') {
        builder.append(s).append(System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: "+width);
    int height = sc.nextInt();
    System.out.println("Height of image: "+height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): "+maxValue);

    //now read the image data
    Pixel[][] pixels = new Pixel[height][width];
    for (int row=0;row<height;row++) {
      for (int col=0;col<width;col++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        pixels[row][col] = new Pixel(r,g,b);
      }
    }
    images.put(filename, pixels);
  }


  public void saveImage(String pathname, String filename){
    StringBuilder sb = new StringBuilder();
    try {
      File newFile = new File(pathname);
      if (newFile.createNewFile()) {
        System.out.println("File created: " + newFile.getName());
      } else {
        System.out.println("File already exists.");
      }

    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

    try {
      FileWriter writer = new FileWriter(pathname);
      sb.append("P3");
      sb.append((System.lineSeparator()));
      sb.append(images.get(filename)[0].length);
      sb.append(" ");
      sb.append(images.get(filename).length);
      sb.append((System.lineSeparator()));
      sb.append(findTotalValue(filename));
      sb.append(System.lineSeparator());
      for(int row = 0; row< images.get(filename).length; row++) {
        for (int col = 0; col < images.get(filename)[0].length; col++) {
          sb.append(images.get(filename)[row][col].r);
          sb.append((System.lineSeparator()));
          sb.append((images.get(filename)[row][col].g));
          sb.append((System.lineSeparator()));
          sb.append((images.get(filename)[row][col].b));
          sb.append((System.lineSeparator()));
        }
      }
      writer.write(sb.toString());
      writer.close();
     System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

  }

  private int findTotalValue(String filename){
    int value = images.get(filename)[0][0].findValue();

    for (int row = 0; row < images.get(filename).length; row++) {
      for (int col = 0; col < images.get(filename)[0].length; col++) {
        value = Math.max(images.get(filename)[row][col].findValue(), value);
      }
    }
    return value;

  }

}
