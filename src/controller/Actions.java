package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;

import model.ImageModel;
import view.ImageView;

public class Actions {

  public static void preform(ImageModel image, String instruction,
                             String filepath, String filename, int increment, ImageView view)
          throws IOException, IllegalArgumentException {
    switch (instruction.toLowerCase(Locale.ROOT)){
      case ("vertical-flip"):
        image.flipVertically(filepath, filename);
        view.renderMessage("Vertically flipped " + filename);
        break;
      case("horizontal-flip"):
        image.flipHorizontally(filepath, filename);
        view.renderMessage("Horizontally flipped " + filename);
        break;
      case("greyscale-red"):
        image.redGreyscale(filepath, filename);
        view.renderMessage("Created red greyscale of " + filename);
        break;
      case("greyscale-green"):
        image.greenGreyscale(filepath, filename);
        view.renderMessage("Created green greyscale of " + filename);
        break;
      case("greyscale-blue"):
        image.blueGreyscale(filepath, filename);
        view.renderMessage("Created blue greyscale of " + filename);
        break;
      case("adjust-brightness"):
        image.adjustBrightness(increment, filepath, filename);
        view.renderMessage("Adjusted brightness of " + filename);
        break;
      case("luma"):
        image.lumaGreyscale(filepath, filename);
        view.renderMessage("Created luma greyscale of " + filename);
        break;
      case("intensity"):
        image.intensityGreyscale(filepath, filename);
        view.renderMessage("Created intensity greyscale of " + filename);
        break;
      case("max"):
        image.valueGreyscale(filepath, filename);
        view.renderMessage("Created value greyscale of " + filename);
        break;
      case("load"):
        image.loadImage(filepath, filename);
        view.renderMessage("Loaded " + filename);
        break;
      case("save"):
        image.saveImage(filepath, filename);
        view.renderMessage("Saved " + filename);
        break;
      default:
        throw new IllegalArgumentException("Insufficient instruction");
    }
  }


}
