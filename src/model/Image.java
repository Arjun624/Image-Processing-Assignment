package model;

public class Image implements ImageEditor{

  Pixel[][] imagePixels;

  public Image(Pixel[][] imagePixels){
    this.imagePixels = imagePixels;
  }

  @Override
  public Image flipVertically() {
    return null;
  }

  @Override
  public Image flipHorizontally() {
    return null;
  }

  @Override
  public Image showR() {
    Pixel[][] newImage = new Pixel[this.imagePixels.length][this.imagePixels[0].length];
    for (int i = 0; i < this.imagePixels.length; i++) {
      for (int j = 0; j < this.imagePixels[0].length; j++) {
        int red = this.imagePixels[i][j].r;
        newImage[i][j]  = new Pixel(i,j,red,red,red);
      }
    }
    return new Image(newImage);
  }

  @Override
  public Image showG() {
    Pixel[][] newImage = new Pixel[this.imagePixels.length][this.imagePixels[0].length];
    for (int i = 0; i < this.imagePixels.length; i++) {
      for (int j = 0; j < this.imagePixels[0].length; j++) {
        int green = this.imagePixels[i][j].g;
        newImage[i][j]  = new Pixel(i,j,green,green,green);
      }
    }
    return new Image(newImage);
  }

  @Override
  public Image showB() {
    Pixel[][] newImage = new Pixel[this.imagePixels.length][this.imagePixels[0].length];
    for (int i = 0; i < this.imagePixels.length; i++) {
      for (int j = 0; j < this.imagePixels[0].length; j++) {
        int blue = this.imagePixels[i][j].b;
        newImage[i][j]  = new Pixel(i,j,blue,blue,blue);
      }
    }
    return new Image(newImage);
  }

  @Override
  public Image adjustBrightness(Image image) {
    return null;
  }
}
