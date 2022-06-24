package view;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import model.Pixel;

public interface GUIView extends ImageView {
  void showErrorPopup(String s);

  void displayHistogram(Pixel[][] pixels);

  BufferedImage getBufferedImage(Pixel[][] pixels);

  void renderMessage(String s);


  void addEdit(String command, ArrayList<String> inputtedEdits);

  void changeLabelText(String color);

  void setIncrement();

  int getIncrement();

  public File getSaveFile();

  public File GetLoadFile();


  void changeImage(ImageIcon image);


  void setActionListeners(ActionListener listener);

  void reset();

   void setDownScaleHeight(int imageHeight);
   void setDownScaleWidth(int imageWidth);

   int getDownScaleHeight();
   int getDownScaleWidth();
}



