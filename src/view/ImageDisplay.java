package view;

import java.io.IOException;

public class ImageDisplay implements ImageView{
  Appendable ap;

  public ImageDisplay(Appendable ap){
    this.ap = ap;
  }

  public ImageDisplay(){
    this.ap = System.out;
  }
  public void renderMessage(String message) throws IOException {
    this.ap.append(message + System.lineSeparator());
  }
}
