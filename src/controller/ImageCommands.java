package controller;

import java.io.FileNotFoundException;

import model.ImageModel;

public interface ImageCommands {

   void execute(ImageModel model) throws FileNotFoundException;

}
