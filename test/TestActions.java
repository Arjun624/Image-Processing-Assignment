import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import controller.Actions.AdjustBrightness;
import controller.Actions.BlueGreyscale;
import controller.Actions.GreenGreyscale;
import controller.Actions.HorizontalFlip;
import controller.Actions.IntensityGreyscale;
import controller.Actions.LoadImage;
import controller.Actions.LumaGreyscale;
import controller.Actions.Quit;
import controller.Actions.RedGreyscale;
import controller.Actions.SaveImage;
import controller.Actions.ValueGreyscale;
import controller.Actions.VerticalFlip;
import model.ImageEditor;
import model.ImageModel;
import view.ImageDisplay;
import view.ImageView;

import static org.junit.Assert.assertEquals;

/**
 * Test Each action class in the controller.
 */
public class TestActions {

  Appendable ap;
  Appendable ap2;
  ImageEditor m;
  ImageView v;
  ImageEditor m2;

  @Before
  public void init() {
    ap = new StringBuilder();
    m = new MockImageModel(ap);
    ap2 = new StringBuilder();
    v = new ImageDisplay(ap2);
    m2 = new ImageModel(new HashMap<>(), v);
  }

  @Test
  public void testAdjustBrightness() throws IOException {
    AdjustBrightness ab = new AdjustBrightness(10, "hello", "test");
    ab.execute(m, v);
    assertEquals("adjusted hello by 10. Is now test\n", ap.toString());

    try {
      ab.execute(m2, v);
    } catch (NullPointerException e){
      assertEquals("hello not loaded!", ap2.toString());
    }

  }

  @Test
  public void testBlueGreyscale() throws IOException{
    BlueGreyscale bg = new BlueGreyscale("hello", "test");
    bg.execute(m, v);
    assertEquals("preformed a blue greyscale hello. Is now test\n", ap.toString());

    try {
      bg.execute(m2, v);
    } catch (NullPointerException e){
      assertEquals("hello not loaded!", ap2.toString());
    }
  }

  @Test
  public void testGreenGreyscale() throws IOException{
    GreenGreyscale gg = new GreenGreyscale("hello", "test");
    gg.execute(m, v);
    assertEquals("preformed a green greyscale hello. Is now test\n", ap.toString());

    try {
      gg.execute(m2, v);
    } catch (NullPointerException e){
      assertEquals("hello not loaded!", ap2.toString());
    }
  }

  @Test
  public void testRedGreyscale() throws IOException{
    RedGreyscale rg = new RedGreyscale("hello", "test");
    rg.execute(m, v);
    assertEquals("preformed a red greyscale hello. Is now test\n", ap.toString());

    try {
      rg.execute(m2, v);
    } catch (NullPointerException e){
      assertEquals("hello not loaded!", ap2.toString());
    }
  }

  @Test
  public void testLumaGreyscale() throws IOException{
    LumaGreyscale lg = new LumaGreyscale("hello", "test");
    lg.execute(m, v);
    assertEquals("preformed a luma greyscale hello. Is now test\n", ap.toString());

    try {
      lg.execute(m2, v);
    } catch (NullPointerException e){
      assertEquals("hello not loaded!", ap2.toString());
    }
  }

  @Test
  public void testIntensityGreyscale() throws IOException{
    IntensityGreyscale ig = new IntensityGreyscale("hello", "test");
    ig.execute(m, v);
    assertEquals("preformed an intensity greyscale hello. Is now test\n", ap.toString());

    try {
      ig.execute(m2, v);
    } catch (NullPointerException e){
      assertEquals("hello not loaded!", ap2.toString());
    }
  }

  @Test
  public void testValueGreyscale() throws IOException{
    ValueGreyscale vg = new ValueGreyscale("hello", "test");
    vg.execute(m, v);
    assertEquals("preformed a value greyscale hello. Is now test\n", ap.toString());

    try {
      vg.execute(m2, v);
    } catch (NullPointerException e){
      assertEquals("hello not loaded!", ap2.toString());
    }
  }

  @Test
  public void testHorizontalFLip() throws IOException{
    HorizontalFlip hp = new HorizontalFlip("hello", "test");
    hp.execute(m, v);
    assertEquals("flipped hello horizontally. Is now test\n", ap.toString());

    try {
      hp.execute(m2, v);
    } catch (NullPointerException e){
      assertEquals("hello not loaded!", ap2.toString());
    }
  }

  @Test
  public void testVerticalFLip() throws IOException{
    VerticalFlip vf = new VerticalFlip("hello", "test");
    vf.execute(m, v);
    assertEquals("flipped hello vertically. Is now test\n", ap.toString());

    try {
      vf.execute(m2, v);
    } catch (NullPointerException e){
      assertEquals("hello not loaded!", ap2.toString());
    }
  }

  @Test
  public void testQuit() throws IOException{
    Quit q = new Quit();
    q.execute(m, v);
    assertEquals("program quit", ap.toString());

  }

  @Test
  public void testLoadImage() throws IOException{
    LoadImage l = new LoadImage("hello", "test");
    l.execute(m, v);
    assertEquals("loaded hello as test\n", ap.toString());

    try {
      l.execute(m2, v);
    } catch (NullPointerException e){
      assertEquals("hello does not exist!", ap2.toString());
    }
  }

  @Test
  public void testSaveImage() throws IOException{
    SaveImage s = new SaveImage("hello", "test");
    s.execute(m, v);
    assertEquals("saved test as hello\n", ap.toString());

    try {
      s.execute(m2, v);
    } catch (NullPointerException e){
      assertEquals("hello does not exist!", ap2.toString());
    }
  }
}
