package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import controller.commands.AdjustBrightness;
import controller.commands.BlueGreyscale;
import controller.commands.GreenGreyscale;
import controller.commands.HorizontalFlip;
import controller.commands.IntensityGreyscale;
import controller.commands.LoadImage;
import controller.commands.LumaGreyscale;
import controller.commands.Quit;
import controller.commands.RedGreyscale;
import controller.commands.SaveImage;
import controller.commands.ValueGreyscale;
import controller.commands.VerticalFlip;
import model.ImageEditor;
import view.ImageView;

/**
 * Implementation of an image controller.
 */
public class ImageControllerImpl implements ImageController {

  private final ImageView view;

  private final Readable rd;

  private final Map<String, Function<Scanner, ImageCommands>> commands;

  /**
   * Constructs an {@code ImageControllerImpl} where the user can set the view and readable object.
   *
   * @param view the view
   * @param rd   the readable object to be read by the scanner
   */
  public ImageControllerImpl(ImageView view, Readable rd) throws IllegalArgumentException {
    if (view == null || rd == null) {
      throw new IllegalArgumentException("View or readable cannot be null");
    }

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
    commands.put("value", s -> new ValueGreyscale(s.next(), s.next()));
    commands.put("q", s -> new Quit());

  }

  @Override
  public void start(ImageEditor model) throws IllegalArgumentException, IOException {
    Scanner s = new Scanner(rd);

    while (!model.getStatus()) {
      view.renderMessage("Enter a command: ");
      Function<Scanner, ImageCommands> givenCommand = commands.getOrDefault(s.next(), null);
      if (givenCommand == null) {
        view.renderMessage("Invalid command!");
      } else {
        givenCommand.apply(s).execute(model, view);
      }
    }

  }
}
