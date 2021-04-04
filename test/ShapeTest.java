import static org.junit.Assert.assertEquals;

import cs5004.easyanimator.shape.Oval;
import cs5004.easyanimator.shape.Rectangle;
import cs5004.easyanimator.shape.Shape;
import java.awt.Color;
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
        500, 500, 1, 100);
    oval = new Oval(250, 250, 5, 10, "oval", Color.BLUE,
        500, 500, 1, 100);
    circle = new Oval(300, 300, 5, "circle", Color.GREEN,
        500, 500, 10, 50);
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
    assertEquals(1,oval.getAppearTime());
    assertEquals(100,oval.getDisappearTime());
  }

  @Test
  public void testCircle() {
    assertEquals(5, circle.getHeight(),0.01);
    assertEquals(5,circle.getWidth(),0.01);
    assertEquals("circle", circle.getName());
    assertEquals(300,circle.getPosition().getX(),0.01);
    assertEquals(300,circle.getPosition().getY(),0.01);
    assertEquals(10,circle.getAppearTime());
    assertEquals(50,circle.getDisappearTime());
  }


  @Test
  public void changeDimensions() {
  }

  @Test
  public void setPosition() {
  }

  @Test
  public void getPosition() {
  }

  @Test
  public void changeColor() {
  }

  @Test
  public void getColor() {
  }

  @Test
  public void getName() {
  }

  @Test
  public void getWidth() {
  }

  @Test
  public void getHeight() {
  }

  @Test
  public void getAppearTime() {
  }

  @Test
  public void getDisappearTime() {
  }


  /**
   * Tests whether the area methods work correctly for all shapes
   */
  @Test
  public void testArea() {
    assertEquals(Math.PI*25,circle.area(),0.001);
  }

  /**
   * Tests whether the perimeter methods work correctly for all shapes
   */
  @Test
  public void testPerimeter() {
    assertEquals(2*Math.PI*5,circle.perimeter(),0.001);
//    assertEquals(2*Math.PI*10,circle2.perimeter(),0.001);
//    assertEquals(2*Math.PI*20,circle3.perimeter(),0.001);
//    assertEquals(9,rect1.perimeter(),0.001);
//    assertEquals(40,rect2.perimeter(),0.001);
  }

  @Test
  public void testResizes() {
    Shape resizedCircle1,resizedCircle2,resizedCircle3,resizedRect1,
        resizedRect2;
    System.out.println(rectangle);
    Shape newCircle = circle.changeDimensions(2.5,2.5);
//    resizedCircle2 = circle2.resize(0);
//    resizedCircle3 = circle3.resize(10);
//    resizedRect1 = rect1.resize(12.5);
//    resizedRect2 = rect2.resize(0.001);
//
    System.out.println(circle);
    assertEquals(0.25*circle.area(),  newCircle.area(),0.001);
//    assertEquals(0*circle2.area(),resizedCircle2.area(),0.001);
//    assertEquals(10*circle3.area(),resizedCircle3.area(),0.001);
//    assertEquals(12.5*rect1.area(),resizedRect1.area(),0.001);
//    assertEquals(0.001*rect2.area(),resizedRect2.area(),0.001);
  }
}