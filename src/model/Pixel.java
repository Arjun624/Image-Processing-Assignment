package model;

/**
 * represnets a singular pixel in an image.
 */
public class Pixel {
  protected int x;
  protected int y;
  protected int r;
  protected int g;
  protected int b;

  /**
   * Constructs a new Pixel by its position and color on the image;
   * @param x the x co-ordinate
   * @param y the y co-ordinate
   * @param r the red value
   * @param g the blue value
   * @param b the green value
   * @throws IllegalArgumentException if the position or color values are out of bounds
   */
  public Pixel(int x, int y, int r, int g, int b) throws IllegalArgumentException{
    if(x < 0 || y < 0 ){
      throw new IllegalArgumentException("invalid position");
    }
    if (r < 0 || r > 255 | g < 0 || g > 255 || b < 0 || b > 255){
      throw new IllegalArgumentException("color invalid");
    }
  }

}
