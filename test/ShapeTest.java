import cs5004.easyanimator.shape.Oval;
import cs5004.easyanimator.shape.Rectangle;
import cs5004.easyanimator.shape.Shape;
import java.awt.Color;
import org.junit.Before;
import org.junit.Test;

public class ShapeTest {

  @Before
  public void setUp() {
    Shape rectangle = new Rectangle(0, 0, 5, 10, "rectangle", Color.BLACK,
        500, 500, 1, 100);
    Shape square = new Rectangle(15,25, 5, 5, "square", Color.CYAN,
        500, 500, 1, 100);
    Shape oval = new Oval(250, 250, 5, 10, "oval", Color.BLUE,
        500, 500, 1, 100);
    Shape circle = new Oval(300, 300, 5, "circle", Color.GREEN,
        500, 500, 1, 100);
  }
  

  @Test
  public void distanceFromOrigin() {
  }

  @Test
  public void area() {
  }

  @Test
  void perimeter() {
  }

  @Test
  void changeDimensions() {
  }

  @Test
  void setPosition() {
  }

  @Test
  void getPosition() {
  }

  @Test
  void changeColor() {
  }

  @Test
  void getColor() {
  }

  @Test
  void getName() {
  }

  @Test
  void getWidth() {
  }

  @Test
  void getHeight() {
  }

  @Test
  void getAppearTime() {
  }

  @Test
  void getDisappearTime() {
  }
}