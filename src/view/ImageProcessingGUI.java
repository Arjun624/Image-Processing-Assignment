package view;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JMenuItem;
import javax.swing.JComboBox;
import javax.swing.WindowConstants;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;


import model.Pixel;

/**
 * Represents a GUI view for an image processor.
 */
public class ImageProcessingGUI extends JFrame implements GUIView {


  private int brightness;

  private int downscaleHeight;

  private int downscaleWidth;
  ArrayList<String> edits;
  private JPanel picturePanel;
  private JPanel flipCommands;
  private JPanel filterCommands;
  private JPanel colorCommands;
  private JPanel brightnessCommands;
  private JPanel specificGreyscaleCommands;

  private JPanel downScaleCommands;
  private JPanel allCommands;

  private JLabel incrementLabel;

  private JLabel downScaleLabel;
  private JLabel chosenFlip;
  private JLabel chosenColor;
  private JLabel chosenGreyScale;
  private JLabel chosenFilter;

  private JMenuItem load;

  private JMenuItem save;

  private JMenuItem howTo;

  private JButton editImageButton;
  private JButton chooseGreyscaleButton;
  private JButton chooseColorButton;
  private JButton chooseFilterButton;
  private JButton chooseFlipButton;
  private JButton adjustBrightnessButton;

  private JButton downScaleButton;

  private JComboBox<String> dropDownGreyscale;
  private JComboBox<String> dropDownColorCombinations;
  private JComboBox<String> dropDownFilters;
  private JComboBox<String> dropDownFlips;

  private final ImageIcon[] imageBoxes;

  private JMenuItem validCommands;
  private JMenuItem documentation;

  private JMenuItem quit;


  /**
   * Constructs a default {@code ImageProcessingGUI}.
   */
  public ImageProcessingGUI() {
    this.instantiateLabels();
    this.instantiatePanelsAndLayout();
    this.instantiateButtons();
    this.instantiateDropDowns();
    this.instantiateMenuBar();
    this.brightness = 0;
    this.downscaleHeight = 0;
    this.downscaleWidth = 0;
    this.edits = new ArrayList<>();
    this.imageBoxes =
            new ImageIcon[]{new ImageIcon("none"), new ImageIcon("histogram")};
    setDefaultLookAndFeelDecorated(true);
    this.setTitle("ImageProcessing");
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setSize(950, 950);
    this.setResizable(false);
    Container c = this.getContentPane();
    BoxLayout bl = new BoxLayout(c, BoxLayout.X_AXIS);
    c.setLayout(bl);
    c.add(this.allCommands);
    c.add(this.picturePanel);
    this.addElementsToPanels();
    this.displayImage();
    this.pack();
    this.setVisible(true);
  }

  /**
   * Instantiates the panels and layout of the GUI.
   */
  private void instantiatePanelsAndLayout() {
    this.flipCommands = new JPanel();
    this.filterCommands = new JPanel();
    this.colorCommands = new JPanel();
    this.brightnessCommands = new JPanel();
    this.allCommands = new JPanel();
    this.specificGreyscaleCommands = new JPanel();
    this.picturePanel = new JPanel();
    this.downScaleCommands = new JPanel();

    this.flipCommands.setLayout(new GridLayout(3, 1));
    this.flipCommands.setBorder(BorderFactory.createTitledBorder("Flip Image"));
    this.filterCommands.setLayout(new GridLayout(3, 1));
    this.filterCommands.setBorder(BorderFactory.createTitledBorder("Filter Image"));
    this.colorCommands.setLayout(new GridLayout(3, 1));
    this.colorCommands.setBorder(BorderFactory.createTitledBorder("Choose Color Combination"));
    this.brightnessCommands.setLayout(new GridLayout(2, 1));
    this.brightnessCommands.setBorder(BorderFactory.createTitledBorder("Adjust Brightness"));
    this.specificGreyscaleCommands.setLayout(new GridLayout(3, 1));
    this.specificGreyscaleCommands.setBorder(BorderFactory.createTitledBorder("Choose Greyscale"));
    this.downScaleCommands.setLayout(new GridLayout(2, 1));
    this.downScaleCommands.setBorder(BorderFactory.createTitledBorder("Down Scale Image"));
    this.picturePanel.setBorder(BorderFactory.createTitledBorder("Loaded Image and Histogram"));
    this.picturePanel.setLayout(new GridLayout(1, 0, 10, 10));
    this.allCommands.setLayout(new GridLayout(7, 1));
  }

  /**
   * Instantiates the labels of the GUI.
   */
  private void instantiateLabels() {
    this.incrementLabel = new JLabel("Increment: N/A");
    this.chosenFlip = new JLabel("\tNone selected");
    this.chosenColor = new JLabel("\tNone selected");
    this.chosenGreyScale = new JLabel("\tNone selected");
    this.chosenFilter = new JLabel("\tNone selected");
    this.downScaleLabel = new JLabel("Please select a width and a height");
  }


  /**
   * Instantiates the buttons  of the GUI.
   */
  public void instantiateButtons() {
    this.editImageButton = new JButton("Edit Image");
    chooseGreyscaleButton = new JButton("Choose Greyscale");
    chooseColorButton = new JButton("Choose Color Combination");
    chooseFilterButton = new JButton("Choose Filter");
    chooseFlipButton = new JButton("Choose Orientation or Size");
    adjustBrightnessButton = new JButton("Select Brightness Increment");
    this.downScaleButton = new JButton("Select Down Scale Size");

    this.editImageButton.setActionCommand("Edit");
    chooseGreyscaleButton.setActionCommand("Picked Greyscale");
    chooseColorButton.setActionCommand("Picked Color");
    chooseFilterButton.setActionCommand("Picked Filter");
    chooseFlipButton.setActionCommand("Picked Flip");
    adjustBrightnessButton.setActionCommand("Brightness");
    this.downScaleButton.setActionCommand("Down Scale");


  }

  /**
   * Adds elements to the panels of the GUI.
   */
  private void addElementsToPanels() {
    this.filterCommands.add(this.dropDownFilters);
    this.filterCommands.add(chooseFilterButton);
    this.filterCommands.add(this.chosenFilter);
    this.colorCommands.add(this.dropDownColorCombinations);
    this.colorCommands.add(chooseColorButton);
    this.colorCommands.add(this.chosenColor);
    this.flipCommands.add(this.dropDownFlips);
    this.flipCommands.add(chooseFlipButton);
    this.flipCommands.add(this.chosenFlip);
    this.brightnessCommands.add(adjustBrightnessButton);
    this.brightnessCommands.add(this.incrementLabel);
    this.specificGreyscaleCommands.add(this.dropDownGreyscale);
    this.specificGreyscaleCommands.add(chooseGreyscaleButton);
    this.specificGreyscaleCommands.add(this.chosenGreyScale);
    this.downScaleCommands.add(this.downScaleButton);
    this.downScaleCommands.add(this.downScaleLabel);

    this.allCommands.add(this.colorCommands);
    this.allCommands.add(this.flipCommands);
    this.allCommands.add(this.filterCommands);
    this.allCommands.add(this.brightnessCommands);
    this.allCommands.add(this.specificGreyscaleCommands);
    this.allCommands.add(this.downScaleCommands);
    this.allCommands.add(this.editImageButton);

  }


  @Override
  public void setActionListeners(ActionListener listener) {
    this.load.addActionListener(listener);
    this.save.addActionListener(listener);
    this.howTo.addActionListener(listener);
    this.quit.addActionListener(listener);
    this.validCommands.addActionListener(listener);
    this.documentation.addActionListener(listener);
    this.chooseFilterButton.addActionListener(listener);
    this.chooseColorButton.addActionListener(listener);
    this.chooseGreyscaleButton.addActionListener(listener);
    this.chooseFlipButton.addActionListener(listener);
    this.editImageButton.addActionListener(listener);
    this.adjustBrightnessButton.addActionListener(listener);
    this.downScaleButton.addActionListener(listener);
  }

  /**
   * Displays the images and histogram on the GUI.
   */
  protected void displayImage() {


    this.picturePanel.removeAll();

    JLabel[] imageLabel = new JLabel[imageBoxes.length];
    JScrollPane[] imageScrollPane = new JScrollPane[imageBoxes.length];

    for (int i = 0; i < imageLabel.length; i++) {
      imageLabel[i] = new JLabel();
      imageScrollPane[i] = new JScrollPane(imageLabel[i]);
      imageLabel[i].setIcon(imageBoxes[i]);
      imageScrollPane[i].setPreferredSize(new Dimension(600, 600));
      this.picturePanel.add(imageScrollPane[i]);
    }

    this.picturePanel.repaint();
    this.picturePanel.revalidate();


  }

  /**
   * Instantiates the dropdowns of the GUI.
   */
  private void instantiateDropDowns() {
    String[] greyScale = new String[]{"NONE", "RED", "GREEN", "BLUE", "LUMA", "VALUE", "INTENSITY"};
    this.dropDownGreyscale = new JComboBox<>(greyScale);
    String[] colorCombinations = new String[]{"NONE", "SEPIA", "GREYSCALE"};
    this.dropDownColorCombinations = new JComboBox<>(colorCombinations);
    String[] filters = new String[]{"NONE", "BLUR", "SHARPEN"};
    this.dropDownFilters = new JComboBox<>(filters);
    String[] flips = new String[]{"NONE", "VERTICAL FLIP", "HORIZONTAL FLIP"};
    this.dropDownFlips = new JComboBox<>(flips);
    this.dropDownGreyscale.setMaximumSize(this.dropDownGreyscale.getPreferredSize());
    this.dropDownGreyscale.setAlignmentX(0.5F);
    this.dropDownColorCombinations.setMaximumSize(
            this.dropDownColorCombinations.getPreferredSize());
    this.dropDownColorCombinations.setAlignmentX(0.5F);
    this.dropDownFilters.setMaximumSize(this.dropDownFilters.getPreferredSize());
    this.dropDownFilters.setAlignmentX(0.5F);
    this.dropDownFlips.setMaximumSize(this.dropDownFlips.getPreferredSize());
    this.dropDownFlips.setAlignmentX(0.5F);
  }

  /**
   * Instantiates the menu bar of the GUI.
   */
  private void instantiateMenuBar() {
    JMenuBar menuBar = new JMenuBar();

    JMenu file = new JMenu("File");
    load = new JMenuItem("Load");
    load.setActionCommand("Load");

    save = new JMenuItem("Save");
    save.setActionCommand("Save");

    quit = new JMenuItem("Quit");
    quit.setActionCommand("Quit");
    file.add(load);
    file.add(save);
    file.add(quit);

    JMenu help = new JMenu("Help");
    howTo = new JMenuItem("How to use");
    howTo.setActionCommand("HowTo");

    validCommands = new JMenuItem("Information on Valid Commands");
    validCommands.setActionCommand("ValidCommands");

    documentation = new JMenuItem("Full Documentation");
    documentation.setActionCommand("Documentation");
    help.add(howTo);
    help.add(validCommands);
    help.add(documentation);


    menuBar.add(file);
    menuBar.add(help);
    setJMenuBar(menuBar);
  }

  @Override
  public void changeLabelText(String type) {
    if (type.equals("FLIP")) {
      chosenFlip.setText("\tSelected: "
              + dropDownFlips.getItemAt(dropDownFlips.getSelectedIndex()));
    }
    if (type.equals("COLOR")) {
      chosenColor.setText("\tSelected: "
              + dropDownColorCombinations.getItemAt(dropDownColorCombinations.getSelectedIndex()));
    }
    if (type.equals("GREYSCALE")) {
      chosenGreyScale.setText("\tSelected: "
              + dropDownGreyscale.getItemAt(dropDownGreyscale.getSelectedIndex()));
    }
    if (type.equals("FILTER")) {
      chosenFilter.setText("\tSelected: "
              + dropDownFilters.getItemAt(dropDownFilters.getSelectedIndex()));
    }
    if (type.equals("BRIGHTEN")) {
      incrementLabel.setText("Brightness increment: " + brightness);
    }
    if (type.equals("DIM")) {
      incrementLabel.setText("Dimness increment: " + brightness);
    }
    if (type.equals("INVALID")) {
      incrementLabel.setText("Invalid Increment, Please try again.");
    }
    if (type.equals("DOWNSCALE")) {
      downScaleLabel.setText("Width: " + downscaleWidth + "," + " Height: " + downscaleHeight);
    }
  }

  @Override
  public void setIncrement() {
    String increment = JOptionPane.showInputDialog(new JFrame(),
            "Enter an increment. Positive "
                    + "to increase brightness and negative to decrease brightness");
    try {
      brightness = Integer.parseInt(increment);
    } catch (Exception ex) {
      incrementLabel.setText("Invalid Increment, Please try again.");
    }
  }

  @Override
  public int getIncrement() {
    return brightness;
  }

  @Override
  public void addEdit(String type, ArrayList<String> inputtedEdits) {
    if (type.equals("FLIP")) {
      inputtedEdits.add(this.getLabelText(chosenFlip));
      chooseFlipButton.setEnabled(false);
      dropDownFlips.setEnabled(false);
    }
    if (type.equals("COLOR")) {
      inputtedEdits.add(this.getLabelText(chosenColor));
      chooseColorButton.setEnabled(false);
      dropDownColorCombinations.setEnabled(false);
    }
    if (type.equals("GREYSCALE")) {
      inputtedEdits.add(this.getLabelText(chosenGreyScale));
      chooseGreyscaleButton.setEnabled(false);
      dropDownGreyscale.setEnabled(false);
    }
    if (type.equals("FILTER")) {
      inputtedEdits.add(this.getLabelText(chosenFilter));
      chooseFilterButton.setEnabled(false);
      dropDownFilters.setEnabled(false);
    }
    if (type.equals("BRIGHTEN")) {
      inputtedEdits.add("BRIGHTEN");
      adjustBrightnessButton.setEnabled(false);
    }
    if (type.equals("DOWNSCALE")) {
      inputtedEdits.add("DOWNSCALE");
      downScaleButton.setEnabled(false);
    }

  }

  /**
   * Gets the label text of a specific label.
   *
   * @param label the label to have its text returned
   * @return the text of the inputted label
   */
  private String getLabelText(JLabel label) {
    if (!label.toString().equalsIgnoreCase("NONE")) {
      return label.getText().substring(11);
    }
    return null;

  }

  @Override
  public void displayHistogram(Pixel[][] pixels) {

    int[] red = new int[256];
    int[] green = new int[256];
    int[] blue = new int[256];
    int[] intensity = new int[256];
    for (Pixel[] pixel : pixels) {
      for (int j = 0; j < pixels[0].length; j++) {
        int pixelIntensity = (pixel[j].getRed()
                + pixel[j].getGreen()
                + pixel[j].getBlue()) / 3;
        red[pixel[j].getRed()] += 1;
        green[pixel[j].getGreen()] += 1;
        blue[pixel[j].getBlue()] += 1;
        intensity[pixelIntensity] += 1;
      }
    }
    ImageIcon image = new ImageIcon(makeHistogram(red, green, blue, intensity));
    imageBoxes[1] = image;
    displayImage();

  }

  @Override
  public BufferedImage getBufferedImage(Pixel[][] pixels) {
    int length = pixels.length;
    int width = pixels[0].length;
    BufferedImage bufferedImage = new BufferedImage(width, length, BufferedImage.TYPE_INT_RGB);
    for (int row = 0; row < length; row++) {
      for (int col = 0; col < width; col++) {
        Color c = new Color(pixels[row][col].getRed(),
                pixels[row][col].getGreen(),
                pixels[row][col].getBlue(),
                pixels[row][col].getAlpha());
        bufferedImage.setRGB(col, row, c.getRGB());
      }
    }
    return bufferedImage;
  }

  /**
   * Makes a buffered image out of the intensity of the RGB and intensity of an image.
   *
   * @param red       the red values of an image
   * @param green     the green values of an image
   * @param blue      the blue values of an image
   * @param intensity the intensity of each RBG value
   * @return a buffered image of the histogram to be displayed on the GUI.
   */
  private BufferedImage makeHistogram(int[] red, int[] green, int[] blue, int[] intensity) {
    BufferedImage bufferedImage = new BufferedImage(600, 950,
            BufferedImage.TYPE_INT_RGB);
    Graphics2D graph = bufferedImage.createGraphics();
    drawLines(graph, red, Color.red);
    drawLines(graph, green, Color.green);
    drawLines(graph, blue, Color.blue);
    drawLines(graph, intensity, Color.gray);
    return bufferedImage;
  }

  /**
   * Draws the lines of the histogram.
   *
   * @param graph the graphics used to draw the lines on the buffered image
   * @param nums  the array of numbers to be displayed
   * @param c     the color of the line
   */
  private void drawLines(Graphics2D graph, int[] nums, Color c) {
    graph.setColor(c);
    for (int i = 0; i < 254; i++) {
      for (int j = 0; j < 254; j++) {
        graph.drawLine((int) (i * 2.34), 950 - nums[i] / 2, (int) ((i + 1) * 2.34), 950
                - nums[i + 1] / 2);
      }
    }
  }


  @Override
  public void showErrorPopup(String errorMessage) {
    JOptionPane.showMessageDialog(new JFrame(), errorMessage, "Error",
            JOptionPane.ERROR_MESSAGE);
  }


  @Override
  public void renderMessage(String message) {
    JOptionPane.showMessageDialog(new JFrame(), message, "Message",
            JOptionPane.INFORMATION_MESSAGE);
  }

  @Override
  public void displayWelcomeMessage() {
    StringBuilder welcomeMessage = new StringBuilder("Welcome to the Image Program!" +
            "\n" + "Please select an image to load from the file menu.");
    welcomeMessage.append("\n" + "Valid edits:");
    welcomeMessage.append("\n" + "\t" + "1. Flip Horizontal");
    welcomeMessage.append("\n" + "\t" + "2. Flip Vertical");
    welcomeMessage.append("\n" + "\t" + "3. Sepia");
    welcomeMessage.append("\n" + "\t" + "4. Grayscale");
    welcomeMessage.append("\n" + "\t" + "5. Adjust Brightness");
    welcomeMessage.append("\n" + "\t" + "6. Blur");
    welcomeMessage.append("\n" + "\t" + "7. Sharpen");
    welcomeMessage.append("\n" + "\t" + "8. Red Greyscale");
    welcomeMessage.append("\n" + "\t" + "9. Green Greyscale");
    welcomeMessage.append("\n" + "\t" + "10. Blue Greyscale");
    welcomeMessage.append("\n" + "\t" + "11. Luma Greyscale");
    welcomeMessage.append("\n" + "\t" + "12. Value Greyscale");
    welcomeMessage.append("\n" + "\t" + "13. Intensity Greyscale");
    welcomeMessage.append("\n" + "\t" + "14. Downscale Image");

    JOptionPane.showMessageDialog(new JFrame(), welcomeMessage, "Welcome",
            JOptionPane.INFORMATION_MESSAGE);
  }

  @Override
  public void changeImage(ImageIcon image) {
    imageBoxes[0] = image;
    displayImage();
  }

  @Override
  public File getSaveFile() {
    JFileChooser chooser = new JFileChooser();
    chooser.showSaveDialog(this);
    return chooser.getSelectedFile();
  }

  @Override
  public File getLoadFile() {
    JFileChooser chooser = new JFileChooser();
    chooser.showOpenDialog(this);
    return chooser.getSelectedFile();
  }


  @Override
  public void reset() {
    this.dropDownColorCombinations.setEnabled(true);
    this.chooseColorButton.setEnabled(true);
    this.dropDownGreyscale.setEnabled(true);
    this.chooseGreyscaleButton.setEnabled(true);
    this.dropDownFilters.setEnabled(true);
    this.chooseFilterButton.setEnabled(true);
    this.dropDownFlips.setEnabled(true);
    this.chooseFlipButton.setEnabled(true);
    this.adjustBrightnessButton.setEnabled(true);
    this.chosenFlip.setText("None selected");
    this.chosenFilter.setText("None selected");
    this.chosenColor.setText("None selected");
    this.chosenGreyScale.setText("None selected");
    this.incrementLabel.setText("Increment: N/A");
    this.downScaleButton.setEnabled(true);
    this.downScaleLabel.setText("Please select a width and a height");
    this.downscaleHeight = 0;
    this.downscaleWidth = 0;
    this.brightness = 0;

  }

  @Override
  public void setDownScaleHeight(int imageHeight) {
    String downScaleHeight = JOptionPane.showInputDialog(new JFrame(),
            "Enter a downscale Height. "
                    + "Must be an positive integer.");
    try {
      downscaleHeight = Integer.parseInt(downScaleHeight);
      if (downscaleHeight > imageHeight || downscaleHeight <= 0) {
        downScaleLabel.setText(
                "Invalid downscale height. Must be positive and less than or equal to the "
                        + "image height.");
        downscaleHeight = 0;
      }
    } catch (Exception ex) {
      downScaleLabel.setText("Invalid Downscale Height, Please try again.");
    }
  }

  @Override
  public void setDownScaleWidth(int imageWidth) {
    String downScaleWidth = JOptionPane.showInputDialog(new JFrame(),
            "Enter a downscale width. "
                    + "Must be an positive integer.");
    try {
      downscaleWidth = Integer.parseInt(downScaleWidth);
      if (downscaleWidth > imageWidth || downscaleWidth <= 0) {
        downScaleLabel.setText("Invalid Downscale Width.\n " +
                "Must be positive and less than or equal to the "
                + "image height.");
        downscaleWidth = 0;
      }
    } catch (Exception ex) {
      downScaleLabel.setText("Invalid Downscale Width, Please try again.");
    }
  }

  @Override
  public int getDownScaleHeight() {
    return downscaleHeight;
  }

  @Override
  public int getDownScaleWidth() {
    return downscaleWidth;
  }
}

