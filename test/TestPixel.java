import org.junit.Test;

import model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Tests the {@code Pixel}.
 */
public class TestPixel {
  @Test
  public void testValidInitialization(){
    Pixel p = new Pixel(0,255,1);
  }

  @Test
  public void testInvalidInitialization(){
    try {
      new Pixel(-1,1,1);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "color invalid");
    }

    try {
      new Pixel(0,-1,1);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "color invalid");
    }

    try {
      new Pixel(1,1,-1);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "color invalid");
    }

    try {
      new Pixel(13,14,321);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "color invalid");
    }

    try {
      new Pixel(255,256,1);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "color invalid");
    }

    try {
      new Pixel(1000,1,1);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "color invalid");
    }
  }

}
