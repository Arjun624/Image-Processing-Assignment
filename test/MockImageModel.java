import java.io.IOException;
import java.util.HashMap;

import controller.ImageCommands;
import model.ImageEditor;
import model.Pixel;

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
  public void adjustBrightness(int increment, String filename, String newFilename)
          throws IOException {
    this.ap.append("adjusted " + filename + " by " + increment + ". Is now " + newFilename + "\n");
  }

  @Override
  public void greyscale(String filename, String newFilename, String type) throws IOException {
    this.ap.append("preformed a greyscale of type " + type + " on " + filename +
            ". Is now " + newFilename + "\n");
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
  public void filterImage(String fileName, String newFileName, double[][] kernal)
          throws IOException, IllegalArgumentException {
    this.ap.append("filtered " + fileName + ". Is now " + newFileName + "\n");
  }

  @Override
  public void colorTransform(float[][] colors, String filename, String newFilename)
          throws IOException, IllegalArgumentException {
    this.ap.append("transformed " + filename + ". Is now " + newFilename + "\n");
  }

  @Override
  public void imageDownscale(int height, int width, String filename, String newFilename) throws IOException, IllegalArgumentException {
    this.ap.append(String.format("Image downscaled to %dx%d"),height,width);
  }

  @Override
  public void partialImageManipulation(String maskName, String filename, String newFilename, ImageCommands c) throws IllegalArgumentException, IOException {
    this.ap.append("Image partially manipulated");
  }

}
