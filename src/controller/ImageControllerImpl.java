package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.Image;
import model.Pixel;

/**
 * Implementation of a image controller
 */
public class ImageControllerImpl implements ImageController{

  String fileName;
  String instruction;
  int increment;

  public ImageControllerImpl(String fileName, String instruction, int increment){
    this.fileName = fileName;
    this.instruction = instruction;
    this.increment = increment;
  }
  protected Image usePPM(String filename){
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    }
    catch (FileNotFoundException e) {
      System.out.println("File "+filename+ " not found!");
      return null;
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

    Pixel[][] arr = new Pixel[height][width];
    for (int i=0;i<height;i++) {
      for (int j=0;j<width;j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        arr[i][j] = new Pixel(r,g,b);
      }
    }
    return new Image(arr);
  }

  @Override
  public void editImage() {
    Image newImage = usePPM(fileName);
    Actions.preform(newImage, instruction, increment);
  }

}
