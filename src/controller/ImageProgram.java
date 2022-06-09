package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ImageProgram {
  public static void main(String[] args) throws FileNotFoundException {
    if(args.length < 3){
      System.out.println("Enter all commands on one line");
      Scanner s = new Scanner(System.in);
      String info = s.nextLine();
      Scanner scan = new Scanner(info);
      List<String> input = new ArrayList<>();
      while (scan.hasNext()){
        input.add(scan.next());
      }
      String[] arr = input.toArray(new String[input.size()]);
      ImageControllerImpl controller = new ImageControllerImpl(arr);
      controller.go();
    }
    else {
      ImageControllerImpl controller = new ImageControllerImpl(args);
      controller.go();
    }

  }

}
