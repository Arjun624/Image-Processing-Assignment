import java.io.IOException;
import org.junit.Test;

import view.ImageDisplay;

import static org.junit.Assert.assertEquals;

public class TestView {

  @Test
  public void testRenderMessage() throws IOException {

    StringBuilder out = new StringBuilder();
    ImageDisplay test = new ImageDisplay(out);
    test.renderMessage("Go Broncos!");
    assertEquals("Go Broncos!\n", out.toString());
  }
}
