package model;

import java.util.Objects;

/**
 * Represents a singular pixel found in an image.
 */
public class Pixel {
  protected int r;
  protected int g;
  protected int b;

  /**
   * Constructs a new Pixel by its position and color on the image;
   * @param r the red value
   * @param g the blue value
   * @param b the green value
   * @throws IllegalArgumentException if the position or color values are out of bounds
   */
  public Pixel(int r, int g, int b) throws IllegalArgumentException {
    if (r < 0 || r > 255 | g < 0 || g > 255 || b < 0 || b > 255){
      throw new IllegalArgumentException("color invalid");
    }

    this.r = r;
    this.g = g;
    this.b = b;

  }

  /**
   * Finds the max rgb value of a pixel.
   * @return the max rgb value
   */
  protected int findValue(){
    return Math.max(Math.max(this.r, this.g), this.b);
  }

  /**
   * Finds the intensity of a pixel.
   * @return the intensity
   */
  protected int findIntensity(){
    return (this.r + this.g + this.b) / 3;
  }

  /**
   * Finds the luma of a pixel.
   * @return the luma
   */
  protected int findLuma(){
    return (int) ((0.2126 * this.r) + (0.7152 * this.g) + (0.0722 * this.b));
  }

  public boolean equals(Object o){
    if(o instanceof Pixel){
      return this.r == ((Pixel) o).r && this.g == ((Pixel) o).g && this.b == ((Pixel) o).b;
    }
    return false;
  }

  public int hashCode(){
    return Objects.hashCode(this);
  }

}
