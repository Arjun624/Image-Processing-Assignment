import org.junit.Test;

import model.Pixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests the {@code Pixel}.
 */
public class TestPixel {
  @Test
  public void testValidInitialization() {
    Pixel p = new Pixel(0, 255, 1);
    assertEquals(0, p.getRed());
    assertEquals(255, p.getGreen());
    assertEquals(1, p.getBlue());
    assertEquals(255,p.getAlpha());
    Pixel p2 = new Pixel(0, 255, 1,12);
    assertEquals(0, p2.getRed());
    assertEquals(255, p2.getGreen());
    assertEquals(1, p2.getBlue());
    assertEquals(12,p2.getAlpha());
  }

  @Test
  public void testInvalidInitialization() {
    try {
      new Pixel(-1, 1, 1);
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "color invalid");
    }

    try {
      new Pixel(0, -1, 1);
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "color invalid");
    }

    try {
      new Pixel(1, 1, -1);
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "color invalid");
    }

    try {
      new Pixel(13, 14, 321);
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "color invalid");
    }

    try {
      new Pixel(255, 256, 1);
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "color invalid");
    }

    try {
      new Pixel(1000, 1, 1);
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "color invalid");
    }
  }

}
