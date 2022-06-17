import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import controller.ImageController;
import controller.ImageControllerImpl;
import controller.commands.AdjustBrightness;
import controller.commands.BlueGreyscale;
import controller.commands.GreenGreyscale;
import controller.commands.HorizontalFlip;
import controller.commands.IntensityGreyscale;
import controller.commands.LoadImage;
import controller.commands.LumaGreyscale;
import controller.commands.Quit;
import controller.commands.RedGreyscale;
import controller.commands.SaveImage;
import controller.commands.ValueGreyscale;
import controller.commands.VerticalFlip;
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
    AdjustBrightness ab = new AdjustBrightness(10, "bright", "test");
    ab.execute(m, v);
    assertEquals("adjusted bright by 10. Is now test\n", ap.toString());

    ab.execute(m2, v);
    assertEquals(ap2.toString(), "bright not loaded!\n");

  }

  @Test
  public void testBlueGreyscale() throws IOException {
    BlueGreyscale bg = new BlueGreyscale("arjun", "test");
    bg.execute(m, v);
    assertEquals("preformed a greyscale of type blue on arjun. Is now test\n",
            ap.toString());

    bg.execute(m2, v);
    assertEquals("arjun file doesn't exist\n", ap2.toString());
  }

  @Test
  public void testGreenGreyscale() throws IOException {
    GreenGreyscale gg = new GreenGreyscale("arjun", "test");
    gg.execute(m, v);
    assertEquals("preformed a greyscale of type green on arjun. Is now test\n",
            ap.toString());

    gg.execute(m2, v);
    assertEquals("arjun file doesn't exist\n", ap2.toString());
  }

  @Test
  public void testRedGreyscale() throws IOException {
    RedGreyscale rg = new RedGreyscale("arjun", "test");
    rg.execute(m, v);
    assertEquals("preformed a greyscale of type red on arjun. Is now test\n",
            ap.toString());

    rg.execute(m2, v);
    assertEquals("arjun file doesn't exist\n", ap2.toString());
  }

  @Test
  public void testLumaGreyscale() throws IOException {
    LumaGreyscale lg = new LumaGreyscale("arjun", "test");
    lg.execute(m, v);
    assertEquals("preformed a greyscale of type luma on arjun. Is now test\n",
            ap.toString());

    lg.execute(m2, v);
    assertEquals("arjun file doesn't exist\n", ap2.toString());
  }

  @Test
  public void testIntensityGreyscale() throws IOException {
    IntensityGreyscale ig = new IntensityGreyscale("arjun", "test");
    ig.execute(m, v);
    assertEquals("preformed a greyscale of type intensity on arjun. Is now test\n",
            ap.toString());

    ig.execute(m2, v);
    assertEquals("arjun file doesn't exist\n", ap2.toString());
  }

  @Test
  public void testValueGreyscale() throws IOException {
    ValueGreyscale vg = new ValueGreyscale("arjun", "test");
    vg.execute(m, v);
    assertEquals("preformed a greyscale of type value on arjun. Is now test\n",
            ap.toString());

    vg.execute(m2, v);
    assertEquals("arjun file doesn't exist\n", ap2.toString());
  }

  @Test
  public void testHorizontalFLip() throws IOException {
    HorizontalFlip hp = new HorizontalFlip("arjun", "test");
    hp.execute(m, v);
    assertEquals("flipped arjun horizontally. Is now test\n", ap.toString());

    hp.execute(m2, v);
    assertEquals("arjun not loaded!\n", ap2.toString());
  }

  @Test
  public void testVerticalFLip() throws IOException {
    VerticalFlip vf = new VerticalFlip("arjun", "test");
    vf.execute(m, v);
    assertEquals("flipped arjun vertically. Is now test\n", ap.toString());

    vf.execute(m2, v);
    assertEquals("arjun not loaded!\n", ap2.toString());
  }

  @Test
  public void testQuit() throws IOException {
    Quit q = new Quit();
    q.execute(m, v);
    assertEquals("program quit", ap.toString());

  }

  @Test
  public void testLoadImage() throws IOException {
    ImageController c = new ImageControllerImpl(v,new InputStreamReader(System.in),m);
    LoadImage l = new LoadImage("arjun", "test",c);

    l.execute(m2, v);
    assertEquals("arjun does not exist!\n", ap2.toString());
  }

  @Test
  public void testSaveImage() throws IOException {
    ImageController c = new ImageControllerImpl(v,new InputStreamReader(System.in),m);
    SaveImage s = new SaveImage("arjun", "test", c);
    s.execute(m2, v);
    assertEquals("\n", ap2.toString());
  }
}
