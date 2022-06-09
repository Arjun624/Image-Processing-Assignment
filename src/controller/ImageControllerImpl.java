package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import model.ImageModel;
import view.ImageView;

/**
 * Implementation of an image controller
 */
public class ImageControllerImpl implements ImageController{

  String[] args;
  ImageView view;

  Readable rd;

  Map<String, Function<Scanner, ImageCommands>> commands;

  public ImageControllerImpl(String[] args, ImageView view, Readable rd){
    this.args = args;
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

  }

  @Override
  public void go() throws IllegalArgumentException, IOException {
//      ImageModel model = new ImageModel();
//      int i = 0;
//      while (i < args.length){
//        if(args[i].equalsIgnoreCase("adjust-brightness")){
//          Actions.preform(model, args[i], args[i+2], args[i+3],
//                  Integer.parseInt(args[i+1]), view);
//          i += 4;
//        }
//        else {
//          Actions.preform(model, args[i], args[i+1], args[i + 2], 0, view);
//          i += 3;
//        }
//      }

    Function<Scanner, ImageCommands> givenCommand = commands.get(rd.toString());
    if (givenCommand == null) {
      throw new IllegalArgumentException("Invalid command");
    } else {
      givenCommand.apply(new Scanner(rd)).execute(new ImageModel());
    }
  }

}
