package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import controller.Actions.AdjustBrightness;
import controller.Actions.BlueGreyscale;
import controller.Actions.GreenGreyscale;
import controller.Actions.HorizontalFlip;
import controller.Actions.IntensityGreyscale;
import controller.Actions.LoadImage;
import controller.Actions.LumaGreyscale;
import controller.Actions.Quit;
import controller.Actions.RedGreyscale;
import controller.Actions.SaveImage;
import controller.Actions.ValueGreyscale;
import controller.Actions.VerticalFlip;
import model.ImageModel;
import view.ImageView;

/**
 * Implementation of an image controller
 */
public class ImageControllerImpl implements ImageController{

  ImageView view;

  Readable rd;

  Map<String, Function<Scanner, ImageCommands>> commands;

  public ImageControllerImpl(ImageView view, Readable rd){

    this.view = view;
    this.rd = rd;

    commands = new HashMap<>();
    commands.put("adjust-brightness", s -> new AdjustBrightness(s.nextInt(), s.next(), s.next()));
    commands.put("load", s -> new LoadImage(s.next(), s.next()));
    commands.put("save", s -> new SaveImage(s.next(), s.next()));
    commands.put("vertical-flip", s -> new VerticalFlip(s.next(), s.next()));
    commands.put("horizontal-flip", s -> new HorizontalFlip(s.next(), s.next()));
    commands.put("greyscale-red", s -> new RedGreyscale(s.next(), s.next()));
    commands.put("greyscale-green", s -> new GreenGreyscale(s.next(), s.next()));
    commands.put("greyscale-blue", s -> new BlueGreyscale(s.next(), s.next()));
    commands.put("luma", s -> new LumaGreyscale(s.next(), s.next()));
    commands.put("intensity", s -> new IntensityGreyscale(s.next(), s.next()));
    commands.put("max", s -> new ValueGreyscale(s.next(), s.next()));
    commands.put("q", s -> new Quit());

  }

  @Override
  public void go(ImageModel model) throws IllegalArgumentException, IOException {
    Scanner s = new Scanner(rd);
    Function<Scanner, ImageCommands> givenCommand = commands.getOrDefault(s.next(), null);
    if (givenCommand == null) {
      view.renderMessage("Invalid command!");
    } else {
      givenCommand.apply(s).execute(model, view);
    }
  }
}
