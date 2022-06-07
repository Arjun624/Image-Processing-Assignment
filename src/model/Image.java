package model;

public class Image implements ImageEditor{

  Pixel[][] imagePixels;

  public Image(Pixel[][] imagePixels){
    this.imagePixels = imagePixels;
  }

  @Override
  public Image flipVertically() {
    Image newImage = this;
    int middle = this.imagePixels.length/2;
    Pixel[][] pixels = newImage.imagePixels;

    for(int i = 0; i< imagePixels.length; i++){
      for(int j = 0; i<imagePixels[0].length; j++){
        if(pixels[i][j].x<middle){
          pixels[i][j].x=middle+(middle-pixels[i][j].x);
        } else if(pixels[i][j].x>middle){
          pixels[i][j].x=middle-(pixels[i][j].x-middle);
        }
      }
    }

    return newImage;
  }

  @Override
  public Image flipHorizontally() {
    Image newImage = this;
    int middle = this.imagePixels[0].length/2;
    Pixel[][] pixels = newImage.imagePixels;

    for(int i = 0; i< imagePixels.length; i++){
      for(int j = 0; i<imagePixels[0].length; j++){
        if(pixels[i][j].y<middle){
          pixels[i][j].y=middle+(middle-pixels[i][j].y);
        } else if(pixels[i][j].y>middle){
          pixels[i][j].y=middle-(pixels[i][j].y-middle);
        }
      }
    }

    return newImage;
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
