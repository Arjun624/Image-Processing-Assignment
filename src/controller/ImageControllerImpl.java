package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.ImageModel;
import view.ImageView;

/**
 * Implementation of an image controller
 */
public class ImageControllerImpl implements ImageController{

  String[] args;
  ImageView view;

  public ImageControllerImpl(String[] args, ImageView view){
    this.args = args;
    this.view = view;
  }

  @Override
  public void go() throws IllegalArgumentException, IOException {
      ImageModel model = new ImageModel();
      int i = 0;
      while (i < args.length){
        if(args[i].equalsIgnoreCase("adjust-brightness")){
          Actions.preform(model, args[i], args[i+2], args[i+3],
                  Integer.parseInt(args[i+1]), view);
          i += 4;
        }
        else {
          Actions.preform(model, args[i], args[i+1], args[i + 2], 0, view);
          i += 3;
        }
      }
  }

}
