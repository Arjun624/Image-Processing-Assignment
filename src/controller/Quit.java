package controller;

import java.io.IOException;

import model.ImageModel;

public class Quit implements ImageCommands{
  @Override
  public void execute(ImageModel model) throws IOException {
    model.quit = true;
  }
}
