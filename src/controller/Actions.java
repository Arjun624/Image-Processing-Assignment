package controller;

import java.util.Locale;

import model.Image;

public class Actions {

  public static void preform(Image image, String instruction, int increment){
    switch (instruction.toLowerCase(Locale.ROOT)){
      case ("vertical-flip"):
        image.flipVertically();
        break;
      case("horizontal-flip"):
        image.flipHorizontally();
        break;
      case("greyscale-red"):
        image.redGreyscale();
        break;
      case("greyscale-green"):
        image.greenGreyscale();
        break;
      case("greyscale-blue"):
        image.blueGreyscale();
        break;
      case("adjust-brightness"):
        image.adjustBrightness(increment);
        break;
      case("luma"):
        image.lumaGreyscale();
        break;
      case("intensity"):
        image.intensityGreyscale();
        break;
      case("max"):
        image.valueGreyscale();
        break;
      default:
        break;
    }
  }


}
