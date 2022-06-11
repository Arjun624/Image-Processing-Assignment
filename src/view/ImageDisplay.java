package view;

import java.io.IOException;

/**
 * Represents a way to display messages to the user.
 */
public class ImageDisplay implements ImageView {
  Appendable ap;

  /**
   * Constructs a {@code ImageDisplay} where the user sets the appendable object.
   *
   * @param ap the appendable object
   */
  public ImageDisplay(Appendable ap) {
    if (ap == null) {
      throw new IllegalArgumentException("Appendable cannot be null");
    }
    this.ap = ap;
  }


  @Override
  public void renderMessage(String message) throws IOException {
    this.ap.append(message).append(System.lineSeparator());
  }

  @Override
  public void displayWelcomeMessage() throws IOException {
    this.renderMessage("Welcome to the Image Program!");
    this.renderMessage("Valid Commands include:");
    this.renderMessage("(1) 'load' <filepath> <filename>");
    this.renderMessage("(2) 'save' <filepath> <filename>");
    this.renderMessage("(3) 'adjust-brightness' <increment> <filename> <new filename>");
    this.renderMessage("(4) 'vertical-flip' <filename> <new filename>");
    this.renderMessage("(5) 'horizontal-flip' <filename> <new filename>");
    this.renderMessage("(6) 'greyscale-red' <filename> <new filename>");
    this.renderMessage("(7) 'greyscale-green' <filename> <new filename>");
    this.renderMessage("(8) 'greyscale-blue' <filename> <new filename>");
    this.renderMessage("(9) 'luma' <filename> <new filename>");
    this.renderMessage("(10) 'intensity' <filename> <new filename>");
    this.renderMessage("(11) 'value' <filename> <new filename>");
    this.renderMessage("(12) 'q' to quit");
  }
}
