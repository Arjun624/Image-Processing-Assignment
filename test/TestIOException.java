import org.junit.Test;

import java.io.IOException;

import view.ImageDisplay;
import view.ImageView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test that checks if an InputOutput exception is thrown if the transmission fails.
 */
public class TestIOException {

  @Test
  public void testIO() {
    ImageView v = new ImageDisplay(new MockAppendable());
    try {
      v.renderMessage("hello");
      fail("Should have thrown an exception");
    } catch (IOException e) {
      assertEquals(e.getMessage(), "IO");
    }
    try {
      v.displayWelcomeMessage();
      fail("Should have thrown an exception");
    } catch (IOException e) {
      assertEquals(e.getMessage(), "IO");
    }
  }
}
