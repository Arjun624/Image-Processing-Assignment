package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import model.ImageModel;
import view.ImageDisplay;
import view.ImageView;

public class ImageProgram {
  public static void main(String[] args) throws IOException {
    ImageControllerImpl controller;
    ImageView view = new ImageDisplay();


      ImageModel model = new ImageModel();
      InputStreamReader in = new InputStreamReader(System.in);
      while(!in.ready()){
        controller = new ImageControllerImpl( view, in);
        controller.go(model);
      }






  }

}
