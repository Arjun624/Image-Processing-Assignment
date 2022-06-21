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
    flipCommands.setLayout(new GridLayout(1, 2)); //Sets layouts of 3 panels as a gridLayout
    filterCommands.setLayout(new GridLayout(1, 2));
    colorCommands.setLayout(new GridLayout(1, 2));
    loadAndSave.setLayout(new GridLayout(1, 2));
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
    if (game.equals("Start")) {
//      startbutton.setEnabled(false); //Disables the start button
//      buttonA.setEnabled(true); //Enables the answer choice buttons
//      buttonB.setEnabled(true);
//      buttonC.setEnabled(true);
//      buttonD.setEnabled(true);
//
//      questionArea.setText(questions.getGame().get(questionCount).getQuestion()); //Adds the question to the text Area
//      buttonA.setText(questions.getGame().get(questionCount).getChoiceA()); //Sets the answer choices as the text of the buttons
//      buttonB.setText(questions.getGame().get(questionCount).getChoiceB());
//      buttonC.setText(questions.getGame().get(questionCount).getChoiceC());
//      buttonD.setText(questions.getGame().get(questionCount).getChoiceD());


//    }
//
//    if (game.equals("A")) {
//      findUpdateCorrectAnswer(questions.getGame().get(questionCount).getChoiceA()); //Calls the  findUpdateCorrectAnswer method
//
//    }
//    if (game.equals("B")) {
//
//      findUpdateCorrectAnswer(questions.getGame().get(questionCount).getChoiceB()); //Calls the  findUpdateCorrectAnswer method
//
//    }
//    if (game.equals("C")) {
//
//      findUpdateCorrectAnswer(questions.getGame().get(questionCount).getChoiceC()); //Calls the  findUpdateCorrectAnswer method
//
//    }
//    if (game.equals("D")) {
//      findUpdateCorrectAnswer(questions.getGame().get(questionCount).getChoiceD()); //Calls the  findUpdateCorrectAnswer method
//    }
//    if (game.equals("Quit")) {
//      this.dispose(); //Closes the frame
//    }

    }

//  private void findUpdateCorrectAnswer(String aChoice) {
//    buttonA.setEnabled(false); //disables all the buttons
//    buttonB.setEnabled(false);
//    buttonC.setEnabled(false);
//    buttonD.setEnabled(false);
//    if (aChoice.equals(questions.getGame().get(questionCount).getCorrectAns())) //Checks if the choice picked is the correct answer
//    {
//      if (questionCount == 0) {
//        startbutton.setText("Next Question"); //Changes start button the a next question button
//        startbutton.setEnabled(true); //Enables the start button
//
//
//      }
//
//      startbutton.setEnabled(true); //Enables the start button
//
//      if (moneyWon == 0.0) { //Changes the value of moneyWon
//        moneyWon = 1000.00;
//        questionArea.setText("Correct! You have won $" + moneyWon); //changes the text of the textArea
//      } else if (moneyWon == 512000.00) {
//        questionArea.setText("CONGRATULATIONS!! \n YOU JUST WON A \nMILLION DOLLARS!"); //changes the text of the textArea
//        startbutton.setEnabled(false);
//      } else {
//        moneyWon = moneyWon * 2;
//        questionArea.setText("Correct! You have won $" + moneyWon); //changes the text of the textArea
//      }
//      questionCount++; //Increments the question count
//    } else {
//      questionArea.setText("Sorry! Wrong Answer. \nTotal money won: $" + moneyWon + " \n The correct answer was: " + questions.getGame().get(questionCount).getCorrectAns()); //changes the text of the textArea
//      startbutton.setText("Game Over"); //Changes the start button text to "game over"
//
//
//    }
//  }
  }
}