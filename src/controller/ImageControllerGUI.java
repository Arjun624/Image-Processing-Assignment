package controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;

import controller.commands.AdjustBrightness;
import controller.commands.BlueGreyscale;
import controller.commands.Blur;
import controller.commands.GreenGreyscale;
import controller.commands.Greyscale;
import controller.commands.HorizontalFlip;
import controller.commands.IntensityGreyscale;
import controller.commands.LumaGreyscale;
import controller.commands.RedGreyscale;
import controller.commands.Sepia;
import controller.commands.Sharpen;
import controller.commands.ValueGreyscale;
import controller.commands.VerticalFlip;
import model.ImageEditor;
import model.ImageModel;
import model.Pixel;
import view.ImageDisplay;

public class ImageControllerGUI implements ImageController, ActionListener {


  private final ImageEditor model;
  private final ImageProcessingGUI gui;

  private String filename;

  public ImageControllerGUI(ImageEditor model) throws IOException {
    this.model = model;
    this.gui = new ImageProcessingGUI(this);
    this.filename = "image";
  }

  /**
   * Method that takes in the user arguments and preforms any action.
   *
   * @throws IOException if the program cannot read the input or write the output
   */
  @Override
  public void start() throws IOException {

  }

  /**
   * Loads an image from the inputted path and adds it to the map of images.
   *
   * @param pathname is the path to the image to be loaded.
   * @param filename is the name of the image to be loaded.
   * @throws IOException            if the image cannot be loaded.
   * @throws NoSuchElementException if the path does not exist
   */
  @Override
  public void loadImage(String pathname, String filename) throws IOException {

    if (pathname.length() >= 4 && pathname.substring(pathname.length()
            - 4).equalsIgnoreCase(".ppm")) {
      loadPPM(new File(pathname));
    } else {
      loadOther(new File(pathname));
    }
  }

  private void loadPPM(File file) throws IOException,
          NoSuchElementException {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(file));
    } catch (FileNotFoundException e) {
      throw new NoSuchElementException("File " + file.getName() + " not found!");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      gui.showErrorPopup("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    //now read the image data
    Pixel[][] pixels = new Pixel[height][width];
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        pixels[row][col] = new Pixel(r, g, b);
      }
    }
    System.out.println("yuh");
    model.add(filename, pixels);
    gui.getHistogram(filename);
    //view.renderMessage("Image: " + pathname + "\nloaded as: " + filename);

  }

  private void loadOther(File file) throws IOException,
          NoSuchElementException {
    BufferedImage b;

    try {
      b = ImageIO.read(file);
    } catch (IOException e) {
      throw new NoSuchElementException();
    }
    int width = b.getWidth();
    int height = b.getHeight();
    Pixel[][] arr = new Pixel[height][width];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        Color c = new Color(b.getRGB(i, j));
        arr[j][i] = new Pixel(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
      }
    }
    model.add(filename, arr);
    gui.getHistogram(filename);
  }


  /**
   * Saves the given filename to the given pathname on the local disk.
   *
   * @param pathname the pathname of the file to save.
   * @param filename is the name of the file to save.
   * @throws IOException if the file cannot be saved.
   */
  @Override
  public void saveImage(String pathname, String filename) throws IOException {

  }

  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    Object game = e.getActionCommand();
    if(game.equals("Load")){
      JFileChooser chooser = new JFileChooser();
      chooser.showOpenDialog(gui);
      File file = chooser.getSelectedFile();
      System.out.println(file.getName());
      String format = file.getName().split("\\.")[1];
      try{
        this.loadImage(file.getAbsolutePath(), filename);
        gui.imageBoxes[0] = new ImageIcon(file.getAbsolutePath());
        gui.replaceImage(filename);
      }catch(Exception ex){
        gui.showErrorPopup("Error loading file, please check file format.");
      }
    }
    if (game.equals("Picked Filter")) {
      gui.changeLabelText("FILTER");
      gui.addEdit("FILTER");
    }
    if (game.equals("Picked Color")) {
      gui.changeLabelText("COLOR");
      gui.addEdit("COLOR");
    }
    if (game.equals("Picked Greyscale")) {
      gui.changeLabelText("GREYSCALE");
      gui.addEdit("GREYSCALE");
    }
    if (game.equals("Picked Flip")) {
      gui.changeLabelText("FLIP");
      gui.addEdit("FLIP");
    }
    if (game.equals("Edit")) {
      try {
        gui.editImage();
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    }
    if (game.equals("Brightness")) {
       gui.setIncrement();

      try {
        int brightness = gui.getIncrement();
        if(brightness>0){
          gui.changeLabelText("BRIGHTEN");
          gui.addEdit("BRIGHTEN");
        } else if(brightness<0){
          gui.changeLabelText("BRIGHTEN");
          gui.addEdit("DIM");
        } else {
          gui.changeLabelText("BRIGHTEN");
        };
      } catch (Exception var5) {
        gui.changeLabelText("INVALID");
      }
    }
    if(game.equals("Save")){
      String newFile = "res/" + JOptionPane.showInputDialog(new JFrame(), "Enter the new file name");
      try {
        gui.save(newFile);
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    }
  }

  public void edit(String command) throws IOException {
    String newFilename;
    switch (command) {
      case ("VERTICAL FLIP"):
        newFilename = filename + "-vf";
        new VerticalFlip(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        gui.replaceImage(filename);
        break;
      case ("HORIZONTAL FLIP"):
        newFilename = filename + "-hf";
        new HorizontalFlip(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        gui.replaceImage(filename);
        break;
      case ("SEPIA"):
        newFilename = filename + "-sep";
        new Sepia(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        gui.getHistogram(filename);
        gui.replaceImage(filename);
        break;
      case ("GREYSCALE"):
        newFilename = filename + "-vf";
        new Greyscale(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        gui.getHistogram(filename);
        gui.replaceImage(filename);
        break;
      case ("BLUR"):
        newFilename = filename + "-bl";
        new Blur(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        gui.getHistogram(filename);
        gui.replaceImage(filename);
        break;
      case ("SHARPEN"):
        newFilename = filename + "-sh";
        new Sharpen(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        gui.getHistogram(filename);
        gui.replaceImage(filename);
        break;
      case ("RED"):
        newFilename = filename + "-gr";
        new RedGreyscale(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        gui.getHistogram(filename);
        gui.replaceImage(filename);
        break;
      case ("GREEN"):
        newFilename = filename + "-gg";
        new GreenGreyscale(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        gui.getHistogram(filename);
        gui.replaceImage(filename);
        break;
      case ("BLUE"):
        newFilename = filename + "-gb";
        new BlueGreyscale(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        gui.getHistogram(filename);
        gui.replaceImage(filename);
        break;
      case ("LUMA"):
        newFilename = filename + "-gl";
        new LumaGreyscale(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        gui.getHistogram(filename);
        gui.replaceImage(filename);
        break;
      case ("INTENSITY"):
        newFilename = filename + "-gi";
        new IntensityGreyscale(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        gui.getHistogram(filename);
        gui.replaceImage(filename);
        break;
      case ("VALUE"):
        newFilename = filename + "-gv";
        new ValueGreyscale(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        gui.getHistogram(filename);
        gui.replaceImage(filename);
        break;
      case ("BRIGHTEN"):
        newFilename = filename + "-br";
        new AdjustBrightness(gui.getIncrement(), filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        gui.getHistogram(filename);
        gui.replaceImage(filename);
        break;
      default:
        break;
    }
  }

  protected Map<String, Pixel[][]> getModelMap(){
    return model.getMap();
  }



}
