import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import controller.ImageCommands;
import controller.ImageControllerImpl;
import model.ImageEditor;
import model.ImageModel;
import model.Pixel;
import view.ImageDisplay;
import view.ImageView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests the {@code ImageModel}.
 */
public class TestModel {

  @Test
  public void testValidInitialization() {
    ImageModel m = new ImageModel();
    ImageView v = new ImageDisplay(System.out);
    assertFalse(m.quit);
    assertTrue(m.images.isEmpty());

    HashMap<String, Pixel[][]> testMap = new HashMap<>();
    String file = "test";
    Pixel[][] arr = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr[i][j] = new Pixel(i, j, 1);
      }
    }
    testMap.put(file, arr);
    ImageModel m1 = new ImageModel(testMap, v);
    assertFalse(m1.quit);
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(m1.images.get(file)[i][j], new Pixel(i, j, 1));
      }
    }
  }

  @Test
  public void testNullMap() {
    ImageView v = new ImageDisplay(System.out);
    try {
      new ImageModel(null, v);
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "HashMap or ImageView cannot be null");
    }
  }

  @Test
  public void testNullView() {
    HashMap<String, Pixel[][]> testMap = new HashMap<>();
    try {
      new ImageModel(testMap, null);
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "HashMap or ImageView cannot be null");
    }
  }

  @Test
  public void testFlipVertically() throws IOException {
    ImageView v = new ImageDisplay(new StringBuilder());
    HashMap<String, Pixel[][]> testMap = new HashMap<>();
    String file = "test";
    Pixel[][] arr = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr[i][j] = new Pixel(i, j, 1);
      }
    }
    testMap.put(file, arr);
    ImageModel m1 = new ImageModel(testMap, v);
    m1.flipVertically("test", "test-vf");
    Pixel[][] arr1 = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr1[2 - i][j] = new Pixel(i, j, 1);
      }
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(m1.images.get("test-vf")[i][j], arr1[i][j]);
      }
    }

    try {
      m1.flipVertically("yes", "no");
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "file doesn't exist");
    }
  }

  @Test
  public void testFLipHorizontally() throws IOException {
    ImageView v = new ImageDisplay(new StringBuilder());
    HashMap<String, Pixel[][]> testMap = new HashMap<>();
    String file = "test";
    Pixel[][] arr = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr[i][j] = new Pixel(i, j, 1);
      }
    }
    testMap.put(file, arr);
    ImageModel m1 = new ImageModel(testMap, v);
    m1.flipHorizontally("test", "test-hf");
    Pixel[][] arr1 = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr1[i][2 - j] = new Pixel(i, j, 1);
      }
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(m1.images.get("test-hf")[i][j], arr1[i][j]);
      }
    }

    try {
      m1.flipHorizontally("yes", "no");
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "file doesn't exist");
    }
  }

  @Test
  public void testRedGreyscale() throws IOException {
    ImageView v = new ImageDisplay(new StringBuilder());
    HashMap<String, Pixel[][]> testMap = new HashMap<>();
    String file = "test";
    Pixel[][] arr = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr[i][j] = new Pixel(110 + i, 120, 130);
      }
    }
    testMap.put(file, arr);
    ImageModel m1 = new ImageModel(testMap, v);
    m1.greyscale("test", "test-r", "red");
    Pixel[][] arr1 = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr1[i][j] = new Pixel(110 + i, 110 + i, 110 + i);
      }
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(m1.images.get("test-r")[i][j], arr1[i][j]);
      }
    }

    try {
      m1.greyscale("yes", "no", "red");
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "file doesn't exist");
    }
  }

  @Test
  public void testGreenGreyscale() throws IOException {
    ImageView v = new ImageDisplay(new StringBuilder());
    HashMap<String, Pixel[][]> testMap = new HashMap<>();
    String file = "test";
    Pixel[][] arr = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr[i][j] = new Pixel(110, 120 + j, 130);
      }
    }
    testMap.put(file, arr);
    ImageModel m1 = new ImageModel(testMap, v);
    m1.greyscale("test", "test-g", "green");
    Pixel[][] arr1 = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr1[i][j] = new Pixel(120 + j, 120 + j, 120 + j);
      }
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(m1.images.get("test-g")[i][j], arr1[i][j]);
      }
    }

    try {
      m1.greyscale("yes", "no", "green");
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "file doesn't exist");
    }
  }

  @Test
  public void testBlueGreyscale() throws IOException {
    ImageView v = new ImageDisplay(new StringBuilder());
    HashMap<String, Pixel[][]> testMap = new HashMap<>();
    String file = "test";
    Pixel[][] arr = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr[i][j] = new Pixel(110, 120, 130 + i + j);
      }
    }
    testMap.put(file, arr);
    ImageModel m1 = new ImageModel(testMap, v);
    m1.greyscale("test", "test-b", "blue");
    Pixel[][] arr1 = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr1[i][j] = new Pixel(130 + i + j, 130 + i + j, 130 + i + j);
      }
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(m1.images.get("test-b")[i][j], arr1[i][j]);
      }
    }

    try {
      m1.greyscale("yes", "no", "blue");
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "file doesn't exist");
    }
  }

  @Test
  public void testAdjustBrightness() throws IOException {
    ImageView v = new ImageDisplay(new StringBuilder());
    HashMap<String, Pixel[][]> testMap = new HashMap<>();
    String file = "test";
    Pixel[][] arr = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr[i][j] = new Pixel(i, j, i + j);
      }
    }
    testMap.put(file, arr);
    ImageModel m1 = new ImageModel(testMap, v);
    m1.adjustBrightness(10, "test", "test-b1");
    Pixel[][] arr1 = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr1[i][j] = new Pixel(i + 10, j + 10, i + j + 10);
      }
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(m1.images.get("test-b1")[i][j], arr1[i][j]);
      }
    }

    ImageView v2 = new ImageDisplay(new StringBuilder());
    HashMap<String, Pixel[][]> testMap2 = new HashMap<>();
    String file2 = "test";
    Pixel[][] arr2 = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr2[i][j] = new Pixel(100, 100, 100);
      }
    }
    testMap2.put(file2, arr2);
    ImageModel m2 = new ImageModel(testMap2, v2);
    m2.adjustBrightness(-10, "test", "test-b2");
    Pixel[][] testArr2 = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        testArr2[i][j] = new Pixel(90, 90, 90);
      }
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(m2.images.get("test-b2")[i][j], testArr2[i][j]);
      }
    }

    ImageView v3 = new ImageDisplay(new StringBuilder());
    HashMap<String, Pixel[][]> testMap3 = new HashMap<>();
    String file3 = "test";
    Pixel[][] arr3 = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr3[i][j] = new Pixel(250, 250, 250);
      }
    }
    testMap3.put(file3, arr3);
    ImageModel m3 = new ImageModel(testMap3, v3);
    m3.adjustBrightness(10, "test", "test-b3");
    Pixel[][] testArr3 = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        testArr3[i][j] = new Pixel(255, 255, 255);
      }
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(m3.images.get("test-b3")[i][j], testArr3[i][j]);
      }
    }

    ImageView v4 = new ImageDisplay(new StringBuilder());
    HashMap<String, Pixel[][]> testMap4 = new HashMap<>();
    String file4 = "test";
    Pixel[][] arr4 = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr4[i][j] = new Pixel(5, 5, 5);
      }
    }
    testMap4.put(file4, arr4);
    ImageModel m4 = new ImageModel(testMap4, v4);
    m4.adjustBrightness(-10, "test", "test-b4");
    Pixel[][] testArr4 = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        testArr4[i][j] = new Pixel(0, 0, 0);
      }
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(m4.images.get("test-b4")[i][j], testArr4[i][j]);
      }
    }

    try {
      m4.adjustBrightness(40, "yes", "no");
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "file doesn't exist");
    }
  }

  @Test
  public void testLumaGreyscale() throws IOException {
    ImageView v = new ImageDisplay(new StringBuilder());
    HashMap<String, Pixel[][]> testMap = new HashMap<>();
    String file = "test";
    Pixel[][] arr = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr[i][j] = new Pixel(i, j, i + j);
      }
    }
    testMap.put(file, arr);
    ImageModel m1 = new ImageModel(testMap, v);
    m1.greyscale("test", "test-lg", "luma");
    Pixel[][] arr1 = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        int luma = (int) ((0.2126 * i) + (0.7152 * j) + (0.0722 * (i + j)));
        arr1[i][j] = new Pixel(luma, luma, luma);
      }
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(m1.images.get("test-lg")[i][j], arr1[i][j]);
      }
    }

    try {
      m1.greyscale("yes", "no", "luma");
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "file doesn't exist");
    }
  }

  @Test
  public void testIntensityGreyscale() throws IOException {
    ImageView v = new ImageDisplay(new StringBuilder());
    HashMap<String, Pixel[][]> testMap = new HashMap<>();
    String file = "test";
    Pixel[][] arr = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr[i][j] = new Pixel(i, j, i + j);
      }
    }
    testMap.put(file, arr);
    ImageModel m1 = new ImageModel(testMap, v);
    m1.greyscale("test", "test-ig", "intensity");
    Pixel[][] arr1 = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        int intensity = (2 * i + 2 * j) / 3;
        arr1[i][j] = new Pixel(intensity, intensity, intensity);
      }
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(m1.images.get("test-ig")[i][j], arr1[i][j]);
      }
    }
    try {
      m1.greyscale("yes", "no", "intensity");
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "file doesn't exist");
    }
  }

  @Test
  public void testValueGreyscale() throws IOException {
    ImageView v = new ImageDisplay(new StringBuilder());
    HashMap<String, Pixel[][]> testMap = new HashMap<>();
    String file = "test";
    Pixel[][] arr = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr[i][j] = new Pixel(i, j, i + j);
      }
    }
    testMap.put(file, arr);
    ImageModel m1 = new ImageModel(testMap, v);
    m1.greyscale("test", "test-vg", "value");
    Pixel[][] arr1 = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr1[i][j] = new Pixel(i + j, i + j, i + j);
      }
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(m1.images.get("test-vg")[i][j], arr1[i][j]);
      }
    }

    try {
      m1.greyscale("yes", "no", "value");
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "file doesn't exist");
    }
  }

  @Test
  public void testBlur() throws IOException {
    ImageView v = new ImageDisplay(new StringBuilder());
    HashMap<String, Pixel[][]> testMap = new HashMap<>();
    String file = "test";
    Pixel[][] arr = new Pixel[2][2];
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        arr[i][j] = new Pixel(110 + i, 120 - (3 * j), 130);
      }
    }
    testMap.put(file, arr);
    ImageModel m1 = new ImageModel(testMap, v);
    double[][] kernal = new double[][]{
            {0.0625, 0.125, 0.0625},
            {0.125, 0.25, 0.125},
            {0.0625, 0.125, 0.0625},
    };
    m1.filterImage("test", "test-b", kernal);
    Pixel[][] arr1 = new Pixel[2][2];
    arr1[0][0] = new Pixel(62, 67, 73);
    arr1[0][1] = new Pixel(62, 66, 73);
    arr1[1][0] = new Pixel(62, 67, 73);
    arr1[1][1] = new Pixel(62, 66, 73);


    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        assertEquals(m1.images.get("test-b")[i][j], arr1[i][j]);
      }
    }

    double[][] evenKernal = new double[][]{
            {0.0625, 0.125},
            {0.125, 0.25}
    };
    try {
      m1.filterImage("test", "test-b", evenKernal);
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Kernal must have an odd number of rows and columns");
    }

    try {
      m1.filterImage("test", "test-b", null);
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "kernal is null");
    }

    try {
      m1.filterImage("yes", "no", kernal);
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "file doesn't exist");
    }

  }

  @Test
  public void testSharpen() throws IOException {
    ImageView v = new ImageDisplay(new StringBuilder());
    HashMap<String, Pixel[][]> testMap = new HashMap<>();
    String file = "test";
    Pixel[][] arr = new Pixel[2][2];
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        arr[i][j] = new Pixel(50 + i, 200-(5*j), 99);
      }
    }
    testMap.put(file, arr);
    ImageModel m1 = new ImageModel(testMap, v);
    double[][] kernal = new double[][]{
            {-0.125, -0.125, -0.125, -0.125, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, 0.25, 1, 0.25, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, -0.125, -0.125, -0.125, -0.125},
    };
    m1.filterImage("test", "test-s", kernal);
    Pixel[][] arr1 = new Pixel[2][2];
    arr1[0][0] = new Pixel(88, 255, 173);
    arr1[0][1] = new Pixel(88, 255, 173);
    arr1[1][0] = new Pixel(89, 255, 173);
    arr1[1][1] = new Pixel(89, 255, 173);


    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        assertEquals(m1.images.get("test-s")[i][j], arr1[i][j]);
      }
    }

    double[][] evenKernal = new double[][]{
            {0.0625, 0.125},
            {0.125, 0.25}
    };
    try {
      m1.filterImage("test", "test-b", evenKernal);
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Kernel must have an odd number of rows and columns");
    }

    try {
      m1.filterImage("test", "test-b", null);
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "kernel is null");
    }

    try {
      m1.filterImage("yes", "no", kernal);
      fail("Should have thrown an exception");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "file doesn't exist");
    }

  }


  @Test
  public void testInvalidGreyscale() throws IOException {
    ImageView v = new ImageDisplay(new StringBuilder());
    HashMap<String, Pixel[][]> testMap = new HashMap<>();
    String file = "test";
    Pixel[][] arr = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr[i][j] = new Pixel(110, 120, 130 + i + j);
      }
    }
    testMap.put(file, arr);
    ImageModel m1 = new ImageModel(testMap, v);
    try {
      m1.greyscale("test", "test-b", "blue");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "color invalid");
    }

  }

  @Test
  public void testColorTransform() throws IOException {
    ImageView v = new ImageDisplay(new StringBuilder());
    HashMap<String, Pixel[][]> testMap = new HashMap<>();
    String file = "test";
    Pixel[][] arr = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr[i][j] = new Pixel(100, 100, 100);
      }
    }
    testMap.put(file, arr);
    ImageModel m1 = new ImageModel(testMap, v);
    float[][] colors = new float[][]{{(float) 0.5, (float) 0.5, (float) 0.5},
            {(float) 0.5, (float) 0.5, (float) 0.5},
            {(float) 0.5, (float) 0.5, (float) 0.5}};
    m1.colorTransform(colors, "test", "test-ct");
    Pixel[][] arr2 = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr2[i][j] = new Pixel(150, 150, 150);
      }
    }
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(m1.images.get("test-ct")[i][j], arr2[i][j]);
      }
    }

    try {
      m1.colorTransform(null, "test", "test-ct");
      fail("Should have thrown exception");
    } catch (IllegalArgumentException e){
      assertEquals(e.getMessage(), "colors is null");
    }
    float[][] failure = new float[2][2];
    try {
      m1.colorTransform(failure, "test", "test-ct");
      fail("Should have thrown exception");
    } catch (IllegalArgumentException e){
      assertEquals(e.getMessage(), "color matrix not size 3x3!");
    }
  }
}
