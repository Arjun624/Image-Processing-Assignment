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
    ImageView v = new ImageDisplay(System.out);
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
