package controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

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
import model.Pixel;
import view.GUIView;
import view.ImageDisplay;

public class ImageProcessingGUI extends JFrame implements ActionListener {
  private ImageEditor model;
  private GUIView view;
  private int brightness;
  private String filename;

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


  private final JMenuBar menuBar = new JMenuBar();
  String[] greyScale = new String[]{"NONE", "RED", "GREEN", "BLUE", "LUMA", "VALUE", "INTENSITY"};
  final JComboBox<String> dropDownGreyscale;
  String[] colorCombinations;
  final JComboBox<String> dropDownColorCombinations;
  String[] filters;
  final JComboBox<String> dropDownFilters;
  String[] orientationAndSize;
  final JComboBox<String> dropOrientationAndSize;

  ImageIcon[] imageBoxes = new ImageIcon[]{new ImageIcon("none"), new ImageIcon("histogram")};

  public ImageProcessingGUI(ImageEditor model, GUIView view) throws IOException {
    instantiateLabels();
    instantiatePanels();
    this.model = model;
    this.view = view;
    this.brightness = 0;
    this.filename = "test";
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
    this.picturePanel.setBorder(BorderFactory.createTitledBorder("Showing an image"));
    this.picturePanel.setLayout(new GridLayout(1, 0, 10, 10));

    this.allCommands.setLayout(new GridLayout(6, 1));
    this.instantiateButtons();
    this.displayImage(picturePanel);
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
    JButton chooseGreyscaleButton = new JButton("Choose Greyscale");
    JButton chooseColorButton = new JButton("Choose Color Combination");
    JButton chooseFilterButton = new JButton("Choose Filter");
    JButton chooseFlipButton = new JButton("Choose Orientation or Size");
    JButton adjustBrightnessButton = new JButton("Select Brightness Increment");


    chooseFilterButton.addActionListener(this);
    chooseFilterButton.setActionCommand("Picked Filter");
    chooseColorButton.addActionListener(this);
    chooseColorButton.setActionCommand("Picked Color");
    chooseGreyscaleButton.addActionListener(this);
    chooseGreyscaleButton.setActionCommand("Picked Greyscale");
    chooseFlipButton.addActionListener(this);
    chooseFlipButton.setActionCommand("Picked Flip");
    this.editImageButton.addActionListener(this);
    this.editImageButton.setActionCommand("Edit");

    adjustBrightnessButton.addActionListener(this);
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

    picturePanel.setBorder(BorderFactory.createTitledBorder("Showing an image"));
    picturePanel.setLayout(new GridLayout(1, 0, 10, 10));

    this.add(picturePanel);

  }

  private void displayImage(JPanel imagePanel) {


    imagePanel.removeAll();

    JLabel[] imageLabel = new JLabel[imageBoxes.length];
    JScrollPane[] imageScrollPane = new JScrollPane[imageBoxes.length];

    for (int i = 0; i < imageLabel.length; i++) {
      imageLabel[i] = new JLabel();
      imageScrollPane[i] = new JScrollPane(imageLabel[i]);
      imageLabel[i].setIcon(imageBoxes[i]);
      imageScrollPane[i].setPreferredSize(new Dimension(600, 600));
      imagePanel.add(imageScrollPane[i]);
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
    JMenu file = new JMenu("File");
    JMenuItem load = new JMenuItem("Load");
    load.addActionListener((ActionListener) this);
    load.setActionCommand("Load");
    JMenuItem save = new JMenuItem("Save");
    save.addActionListener((ActionListener) this);
    save.setActionCommand("Save");
    JMenuItem quit = new JMenuItem("Quit");
    file.add(load);
    file.add(save);
    file.add(quit);

    JMenu help = new JMenu("Help");
    JMenuItem howTo = new JMenuItem("How to use");
    howTo.addActionListener((ActionListener) this);
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


  public void actionPerformed(ActionEvent e) {
    Object game = e.getActionCommand();
    if(game.equals("Load")){
      JFileChooser chooser = new JFileChooser();
      chooser.showOpenDialog(this);
      File file = chooser.getSelectedFile();
      try{
        loadPic(file);
        imageBoxes[0] = new ImageIcon(file.getAbsolutePath());
        displayImage(this.picturePanel);
      }catch(Exception ex){
        System.out.println("Error loading image");
      }
    }
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
      String increment = JOptionPane.showInputDialog(new JFrame(), "Enter an increment. Positive "
              + "to increase brightness and negative to decrease brightness");

      try {
        brightness = Integer.parseInt(increment);
        this.incrementLabel.setText(increment);
        JLabel brightened = new JLabel();
        brightened.setText("SELECTED:  BRIGHTEN");
        addEdit(brightened);
      } catch (Exception var5) {
        this.incrementLabel.setText("Invalid Increment, Please try again.");
      }
    }
    if(game.equals("Save")){
      String newFile = "res/" + JOptionPane.showInputDialog(new JFrame(), "Enter the new file name");
      try {
        this.save(newFile);
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    }
//    if (game.equals("HowTo")) {
//      String increment = JOptionPane.showInputDialog(new JFrame(), "Enter an increment. Positive "
//              + "to increase brightness and negative to decrease brightness");
//
//      try {
//        Integer.parseInt(increment);
//        this.incrementLabel.setText(increment);
//      } catch (Exception var5) {
//        this.incrementLabel.setText("Invalid Increment, Please try again.");
//      }
//    }


  }

  private void changeLabelText(JComboBox<String> dropDown, JLabel label) {
    label.setText("\tSelected: " + dropDown.getItemAt(dropDown.getSelectedIndex()));
  }

  public void loadPic(File file) throws IOException {
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
    getHistogram(filename);
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
        getHistogram(filename);
        replaceImage(filename);
        break;
      case ("GREYSCALE"):
        newFilename = filename + "-vf";
        new Greyscale(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        getHistogram(filename);
        replaceImage(filename);
        break;
      case ("BLUR"):
        newFilename = filename + "-bl";
        new Blur(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        getHistogram(filename);
        replaceImage(filename);
        break;
      case ("SHARPEN"):
        newFilename = filename + "-sh";
        new Sharpen(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        getHistogram(filename);
        replaceImage(filename);
        break;
      case ("RED"):
        newFilename = filename + "-gr";
        new RedGreyscale(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        getHistogram(filename);
        replaceImage(filename);
        break;
      case ("GREEN"):
        newFilename = filename + "-gg";
        new GreenGreyscale(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        getHistogram(filename);
        replaceImage(filename);
        break;
      case ("BLUE"):
        newFilename = filename + "-gb";
        new BlueGreyscale(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        getHistogram(filename);
        replaceImage(filename);
        break;
      case ("LUMA"):
        newFilename = filename + "-gl";
        new LumaGreyscale(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        getHistogram(filename);
        replaceImage(filename);
        break;
      case ("INTENSITY"):
        newFilename = filename + "-gi";
        new IntensityGreyscale(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        getHistogram(filename);
        replaceImage(filename);
        break;
      case ("VALUE"):
        newFilename = filename + "-gv";
        new ValueGreyscale(filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        getHistogram(filename);
        replaceImage(filename);
        break;
      case ("BRIGHTEN"):
        newFilename = filename + "-br";
        new AdjustBrightness(brightness, filename, newFilename).execute(model, new ImageDisplay(System.out));
        filename = newFilename;
        getHistogram(filename);
        replaceImage(filename);
        break;
      default:
        break;
    }
  }

  public void replaceImage(String filename) {
    int length = model.getMap().get(filename).length;
    int width = model.getMap().get(filename)[0].length;
    BufferedImage bufferedImage = new BufferedImage(width, length, BufferedImage.TYPE_INT_RGB);
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
    imageBoxes[0]=image;
    displayImage(picturePanel);
  }

  public void save(String pathname) throws IOException {
    if (!model.getMap().containsKey(filename)) {
      System.out.println("Image " + filename + " does not exist or has not been loaded!");
      return;
    }

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


    ArrayList<String> formats = new ArrayList<>(Arrays.asList(ImageIO.getWriterFormatNames()));
    String type2 = pathname.split("\\.")[1];

    if (formats.contains(type2)) {
      File file = new File(pathname);
      ImageIO.write(bufferedImage, type2, file);
      System.out.println("Image: " + filename + "\nsaved as: " + pathname);
    } else {
      System.out.println("Image type not supported");
    }
  }

  public void getHistogram(String filename) {
    Pixel[][] pixels = model.getMap().get(filename);
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
    displayImage(picturePanel);

  }

  public BufferedImage makeHistogram(int[] red, int[] green, int[] blue, int[] intensity){
    BufferedImage bufferedImage = new BufferedImage(600, 950, BufferedImage.TYPE_INT_RGB);
    Graphics2D graph = bufferedImage.createGraphics();
    graph.setColor(Color.red);
    for (int i = 0; i < 254; i++) {
      for (int j = 0; j < 254; j++) {
        graph.drawLine(i * 2 ,red[i],(i+1) * 2,red[i+1]);
      }
    }
    graph.setColor(Color.BLUE);
    for (int i = 0; i < 254; i++) {
      for (int j = 0; j < 254; j++) {
        graph.drawLine(i * 2,blue[i],(i+1) * 2,blue[i+1]);
      }
    }
    graph.setColor(Color.green);
    for (int i = 0; i < 254; i++) {
      for (int j = 0; j < 254; j++) {
        graph.drawLine(i*2,green[i],(i+1) * 2,green[i+1]);
      }
    }
    graph.setColor(Color.gray);
    for (int i = 0; i < 254; i++) {
      for (int j = 0; j < 254; j++) {
        graph.drawLine(i*2,intensity[i],(i+1) * 2,intensity[i+1]);
      }
    }
    return bufferedImage;
  }
}

