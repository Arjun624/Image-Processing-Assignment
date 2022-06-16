import java.io.IOException;
import java.util.HashMap;

import controller.model.ImageEditor;
import controller.model.Pixel;

/**
 * A mock {@code ImageModel} used strictly for testing.
 */
public class MockImageModel implements ImageEditor {

  Appendable ap;
  boolean quit;

  public MockImageModel(Appendable ap) {
    this.ap = ap;
    this.quit = false;
  }

  @Override
  public void flipVertically(String filename, String newFilename) throws IOException {
    this.ap.append("flipped " + filename + " vertically. Is now " + newFilename + "\n");
  }

  @Override
  public void flipHorizontally(String filename, String newFilename) throws IOException {
    this.ap.append("flipped " + filename + " horizontally. Is now " + newFilename + "\n");
  }

  @Override
  public void redGreyscale(String filename, String newFilename) throws IOException {
    this.ap.append("preformed a red greyscale " + filename + ". Is now " + newFilename + "\n");
  }

  @Override
  public void greenGreyscale(String filename, String newFilename) throws IOException {
    this.ap.append("preformed a green greyscale " + filename + ". Is now " + newFilename + "\n");
  }

  @Override
  public void blueGreyscale(String filename, String newFilename) throws IOException {
    this.ap.append("preformed a blue greyscale " + filename + ". Is now " + newFilename + "\n");
  }

  @Override
  public void lumaGreyscale(String filename, String newFilename) throws IOException {
    this.ap.append("preformed a luma greyscale " + filename + ". Is now " + newFilename + "\n");
  }

  @Override
  public void intensityGreyscale(String filename, String newFilename) throws IOException {
    this.ap.append("preformed an intensity greyscale "
            + filename
            + ". Is now "
            + newFilename
            + "\n");
  }

  @Override
  public void valueGreyscale(String filename, String newFilename) throws IOException {
    this.ap.append("preformed a value greyscale " + filename + ". Is now " + newFilename + "\n");
  }

  @Override
  public void adjustBrightness(int increment, String filename, String newFilename)
          throws IOException {
    this.ap.append("adjusted " + filename + " by " + increment + ". Is now " + newFilename + "\n");
  }


  @Override
  public boolean getStatus() {
    return this.quit;
  }

  @Override
  public void quit() throws IOException {
    this.quit = true;
    this.ap.append("program quit");
  }

  @Override
  public void add(String imageName, Pixel[][] arr) throws IOException {
    this.ap.append("added " + imageName + " to hashmap\n");
  }

  @Override
  public HashMap<String, Pixel[][]> getMap() {
    return null;
  }

  @Override
  public int findTotalValue(String filename) {
    return 0;
  }

  @Override
  public void sharpenImage(String fileName, String newFileName) throws IOException, IllegalArgumentException {
    this.ap.append("sharpened " + fileName + ". Is now " + newFileName + "\n");
  }
}
