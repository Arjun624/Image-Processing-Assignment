package controller;

import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

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
import view.GUIView;
import view.ImageDisplay;

/**
 * Represents a controller for a GUI fpr a Image Processor.
 */
public class ImageControllerGUI extends ALoadSave implements ImageController, ActionListener {


  private final ImageEditor model;
  private final GUIView gui;

  private String filename;

  ArrayList<String> inputtedEdits;

  /**
   * Constructs an {@code ImageControllerGUI} based on an inputted model and GUIView.
   *
   * @param model the model
   * @param view  the view
   */
  public ImageControllerGUI(ImageEditor model, GUIView view) {
    this.model = model;
    this.gui = view;
    this.filename = "image";
    this.inputtedEdits = new ArrayList<>();
    gui.setActionListeners(this);
  }

  /**
   * Method that takes in the user arguments and preforms any action.
   */
  @Override
  public void start() {
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

  //loads a ppm image
  private void loadPPM(File file) throws IOException, NoSuchElementException {
    model.add(filename, this.getPPMPixelArray(file, gui));
    this.replaceImage(gui.getBufferedImage(model.getMap().get(filename)));
    gui.displayHistogram(model.getMap().get(filename));
    gui.renderMessage("Image: " + file.getAbsolutePath() + "\nloaded as: " + filename);

  }

  //loads every other type of image
  private void loadOther(File file) throws IOException, NoSuchElementException {
    BufferedImage b;


    try {
      b = ImageIO.read(file);
      this.replaceImage(b);

    } catch (IOException e) {
      throw new NoSuchElementException();
    }

    model.add(filename, this.getOtherPixelArray(b));
    gui.displayHistogram(model.getMap().get(filename));
    gui.renderMessage("Image: " + file.getName() + "\nloaded as: " + filename);
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
    if (pathname.length() >= 4 && pathname.substring(pathname.length()
            - 4).equalsIgnoreCase(".ppm")) {
      savePPM(pathname);
    } else {
      saveOther(pathname);
    }
  }

  //saves a ppm image
  private void savePPM(String pathname) {
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
      this.savePPM(model, filename, pathname);
      gui.renderMessage("Successfully saved file.");
    } catch (IOException e) {
      gui.showErrorPopup("An error occurred.");
      e.printStackTrace();
    }

  }

  //saves every other type of image
  private void saveOther(String pathname) throws IOException {

    if (!model.getMap().containsKey(filename)) {
      return;
    }


    BufferedImage bufferedImage = gui.getBufferedImage(model.getMap().get(filename));


    saveOther(bufferedImage, filename, pathname, gui);

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
      try {
        File file = gui.getLoadFile();
        Objects.requireNonNull(file.getName());
        filename = file.getName();
        this.loadImage(file.getAbsolutePath(), filename);
      } catch (Exception ex) {
        ex.printStackTrace();
        gui.showErrorPopup("Error loading file, please check file format.");
      }
    }
    if (game.equals("ValidCommands")) {
      try {
        Desktop desktop = java.awt.Desktop.getDesktop();
        URI oURL = new URI("https://github.com/Arjun624/Image-Processing-Assignment/blob/master"
                + "/USEME.md");
        desktop.browse(oURL);
        return;
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
    if (game.equals("Documentation")) {
      try {
        Desktop desktop = java.awt.Desktop.getDesktop();
        URI oURL = new URI("https://github.com/Arjun624/Image-Processing-Assignment/blob/master"
                + "/README.md");
        desktop.browse(oURL);
        return;
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
    if (game.equals("HowTo")) {
      gui.renderMessage("1. Load an image.\n2. Select an image edit(s).\n3. Click Edit.\n4. " +
              "Save the image.");
      return;
    }
    if (game.equals("Quit")) {
      System.exit(0);
      return;
    }
    if (model.getMap().isEmpty()) {
      gui.showErrorPopup("No image loaded");
      return;
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
      if (gui.getDownScaleWidth() == 0) {
        gui.setDownScaleWidth(model.getMap().get(filename)[0].length);
      }
      if (gui.getDownScaleHeight() == 0) {
        gui.setDownScaleHeight(model.getMap().get(filename).length);
      }
      if (gui.getDownScaleHeight() != 0 && gui.getDownScaleWidth() != 0) {
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

  //processes the inpuuted edit in order to edit an image
  private void edit(String command) throws IOException {
    String newFilename;
    switch (command) {
      case ("VERTICAL FLIP"):
        newFilename = filename + "-vf";
        new VerticalFlip(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        gui.displayHistogram(model.getMap().get(filename));
        this.replaceImage(gui.getBufferedImage(model.getMap().get(filename)));
        break;
      case ("HORIZONTAL FLIP"):
        newFilename = filename + "-hf";
        new HorizontalFlip(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        gui.displayHistogram(model.getMap().get(filename));
        this.replaceImage(gui.getBufferedImage(model.getMap().get(filename)));
        break;
      case ("SEPIA"):
        newFilename = filename + "-sep";
        new Sepia(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        gui.displayHistogram(model.getMap().get(filename));
        this.replaceImage(gui.getBufferedImage(model.getMap().get(filename)));
        break;
      case ("GREYSCALE"):
        newFilename = filename + "-vf";
        new Greyscale(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        gui.displayHistogram(model.getMap().get(filename));
        this.replaceImage(gui.getBufferedImage(model.getMap().get(filename)));
        break;
      case ("BLUR"):
        newFilename = filename + "-bl";
        new Blur(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        gui.displayHistogram(model.getMap().get(filename));
        this.replaceImage(gui.getBufferedImage(model.getMap().get(filename)));
        break;
      case ("SHARPEN"):
        newFilename = filename + "-sh";
        new Sharpen(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        gui.displayHistogram(model.getMap().get(filename));
        this.replaceImage(gui.getBufferedImage(model.getMap().get(filename)));
        break;
      case ("RED"):
        newFilename = filename + "-gr";
        new RedGreyscale(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        gui.displayHistogram(model.getMap().get(filename));
        this.replaceImage(gui.getBufferedImage(model.getMap().get(filename)));
        break;
      case ("GREEN"):
        newFilename = filename + "-gg";
        new GreenGreyscale(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        gui.displayHistogram(model.getMap().get(filename));
        this.replaceImage(gui.getBufferedImage(model.getMap().get(filename)));
        break;
      case ("BLUE"):
        newFilename = filename + "-gb";
        new BlueGreyscale(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        gui.displayHistogram(model.getMap().get(filename));
        this.replaceImage(gui.getBufferedImage(model.getMap().get(filename)));
        break;
      case ("LUMA"):
        newFilename = filename + "-gl";
        new LumaGreyscale(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        gui.displayHistogram(model.getMap().get(filename));
        this.replaceImage(gui.getBufferedImage(model.getMap().get(filename)));
        break;
      case ("INTENSITY"):
        newFilename = filename + "-gi";
        new IntensityGreyscale(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        gui.displayHistogram(model.getMap().get(filename));
        this.replaceImage(gui.getBufferedImage(model.getMap().get(filename)));
        break;
      case ("VALUE"):
        newFilename = filename + "-gv";
        new ValueGreyscale(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        gui.displayHistogram(model.getMap().get(filename));
        this.replaceImage(gui.getBufferedImage(model.getMap().get(filename)));
        break;
      case ("BRIGHTEN"):
        newFilename = filename + "-br";
        new AdjustBrightness(gui.getIncrement(), filename, newFilename).execute(model,
                new ImageDisplay(System.out));
        filename = newFilename;
        gui.displayHistogram(model.getMap().get(filename));
        this.replaceImage(gui.getBufferedImage(model.getMap().get(filename)));
        break;
      case ("DOWNSCALE"):
        newFilename = filename + "-ds";
        new ImageDownscale(gui.getDownScaleWidth(), gui.getDownScaleHeight(), filename,
                newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        gui.displayHistogram(model.getMap().get(filename));
        this.replaceImage(gui.getBufferedImage(model.getMap().get(filename)));
        break;
      default:
        break;
    }
  }

  /**
   * replaces an Image with a new one.
   *
   * @param oldImage the image to be replaced.
   */
  public void replaceImage(BufferedImage oldImage) throws IOException {


    int width = Math.max(600, oldImage.getWidth());
    int height = Math.max(600, oldImage.getHeight());
    BufferedImage reSizedImage = new BufferedImage(width, height, oldImage.getType());
    Graphics2D g = reSizedImage.createGraphics();
    g.drawImage(oldImage, 0, 0, width, height, null);
    ImageIcon image = new ImageIcon(reSizedImage);
    gui.changeImage(image);
  }

  //edits an image
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
      gui.reset();

    }

  }


}
