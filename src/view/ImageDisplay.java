package view;

public class ImageDisplay implements ImageView{
  @Override
  public void printImage(Image image) {
    for (int i = 0; i < image.imagePixels.length; i++) {
      for (int j = 0; j < image.imagePixels[0].length; j++) {
          //print image.imagePixels[i][j] somehow
      }
    }
  }
}
