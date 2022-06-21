package controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import javax.imageio.ImageIO;
import javax.swing.*;

import controller.commands.Blur;
import controller.commands.Greyscale;
import controller.commands.HorizontalFlip;
import controller.commands.Sepia;
import controller.commands.Sharpen;
import controller.commands.VerticalFlip;
import model.ImageEditor;
import model.Pixel;
import view.GUIView;
import view.ImageDisplay;

public class ImageProcessingGUI extends JFrame implements ActionListener {
  private ImageEditor model;
  private GUIView view;
  private int compNum;
  private String filename;

  ArrayList<String> edits;
  private JPanel picturePanel = new JPanel();
  private JPanel flipCommands = new JPanel();
  private JPanel filterCommands = new JPanel();
  private JPanel colorCommands = new JPanel();
  private JPanel brightnessCommands = new JPanel();
  private JPanel loadAndSave = new JPanel();
  private JLabel specificGreyscaleCommands = new JLabel();
  private JButton loadButton = new JButton("Load Image");
  private JButton saveButton = new JButton("Save Image");
  private JButton helpButton = new JButton("Help!");
  private JButton editImageButton = new JButton("Edit Image");
  private JButton chooseGreyscaleButton = new JButton("Choose Greyscale");
  private JButton chooseColorButton = new JButton("Choose Color Combination");
  private JButton chooseFilterButton = new JButton("Choose Filter");
  private JButton chooseFlipButton = new JButton("Choose Orientation or Size");
  private JButton adjustBrightnessButton = new JButton("Select Brightness Increment");
  private JLabel incrementLabel = new JLabel("Increment: N/A");
  private JLabel chosenFlip = new JLabel("\tNone selected");
  private JLabel chosenColor = new JLabel("\tNone selected");
  private JLabel chosenGreyScale = new JLabel("\tNone selected");
  private JLabel chosenFilter = new JLabel("\tNone selected");
  private JPanel allCommands = new JPanel();
  String[] greyScale = new String[]{"NONE", "RED", "GREEN", "BLUE", "LUMA", "VALUE", "INTENSITY"};
  final JComboBox<String> dropDownGreyscale;
  String[] colorCombinations;
  final JComboBox<String> dropDownColorCombinations;
  String[] filters;
  final JComboBox<String> dropDownFilters;
  String[] orientationAndSize;
  final JComboBox<String> dropOrientationAndSize;

  public ImageProcessingGUI(ImageEditor model, GUIView view) throws IOException {
    this.model = model;
    this.view = view;
    this.compNum = 0;
    this.filename = "test";
    loadPic();
    edits = new ArrayList<>();
    this.dropDownGreyscale = new JComboBox(this.greyScale);
    this.colorCombinations = new String[]{"NONE", "SEPIA", "GREYSCALE"};
    this.dropDownColorCombinations = new JComboBox(this.colorCombinations);
    this.filters = new String[]{"NONE", "BLUR", "SHARPEN"};
    this.dropDownFilters = new JComboBox(this.filters);
    this.orientationAndSize = new String[]{"NONE", "VERTICAL FLIP", "HORIZONTAL FLIP"};
    this.dropOrientationAndSize = new JComboBox(this.orientationAndSize);
    setDefaultLookAndFeelDecorated(true);
    this.setTitle("ImageProcessing");
    this.setDefaultCloseOperation(3);
    this.setSize(400, 400);
    this.setResizable(false);
    Container c = this.getContentPane();
    BoxLayout bl = new BoxLayout(c, 0);
    c.setLayout(bl);
    this.flipCommands.setLayout(new GridLayout(3, 1));
    this.flipCommands.setBorder(BorderFactory.createTitledBorder("Change Orientation or Size"));
    this.filterCommands.setLayout(new GridLayout(3, 1));
    this.filterCommands.setBorder(BorderFactory.createTitledBorder("Filter Image"));
    this.colorCommands.setLayout(new GridLayout(3, 1));
    this.colorCommands.setBorder(BorderFactory.createTitledBorder("Choose Color Combination"));
    this.loadAndSave.setLayout(new GridLayout(3, 1));
    this.brightnessCommands.setLayout(new GridLayout(2, 1));
    this.brightnessCommands.setBorder(BorderFactory.createTitledBorder("Adjust Brightness"));
    this.specificGreyscaleCommands.setLayout(new GridLayout(3, 1));
    this.specificGreyscaleCommands.setBorder(BorderFactory.createTitledBorder("Choose Greyscale"));
    this.allCommands.setLayout(new GridLayout(7, 1));
    this.allCommands.add(this.loadAndSave);
    this.allCommands.add(this.colorCommands);
    this.allCommands.add(this.flipCommands);
    this.allCommands.add(this.filterCommands);
    this.allCommands.add(this.brightnessCommands);
    this.allCommands.add(this.specificGreyscaleCommands);
    this.allCommands.add(this.editImageButton);
    c.add(this.allCommands);
    c.add(this.picturePanel, "North");
    this.instantiateButtons();
    this.pack();
    this.setVisible(true);

  }

  public void instantiateButtons() {
    this.loadButton.addActionListener(this);
    this.loadButton.setActionCommand("Load");
    this.saveButton.addActionListener(this);
    this.saveButton.setActionCommand("Save");
    this.chooseFilterButton.addActionListener(this);
    this.chooseFilterButton.setActionCommand("Picked Filter");
    this.chooseColorButton.addActionListener(this);
    this.chooseColorButton.setActionCommand("Picked Color");
    this.chooseGreyscaleButton.addActionListener(this);
    this.chooseGreyscaleButton.setActionCommand("Picked Greyscale");
    this.chooseFlipButton.addActionListener(this);
    this.chooseFlipButton.setActionCommand("Picked Flip");
    this.editImageButton.addActionListener(this);
    this.editImageButton.setActionCommand("Edit");
    this.helpButton.addActionListener(this);
    this.helpButton.setActionCommand("Help");
    this.adjustBrightnessButton.addActionListener(this);
    this.adjustBrightnessButton.setActionCommand("Brightness");
    this.dropDownGreyscale.setMaximumSize(this.dropDownGreyscale.getPreferredSize());
    this.dropDownGreyscale.setAlignmentX(0.5F);
    System.out.println((String)this.dropDownGreyscale.getItemAt(this.dropDownGreyscale.getSelectedIndex()));
    this.dropDownColorCombinations.setMaximumSize(this.dropDownColorCombinations.getPreferredSize());
    this.dropDownColorCombinations.setAlignmentX(0.5F);
    System.out.println((String)this.dropDownColorCombinations.getItemAt(this.dropDownColorCombinations.getSelectedIndex()));
    this.dropDownFilters.setMaximumSize(this.dropDownFilters.getPreferredSize());
    this.dropDownFilters.setAlignmentX(0.5F);
    System.out.println((String)this.dropDownFilters.getItemAt(this.dropDownFilters.getSelectedIndex()));
    this.dropOrientationAndSize.setMaximumSize(this.dropOrientationAndSize.getPreferredSize());
    this.dropOrientationAndSize.setAlignmentX(0.5F);
    System.out.println((String)this.dropOrientationAndSize.getItemAt(this.dropOrientationAndSize.getSelectedIndex()));
    this.loadAndSave.add(this.helpButton);
    this.loadAndSave.add(this.loadButton);
    this.loadAndSave.add(this.saveButton);
    this.filterCommands.add(this.dropDownFilters);
    this.filterCommands.add(this.chooseFilterButton);
    this.filterCommands.add(this.chosenFilter);
    this.colorCommands.add(this.dropDownColorCombinations);
    this.colorCommands.add(this.chooseColorButton);
    this.colorCommands.add(this.chosenColor);
    this.flipCommands.add(this.dropOrientationAndSize);
    this.flipCommands.add(this.chooseFlipButton);
    this.flipCommands.add(this.chosenFlip);
    this.brightnessCommands.add(this.adjustBrightnessButton);
    this.brightnessCommands.add(this.incrementLabel);
    this.specificGreyscaleCommands.add(this.dropDownGreyscale);
    this.specificGreyscaleCommands.add(this.chooseGreyscaleButton);
    this.specificGreyscaleCommands.add(this.chosenGreyScale);
    this.picturePanel.add(new JLabel(new ImageIcon("res/battlefield.jpg")));

//    buttonA.setEnabled(false); //Disables the buttons
//    buttonB.setEnabled(false);
//    buttonC.setEnabled(false);
//    buttonD.setEnabled(false);


  }


  public void actionPerformed(ActionEvent e) {
    Object game = e.getActionCommand();
    if (game.equals("Picked Filter")) {
      this.changeLabelText(dropDownFilters, chosenFilter);
      addEdit(chosenFilter);
    }
    if (game.equals("Picked Color")) {
      this.changeLabelText(dropDownColorCombinations, chosenColor);
      addEdit(chosenColor);
    }
    if (game.equals("Picked Greyscale")) {
      this.changeLabelText(dropDownGreyscale, chosenGreyScale);
      addEdit(chosenGreyScale);
    }
    if (game.equals("Picked Flip")) {
      this.changeLabelText(dropOrientationAndSize, chosenFlip);
      addEdit(chosenFlip);
    }
    if (game.equals("Edit")) {
      try {
        editImage();
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    }
    if (game.equals("Brightness")) {
      String increment = JOptionPane.showInputDialog(new JFrame(), "Enter an increment. Positive to increase brightness and negative to decrease brightness");

      try {
        Integer.parseInt(increment);
        this.incrementLabel.setText(increment);
      } catch (Exception var5) {
        this.incrementLabel.setText("Invalid Increment, Please try again.");
      }
    }


  }

  private void changeLabelText(JComboBox<String> dropDown, JLabel label) {
    label.setText("\tSelected: " + dropDown.getItemAt(dropDown.getSelectedIndex()));
  }

  public void loadPic() throws IOException {
    BufferedImage b;
    try {
      b = ImageIO.read(new File("res/battlefield.jpg"));
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
  }

  public void editImage() throws IOException {
    for (int i = 0; i < edits.size(); i++) {
      edit(edits.get(i));
    }
    edits.clear();
  }

  public void addEdit(JLabel command) {
    if (!command.toString().equalsIgnoreCase("NONE")) {
      edits.add(command.getText().substring(11));
    }
  }

  public void edit(String command) throws IOException {
    String newFilename;
    switch (command) {
      case ("VERTICAL FLIP"):
        newFilename = filename + "-vf";
        new VerticalFlip(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        replaceImage(filename);
        break;
      case ("HORIZONTAL FLIP"):
        newFilename = filename + "-hf";
        new HorizontalFlip(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        replaceImage(filename);
        break;
      case ("SEPIA"):
        newFilename = filename + "-sep";
        new Sepia(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        replaceImage(filename);
        break;
      case ("GREYSCALE"):
        newFilename = filename + "-vf";
        new Greyscale(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        replaceImage(filename);
        break;
      case ("BLUR"):
        newFilename = filename + "-bl";
        new Blur(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        replaceImage(filename);
        break;
      case ("SHARPEN"):
        newFilename = filename + "-sh";
        new Sharpen(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        replaceImage(filename);
        break;
      default:
        break;
    }
  }

  public void replaceImage(String filename) {
    int length = model.getMap().get(filename).length;
    int width = model.getMap().get(filename)[0].length;
    BufferedImage bufferedImage = new BufferedImage(width,
            length, BufferedImage.TYPE_INT_RGB);
    for (int row = 0; row < length; row++) {
      for (int col = 0; col < width; col++) {
        Color c = new Color(model.getMap().get(filename)[row][col].getRed(),
                model.getMap().get(filename)[row][col].getGreen(),
                model.getMap().get(filename)[row][col].getBlue(),
                model.getMap().get(filename)[row][col].getAlpha());
        bufferedImage.setRGB(col, row, c.getRGB());
      }
    }
    ImageIcon image = new ImageIcon(bufferedImage);
    picturePanel.getComponent(compNum).setVisible(false);
    compNum += 1;
    picturePanel.add(new JLabel(image));
  }

  public void getHistogram(String filename){
    Pixel[][] pixels = model.getMap().get(filename);
    int[] red = new int[256];
    int[] green = new int[256];
    int[] blue = new int[256];
    int[] intensity = new int[256];
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[0].length; j++) {
        int pixelIntensity = (pixels[i][j].getRed() + pixels[i][j].getGreen() + pixels[i][j].getBlue())/3;
        red[pixels[i][j].getRed()] += 1;
        green[pixels[i][j].getGreen()] += 1;
        blue[pixels[i][j].getBlue()] += 1;
        intensity[pixelIntensity] += 1;
      }
    }
  }

}