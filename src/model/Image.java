package model;

public class Image implements ImageEditor{

  Pixel[][] imagePixels;

  public Image(Pixel[][] imagePixels){
    this.imagePixels = imagePixels;
  }

  @Override
  public Image flipVertically() {

    Image newImage = this;
    Pixel[][] pixels = newImage.imagePixels;
    int yLength = imagePixels.length-1;

    for(int row = 0; row< imagePixels.length; row++){
      for(int col = 0; col<imagePixels[0].length; col++){
        pixels[row][col]=pixels[yLength-row][col];
      }
    }

    return newImage;
  }

  @Override
  public Image flipHorizontally() {

    Image newImage = this;
    Pixel[][] pixels = newImage.imagePixels;
    int xLength = imagePixels[0].length-1;

    for(int row = 0; row< imagePixels.length; row++){
      for(int col = 0; col<imagePixels[0].length; col++){
        pixels[row][col]=pixels[row][xLength-col];
      }
    }

    return newImage;
  }

  @Override
  public Image showR() {
    Pixel[][] newImage = new Pixel[this.imagePixels.length][this.imagePixels[0].length];
    for (int row = 0; row < this.imagePixels.length; row++) {
      for (int col = 0; col < this.imagePixels[0].length; col++) {
        int red = this.imagePixels[row][col].r;
        newImage[row][col]  = new Pixel(col,row,red,red,red);
      }
    }
    return new Image(newImage);
  }

  @Override
  public Image showG() {
    Pixel[][] newImage = new Pixel[this.imagePixels.length][this.imagePixels[0].length];
    for (int row = 0; row < this.imagePixels.length; row++) {
      for (int col = 0; col < this.imagePixels[0].length; col++) {
        int green = this.imagePixels[row][col].g;
        newImage[row][col]  = new Pixel(col,row,green,green,green);
      }
    }
    return new Image(newImage);
  }

  @Override
  public Image showB() {
    Pixel[][] newImage = new Pixel[this.imagePixels.length][this.imagePixels[0].length];
    for (int row = 0; row < this.imagePixels.length; row++) {
      for (int col = 0; col < this.imagePixels[0].length; col++) {
        int blue = this.imagePixels[row][col].b;
        newImage[row][col]  = new Pixel(col,row,blue,blue,blue);
      }
    }
    return new Image(newImage);
  }

  @Override
  public Image adjustBrightness(int increment) {
    return null;
  }

  @Override
  public Image showLuma() {
    return null;
  }

  @Override
  public Image showIntensity() {
    return null;
  }

  @Override
  public Image showValue() {
    return null;
  }


}
