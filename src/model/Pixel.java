package model;

import java.util.Objects;

/**
 * Represents a singular pixel found in an image.
 */
public class Pixel {
  private final int r;
  private final int g;
  private final int b;

  private final int alpha;

  /**
   * Constructs a new Pixel by its position and color on the image.
   *
   * @param r the red value
   * @param g the green value
   * @param b the blue value
   * @throws IllegalArgumentException if color values are out of bounds
   */
  public Pixel(int r, int g, int b) throws IllegalArgumentException {
    if (r < 0 || r > 255 | g < 0 || g > 255 || b < 0 || b > 255) {
      throw new IllegalArgumentException("color invalid");
    }

    this.r = r;
    this.g = g;
    this.b = b;
    this.alpha = 1;

  }

  /**
   * Constructs a new Pixel by its position and color on the image.
   *
   * @param r the red value
   * @param g the green value
   * @param b the blue value
   * @throws IllegalArgumentException if color values are out of bounds
   */
  public Pixel(int r, int g, int b, int alpha) throws IllegalArgumentException {
    if (r < 0 || r > 255 | g < 0 || g > 255 || b < 0 || b > 255) {
      throw new IllegalArgumentException("color invalid");
    }

    this.r = r;
    this.g = g;
    this.b = b;
    this.alpha = alpha;

  }

  /**
   * Finds the max rgb value of a pixel.
   *
   * @return the max rgb value
   */
  protected int findValue() {
    return Math.max(Math.max(this.r, this.g), this.b);
  }

  /**
   * Finds the intensity of a pixel.
   *
   * @return the intensity
   */
  protected int findIntensity() {
    return (this.r + this.g + this.b) / 3;
  }

  /**
   * Finds the luma of a pixel.
   *
   * @return the luma
   */
  protected int findLuma() {
    return (int) ((0.2126 * this.r) + (0.7152 * this.g) + (0.0722 * this.b));
  }

  /**
   * Overrides the equals method, to compare pixels. Checks if the red, green, and blue values are
   * the same.
   *
   * @param o is the pixel to compare to
   * @return true if the pixels are equal, false otherwise
   */
  public boolean equals(Object o) {
    if (o instanceof Pixel) {
      return this.r == ((Pixel) o).r && this.g == ((Pixel) o).g && this.b == ((Pixel) o).b
              && this.alpha == ((Pixel) o).alpha;
    }
    return false;
  }

  /**
   * Overrides the hashcode method, to compare pixels.
   *
   * @return a new integer hashcode.
   */
  public int hashCode() {
    return Objects.hashCode(this);
  }

  /**
   * Gets the red value of the pixel.
   * @return an integer representing the red value
   */
  public int getRed() {
    return r;
  }

  /**
   * Gets the green value of the pixel.
   * @return an integer representing the green value
   */
  public int getGreen() {
    return g;
  }

  /**
   * Gets the blue value of the pixel.
   * @return an integer representing the blue value
   */
  public int getBlue() {
    return b;
  }

  /**
   * Gets the alpha value of the pixel.
   * @return an integer representing the alpha value
   */
  public int getAlpha() {
    return alpha;
  }

}
