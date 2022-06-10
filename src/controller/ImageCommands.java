package controller;

import java.io.IOException;

import model.ImageEditor;
import model.ImageModel;
import view.ImageView;

/**
 * Represents all the possible command to be called on an image.
 */
public interface ImageCommands {

   /**
    * Executes the specific action of the class.
    * @param model the model to be edited
    * @param view the view
    * @throws IOException if the program cannot read the input or write the output
    */
   void execute(ImageEditor model, ImageView view) throws IOException;

}
