package controller;

import java.io.FileNotFoundException;
import java.util.Locale;

import model.ImageModel;

public class Actions {

  public static void
  preform(ImageModel image, String filepath, String instruction, String filename, int increment)
          throws FileNotFoundException, IllegalArgumentException {
    switch (instruction.toLowerCase(Locale.ROOT)){
      case ("vertical-flip"):
        image.flipVertically(filepath, filename);
        break;
      case("horizontal-flip"):
        image.flipHorizontally(filepath, filename);
        break;
      case("greyscale-red"):
        image.redGreyscale(filepath, filename);
        break;
      case("greyscale-green"):
        image.greenGreyscale(filepath, filename);
        break;
      case("greyscale-blue"):
        image.blueGreyscale(filepath, filename);
        break;
      case("adjust-brightness"):
        image.adjustBrightness(increment, filepath, filename);
        break;
      case("luma"):
        image.lumaGreyscale(filepath, filename);
        break;
      case("intensity"):
        image.intensityGreyscale(filepath, filename);
        break;
      case("max"):
        image.valueGreyscale(filepath, filename);
        break;
      case("load"):
        image.load(filepath, filename);
        break;
      case("save"):
        image.saveImage(filepath, filename);
        break;
      default:
        throw new IllegalArgumentException("Cannot preform: " + instruction);
    }
  }


}
