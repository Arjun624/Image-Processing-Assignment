package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class ImageModel {
  HashMap<String, Pixel[][]> images;
  String pathname;

  public ImageModel(){
    images = new HashMap<String, Pixel[][]>();
  }

  public ImageModel(HashMap<String, Pixel[][]> images){
   this.images = images;
  }

  public void flipVertically(String filename) {

    int yLength = images.get(filename).length-1;

    //System.arraycopy(pixels[yLength - row], 0, pixels[row], 0, imagePixels[0].length);
    for(int row = 0; row< images.get(filename).length; row++){
      for(int col = 0; col<images.get(filename)[0].length; col++){
        images.get(filename)[row][col]=images.get(filename)[yLength-row][col];
      }
    }

  }

  public void flipHorizontally(String filename) {

    int xLength = images.get(filename)[0].length-1;

    for(int row = 0; row< images.get(filename).length; row++){
      for(int col = 0; col<images.get(filename)[0].length; col++){
        images.get(filename)[row][col]=images.get(filename)[row][xLength-col];
      }
    }
  }

  public void redGreyscale(String filename) {
    for (int row = 0; row < this.images.get(filename).length; row++) {
      for (int col = 0; col < this.images.get(filename)[0].length; col++) {
        int red = this.images.get(filename)[row][col].r;
        images.get(filename)[row][col]  = new Pixel(red,red,red);
      }
    }
  }

  public void greenGreyscale(String filename) {
    for (int row = 0; row < this.images.get(filename).length; row++) {
      for (int col = 0; col < this.images.get(filename)[0].length; col++) {
        int green = this.images.get(filename)[row][col].g;
        images.get(filename)[row][col]  = new Pixel(green,green,green);
      }
    }
  }

  public void blueGreyscale(String filename) {
    for (int row = 0; row < this.images.get(filename).length; row++) {
      for (int col = 0; col < this.images.get(filename)[0].length; col++) {
        int blue = this.images.get(filename)[row][col].b;
        images.get(filename)[row][col]  = new Pixel(blue,blue,blue);
      }
    }
  }

  public void adjustBrightness(int increment, String filename) {
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
        images.get(filename)[row][col] = new Pixel(newRed, newGreen, newBlue);
      }
    }
  }

  public void lumaGreyscale(String filename) {
    for (int row = 0; row < this.images.get(filename).length; row++) {
      for (int col = 0; col < this.images.get(filename)[0].length; col++) {
        int luma= this.images.get(filename)[row][col].findLuma();
        images.get(filename)[row][col]  = new Pixel(luma,luma,luma);
      }
    }
  }

  public void intensityGreyscale(String filename) {
    for (int row = 0; row < this.images.get(filename).length; row++) {
      for (int col = 0; col < this.images.get(filename)[0].length; col++) {
        int intensity= this.images.get(filename)[row][col].findIntensity();
        images.get(filename)[row][col]  = new Pixel(intensity,intensity,intensity);
      }
    }
  }

  public void valueGreyscale(String filename) {
    for (int row = 0; row < this.images.get(filename).length; row++) {
      for (int col = 0; col < this.images.get(filename)[0].length; col++) {
        int value= this.images.get(filename)[row][col].findValue();
        images.get(filename)[row][col]  = new Pixel(value,value,value);
      }
    }
  }

  public void load(String filename) throws FileNotFoundException{
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    }
    catch (FileNotFoundException e) {
      System.out.println("File "+filename+ " not found!");
      throw new FileNotFoundException("File "+filename+ " not found!");
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


  protected void saveImage(String pathname, String imageName){
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
      writer.write("P3");
      writer.write(images.get(imageName)[0].length);
      writer.write(images.get(imageName).length);
      writer.write(images.get(imageName)[0][0].findValue());
      for(int row = 0; row< images.get(imageName).length; row++) {
        for (int col = 0; col < images.get(imageName)[0].length; col++) {
          writer.write(images.get(imageName)[row][col].r);
          writer.write(images.get(imageName)[row][col].g);
          writer.write(images.get(imageName)[row][col].b);
        }
      }
      writer.close();
     System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

  }

}
