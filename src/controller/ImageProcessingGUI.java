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
  private JTextArea specificGreyscale = new JTextArea(); //Instantiate new TextArea where the question text will be scored
  private JScrollPane questionScrollPane = new JScrollPane(specificGreyscale);// Turns the textArea into a scrollPane that has a scroll bar
  private JButton loadButton = new JButton("Load Image"); //Instantiating new JButtons and sets the text of the buttons
  private JButton saveButton = new JButton("Save Image"); //Instantiating new JButtons and sets the text of the buttons
  private JButton vflipButton = new JButton("Flip Image Vertically");
  private JButton hflipButton = new JButton("Flip Image Horizontally");
  private JButton greyscaleButton = new JButton("Convert to a Greyscale Image");
  private JButton sepiaButton = new JButton("Convert to a Sepia Image");
  private JButton blurButton = new JButton("Blur Image");
  private JButton sharpenButton = new JButton("Sharpen Image");

  private JPanel allCommands = new JPanel();



  public ImageProcessingGUI() {
    setDefaultLookAndFeelDecorated(true);
    this.setTitle("ImageProcessing"); //Sets the characteristics of the JFrame
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(750, 400);
    this.setResizable(false);

    Container c = this.getContentPane(); //Obtains the object where everything is stored on the frame
    BoxLayout bl = new BoxLayout(c, BoxLayout.LINE_AXIS); //Instantiating a borderLayout
    c.setLayout(bl); //Changing the frame layout to a borderLayout
    flipCommands.setLayout(new GridLayout(2, 1)); //Sets layouts of 3 panels as a gridLayout
    filterCommands.setLayout(new GridLayout(2, 1));
    colorCommands.setLayout(new GridLayout(2, 1));
    loadAndSave.setLayout(new GridLayout(2, 1));
    allCommands.setLayout(new GridLayout(10, 1));
    JLabel colorLabel = new JLabel("Color Commands:");
    JLabel filterLabel = new JLabel("Filter Image:");
    JLabel flipLabel = new JLabel("Flip Image:");//Instantiating new JLabels and sets the text of the labels
    JLabel specificGreyscaleLabel = new JLabel("Specific Greyscale Options:");//Instantiating new JLabels and sets the text of the labels
    String[] choices = { "RED", "GREEN", "BLUE", "LUMA",
            "VALUE", "INTENSITY" };
    final JComboBox<String> cb = new JComboBox<String>(choices);
    cb.setMaximumSize(cb.getPreferredSize()); // added code
    cb.setAlignmentX(Component.CENTER_ALIGNMENT);// added code

    allCommands.add(colorLabel);
    allCommands.add(colorCommands);
    allCommands.add(flipLabel);
    allCommands.add(flipCommands);
    allCommands.add(filterLabel);
    allCommands.add(filterCommands);
    allCommands.add(specificGreyscaleLabel);
    allCommands.add(cb);
    allCommands.add(loadAndSave);


    c.add(allCommands);
    c.add(picturePanel, BorderLayout.NORTH); //Adds everything to the frame





    questionScrollPane.setPreferredSize(new Dimension(100, 100)); //Sets size of text Area
    specificGreyscale.setText("Red \n Blue \n Green"); //Text of the text Area
    specificGreyscale.setWrapStyleWord(true); //Stops the Text from getting cut off from the TextArea

    specificGreyscale.setEnabled(false); //Disables the typing feature of the textArea
    specificGreyscale.setFont(new Font("TimesRoman", Font.BOLD, 15)); //Font of the text

    instantiateButtons(); //Calls the button method

    this.pack(); //Makes everything fit on the frame
    this.setVisible(true); //Makes the frame visible

  }

  public void instantiateButtons() {

    loadButton.addActionListener((ActionListener) this); //Adds the action Listener and Action Command for each button
    loadButton.setActionCommand("Load");

    saveButton.addActionListener((ActionListener) this);
    saveButton.setActionCommand("Quit");


    vflipButton.addActionListener((ActionListener) this);
    vflipButton.setActionCommand("vFlip");


    hflipButton.addActionListener((ActionListener) this);
    hflipButton.setActionCommand("hFlip");

    sepiaButton.addActionListener((ActionListener) this);
    sepiaButton.setActionCommand("sepia");

    greyscaleButton.addActionListener((ActionListener) this);
    greyscaleButton.setActionCommand("greyscale");

    blurButton.addActionListener((ActionListener) this);
    blurButton.setActionCommand("blur");

    sharpenButton.addActionListener((ActionListener) this);
    sharpenButton.setActionCommand("sharpen");

    loadAndSave.add(loadButton); //Adds all the buttons to the frame
    loadAndSave.add(saveButton);

    filterCommands.add(blurButton);
    filterCommands.add(sharpenButton);
    colorCommands.add(sepiaButton);
    colorCommands.add(greyscaleButton);
    flipCommands.add(vflipButton);
    flipCommands.add(hflipButton);

    picturePanel.add(new JLabel(new ImageIcon("res/battlefield.jpg")));//Adds the image to frame


//    buttonA.setEnabled(false); //Disables the buttons
//    buttonB.setEnabled(false);
//    buttonC.setEnabled(false);
//    buttonD.setEnabled(false);



  }


  public void actionPerformed(ActionEvent e) {
    Object game = e.getActionCommand();
    if (game.equals("vFlip")) {
System.out.println("Click");
    }


  }
}