package controller;

import java.util.Locale;

import model.ImageModel;

public class Actions {

  public static void preform(ImageModel image, String instruction, String filename, int increment){
    switch (instruction.toLowerCase(Locale.ROOT)){
      case ("vertical-flip"):
        image.flipVertically(filename);
        break;
      case("horizontal-flip"):
        image.flipHorizontally(filename);
        break;
      case("greyscale-red"):
        image.redGreyscale(filename);
        break;
      case("greyscale-green"):
        image.greenGreyscale(filename);
        break;
      case("greyscale-blue"):
        image.blueGreyscale(filename);
        break;
      case("adjust-brightness"):
        image.adjustBrightness(increment, filename);
        break;
      case("luma"):
        image.lumaGreyscale(filename);
        break;
      case("intensity"):
        image.intensityGreyscale(filename);
        break;
      case("max"):
        image.valueGreyscale(filename);
        break;
      default:
        break;
    }
  }


}
