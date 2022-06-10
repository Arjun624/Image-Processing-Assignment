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

  @Test
  public void testWelcomeMessage() throws IOException {
    StringBuilder out = new StringBuilder();
    ImageDisplay test = new ImageDisplay(out);
    test.displayWelcomeMessage();
    assertEquals("Welcome to the Image Program!", out.toString().split("\n")[0]);
    assertEquals("Valid Commands include:", out.toString().split("\n")[1]);
    assertEquals("(1) 'load' <filepath> <filename>", out.toString().split("\n")[2]);
    assertEquals("(2) 'save' <filepath> <filename>", out.toString().split("\n")[3]);
    assertEquals("(3) 'adjust-brightness' <increment> <filename> <new filename>",
            out.toString().split("\n")[4]);
    assertEquals("(4) 'vertical-flip' <filename> <new filename>",
            out.toString().split("\n")[5]);
    assertEquals("(5) 'horizontal-flip' <filename> <new filename>",
            out.toString().split("\n")[6]);
    assertEquals("(6) 'greyscale-red' <filename> <new filename>",
            out.toString().split("\n")[7]);
    assertEquals("(7) 'greyscale-green' <filename> <new filename>",
            out.toString().split("\n")[8]);
    assertEquals("(8) 'greyscale-blue' <filename> <new filename>",
            out.toString().split("\n")[9]);
    assertEquals("(9) 'luma' <filename> <new filename>",
            out.toString().split("\n")[10]);
    assertEquals("(10) 'intensity' <filename> <new filename>",
            out.toString().split("\n")[11]);
    assertEquals("(11) 'max' <filepath> <filename>",
            out.toString().split("\n")[12]);
    assertEquals("(12) 'q' to quit", out.toString().split("\n")[13]);

  }
}
