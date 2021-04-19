/* CS 5004 - Easy Animator - Model
 * Vandita Attal & Swapnil Mittal
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import cs5004.animator.animation.Animation;
import cs5004.animator.animation.ChangeColor;
import cs5004.animator.animation.Move;
import cs5004.animator.animation.Scale;
import cs5004.animator.animation.TypeOfAnimation;
import cs5004.animator.shape.Ellipse;
import cs5004.animator.shape.Rectangle;
import cs5004.animator.shape.Shape;
import cs5004.animator.shape.TypeOfShape;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * This is a test class for testing animations.
 */
public class AnimationTest {

  private Shape rectangle;
  private Shape oval;
  private Animation colorChange;
  private Animation move;
  private Animation scale;

  @Before
  public void setUp() {
    rectangle = new Rectangle(0, 0, 10, 5, "rectangle", Color.RED.getRed(), Color.RED.getGreen(),
        Color.RED.getBlue(), 1, 100);
    oval = new Ellipse(50, 50, 10, 5, "oval", Color.PINK.getRed(), Color.PINK.getGreen(),
        Color.PINK.getBlue(), 5, 100);
    colorChange = new ChangeColor(rectangle, 5, 10, Color.GREEN.getRed(), Color.GREEN.getGreen(),
        Color.GREEN.getBlue());
    move = new Move(oval, 10, 20, 70, 70);
    scale = new Scale(rectangle, TypeOfShape.RECTANGLE, 50, 75, 20, 20);
  }

  @Test
  public void testBadAnimationConstructor() {
    try {
      new ChangeColor(rectangle, 101, 200, 255, 255, 255);
      fail("IllegalArgumentException was not thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("Starting and ending time must be within shape's appear and disappear time.",
          e.getMessage());
    }

    //negative time
    try {
      new ChangeColor(rectangle, -1, 50, 255, 255, 255);
      fail("IllegalArgumentException was not thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("Starting and ending time must be within shape's appear and disappear time.",
          e.getMessage());
    }
  }


  @Test
  public void testColorChangeActionStep() {
    double tweenR =
        (Color.red.getRed() * (10 - 7) / (10 - 5)) + (
            Color.green.getRed() * (7 - 5) / (10 - 5));
    double tweenG =
        (Color.red.getGreen() * (10 - 7) / (10 - 5)) + (
            Color.green.getGreen() * (7 - 5) / (10 - 5));
    double tweenB =
        (Color.red.getBlue() * (10 - 7) / (10 - 5)) + (
            Color.green.getBlue() * (7 - 5) / (10 - 5));

    assertEquals(Color.red, rectangle.getColor());
    colorChange.actionStep(7);
    assertEquals(new Color((int) tweenR, (int) tweenG, (int) tweenB), rectangle.getColor());
    colorChange.actionStep(10);
    assertEquals(Color.green, rectangle.getColor());
  }

  @Test
  public void testMoveActionStep() {
    double tweenX =
        (oval.getPosition().getX() * (20 - 15) / (20 - 10)) + (
            70 * (15 - 10) / (20 - 10));
    double tweenY =
        (oval.getPosition().getY() * (20 - 15) / (20 - 10)) + (
            70 * (15 - 10) / (20 - 10));

    assertEquals(50, oval.getPosition().getX(), 0.01);
    assertEquals(50, oval.getPosition().getY(), 0.01);
    move.actionStep(15);
    assertEquals(tweenX, oval.getPosition().getX(), 0.01);
    assertEquals(tweenY, oval.getPosition().getY(), 0.01);
    move.actionStep(20);
    assertEquals(70, oval.getPosition().getX(), 0.01);
    assertEquals(70, oval.getPosition().getY(), 0.01);
  }

  @Test
  public void testScaleActionStep() {
    double tweenWidth = (rectangle.getWidth() * (75 - 60) / (75 - 50)) + (
        20 * (60 - 50) / (75 - 50));
    double tweenHeight = (rectangle.getHeight() * (75 - 60) / (75 - 50)) + (
        20 * (60 - 50) / (75 - 50));

    assertEquals(10, rectangle.getWidth(), 0.01);
    assertEquals(5, rectangle.getHeight(), 0.01);
    scale.actionStep(60);
    assertEquals(tweenWidth, rectangle.getWidth(), 0.01);
    assertEquals(tweenHeight, rectangle.getHeight(), 0.01);
    scale.actionStep(75);
    assertEquals(20, rectangle.getWidth(), 0.01);
    assertEquals(20, rectangle.getHeight(), 0.01);
  }

  @Test
  public void testGetStartingTime() {
    assertEquals(10, move.getStartingTime(), 0.01);
    assertEquals(50, scale.getStartingTime(), 0.01);
    assertEquals(5, colorChange.getStartingTime(), 0.01);
  }

  @Test
  public void testGetEndingTime() {
    assertEquals(20, move.getEndingTime(), 0.01);
    assertEquals(75, scale.getEndingTime(), 0.01);
    assertEquals(10, colorChange.getEndingTime(), 0.01);
  }

  @Test
  public void testToString() {
    assertEquals("oval moves from (50,50) to (70,70) from time t=10 to t=20", move.toString());
    assertEquals("rectangle changes from RGB(255,0,0) to RGB(0,255,0) from time t=5 to t=10",
        colorChange.toString());

    //rectangle: change both width and height
    assertEquals(
        "rectangle changes width from 10 to 20 and height from 5 to 20 from time t=50 to t=75",
        scale.toString());

    //rectangle: change just width
    assertEquals("rectangle changes width from 10 to 50 from time t=1 to t=50",
        new Scale(rectangle, TypeOfShape.RECTANGLE, 1, 50, 50, 5).toString());

    //rectangle: change just height
    assertEquals("rectangle changes height from 5 to 50 from time t=1 to t=50",
        new Scale(rectangle, TypeOfShape.RECTANGLE, 1, 50, 10, 50).toString());

    //oval: change both radii to the same radius
    assertEquals("oval changes radius from 10 and 5 to 50 from time t=5 to t=50",
        new Scale(oval, TypeOfShape.ELLIPSE, 5, 50, 50, 50).toString());

    //circle: change one radius
    assertEquals(
        "oval changes radius from 10 to 15 and other radius from 5 to 20 from time t=5 to t=50",
        new Scale(oval, TypeOfShape.ELLIPSE, 5, 50, 15, 20).toString());

    //oval: change both radii
    assertEquals("oval changes radius from 10 and 5 to 5 from time t=5 to t=50",
        new Scale(oval, TypeOfShape.ELLIPSE, 5, 50, 5, 5).toString());
  }

  @Test
  public void testGetShape() {
    assertEquals(rectangle, scale.getShape());
    assertEquals(oval, move.getShape());
    assertEquals(rectangle, colorChange.getShape());
  }

  @Test
  public void testGetType() {
    assertEquals(TypeOfAnimation.MOVE, move.getType());
    assertEquals(TypeOfAnimation.SCALE, scale.getType());
    assertEquals(TypeOfAnimation.COLOR, colorChange.getType());
  }

  @Test
  public void testGetChanges() {
    List<String[]> scaleChanges = new ArrayList<>();
    scaleChanges.add(new String[]{"width", "10.0", "20.0"});
    scaleChanges.add(new String[]{"height", "5.0", "20.0"});

    assertEquals(scaleChanges.get(0), scale.getChanges().get(0));
    assertEquals(scaleChanges.get(1), scale.getChanges().get(1));

    List<String[]> moveChanges = new ArrayList<>();
    moveChanges.add(new String[]{"cx", "50.0", "70.0"});
    moveChanges.add(new String[]{"cy", "50.0", "70.0"});

    assertEquals(moveChanges.get(0), move.getChanges().get(0));
    assertEquals(moveChanges.get(1), move.getChanges().get(1));

    List<String[]> colorChanges = new ArrayList<>();
    colorChanges.add(new String[]{"fill", "rgb(255,0,0)", "rgb(0,255,0)"});

    assertEquals(colorChanges.get(0), colorChange.getChanges().get(0));
  }
}