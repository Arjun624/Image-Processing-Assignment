package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.ImageModel;
import view.ImageView;

public interface ImageCommands {

   void execute(ImageModel model, ImageView view) throws IOException;

}
