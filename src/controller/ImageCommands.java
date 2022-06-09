package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.ImageModel;

public interface ImageCommands {

   void execute(ImageModel model) throws IOException;

}
