package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import controller.ImageControllerGUI;
import model.Pixel;

public class ImageProcessingGUI extends JFrame implements GUIView {


  protected int brightness;
  //private String filename;

  ArrayList<String> edits;
  private JPanel picturePanel;
  private JPanel flipCommands ;
  private JPanel filterCommands ;
  private JPanel colorCommands ;
  private JPanel brightnessCommands ;
  private JPanel specificGreyscaleCommands ;
  private JPanel allCommands ;

  private JLabel incrementLabel ;
  private JLabel chosenFlip ;
  private JLabel chosenColor ;
  private JLabel chosenGreyScale;
  private JLabel chosenFilter;
  private JButton editImageButton;

  JMenu file = new JMenu("File");
  JMenuItem load = new JMenuItem("Load");

  JMenuItem save = new JMenuItem("Save");

  JMenu help = new JMenu("Help");
  JMenuItem howTo = new JMenuItem("How to use");

  JButton chooseGreyscaleButton = new JButton("Choose Greyscale");
  JButton chooseColorButton = new JButton("Choose Color Combination");
  JButton chooseFilterButton = new JButton("Choose Filter");
  JButton chooseFlipButton = new JButton("Choose Orientation or Size");
  JButton adjustBrightnessButton = new JButton("Select Brightness Increment");


  private final JMenuBar menuBar = new JMenuBar();
  String[] greyScale = new String[]{"NONE", "RED", "GREEN", "BLUE", "LUMA", "VALUE", "INTENSITY"};
  final JComboBox<String> dropDownGreyscale;
  String[] colorCombinations;
  final JComboBox<String> dropDownColorCombinations;
  String[] filters;
  final JComboBox<String> dropDownFilters;
  String[] orientationAndSize;
  final JComboBox<String> dropOrientationAndSize;

  public ImageIcon[] imageBoxes = new ImageIcon[]{new ImageIcon("none"), new ImageIcon("histogram")};

  public ImageProcessingGUI() throws IOException {
    instantiateLabels();
    instantiatePanels();
    this.brightness = 0;
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
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setSize(950, 950);
    this.setResizable(false);
    Container c = this.getContentPane();
    BoxLayout bl = new BoxLayout(c, BoxLayout.X_AXIS);
    c.setLayout(bl);
    this.flipCommands.setLayout(new GridLayout(3, 1));
    this.flipCommands.setBorder(BorderFactory.createTitledBorder("Change Orientation or Size"));
    this.filterCommands.setLayout(new GridLayout(3, 1));
    this.filterCommands.setBorder(BorderFactory.createTitledBorder("Filter Image"));
    this.colorCommands.setLayout(new GridLayout(3, 1));
    this.colorCommands.setBorder(BorderFactory.createTitledBorder("Choose Color Combination"));
    this.brightnessCommands.setLayout(new GridLayout(2, 1));
    this.brightnessCommands.setBorder(BorderFactory.createTitledBorder("Adjust Brightness"));
    this.specificGreyscaleCommands.setLayout(new GridLayout(3, 1));
    this.specificGreyscaleCommands.setBorder(BorderFactory.createTitledBorder("Choose Greyscale"));
    this.picturePanel.setBorder(BorderFactory.createTitledBorder("Loaded Image and Histogram"));
    this.picturePanel.setLayout(new GridLayout(1, 0, 10, 10));

    this.allCommands.setLayout(new GridLayout(6, 1));
    this.instantiateButtons();
    this.displayImage();
    this.instantiateDropDowns();
    instantiateMenuBar();
    this.allCommands.add(this.colorCommands);
    this.allCommands.add(this.flipCommands);
    this.allCommands.add(this.filterCommands);
    this.allCommands.add(this.brightnessCommands);
    this.allCommands.add(this.specificGreyscaleCommands);
    this.allCommands.add(this.editImageButton);
    c.add(this.allCommands);
    c.add(this.picturePanel);


    this.pack();
    this.setVisible(true);

  }

  private void instantiatePanels() {
    this.picturePanel = new JPanel();
    this.flipCommands = new JPanel();
    this.filterCommands = new JPanel();
    this.colorCommands = new JPanel();
    this.brightnessCommands = new JPanel();
    this.allCommands= new JPanel();
    this.specificGreyscaleCommands = new JPanel();
  }

  private void instantiateLabels() {
    this.incrementLabel = new JLabel("Increment: N/A");
    this.chosenFlip = new JLabel("\tNone selected");
    this.chosenColor = new JLabel("\tNone selected");
    this.chosenGreyScale = new JLabel("\tNone selected");
    this.chosenFilter = new JLabel("\tNone selected");
  }

  public void instantiateButtons() {
    this.editImageButton = new JButton("Edit Image");







    chooseFilterButton.setActionCommand("Picked Filter");
    chooseColorButton.setActionCommand("Picked Color");
    chooseGreyscaleButton.setActionCommand("Picked Greyscale");
    chooseFlipButton.setActionCommand("Picked Flip");
    this.editImageButton.setActionCommand("Edit");
    adjustBrightnessButton.setActionCommand("Brightness");



    this.filterCommands.add(this.dropDownFilters);
    this.filterCommands.add(chooseFilterButton);
    this.filterCommands.add(this.chosenFilter);
    this.colorCommands.add(this.dropDownColorCombinations);
    this.colorCommands.add(chooseColorButton);
    this.colorCommands.add(this.chosenColor);
    this.flipCommands.add(this.dropOrientationAndSize);
    this.flipCommands.add(chooseFlipButton);
    this.flipCommands.add(this.chosenFlip);
    this.brightnessCommands.add(adjustBrightnessButton);
    this.brightnessCommands.add(this.incrementLabel);
    this.specificGreyscaleCommands.add(this.dropDownGreyscale);
    this.specificGreyscaleCommands.add(chooseGreyscaleButton);
    this.specificGreyscaleCommands.add(this.chosenGreyScale);


    this.add(picturePanel);

  }

  public void setActionListeners(ActionListener listener) {
    load.addActionListener( listener);
    save.addActionListener(listener);
    howTo.addActionListener(listener);
    chooseFilterButton.addActionListener(listener);
    chooseColorButton.addActionListener(listener);
    chooseGreyscaleButton.addActionListener(listener);
    chooseFlipButton.addActionListener(listener);
    this.editImageButton.addActionListener(listener);

    adjustBrightnessButton.addActionListener(listener);
  }

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

  private void instantiateDropDowns(){
    this.dropDownGreyscale.setMaximumSize(this.dropDownGreyscale.getPreferredSize());
    this.dropDownGreyscale.setAlignmentX(0.5F);
    this.dropDownColorCombinations.setMaximumSize(this.dropDownColorCombinations.getPreferredSize());
    this.dropDownColorCombinations.setAlignmentX(0.5F);
    this.dropDownFilters.setMaximumSize(this.dropDownFilters.getPreferredSize());
    this.dropDownFilters.setAlignmentX(0.5F);
    this.dropOrientationAndSize.setMaximumSize(this.dropOrientationAndSize.getPreferredSize());
    this.dropOrientationAndSize.setAlignmentX(0.5F);
  }

  private void instantiateMenuBar() {

    load.setActionCommand("Load");

    save.setActionCommand("Save");
    JMenuItem quit = new JMenuItem("Quit");
    file.add(load);
    file.add(save);
    file.add(quit);



    howTo.setActionCommand("HowTo");
    JMenuItem validCommands = new JMenuItem("Information on Valid Commands");
    JMenuItem documentation = new JMenuItem("Full Documentation");
    help.add(howTo);
    help.add(validCommands);
    help.add(documentation);


    menuBar.add(file);
    menuBar.add(help);
    setJMenuBar(menuBar);
  }

  public void changeLabelText(String type) {
    if(type.equals("FLIP")){
      chosenFlip.setText("\tSelected: " + dropOrientationAndSize.getItemAt(dropOrientationAndSize.getSelectedIndex()));
    }
    if(type.equals("COLOR")){
chosenColor.setText("\tSelected: " + dropDownColorCombinations.getItemAt(dropDownColorCombinations.getSelectedIndex()));
    }
    if(type.equals("GREYSCALE")){
chosenGreyScale.setText("\tSelected: " + dropDownGreyscale.getItemAt(dropDownGreyscale.getSelectedIndex()));
    }
    if(type.equals("FILTER")){
 chosenFilter.setText("\tSelected: " + dropDownFilters.getItemAt(dropDownFilters.getSelectedIndex()));
    }
    if(type.equals("BRIGHTEN")){
      incrementLabel.setText("Brightness increment: " + brightness);
    }
    if(type.equals("DIM")){
      incrementLabel.setText("Dimness increment: " + brightness);
    }
    if(type.equals("INVALID")){
      incrementLabel.setText("Invalid Increment, Please try again.");
    }
  }
  public void setIncrement() {
     String increment = JOptionPane.showInputDialog(new JFrame(), "Enter an increment. Positive "
            + "to increase brightness and negative to decrease brightness");
     try{
       brightness = Integer.parseInt(increment);
     } catch(Exception ex){
        incrementLabel.setText("Invalid Increment, Please try again.");
      }
    }

  public int getIncrement() {
    return brightness;
  }

  public void addEdit(String type, ArrayList<String> inputtedEdits) {
    if(type.equals("FLIP")){
      inputtedEdits.add(this.getLabelText(chosenFlip));
    }
    if(type.equals("COLOR")){
      inputtedEdits.add(this.getLabelText(chosenColor));
    }
    if(type.equals("GREYSCALE")){
      inputtedEdits.add(this.getLabelText(chosenGreyScale));
    }
    if(type.equals("FILTER")){
      inputtedEdits.add(this.getLabelText(chosenFilter));
    }
    if(type.equals("BRIGHTEN")){
      JLabel brightened = new JLabel();
      brightened.setText("SELECTED:  BRIGHTEN");
      inputtedEdits.add(this.getLabelText(brightened));
    }

  }

  private String getLabelText(JLabel label) {
    if(!label.toString().equalsIgnoreCase("NONE")){
      return label.getText().substring(11);
    }
    return null;

  }

  public void getHistogram(String filename, Map<String, Pixel[][]> map) {
    if (!map.containsKey(filename)) {
      JOptionPane.showMessageDialog(new JFrame(), "No image loaded!", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }
    Pixel[][] pixels = map.get(filename);
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
    ImageIcon image = new ImageIcon(makeHistogram(red, green,blue,intensity));
    imageBoxes[1] = image;
    displayImage();

  }

  @Override
  public BufferedImage getBufferedImage(String filename, Map<String, Pixel[][]> map) {
    int length = map.get(filename).length;
    int width = map.get(filename)[0].length;
    BufferedImage bufferedImage = new BufferedImage(width, length, BufferedImage.TYPE_INT_RGB);
    for (int row = 0; row < length; row++) {
      for (int col = 0; col < width; col++) {
        Color c = new Color(map.get(filename)[row][col].getRed(),
                map.get(filename)[row][col].getGreen(),
                map.get(filename)[row][col].getBlue(),
                map.get(filename)[row][col].getAlpha());
        bufferedImage.setRGB(col, row, c.getRGB());
      }
    }
    return bufferedImage;
  }

  private BufferedImage makeHistogram(int[] red, int[] green, int[] blue, int[] intensity){
    BufferedImage bufferedImage = new BufferedImage(600, 950, BufferedImage.TYPE_INT_RGB);
    Graphics2D graph = bufferedImage.createGraphics();
    drawLines(graph, red, Color.red);
    drawLines(graph, green, Color.green);
    drawLines(graph, blue, Color.blue);
    drawLines(graph, intensity, Color.gray);
    return bufferedImage;
  }

  private void drawLines(Graphics2D graph, int[] nums, Color c){
    graph.setColor(c);
    for (int i = 0; i < 254; i++) {
      for (int j = 0; j < 254; j++) {
        graph.drawLine((int)(i * 2.34), 950-nums[i]/2,(int)((i+1) * 2.34),950-nums[i+1]/2);
      }
    }
  }



  public void  showErrorPopup(String errorMessage) {
    JOptionPane.showMessageDialog(new JFrame(), errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
  }


  /**
   * Renders the inputted message.
   *
   * @param message the inputted message
   * @throws IOException if the program can't read the input or write the output
   */
  @Override
  public void renderMessage(String message)  {
    JOptionPane.showMessageDialog(new JFrame(), message, "Messsage", JOptionPane.INFORMATION_MESSAGE);
  }

  /**
   * Displays the welcome message.
   */
  @Override
  public void displayWelcomeMessage() {
    StringBuilder welcomeMessage = new StringBuilder("Welcome to the Image Program!" +
            "\n" + "Please select an image to load from the file menu.");
    welcomeMessage.append("\n" + "Valid edits:");
    welcomeMessage.append("\n" + "\t"+ "1. Flip Horizontal");
    welcomeMessage.append("\n" + "\t"+ "2. Flip Vertical");
    welcomeMessage.append("\n" + "\t"+ "3. Sepia");
    welcomeMessage.append("\n" + "\t"+ "4. Grayscale");
    welcomeMessage.append("\n" + "\t"+ "5. Adjust Brightness");
    welcomeMessage.append("\n" + "\t"+ "6. Blur");
    welcomeMessage.append("\n" + "\t"+ "7. Sharpen");
    welcomeMessage.append("\n" + "\t"+ "8. Red Greyscale");
    welcomeMessage.append("\n" + "\t"+ "9. Green Greyscale");
    welcomeMessage.append("\n" + "\t"+ "10. Blue Greyscale");
    welcomeMessage.append("\n" + "\t"+ "11. Luma Greyscale");
    welcomeMessage.append("\n" + "\t"+ "12. Value Greyscale");
    welcomeMessage.append("\n" + "\t"+ "13. Intensity Greyscale");

    JOptionPane.showMessageDialog(new JFrame(), welcomeMessage, "Welcome", JOptionPane.INFORMATION_MESSAGE);
  }

  public void changeImage(ImageIcon image){
    imageBoxes[0]=image;
    displayImage();
  }

  public File getSaveFile(){
    JFileChooser chooser = new JFileChooser();
    chooser.showSaveDialog(this);
    return chooser.getSelectedFile();
  }

  public File GetLoadFile(){
    JFileChooser chooser = new JFileChooser();
    chooser.showOpenDialog(this);
    return  chooser.getSelectedFile();
  }
}
