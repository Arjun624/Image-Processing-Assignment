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
    this.ap.append("flipped ").append(filename).append(" vertically. Is now ").append(newFilename).append("\n");
  }

  @Override
  public void flipHorizontally(String filename, String newFilename) throws IOException {
    this.ap.append("flipped ").append(filename).append(" horizontally. Is now ").append(newFilename).append("\n");
  }

  @Override
  public void redGreyscale(String filename, String newFilename) throws IOException {
    this.ap.append("preformed a red greyscale ").append(filename).append(". Is now ").append(newFilename).append("\n");
  }

  @Override
  public void greenGreyscale(String filename, String newFilename) throws IOException {
    this.ap.append("preformed a green greyscale ").append(filename).append(". Is now ").append(newFilename).append("\n");
  }

  @Override
  public void blueGreyscale(String filename, String newFilename) throws IOException {
    this.ap.append("preformed a blue greyscale ").append(filename).append(". Is now ").append(newFilename).append("\n");
  }

  @Override
  public void lumaGreyscale(String filename, String newFilename) throws IOException {
    this.ap.append("preformed a luma greyscale ").append(filename).append(". Is now ").append(newFilename).append("\n");
  }

  @Override
  public void intensityGreyscale(String filename, String newFilename) throws IOException {
    this.ap.append("preformed an intensity greyscale ").append(filename).append(". Is now ").append(newFilename).append("\n");
  }

  @Override
  public void valueGreyscale(String filename, String newFilename) throws IOException {
    this.ap.append("preformed a value greyscale ").append(filename).append(". Is now ").append(newFilename).append("\n");
  }

  @Override
  public void adjustBrightness(int increment, String filename, String newFilename)
          throws IOException {
    this.ap.append("adjusted ").append(filename).append(" by ").append(String.valueOf(increment)).append(". Is now ").append(newFilename).append("\n");
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
    this.ap.append("added ").append(imageName).append(" to hashmap\n");
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
  public void filterImage(String fileName, String newFileName, double[][] kernal) throws IOException, IllegalArgumentException {

  }

  @Override
  public void colorTransform(float[][] colors, String filename, String newFilename) throws IOException, IllegalArgumentException {

  }


}
