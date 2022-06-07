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
    this.imagePixels = imagePixels;
  }

  @Override
  public Image flipVertically() {

    Pixel[][] newImagePixels = new Pixel[this.imagePixels.length][this.imagePixels[0].length];
    int yLength = imagePixels.length-1;

    //System.arraycopy(pixels[yLength - row], 0, pixels[row], 0, imagePixels[0].length);
    for(int row = 0; row< imagePixels.length; row++){
      for(int col = 0; col<imagePixels[0].length; col++){
        newImagePixels[row][col]=imagePixels[yLength-row][col];
      }
    }

    return new Image(newImagePixels);
  }

  @Override
  public Image flipHorizontally() {

    Pixel[][] newImagePixels = new Pixel[this.imagePixels.length][this.imagePixels[0].length];
    int xLength = imagePixels[0].length-1;

    for(int row = 0; row< imagePixels.length; row++){
      for(int col = 0; col<imagePixels[0].length; col++){
        newImagePixels[row][col]=imagePixels[row][xLength-col];
      }
    }

    return new Image(newImagePixels);
  }

  @Override
  public Image showR() {
    Pixel[][] newImagePixels = new Pixel[this.imagePixels.length][this.imagePixels[0].length];
    for (int row = 0; row < this.imagePixels.length; row++) {
      for (int col = 0; col < this.imagePixels[0].length; col++) {
        int red = this.imagePixels[row][col].r;
        newImagePixels[row][col]  = new Pixel(red,red,red);
      }
    }
    return new Image(newImagePixels);
  }

  @Override
  public Image showG() {
    Pixel[][] newImagePixels = new Pixel[this.imagePixels.length][this.imagePixels[0].length];
    for (int row = 0; row < this.imagePixels.length; row++) {
      for (int col = 0; col < this.imagePixels[0].length; col++) {
        int green = this.imagePixels[row][col].g;
        newImagePixels[row][col]  = new Pixel(green,green,green);
      }
    }
    return new Image(newImagePixels);
  }

  @Override
  public Image showB() {
    Pixel[][] newImagePixels = new Pixel[this.imagePixels.length][this.imagePixels[0].length];
    for (int row = 0; row < this.imagePixels.length; row++) {
      for (int col = 0; col < this.imagePixels[0].length; col++) {
        int blue = this.imagePixels[row][col].b;
        newImagePixels[row][col]  = new Pixel(blue,blue,blue);
      }
    }
    return new Image(newImagePixels);
  }

  @Override
  public Image adjustBrightness(int increment) {
    Pixel[][] newImagePixels = new Pixel[this.imagePixels.length][this.imagePixels[0].length];
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
        newImagePixels[row][col] = new Pixel(newRed, newGreen, newBlue);
      }
    }
    return new Image(newImagePixels);
  }

  @Override
  public Image showLuma() {
    //0.2126*R + 0.7152*G + 0.0722*B
    return null;
  }

  @Override
  public Image showIntensity() {
    //average of r,g,b
    return null;
  }

  @Override
  public Image showValue() {
    //max of r,g,b
    return null;
  }

  //Grey scale methods?


}
