package controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ImageProcessingGUI extends JFrame implements ActionListener {
  private JPanel picturePanel = new JPanel();//Instantiate new JPanels
  private JPanel flipCommands = new JPanel();
  private JPanel filterCommands = new JPanel();
  private JPanel colorCommands = new JPanel();

  private JPanel loadAndSave = new JPanel();
  private JLabel specificGreyscaleCommands = new JLabel(); //Instantiate new TextArea where the question text will be scored
  //private JScrollPane questionScrollPane = new JScrollPane(specificGreyscaleCommands);// Turns the textArea into a scrollPane that has a scroll bar
  private JButton loadButton = new JButton("Load Image"); //Instantiating new JButtons and sets the text of the buttons
  private JButton saveButton = new JButton("Save Image"); //Instantiating new JButtons and sets the text of the buttons
  private JButton helpButton = new JButton("Help!"); //Instantiating new JButtons and sets the text of the buttons

  private JButton editImageButton = new JButton("Edit Image"); //Instantiating new JButtons and sets the text of the buttons
  private JButton chooseGreyscaleButton = new JButton("Choose Greyscale");
  private JButton chooseColorButton = new JButton("Choose Color Combination");
  private JButton chooseFilterButton = new JButton("Choose Filter");
  private JButton chooseFlipButton = new JButton("Choose Orientation or Size");
  private JLabel chosenFlip = new JLabel("\tNone selected");
  private JLabel chosenColor = new JLabel("\tNone selected");
  private JLabel chosenGreyScale = new JLabel("\tNone selected");
  private JLabel chosenFilter = new JLabel("\tNone selected");

  private JPanel allCommands = new JPanel();


  String[] greyScale = { "NONE", "RED", "GREEN", "BLUE", "LUMA",
          "VALUE", "INTENSITY" };
  final JComboBox<String> dropDownGreyscale = new JComboBox<String>(greyScale);

  String[] colorCombinations = { "NONE", "SEPIA", "GREYSCALE" };
  final JComboBox<String> dropDownColorCombinations = new JComboBox<String>(colorCombinations);

  String[] filters = { "NONE", "BLUR", "SHARPEN" };
  final JComboBox<String> dropDownFilters = new JComboBox<String>(filters);

  String[] orientationAndSize = { "NONE", "VERTICAL FLIP", "HORIZONTAL FLIP" };
  final JComboBox<String> dropOrientationAndSize = new JComboBox<String>(orientationAndSize);



  public ImageProcessingGUI() {
    setDefaultLookAndFeelDecorated(true);
    this.setTitle("ImageProcessing"); //Sets the characteristics of the JFrame
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(500, 500);
    this.setResizable(false);

    Container c = this.getContentPane(); //Obtains the object where everything is stored on the frame
    BoxLayout bl = new BoxLayout(c, BoxLayout.LINE_AXIS); //Instantiating a borderLayout
    c.setLayout(bl); //Changing the frame layout to a borderLayout
    flipCommands.setLayout(new GridLayout(3, 1)); //Sets layouts of 3 panels as a gridLayout
    filterCommands.setLayout(new GridLayout(3, 1));
    colorCommands.setLayout(new GridLayout(3, 1));
    loadAndSave.setLayout(new GridLayout(3, 1));
    specificGreyscaleCommands.setLayout(new GridLayout(3, 1));
    allCommands.setLayout(new GridLayout(10, 1));

    JLabel colorLabel = new JLabel("Color Commands:");
    JLabel filterLabel = new JLabel("Filter Image:");
    JLabel flipLabel = new JLabel("Flip Image:");//Instantiating new JLabels and sets the text of the labels
    JLabel specificGreyscaleLabel = new JLabel("Specific Greyscale Options:");//Instantiating new JLabels and sets the text of the labels


    allCommands.add(loadAndSave);
    allCommands.add(colorLabel);
    allCommands.add(colorCommands);
    allCommands.add(flipLabel);
    allCommands.add(flipCommands);
    allCommands.add(filterLabel);
    allCommands.add(filterCommands);
    allCommands.add(specificGreyscaleLabel);
    allCommands.add(specificGreyscaleCommands);
    allCommands.add(editImageButton);



    c.add(allCommands);
    c.add(picturePanel, BorderLayout.NORTH); //Adds everything to the frame




    instantiateButtons(); //Calls the button method

    this.pack(); //Makes everything fit on the frame
    this.setVisible(true); //Makes the frame visible

  }

  public void instantiateButtons() {

    loadButton.addActionListener((ActionListener) this); //Adds the action Listener and Action Command for each button
    loadButton.setActionCommand("Load");

    saveButton.addActionListener((ActionListener) this);
    saveButton.setActionCommand("Save");

    chooseFilterButton.addActionListener((ActionListener) this);
    chooseFilterButton.setActionCommand("Picked Filter");

    chooseColorButton.addActionListener((ActionListener) this);
    chooseColorButton.setActionCommand("Picked Color");

    chooseGreyscaleButton.addActionListener((ActionListener) this);
    chooseGreyscaleButton.setActionCommand("Picked Greyscale");

    chooseFlipButton.addActionListener((ActionListener) this);
    chooseFlipButton.setActionCommand("Picked Flip");

    editImageButton.addActionListener((ActionListener) this);
    editImageButton.setActionCommand("Edit");

    helpButton.addActionListener((ActionListener) this);
    helpButton.setActionCommand("Help");










    dropDownGreyscale.setMaximumSize(dropDownGreyscale.getPreferredSize()); // added code
    dropDownGreyscale.setAlignmentX(Component.CENTER_ALIGNMENT);// added code
    System.out.println(dropDownGreyscale.getItemAt(dropDownGreyscale.getSelectedIndex()));


    dropDownColorCombinations.setMaximumSize(dropDownColorCombinations.getPreferredSize()); // added code
    dropDownColorCombinations.setAlignmentX(Component.CENTER_ALIGNMENT);// added code
    System.out.println(dropDownColorCombinations.getItemAt(dropDownColorCombinations.getSelectedIndex()));


    dropDownFilters.setMaximumSize(dropDownFilters.getPreferredSize()); // added code
    dropDownFilters.setAlignmentX(Component.CENTER_ALIGNMENT);// added code
    System.out.println(dropDownFilters.getItemAt(dropDownFilters.getSelectedIndex()));


    dropOrientationAndSize.setMaximumSize(dropOrientationAndSize.getPreferredSize()); // added code
    dropOrientationAndSize.setAlignmentX(Component.CENTER_ALIGNMENT);// added code
    System.out.println(dropOrientationAndSize.getItemAt(dropOrientationAndSize.getSelectedIndex()));



    loadAndSave.add(helpButton);
    loadAndSave.add(loadButton); //Adds all the buttons to the frame
    loadAndSave.add(saveButton);

    filterCommands.add(dropDownFilters);
    filterCommands.add(chooseFilterButton);
    filterCommands.add(chosenFilter);
    colorCommands.add(dropDownColorCombinations);
    colorCommands.add(chooseColorButton);
    colorCommands.add(chosenColor);
    flipCommands.add(dropOrientationAndSize);
    flipCommands.add(chooseFlipButton);
    flipCommands.add(chosenFlip);
    specificGreyscaleCommands.add(dropDownGreyscale);
    specificGreyscaleCommands.add(chooseGreyscaleButton);
    specificGreyscaleCommands.add(chosenGreyScale);

    picturePanel.add(new JLabel(new ImageIcon("res/battlefield.jpg")));//Adds the image to frame


//    buttonA.setEnabled(false); //Disables the buttons
//    buttonB.setEnabled(false);
//    buttonC.setEnabled(false);
//    buttonD.setEnabled(false);



  }


  public void actionPerformed(ActionEvent e) {
    Object game = e.getActionCommand();
    if (game.equals("Picked Filter")) {
      this.changeLabelText(dropDownFilters, chosenFilter);
    }
    if (game.equals("Picked Color")) {
      this.changeLabelText(dropDownColorCombinations, chosenColor);
    }
    if (game.equals("Picked Greyscale")) {
      this.changeLabelText(dropDownGreyscale, chosenGreyScale);
    }
    if (game.equals("Picked Flip")) {
      this.changeLabelText(dropOrientationAndSize, chosenFlip);
    }


  }

  private void changeLabelText(JComboBox<String> dropDown, JLabel label){
    label.setText("\tSelected: " + dropDown.getItemAt(dropDown.getSelectedIndex()));
  }
}