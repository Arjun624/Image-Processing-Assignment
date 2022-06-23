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

  void getHistogram(String filename, Map<String, Pixel[][]> map);

  BufferedImage getBufferedImage(String filename, Map<String, Pixel[][]> map);

  void renderMessage(String s);


  void addEdit(String command, ArrayList<String> inputtedEdits);

  void changeLabelText(String color);

  void setIncrement();

  int getIncrement();

  public File getSaveFile();

  public File GetLoadFile();


  void changeImage(ImageIcon image);


  void setActionListeners(ActionListener listener);
}



