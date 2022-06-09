package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import view.ImageDisplay;
import view.ImageView;

public class ImageProgram {
  public static void main(String[] args) throws IOException {
    ImageControllerImpl controller;
    ImageView view = new ImageDisplay();
    if(args.length < 3){
      System.out.println("Enter all commands and then enter 'run' to execute");
      Scanner s = new Scanner(System.in);
      List<String> input = new ArrayList<>();
      while (!input.contains("run")){
        input.add(s.next());
      }
      input.remove(input.size()-1);
      String[] arr = input.toArray(new String[0]);
      controller = new ImageControllerImpl(arr, view);
      controller.go();

    }
    else {
      controller = new ImageControllerImpl(args, view);
      controller.go();
    }

  }

}
