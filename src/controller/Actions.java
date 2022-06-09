package controller;

import java.io.FileNotFoundException;
import java.util.Locale;

import model.ImageModel;

public class Actions {

  public static void
  preform(ImageModel image, String instruction, String filepath, String filename, int increment)
          throws FileNotFoundException, IllegalArgumentException {
    switch (instruction.toLowerCase(Locale.ROOT)){
      case ("vertical-flip"):
        image.flipVertically(filepath, filename);
        System.out.println("Vertically flipped " + filename);
        break;
      case("horizontal-flip"):
        image.flipHorizontally(filepath, filename);
        System.out.println("Horizontally flipped " + filename);
        break;
      case("greyscale-red"):
        image.redGreyscale(filepath, filename);
        System.out.println("Created red greyscale of " + filename);
        break;
      case("greyscale-green"):
        image.greenGreyscale(filepath, filename);
        System.out.println("Created green greyscale of " + filename);
        break;
      case("greyscale-blue"):
        image.blueGreyscale(filepath, filename);
        System.out.println("Created blue greyscale of " + filename);
        break;
      case("adjust-brightness"):
        image.adjustBrightness(increment, filepath, filename);
        System.out.println("Adjusted brightness of " + filename);
        break;
      case("luma"):
        image.lumaGreyscale(filepath, filename);
        System.out.println("Created luma greyscale of " + filename);
        break;
      case("intensity"):
        image.intensityGreyscale(filepath, filename);
        System.out.println("Created intensity greyscale of " + filename);
        break;
      case("max"):
        image.valueGreyscale(filepath, filename);
        System.out.println("Created value greyscale of " + filename);
        break;
      case("load"):
        image.load(filepath, filename);
        System.out.println("Loaded " + filename);
        break;
      case("save"):
        image.saveImage(filepath, filename);
        System.out.println("Saved " + filename);
        break;
      default:
        throw new IllegalArgumentException("Insufficient instruction");
    }
  }


}
