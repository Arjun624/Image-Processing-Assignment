import java.io.IOException;
import org.junit.Test;

import view.ImageDisplay;

import static org.junit.Assert.assertEquals;

public class TestView {

  @Test
  public void testValidInit1() throws IOException {
    StringBuilder out = new StringBuilder("Go Nets!");
    ImageDisplay test = new ImageDisplay(out);


    assertEquals("Go Nets!", out.toString());
    test.renderMessage("Go Nuggets!");
    assertEquals("Go Nets!Go Nuggets!\n", out.toString());
  }



  @Test
  public void testRenderMessage() throws IOException {
    StringBuilder out = new StringBuilder();
    ImageDisplay test = new ImageDisplay(out);
    test.renderMessage("Go Broncos!");
    assertEquals("Go Broncos!\n", out.toString());


  }
}
