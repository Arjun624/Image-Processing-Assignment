package controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
import controller.commands.ImageDownscale;
import controller.commands.IntensityGreyscale;
import controller.commands.LumaGreyscale;
import controller.commands.RedGreyscale;
import controller.commands.Sepia;
import controller.commands.Sharpen;
import controller.commands.ValueGreyscale;
import controller.commands.VerticalFlip;
import model.ImageEditor;
import model.Pixel;
import view.GUIView;
import view.ImageDisplay;

public class ImageControllerGUI implements ImageController, ActionListener {


  private final ImageEditor model;
  private final GUIView gui;

  private String filename;

  ArrayList<String> inputtedEdits;

  public ImageControllerGUI(ImageEditor model, GUIView view) throws IOException {
    this.model = model;
    this.gui = view;
    this.filename = "image";
    this.inputtedEdits = new ArrayList<>();
    gui.setActionListeners(this);
  }

  /**
   * Method that takes in the user arguments and preforms any action.
   *
   * @throws IOException if the program cannot read the input or write the output
   */
  @Override
  public void start() throws IOException {
    gui.displayWelcomeMessage();
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

      gui.showErrorPopup("File " + file.getName() + " not found!");
      return;
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
    model.add(filename, pixels);
    gui.getHistogram(filename, model.getMap());
    gui.renderMessage("Image: " + file.getAbsolutePath() + "\nloaded as: " + filename);

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
    gui.getHistogram(filename, model.getMap());
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
    if (pathname.length() >= 4
            && pathname.substring(pathname.length() - 4)
            .equalsIgnoreCase(".ppm")) {
      savePPM(pathname);
    } else {
      saveOther(pathname);
    }
  }

  private void savePPM(String pathname) throws IOException {
    StringBuilder sb = new StringBuilder();
    try {
      if (!model.getMap().containsKey(filename)) {
        gui.showErrorPopup("Image " + filename + " does not exist or has not been loaded!");
        return;
      }
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("Invalid image name: " + filename);
    }
    try {
      File newFile = new File(pathname);
      gui.renderMessage("Image: " + filename + "\nsaved as: " + pathname);
      if (newFile.createNewFile()) {
        gui.renderMessage("File created: " + newFile.getName());
      } else {
        gui.showErrorPopup("File already exists.");
      }

    } catch (IOException e) {
      //view.renderMessage("An error occurred.");
      e.printStackTrace();
    }

    try {
      FileWriter writer = new FileWriter(pathname);
      sb.append("P3");
      sb.append((System.lineSeparator()));
      sb.append(model.getMap().get(filename)[0].length);
      sb.append(" ");
      sb.append(model.getMap().get(filename).length);
      sb.append((System.lineSeparator()));
      sb.append(model.findTotalValue(filename));
      sb.append(System.lineSeparator());
      for (int row = 0; row < model.getMap().get(filename).length; row++) {
        for (int col = 0; col < model.getMap().get(filename)[0].length; col++) {
          sb.append(model.getMap().get(filename)[row][col].getRed());
          sb.append(" ");
          sb.append((model.getMap().get(filename)[row][col].getGreen()));
          sb.append(" ");
          sb.append((model.getMap().get(filename)[row][col].getBlue()));
          sb.append(" ");
        }
        sb.append((System.lineSeparator()));
      }
      writer.write(sb.toString());
      writer.close();
      // view.renderMessage("Successfully wrote to the file.");
    } catch (IOException e) {
      // view.renderMessage("An error occurred.");
      e.printStackTrace();
    }

  }

  private void saveOther(String pathname) throws IOException {

    if (!model.getMap().containsKey(filename)) {

      return;
    }


    BufferedImage bufferedImage = gui.getBufferedImage(filename, model.getMap());


    ArrayList<String> formats = new ArrayList<>(Arrays.asList(ImageIO.getWriterFormatNames()));
    String type2 = pathname.split("\\.")[1];

    if (formats.contains(type2)) {
      File file = new File(pathname);
      ImageIO.write(bufferedImage, type2, file);
      gui.renderMessage("Image: " + filename + "\nsaved as: " + pathname);
    } else {
      gui.showErrorPopup("Image type not supported");
    }


  }

  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    Object game = e.getActionCommand();
    if (game.equals("Load")) {
      File file = gui.GetLoadFile();
      filename = file.getName();
      try {
        this.loadImage(file.getAbsolutePath(), filename);
        this.replaceImage(filename);
      } catch (Exception ex) {
        gui.showErrorPopup("Error loading file, please check file format.");
      }
    }
    if (game.equals("Picked Filter")) {
      gui.changeLabelText("FILTER");
      gui.addEdit("FILTER", inputtedEdits);
    }
    if (game.equals("Picked Color")) {
      gui.changeLabelText("COLOR");
      gui.addEdit("COLOR", inputtedEdits);
    }
    if (game.equals("Picked Greyscale")) {
      gui.changeLabelText("GREYSCALE");
      gui.addEdit("GREYSCALE", inputtedEdits);
    }
    if (game.equals("Picked Flip")) {
      gui.changeLabelText("FLIP");
      gui.addEdit("FLIP", inputtedEdits);
    }
    if (game.equals("Edit")) {
      try {
        this.editImage();
      } catch (Exception ex) {
        throw new RuntimeException(ex);
      }
    }
    if (game.equals("Down Scale")) {
      if(gui.getDownScaleWidth() == 0){
        gui.setDownScaleWidth();
      }
      if(gui.getDownScaleHeight() == 0){
        gui.setDownScaleHeight();
      }
      if(gui.getDownScaleHeight() != 0 && gui.getDownScaleWidth() != 0){
        gui.addEdit("DOWNSCALE", inputtedEdits);
        gui.changeLabelText("DOWNSCALE");
      }



    }
    if (game.equals("Brightness")) {
      gui.setIncrement();

      try {
        int brightness = gui.getIncrement();
        if (brightness > 0) {
          gui.changeLabelText("BRIGHTEN");
          gui.addEdit("BRIGHTEN", inputtedEdits);
        } else if (brightness < 0) {
          gui.changeLabelText("DIM");
          gui.addEdit("BRIGHTEN", inputtedEdits);
        } else {
          gui.changeLabelText("INVALID");
        }
      } catch (Exception var5) {
        gui.changeLabelText("INVALID");
      }
    }
    if (game.equals("Save")) {
      if (model.getMap().isEmpty()) {
        gui.showErrorPopup("No image loaded!");
      } else {
        File file = gui.getSaveFile();
        try {
          this.saveImage(file.getAbsolutePath(), filename);
        } catch (IOException ex) {
          gui.showErrorPopup("Error saving file, please check file format.");
        }
      }
    }
  }

  private void edit(String command) throws IOException {
    String newFilename;
    switch (command) {
      case ("VERTICAL FLIP"):
        newFilename = filename + "-vf";
        new VerticalFlip(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        this.replaceImage(filename);
        break;
      case ("HORIZONTAL FLIP"):
        newFilename = filename + "-hf";
        new HorizontalFlip(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        this.replaceImage(filename);
        break;
      case ("SEPIA"):
        newFilename = filename + "-sep";
        new Sepia(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        gui.getHistogram(filename, model.getMap());
        this.replaceImage(filename);
        break;
      case ("GREYSCALE"):
        newFilename = filename + "-vf";
        new Greyscale(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        gui.getHistogram(filename, model.getMap());
        this.replaceImage(filename);
        break;
      case ("BLUR"):
        newFilename = filename + "-bl";
        new Blur(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        gui.getHistogram(filename, model.getMap());
        this.replaceImage(filename);
        break;
      case ("SHARPEN"):
        newFilename = filename + "-sh";
        new Sharpen(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        gui.getHistogram(filename, model.getMap());
        this.replaceImage(filename);
        break;
      case ("RED"):
        newFilename = filename + "-gr";
        new RedGreyscale(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        gui.getHistogram(filename, model.getMap());
        this.replaceImage(filename);
        break;
      case ("GREEN"):
        newFilename = filename + "-gg";
        new GreenGreyscale(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        gui.getHistogram(filename, model.getMap());
        this.replaceImage(filename);
        break;
      case ("BLUE"):
        newFilename = filename + "-gb";
        new BlueGreyscale(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        gui.getHistogram(filename, model.getMap());
        this.replaceImage(filename);
        break;
      case ("LUMA"):
        newFilename = filename + "-gl";
        new LumaGreyscale(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        gui.getHistogram(filename, model.getMap());
        this.replaceImage(filename);
        break;
      case ("INTENSITY"):
        newFilename = filename + "-gi";
        new IntensityGreyscale(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        gui.getHistogram(filename, model.getMap());
        this.replaceImage(filename);
        break;
      case ("VALUE"):
        newFilename = filename + "-gv";
        new ValueGreyscale(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        gui.getHistogram(filename, model.getMap());
        this.replaceImage(filename);
        break;
      case ("BRIGHTEN"):
        newFilename = filename + "-br";
        new AdjustBrightness(gui.getIncrement(), filename, newFilename).execute(model,
                new ImageDisplay(System.out));
        filename = newFilename;
        gui.getHistogram(filename, model.getMap());
        this.replaceImage(filename);
        break;
      case ("DOWNSCALE"):
        newFilename = filename + "-ds";
        new ImageDownscale(gui.getDownScaleWidth(), gui.getDownScaleHeight(), filename, newFilename).execute(model,
                new ImageDisplay(System.out));
        filename = newFilename;
        gui.getHistogram(filename, model.getMap());
        this.replaceImage(filename);
        break;
      default:
        break;
    }
  }

  public void replaceImage(String filename) {

    ImageIcon image = new ImageIcon(gui.getBufferedImage(filename, model.getMap()));
    gui.changeImage(image);
  }

  private void editImage() throws IOException {
    if (inputtedEdits.isEmpty()) {
      JOptionPane.showMessageDialog(new JFrame(), "No edits to apply", "Error",
              JOptionPane.ERROR_MESSAGE);

    } else {

      for (String edit : inputtedEdits) {
        this.edit(edit);
      }
      gui.renderMessage("Image edited!" + "\n" + "Edits preformed:" + inputtedEdits.toString());
      inputtedEdits.clear();
      gui.resetButtonsAndLabels();

    }

  }


}
