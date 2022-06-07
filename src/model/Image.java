package model;

/**
 * Represents an image, which is a group of pixels. The image is represented by a 2D array of pixels.
 */
public class Image implements ImageEditor{

  public Pixel[][] imagePixels;

  /**
   * Constructor for the Image class. Creates a new image with the inputted 2d array of pixels.
   * @param imagePixels is the 2d array of pixels that represents the image.
   */
  public Image(Pixel[][] imagePixels){
    if(imagePixels == null){
      throw new IllegalArgumentException("Image pixels cannot be null");
    }
    this.imagePixels = imagePixels;
  }

  @Override
  public void flipVertically() {

    int yLength = imagePixels.length-1;

    //System.arraycopy(pixels[yLength - row], 0, pixels[row], 0, imagePixels[0].length);
    for(int row = 0; row< imagePixels.length; row++){
      for(int col = 0; col<imagePixels[0].length; col++){
        imagePixels[row][col]=imagePixels[yLength-row][col];
      }
    }

  }

  @Override
  public void flipHorizontally() {

    int xLength = imagePixels[0].length-1;

    for(int row = 0; row< imagePixels.length; row++){
      for(int col = 0; col<imagePixels[0].length; col++){
        imagePixels[row][col]=imagePixels[row][xLength-col];
      }
    }
  }

  @Override
  public void redGreyscale() {
    for (int row = 0; row < this.imagePixels.length; row++) {
      for (int col = 0; col < this.imagePixels[0].length; col++) {
        int red = this.imagePixels[row][col].r;
        imagePixels[row][col]  = new Pixel(red,red,red);
      }
    }
  }

  @Override
  public void greenGreyscale() {
    for (int row = 0; row < this.imagePixels.length; row++) {
      for (int col = 0; col < this.imagePixels[0].length; col++) {
        int green = this.imagePixels[row][col].g;
        imagePixels[row][col]  = new Pixel(green,green,green);
      }
    }
  }

  @Override
  public void blueGreyscale() {
    for (int row = 0; row < this.imagePixels.length; row++) {
      for (int col = 0; col < this.imagePixels[0].length; col++) {
        int blue = this.imagePixels[row][col].b;
        imagePixels[row][col]  = new Pixel(blue,blue,blue);
      }
    }
  }

  @Override
  public void adjustBrightness(int increment) {
    for(int row = 0; row< imagePixels.length; row++) {
      for (int col = 0; col < imagePixels[0].length; col++) {
        int newRed =  this.imagePixels[row][col].r + increment;
        int newGreen = this.imagePixels[row][col].g + increment;
        int newBlue = this.imagePixels[row][col].b + increment;
        if (newRed > 255) {
          newRed = 255;
        }
        if (newGreen > 255) {
          newGreen = 255;
        }
        if (newBlue > 255) {
          newBlue = 255;
        }
        if (newRed < 0) {
          newRed = 0;
        }
        if (newGreen < 0) {
          newGreen = 0;
        }
        if (newBlue < 0) {
          newBlue = 0;
        }
        imagePixels[row][col] = new Pixel(newRed, newGreen, newBlue);
      }
    }
  }

  @Override
  public void lumaGreyscale() {
    for (int row = 0; row < this.imagePixels.length; row++) {
      for (int col = 0; col < this.imagePixels[0].length; col++) {
        int luma= this.imagePixels[row][col].findLuma();
        imagePixels[row][col]  = new Pixel(luma,luma,luma);
      }
    }
  }

  @Override
  public void intensityGreyscale() {
    for (int row = 0; row < this.imagePixels.length; row++) {
      for (int col = 0; col < this.imagePixels[0].length; col++) {
        int intensity= this.imagePixels[row][col].findIntensity();
        imagePixels[row][col]  = new Pixel(intensity,intensity,intensity);
      }
    }
  }

  @Override
  public void valueGreyscale() {
    for (int row = 0; row < this.imagePixels.length; row++) {
      for (int col = 0; col < this.imagePixels[0].length; col++) {
        int value= this.imagePixels[row][col].findValue();
        imagePixels[row][col]  = new Pixel(value,value,value);
      }
    }
  }

}
