/* CS 5004 - Easy Animator - Model
 * Vandita Attal & Swapnil Mittal
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import cs5004.animator.shape.Ellipse;
import cs5004.animator.shape.Rectangle;
import cs5004.animator.shape.Shape;
import cs5004.animator.shape.TypeOfShape;
import java.awt.Color;
import org.junit.Before;
import org.junit.Test;

/**
 * This is a test class to test shapes.
 */
public class ShapeTest {

  private Shape rectangle;
  private Shape square;
  private Shape oval;
  private Shape circle;

  @Before
  public void setUp() {
    rectangle = new Rectangle(0.0, 0.0, 5, 10, "rectangle", Color.BLACK.getRed(), Color.BLACK.getGreen(), Color.BLACK.getBlue(), 1, 100);
    square = new Rectangle(15, 25, 5, 5, "square", Color.WHITE.getRed(), Color.WHITE.getGreen(), Color.WHITE.getBlue(), 2, 200);
    oval = new Ellipse(250, 250, 5, 10, "oval", Color.BLUE.getRed(), Color.BLUE.getGreen(), Color.BLUE.getBlue(), 3, 300);
    circle = new Ellipse(300, 300, 5, "circle", Color.GREEN.getRed(), Color.GREEN.getGreen(), Color.GREEN.getBlue(), 10, 400);
  }

  @Test
  public void testRectangle() {
    assertEquals(10, rectangle.getHeight(), 0.01);
    assertEquals(5, rectangle.getWidth(), 0.01);
    assertEquals("rectangle", rectangle.getName());
    assertEquals(0, rectangle.getPosition().getX(), 0.01);
    assertEquals(0, rectangle.getPosition().getY(), 0.01);
    assertEquals(1, rectangle.getAppearTime(), 0.01);
    assertEquals(100, rectangle.getDisappearTime(), 0.01);
  }

  @Test
  public void testOval() {
    assertEquals(10, oval.getHeight(), 0.01);
    assertEquals(5, oval.getWidth(), 0.01);
    assertEquals("oval", oval.getName());
    assertEquals(250, oval.getPosition().getX(), 0.01);
    assertEquals(250, oval.getPosition().getY(), 0.01);
    assertEquals(3, oval.getAppearTime(), 0.01);
    assertEquals(300, oval.getDisappearTime(), 0.01);
  }

  @Test
  public void testCircle() {
    assertEquals(5, circle.getHeight(), 0.01);
    assertEquals(5, circle.getWidth(), 0.01);
    assertEquals("circle", circle.getName());
    assertEquals(300, circle.getPosition().getX(), 0.01);
    assertEquals(300, circle.getPosition().getY(), 0.01);
    assertEquals(10, circle.getAppearTime(), 0.01);
    assertEquals(400, circle.getDisappearTime(), 0.01);
  }

  @Test
  public void testBadConstructors() {

    try {
      new Rectangle(10, 20, 100, 200, "R1", 255, 255, 255, -2, 100);
      fail("IllegalArgumentException was not called");
    } catch (IllegalArgumentException e) {
      assertEquals("Appear or disappear time is invalid.", e.getMessage());
    }

    try {
      new Rectangle(10, 20, 100, 200, "R1", 255, 255, 255, 10, 2);
      fail("IllegalArgumentException was not called");
    } catch (IllegalArgumentException e) {
      assertEquals("Appear or disappear time is invalid.", e.getMessage());
    }

    try {
      new Rectangle(10, 20, 100, 200, "R1", 255, 255, 255, 10, -100);
      fail("IllegalArgumentException was not called");
    } catch (IllegalArgumentException e) {
      assertEquals("Appear or disappear time is invalid.", e.getMessage());
    }

  }

  @Test
  public void testChangeDimensions() {

    circle.changeDimensions(2.5, 2.5);
    assertEquals(2.5, circle.getWidth(), 0.01);
    assertEquals(2.5, circle.getHeight(), 0.01);

    oval.changeDimensions(3.5, 5);
    assertEquals(3.5, oval.getWidth(), 0.01);
    assertEquals(5, oval.getHeight(), 0.01);

    rectangle.changeDimensions(20, 20);
    assertEquals(20, rectangle.getWidth(), 0.01);
    assertEquals(20, rectangle.getHeight(), 0.01);
  }

  @Test
  public void testBadChangeDimensions() {

    try {
      rectangle.changeDimensions(-2, 20);
      fail("IllegalArgumentException was not called");
    } catch (IllegalArgumentException e) {
      assertEquals("Shape dimensions cannot be negative or zero.", e.getMessage());
    }

    try {
      rectangle.changeDimensions(20, -3);
      fail("IllegalArgumentException was not called");
    } catch (IllegalArgumentException e) {
      assertEquals("Shape dimensions cannot be negative or zero.", e.getMessage());
    }

  }

  @Test
  public void testSetPosition() {
    circle.setPosition(50, 50);
    assertEquals(50, circle.getPosition().getX(), 0.01);
    assertEquals(50, circle.getPosition().getY(), 0.01);

    rectangle.setPosition(275, 300);
    assertEquals(275, rectangle.getPosition().getX(), 0.01);
    assertEquals(300, rectangle.getPosition().getY(), 0.01);

    oval.setPosition(400, 200);
    assertEquals(400, oval.getPosition().getX(), 0.01);
    assertEquals(200, oval.getPosition().getY(), 0.01);
  }


  @Test
  public void testGetPosition() {
    assertEquals(300, circle.getPosition().getX(), 0.01);
    assertEquals(300, circle.getPosition().getY(), 0.01);

    assertEquals(0, rectangle.getPosition().getX(), 0.01);
    assertEquals(0, rectangle.getPosition().getY(), 0.01);

    assertEquals(250, oval.getPosition().getX(), 0.01);
    assertEquals(250, oval.getPosition().getY(), 0.01);

  }

  @Test
  public void testChangeColor() {
    assertEquals(Color.green, circle.getColor());
    circle.changeColor(Color.blue);
    assertEquals(Color.blue, circle.getColor());

    assertEquals(Color.black, rectangle.getColor());
    rectangle.changeColor(Color.PINK);
    assertEquals(Color.pink, rectangle.getColor());
  }


  @Test
  public void testGetColor() {
    assertEquals(Color.green, circle.getColor());
    assertEquals(Color.blue, oval.getColor());
    assertEquals(Color.black, rectangle.getColor());
    assertEquals(Color.white, square.getColor());
  }

  @Test
  public void testGetName() {
    assertEquals("circle", circle.getName());
    assertEquals("rectangle", rectangle.getName());
  }

  @Test
  public void testGetWidth() {
    assertEquals(5, rectangle.getWidth(), 0.01);
    assertEquals(5, oval.getWidth(), 0.01);
    assertEquals(5, circle.getWidth(), 0.01);
  }

  @Test
  public void testGetHeight() {
    assertEquals(10, rectangle.getHeight(), 0.01);
    assertEquals(10, oval.getHeight(), 0.01);
    assertEquals(5, circle.getHeight(), 0.01);

  }


  @Test
  public void testGetAppearTime() {
    assertEquals(1, rectangle.getAppearTime(), 0.01);
    assertEquals(2, square.getAppearTime(), 0.01);
    assertEquals(3, oval.getAppearTime(), 0.01);
  }


  @Test
  public void testGetDisappearTime() {
    assertEquals(100, rectangle.getDisappearTime(), 0.01);
    assertEquals(200, square.getDisappearTime(), 0.01);
    assertEquals(300, oval.getDisappearTime(), 0.01);
  }

  @Test
  public void testToString() {
    assertEquals("RGB(0,0,0) rectangle rectangle with corner at (0,0), width 5 and height 10",
        rectangle.toString());
    assertEquals("RGB(0,0,255) oval oval with center at (250,250), radius 5 and 10", oval.toString());
    assertEquals("RGB(0,255,0) oval circle with center at (300,300), radius 5 and 5", circle.toString());
  }

  @Test
  public void testGetTypeOfShape() {
    assertEquals(TypeOfShape.RECTANGLE, rectangle.getTypeOfShape());
    assertEquals(TypeOfShape.ELLIPSE, oval.getTypeOfShape());
    assertEquals(TypeOfShape.ELLIPSE, circle.getTypeOfShape());
  }

}