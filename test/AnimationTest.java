import static org.junit.Assert.*;

import cs5004.easyanimator.animation.Animation;
import cs5004.easyanimator.animation.ChangeColor;
import cs5004.easyanimator.animation.Move;
import cs5004.easyanimator.animation.Scale;
import cs5004.easyanimator.shape.Oval;
import cs5004.easyanimator.shape.Rectangle;
import cs5004.easyanimator.shape.Shape;
import cs5004.easyanimator.shape.TypeOfShape;
import java.awt.Color;
import org.junit.Before;
import org.junit.Test;

public class AnimationTest {

  private Shape rectangle;
  private Shape oval;
  private Animation colorChange;
  private Animation move;
  private Animation scale;

  @Before
  public void setUp() {
    rectangle = new Rectangle(0, 0, 10, 5, "rectangle", Color.RED, 500, 500, 1, 100);
    oval = new Oval(50, 50, 10, 5, "oval", Color.PINK, 500, 500, 5, 100);
    colorChange = new ChangeColor(rectangle, 5, 10, Color.GREEN);
    move = new Move(oval, TypeOfShape.OVAL, 10, 20, 70, 70, 500, 500);
    scale = new Scale(rectangle, TypeOfShape.RECTANGLE, 50, 75, 20, 20);
  }

  @Test
  public void testBadAnimationConstructor() {
    try {
      new ChangeColor(rectangle, 101, 200, Color.GREEN);
      fail("IllegalArgumentException was not thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("Starting and ending time must be within shape's appear and disappear time.",
          e.getMessage());
    }

    //negative time
    try {
      new ChangeColor(rectangle, -1, 50, Color.GREEN);
      fail("IllegalArgumentException was not thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("Starting and ending time must be within shape's appear and disappear time.",
          e.getMessage());
    }

    try {
      new Move(null, TypeOfShape.OVAL, 10, 20, 70, 70, 500, 500);
      fail("IllegalArgumentException was not thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("Shape cannot be null.", e.getMessage());
    }
  }

  @Test
  public void testBadMoveConstructor() {
    //negative x coordinate
    try {
      new Move(rectangle, TypeOfShape.RECTANGLE, 1, 50, -5, 75, 500, 500);
      fail("IllegalArgumentException was not thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("The new x coordinate pushes the shape out of bounds.", e.getMessage());
    }

    //above canvas width x coordinate
    try {
      new Move(rectangle, TypeOfShape.RECTANGLE, 1, 50, 501, 75, 500, 500);
      fail("IllegalArgumentException was not thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("The new x coordinate pushes the shape out of bounds.", e.getMessage());
    }

    //shape width exceeds canvas width at new coordinate
    try {
      new Move(rectangle, TypeOfShape.RECTANGLE, 1, 50, 491, 75, 500, 500);
      fail("IllegalArgumentException was not thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("The new x coordinate pushes the shape out of bounds.", e.getMessage());
    }

    //negative y coordinate
    try {
      new Move(rectangle, TypeOfShape.RECTANGLE, 1, 50, 10, -5, 500, 500);
      fail("IllegalArgumentException was not thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("The new y coordinate pushes the shape out of bounds.", e.getMessage());
    }

    //above canvas height y coordinate
    try {
      new Move(rectangle, TypeOfShape.RECTANGLE, 1, 50, 10, 501, 500, 500);
      fail("IllegalArgumentException was not thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("The new y coordinate pushes the shape out of bounds.", e.getMessage());
    }

    //shape height exceeds canvas height at new coordinate
    try {
      new Move(rectangle, TypeOfShape.RECTANGLE, 1, 50, 10, 496, 500, 500);
      fail("IllegalArgumentException was not thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("The new y coordinate pushes the shape out of bounds.", e.getMessage());
    }

    //negative canvas width
    try {
      new Move(rectangle, TypeOfShape.RECTANGLE, 1, 50, 10, 50, -500, 500);
      fail("IllegalArgumentException was not thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("Canvas dimensions cannot be negative.", e.getMessage());
    }

    //negative canvas height
    try {
      new Move(rectangle, TypeOfShape.RECTANGLE, 1, 50, 10, 50, 500, -500);
      fail("IllegalArgumentException was not thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("Canvas dimensions cannot be negative.", e.getMessage());
    }
  }

  @Test
  public void testBadScaleConstructor() {
    try {
      new Scale(rectangle, TypeOfShape.RECTANGLE, 50, 75, 10, 5);
      fail("IllegalArgumentException was not thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("The shape is already at the given dimensions.", e.getMessage());
    }
  }

  @Test
  public void moveActionStep() {
    assertEquals("red", rectangle.getColor());
    colorChange.actionStep();
    assertEquals("green", rectangle.getColor());
  }

  @Test
  public void getStartingTime() {
    assertEquals(10, move.getStartingTime());
    assertEquals(50, scale.getStartingTime());
    assertEquals(5, colorChange.getStartingTime());
  }

  @Test
  public void getEndingTime() {
    assertEquals(20, move.getEndingTime());
    assertEquals(75, scale.getEndingTime());
    assertEquals(10, colorChange.getEndingTime());
  }

  @Test
  public void testToString() {
    assertEquals("oval moves from (50,50) to (70,70) from time t=10 to t=20", move.toString());
    assertEquals("rectangle changes from red to green from time t=5 to t=10", colorChange.toString());
  }

  @Test
  public void getType() {
  }
}