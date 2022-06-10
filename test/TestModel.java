import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import model.ImageModel;
import model.Pixel;
import view.ImageDisplay;
import view.ImageView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests the {@code ImageModel}.
 */
public class TestModel {

  @Test
  public void testValidInitialization(){
    ImageModel m = new ImageModel();
    ImageView v = new ImageDisplay(System.out);
    assertFalse(m.quit);
    assertTrue(m.images.isEmpty());

    HashMap<String, Pixel[][]> testMap = new HashMap<>();
    String file = "test";
    Pixel[][] arr = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr[i][j] = new Pixel(i,j,1);
      }
    }
    testMap.put(file, arr);
    ImageModel m1 = new ImageModel(testMap, v);
    assertFalse(m1.quit);
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(m1.images.get(file)[i][j], new Pixel(i,j,1));
      }
    }
  }

  @Test
  public void testNullMap(){
    ImageView v = new ImageDisplay(System.out);
    try {
      new ImageModel(null, v);
    } catch (IllegalArgumentException e){
      assertEquals(e.getMessage(), "HashMap or ImageView cannot be null");
    }
  }

  @Test
  public void testNullView(){
    HashMap<String, Pixel[][]> testMap = new HashMap<>();
    try {
      new ImageModel(testMap, null);
    } catch (IllegalArgumentException e){
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
        arr[i][j] = new Pixel(i,j,1);
      }
    }
    testMap.put(file, arr);
    ImageModel m1 = new ImageModel(testMap, v);
    m1.flipVertically("test", "test-vf");
    Pixel[][] arr1 = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr1[2-i][j] = new Pixel(i,j,1);
      }
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(m1.images.get("test-vf")[i][j],arr1[i][j]);
      }
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
        arr[i][j] = new Pixel(i,j,1);
      }
    }
    testMap.put(file, arr);
    ImageModel m1 = new ImageModel(testMap, v);
    m1.flipHorizontally("test", "test-hf");
    Pixel[][] arr1 = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr1[i][2-j] = new Pixel(i,j,1);
      }
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(m1.images.get("test-hf")[i][j],arr1[i][j]);
      }
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
        arr[i][j] = new Pixel(110 + i,120,130);
      }
    }
    testMap.put(file, arr);
    ImageModel m1 = new ImageModel(testMap, v);
    m1.redGreyscale("test", "test-r");
    Pixel[][] arr1 = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr1[i][j] = new Pixel(110 + i,110 + i,110 + i);
      }
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(m1.images.get("test-r")[i][j],arr1[i][j]);
      }
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
        arr[i][j] = new Pixel(110,120 + j,130);
      }
    }
    testMap.put(file, arr);
    ImageModel m1 = new ImageModel(testMap, v);
    m1.greenGreyscale("test", "test-g");
    Pixel[][] arr1 = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr1[i][j] = new Pixel(120 + j,120 + j,120 + j);
      }
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(m1.images.get("test-g")[i][j],arr1[i][j]);
      }
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
        arr[i][j] = new Pixel(110,120,130 + i + j);
      }
    }
    testMap.put(file, arr);
    ImageModel m1 = new ImageModel(testMap, v);
    m1.blueGreyscale("test", "test-b");
    Pixel[][] arr1 = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr1[i][j] = new Pixel(130 + i + j, 130 + i + j, 130 + i + j);
      }
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(m1.images.get("test-b")[i][j],arr1[i][j]);
      }
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
        arr[i][j] = new Pixel(i,j,i+j);
      }
    }
    testMap.put(file, arr);
    ImageModel m1 = new ImageModel(testMap, v);
    m1.adjustBrightness(10, "test", "test-b1");
    Pixel[][] arr1 = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr1[i][j] = new Pixel(i + 10,j + 10,i+j+10);
      }
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(m1.images.get("test-b1")[i][j],arr1[i][j]);
      }
    }

    ImageView v2 = new ImageDisplay(new StringBuilder());
    HashMap<String, Pixel[][]> testMap2 = new HashMap<>();
    String file2 = "test";
    Pixel[][] arr2 = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr2[i][j] = new Pixel(i + 100, j + 100,i+j+100);
      }
    }
    testMap.put(file2, arr2);
    ImageModel m2 = new ImageModel(testMap2, v2);
    m2.adjustBrightness(-10, "test", "test-b2");
    Pixel[][] testArr2 = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        testArr2[i][j] = new Pixel(i + 90,j + 90,i+j+90);
      }
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(m2.images.get("test-b1")[i][j],testArr2[i][j]);
      }
    }

//    ImageView v = new ImageDisplay(new StringBuilder());
//    HashMap<String, Pixel[][]> testMap = new HashMap<>();
//    String file = "test";
//    Pixel[][] arr = new Pixel[3][3];
//    for (int i = 0; i < 3; i++) {
//      for (int j = 0; j < 3; j++) {
//        arr[i][j] = new Pixel(i,j,i+j);
//      }
//    }
//    testMap.put(file, arr);
//    ImageModel m1 = new ImageModel(testMap, v);
//    m1.adjustBrightness(10, "test", "test-b1");
//    Pixel[][] arr1 = new Pixel[3][3];
//    for (int i = 0; i < 3; i++) {
//      for (int j = 0; j < 3; j++) {
//        arr1[i][j] = new Pixel(i + 10,j + 10,i+j+10);
//      }
//    }
//
//    for (int i = 0; i < 3; i++) {
//      for (int j = 0; j < 3; j++) {
//        assertEquals(m1.images.get("test-b1")[i][j],arr1[i][j]);
//      }
//    }
//
//    ImageView v = new ImageDisplay(new StringBuilder());
//    HashMap<String, Pixel[][]> testMap = new HashMap<>();
//    String file = "test";
//    Pixel[][] arr = new Pixel[3][3];
//    for (int i = 0; i < 3; i++) {
//      for (int j = 0; j < 3; j++) {
//        arr[i][j] = new Pixel(i,j,i+j);
//      }
//    }
//    testMap.put(file, arr);
//    ImageModel m1 = new ImageModel(testMap, v);
//    m1.adjustBrightness(10, "test", "test-b1");
//    Pixel[][] arr1 = new Pixel[3][3];
//    for (int i = 0; i < 3; i++) {
//      for (int j = 0; j < 3; j++) {
//        arr1[i][j] = new Pixel(i + 10,j + 10,i+j+10);
//      }
//    }
//
//    for (int i = 0; i < 3; i++) {
//      for (int j = 0; j < 3; j++) {
//        assertEquals(m1.images.get("test-b1")[i][j],arr1[i][j]);
//      }
//    }
  }

//  @Test
//  public void test(){
//
//  }
//
//  @Test
//  public void test(){
//
//  }
//
//  @Test
//  public void test(){
//
//  }
//
//  @Test
//  public void test(){
//
//  }
//
//  @Test
//  public void test(){
//
//  }
}
