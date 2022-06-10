import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import model.ImageModel;
import model.Pixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestModel {

  @Test
  public void testValidInitialization(){
    ImageModel m = new ImageModel();
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
    ImageModel m1 = new ImageModel(testMap);
    assertFalse(m1.quit);
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(m1.images.get(file)[i][j], new Pixel(i,j,1));
      }
    }
  }

  @Test
  public void testInvalidInitialization(){
    try {
      new ImageModel(null);
    } catch (IllegalArgumentException e){
      assertEquals(e.getMessage(), "hash map is null");
    }
  }

  @Test
  public void testFlipVertically() throws IOException {
    HashMap<String, Pixel[][]> testMap = new HashMap<>();
    String file = "test";
    Pixel[][] arr = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr[i][j] = new Pixel(i,j,1);
      }
    }
    testMap.put(file, arr);
    ImageModel m1 = new ImageModel(testMap);
    m1.flipVertically("test", "test-vf");
    Pixel[][] arr1 = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        arr1[2-i][j] = new Pixel(i,j,1);
      }
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(m1.images.get("test-vf")[i][j],arr[i][j]);
      }
    }
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
//
//  @Test
//  public void test(){
//
//  }
}
