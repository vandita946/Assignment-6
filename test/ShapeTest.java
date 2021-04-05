import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import cs5004.easyanimator.shape.ColorNames;
import cs5004.easyanimator.shape.Oval;
import cs5004.easyanimator.shape.Rectangle;
import cs5004.easyanimator.shape.Shape;
import java.awt.Color;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import org.junit.Before;
import org.junit.Test;

public class ShapeTest {
  private Shape rectangle;
  private Shape square;
  private Shape oval;
  private Shape circle;

  @Before
  public void setUp() {
    rectangle = new Rectangle(0, 0, 5, 10, "rectangle", Color.BLACK,
        500, 500, 1, 100);
    square = new Rectangle(15,25, 5, 5, "square", Color.WHITE,
        500, 500, 2, 200);
    oval = new Oval(250, 250, 5, 10, "oval", Color.BLUE,
        500, 500, 3, 300);
    circle = new Oval(300, 300, 5, "circle", Color.GREEN,
        500, 500, 10, 400);
  }

  @Test
  public void testRectangle() {
    assertEquals(10, rectangle.getHeight(),0.01);
    assertEquals(5,rectangle.getWidth(),0.01);
    assertEquals("rectangle", rectangle.getName());
    assertEquals(0,rectangle.getPosition().getX(),0.01);
    assertEquals(0,rectangle.getPosition().getY(),0.01);
    assertEquals(1,rectangle.getAppearTime());
    assertEquals(100,rectangle.getDisappearTime());
  }

  @Test
  public void testOval() {
    assertEquals(10, oval.getHeight(),0.01);
    assertEquals(5,oval.getWidth(),0.01);
    assertEquals("oval", oval.getName());
    assertEquals(250,oval.getPosition().getX(),0.01);
    assertEquals(250,oval.getPosition().getY(),0.01);
    assertEquals(3,oval.getAppearTime());
    assertEquals(300,oval.getDisappearTime());
  }

  @Test
  public void testCircle() {
    assertEquals(5, circle.getHeight(),0.01);
    assertEquals(5,circle.getWidth(),0.01);
    assertEquals("circle", circle.getName());
    assertEquals(300,circle.getPosition().getX(),0.01);
    assertEquals(300,circle.getPosition().getY(),0.01);
    assertEquals(10,circle.getAppearTime());
    assertEquals(400,circle.getDisappearTime());
  }

  @Test
  public void testBadConstructors() {
    try {
      new Rectangle(499,499,10,20,"R1",Color.black,500,500,10,40);
      fail("IllegalArgumentException was not called");
    } catch (IllegalArgumentException e) {
      assertEquals("The dimensions of this shape are out of bounds of the canvas.",e.getMessage());
    }

    try {
      new Oval(499,499,10,20,"R1",Color.black,500,500,10,40);
      fail("IllegalArgumentException was not called");
    } catch (IllegalArgumentException e) {
      assertEquals("The dimensions of this shape are out of bounds of the canvas.",e.getMessage());
    }

    try {
      new Oval(499,499,10,"R1",Color.black,500,500,10,40);
      fail("IllegalArgumentException was not called");
    } catch (IllegalArgumentException e) {
      assertEquals("The dimensions of this shape are out of bounds of the canvas.",e.getMessage());
    }

    try {
      new Rectangle(10, 20, 100, 200, "R1", Color.green, 500, 500, -2, 100);
      fail("IllegalArgumentException was not called");
    } catch (IllegalArgumentException e) {
      assertEquals("Appear or disappear time is invalid.", e.getMessage());
    }

    try {
      new Rectangle(10, 20, 100, 200, "R1", Color.green, 500, 500, 10, 2);
      fail("IllegalArgumentException was not called");
    } catch (IllegalArgumentException e) {
      assertEquals("Appear or disappear time is invalid.", e.getMessage());
    }

    try {
      new Rectangle(10, 20, 100, 200, "R1", Color.green, 500, 500, 10, -100);
      fail("IllegalArgumentException was not called");
    } catch (IllegalArgumentException e) {
      assertEquals("Appear or disappear time is invalid.", e.getMessage());
    }

    try {
      new Rectangle(10, 20, 100, 200, "R1", Color.green, 500, 500, 10, 2);
      fail("IllegalArgumentException was not called");
    } catch (IllegalArgumentException e) {
      assertEquals("Appear or disappear time is invalid.", e.getMessage());
    }

  }

  @Test
  public void testChangeDimensions() {

    Shape newCircle = circle.changeDimensions(2.5, 2.5);
    assertEquals(0.25 * circle.area(), newCircle.area(), 0.001);
    assertEquals(2.5,newCircle.getWidth(),0.01);
    assertEquals(2.5,newCircle.getHeight(),0.01);

    Shape newOval = oval.changeDimensions(3.5,5);
    assertEquals(3.5,newOval.getWidth(),0.01);
    assertEquals(5,newOval.getHeight(),0.01);

    Shape newRect = rectangle.changeDimensions(20,20);
    assertEquals(8* rectangle.area(),newRect.area(), 0.01);

  }

  @Test
  public void testBadChangeDimensions() {
    try {
      Shape newCircle = circle.changeDimensions(4000,200);
      fail("IllegalArgumentException was not called");
    } catch (IllegalArgumentException e) {
      assertEquals("The dimensions of this shape are out of bounds of the canvas.",e.getMessage());
    }

    try {
      Shape newRectangle = rectangle.changeDimensions(-2,20);
      fail("IllegalArgumentException was not called");
    } catch (IllegalArgumentException e) {
      assertEquals("Shape dimensions cannot be negative or zero.",e.getMessage());
    }

    try {
      Shape newRectangle = rectangle.changeDimensions(20,-3);
      fail("IllegalArgumentException was not called");
    } catch (IllegalArgumentException e) {
      assertEquals("Shape dimensions cannot be negative or zero.",e.getMessage());
    }

  }

  @Test
  public void testSetPosition() {
    circle.setPosition(50,50);
    assertEquals(50, circle.getPosition().getX(),0.01);
    assertEquals(50, circle.getPosition().getY(),0.01);

    rectangle.setPosition(275,300);
    assertEquals(275, rectangle.getPosition().getX(), 0.01);
    assertEquals(300, rectangle.getPosition().getY(), 0.01);

    oval.setPosition(400,200);
    assertEquals(400, oval.getPosition().getX(), 0.01);
    assertEquals(200, oval.getPosition().getY(), 0.01);
  }

  @Test
  public void testSetBadPosition() {
    try {
      rectangle.setPosition(2000,20);
      fail("IllegalArgumentException was not called");
    } catch (IllegalArgumentException e) {
      assertEquals("Object cannot be placed outside the canvas.",e.getMessage());
    }

    try {
      rectangle.setPosition(-1,20);
      fail("IllegalArgumentException was not called");
    } catch (IllegalArgumentException e) {
      assertEquals("Coordinates cannot be negative.",e.getMessage());
    }

  }

  @Test
  public void testGetPosition() {
    assertEquals(300, circle.getPosition().getX(),0.01);
    assertEquals(300, circle.getPosition().getY(),0.01);

    assertEquals(0, rectangle.getPosition().getX(), 0.01);
    assertEquals(0, rectangle.getPosition().getY(), 0.01);

    assertEquals(250, oval.getPosition().getX(), 0.01);
    assertEquals(250, oval.getPosition().getY(), 0.01);

  }

  @Test
  public void testChangeColor() {
    assertEquals("green", circle.getColor());
    circle.changeColor(Color.blue);
    assertEquals("blue", circle.getColor());

    assertEquals("black",rectangle.getColor());
    rectangle.changeColor(Color.PINK);
    assertEquals("pink",rectangle.getColor());
  }

  @Test
  public void testBadColor() {
    try {
      circle.changeColor(Color.MAGENTA);
      fail("IllegalArgumentException was not called");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid color entered.",e.getMessage());
    }

  }

  @Test
  public void testGetColor() {
    assertEquals("green", circle.getColor());
    assertEquals("blue",oval.getColor());
    assertEquals("black",rectangle.getColor());
    assertEquals("white",square.getColor());
  }

  @Test
  public void testGetName() {
    assertEquals("circle",circle.getName());
    assertEquals("rectangle",rectangle.getName());
  }

  @Test
  public void testGetWidth() {
    assertEquals(5,rectangle.getWidth(),0.01);
    assertEquals(5,oval.getWidth(),0.01);
    assertEquals(5,circle.getWidth(),0.01);
  }

  @Test
  public void testGetHeight() {
    assertEquals(10,rectangle.getHeight(),0.01);
    assertEquals(10,oval.getHeight(),0.01);
    assertEquals(5,circle.getHeight(),0.01);

  }


  @Test
  public void testGetAppearTime() {
    assertEquals(1,rectangle.getAppearTime(),0.01);
    assertEquals(2,square.getAppearTime(),0.01);
    assertEquals(3,oval.getAppearTime(),0.01);
  }


  @Test
  public void testGetDisappearTime() {
    assertEquals(100,rectangle.getDisappearTime(),0.01);
    assertEquals(200,square.getDisappearTime(),0.01);
    assertEquals(300,oval.getDisappearTime(),0.01);
  }


  @Test
  public void testArea() {
    assertEquals(Math.PI*25,circle.area(),0.01);
    assertEquals(50,rectangle.area(),0.01);
    assertEquals(25,square.area(),0.01);
  }


  @Test
  public void testPerimeter() {
    assertEquals(2*Math.PI*5,circle.perimeter(),0.01);
    assertEquals(30,rectangle.perimeter(),0.01);
    assertEquals(20,square.perimeter(),0.01);

  }

  @Test
  public void testToString() {
    assertEquals("black rectangle rectangle with corner at (0,0), width 5 and height 10",rectangle.toString());
    assertEquals("blue oval oval with center at (250,250), radius 5 and 10",oval.toString());
    assertEquals("green oval circle with center at (300,300), radius 5 and 5",circle.toString());
  }

}