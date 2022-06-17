import java.io.IOException;
import java.util.Objects;

import controller.ImageController;

/**
 * Represents a mock controller used strictly for testing.
 */
public class MockController implements ImageController {
  private final Appendable ap;

  /**
   * Constructs a new mock controller for testing where the user sets the appendable
   * @param ap the appendable
   * @throws NullPointerException if appendable is null
   */
  public MockController(Appendable ap){
    Objects.requireNonNull(ap);
    this.ap = ap;
  }
  @Override
  public void start() throws IOException {

  }

  @Override
  public void loadImage(String pathname, String filename) throws IOException {
    this.ap.append("loaded " + pathname + " as " + filename);
  }

  @Override
  public void saveImage(String pathname, String filename) throws IOException {
    this.ap.append("saved " + filename + " as " + pathname);
  }
}
