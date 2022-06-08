package controller;

import java.io.FileNotFoundException;

import model.ImageModel;

/**
 * Implementation of an image controller
 */
public class ImageControllerImpl implements ImageController{

  String[] args;


  public ImageControllerImpl(String[] args){
    this.args = args;
  }

  @Override
  public void go() throws IllegalArgumentException, FileNotFoundException {
      ImageModel model = new ImageModel();
      int i = 0;
      while (i < args.length){
        if(args[i].equalsIgnoreCase("brighten")){
          Actions.preform(model, args[i+2], args[i], args[i+3], Integer.parseInt(args[i+1]));
          i += 4;
        }
        else {
          Actions.preform(model, args[i+1], args[i], args[i + 2], 0);
          i += 3;
        }
      }
  }

}
