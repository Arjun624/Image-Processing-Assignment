import java.io.IOException;

import model.ImageEditor;

/**
 * A mock {@code ImageModel} used strictly for testing.
 */
public class MockImageModel implements ImageEditor {

  Appendable ap;

  public MockImageModel(Appendable ap){
    this.ap = ap;
  }
  @Override
  public void flipVertically(String filename, String newFilename) throws IOException {
    this.ap.append("flipped " + filename + " vertically. Is now " + newFilename);
  }

  @Override
  public void flipHorizontally(String filename, String newFilename) throws IOException {
    this.ap.append("flipped " + filename + " Horizontally. Is now " + newFilename);
  }

  @Override
  public void redGreyscale(String filename, String newFilename) throws IOException {
    this.ap.append("preformed a red greyscale " + filename + ". Is now " + newFilename);
  }

  @Override
  public void greenGreyscale(String filename, String newFilename) throws IOException {
    this.ap.append("preformed a green greyscale " + filename + ". Is now " + newFilename);
  }

  @Override
  public void blueGreyscale(String filename, String newFilename) throws IOException {
    this.ap.append("preformed a blue greyscale " + filename + ". Is now " + newFilename);
  }

  @Override
  public void lumaGreyscale(String filename, String newFilename) throws IOException {
    this.ap.append("preformed a luma greyscale " + filename + ". Is now " + newFilename);
  }

  @Override
  public void intensityGreyscale(String filename, String newFilename) throws IOException {
    this.ap.append("preformed an intensity greyscale " + filename + ". Is now " + newFilename);
  }

  @Override
  public void valueGreyscale(String filename, String newFilename) throws IOException {
    this.ap.append("preformed a value greyscale " + filename + ". Is now " + newFilename);
  }

  @Override
  public void adjustBrightness(int increment, String filename, String newFilename)
          throws IOException {
    this.ap.append("adjusted " + filename + " by "+ increment + ". Is now " + newFilename);
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
