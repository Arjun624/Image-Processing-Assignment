import java.io.IOException;
import java.util.Objects;

import controller.ImageController;

/**
 * Represents a mock controller used strictly for testing.
 */
public class MockControllerText implements ImageController {
  private final Appendable ap;

  /**
   * Constructs a new mock controller for testing where the user sets the appendable.
   *
   * @param ap the appendable
   * @throws NullPointerException if appendable is null
   */
  public MockControllerText(Appendable ap) {
    Objects.requireNonNull(ap);
    this.ap = ap;
  }

  @Override
  public void start()  {
    // do nothing
  }

  @Override
  public void loadImage(String pathname, String filename) throws IOException {
    this.ap.append("loaded ").append(pathname).append(" as ").append(filename);
  }

  @Override
  public void saveImage(String pathname, String filename) throws IOException {
    this.ap.append("saved ").append(filename).append(" as ").append(pathname);
  }
}
