/* CS 5004 - Easy Animator - Model
 * Vandita Attal & Swapnil Mittal
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import cs5004.animator.model.Model;
import cs5004.animator.model.ModelImpl;
import cs5004.animator.shape.Oval;
import cs5004.animator.shape.Rectangle;
import cs5004.animator.shape.Shape;
import cs5004.animator.shape.TypeOfShape;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

/**
 * This is a class to test the model.
 */
public class ModelTest {

  private Model easyAnimator;
  private Shape rectangle;
  private Shape oval;
  private Shape circle;

  @Before
  public void setUp() {
    circle = new Oval(50, 50, 10, 10, "C", Color.PINK, 500, 500, 15, 90);
    oval = new Oval(50, 50, 10, 5, "O", Color.PINK, 1000, 1000, 15, 100);
    rectangle = new Rectangle(0, 0, 10, 5, "R", Color.RED, 1000, 1000, 12, 90);
    easyAnimator = new ModelImpl(1000, 1000);
  }

  @Test
  public void testModelImplConstructor() {
    assertEquals("\n\n", easyAnimator.toString());
  }

  @Test
  public void testAddChangeColorAnimation() {
    easyAnimator.addChangeColorAnimation(rectangle, 15, 20, Color.BLUE);

    String colorChangeString = easyAnimator.toString().split("\n")[4];
    assertEquals("R changes from red to blue from time t=15 to t=20", colorChangeString);
  }

  @Test
  public void testBadAddAnimation() {
    try {
      easyAnimator.addChangeColorAnimation(rectangle, 15, 20, null);
      fail("IllegalArgumentException was not thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("New color is null.", e.getMessage());
    }

    try {
      easyAnimator.addChangeColorAnimation(null, 15, 20, Color.BLUE);
      fail("IllegalArgumentException was not thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("Shape cannot be null.", e.getMessage());
    }

    easyAnimator.addChangeColorAnimation(rectangle, 15, 20, Color.BLUE);

    try {
      easyAnimator.addChangeColorAnimation(rectangle, 17, 22, Color.BLUE);
      fail("IllegalArgumentException was not thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("There is an illegal time overlap with another color change animation.",
          e.getMessage());
    }

    try {
      easyAnimator.addChangeColorAnimation(rectangle, 17, 20, Color.BLUE);
      fail("IllegalArgumentException was not thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("There is an illegal time overlap with another color change animation.",
          e.getMessage());
    }

    try {
      easyAnimator.addChangeColorAnimation(rectangle, 14, 20, Color.BLUE);
      fail("IllegalArgumentException was not thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("There is an illegal time overlap with another color change animation.",
          e.getMessage());
    }
    try {
      easyAnimator.addChangeColorAnimation(rectangle, 14, 22, Color.BLUE);
      fail("IllegalArgumentException was not thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("There is an illegal time overlap with another color change animation.",
          e.getMessage());
    }
    try {
      easyAnimator.addChangeColorAnimation(rectangle, 17, 19, Color.BLUE);
      fail("IllegalArgumentException was not thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("There is an illegal time overlap with another color change animation.",
          e.getMessage());
    }
  }


  @Test
  public void testAddScaleAnimation() {
    easyAnimator.addScaleAnimation(rectangle, TypeOfShape.RECTANGLE, 15, 20, 100, 100);
    String scaleString = easyAnimator.toString().split("\n")[4];
    assertEquals("R changes width from 10 to 100 and height from 5 to 100 from time t=15 to t=20",
        scaleString);
  }

  @Test
  public void testAddMoveAnimation() {
    easyAnimator.addMoveAnimation(rectangle, TypeOfShape.RECTANGLE, 15, 20, 1, 1);
    String moveString = easyAnimator.toString().split("\n")[4];
    assertEquals("R moves from (0,0) to (1,1) from time t=15 to t=20", moveString);
  }

  @Test
  public void testSortAnimationList() {
    easyAnimator.addScaleAnimation(oval, TypeOfShape.OVAL, 20, 25, 100, 100);
    easyAnimator.addMoveAnimation(rectangle, TypeOfShape.RECTANGLE, 15, 20, 1, 1);
    easyAnimator.addChangeColorAnimation(oval, 15, 25, Color.RED);

    assertEquals("R moves from (0,0) to (1,1) from time t=15 to t=20",
        easyAnimator.toString().split("\n")[6]);
    assertEquals("O changes from pink to red from time t=15 to t=25",
        easyAnimator.toString().split("\n")[7]);
    assertEquals("O changes radius from 10 and 5 to 100 from time t=20 to t=25",
        easyAnimator.toString().split("\n")[8]);
  }

  @Test
  public void testGetShapesAtTick() {
    //adding animations so it adds to the internal shapeList
    easyAnimator.addScaleAnimation(oval, TypeOfShape.OVAL, 20, 25, 100, 100);
    easyAnimator.addMoveAnimation(rectangle, TypeOfShape.RECTANGLE, 15, 20, 1, 1);
    easyAnimator.addChangeColorAnimation(circle, 15, 25, Color.RED);

    assertEquals("[]", easyAnimator.getShapesAtTick(1).toString());
    assertEquals("[red rectangle R with corner at (0,0), width 10 and height 5]",
        easyAnimator.getShapesAtTick(13).toString());
    assertEquals(
        "[pink oval O with center at (50,50), radius 10 and 5, red rectangle R with corner at "
            + "(0,0), width 10 and height 5, pink oval C with center at (50,50), radius 10 and 10]",
        easyAnimator.getShapesAtTick(50).toString());
    assertEquals("[pink oval O with center at (50,50), radius 10 and 5]",
        easyAnimator.getShapesAtTick(100).toString());
  }

  @Test
  public void testToString() {
    easyAnimator.addScaleAnimation(oval, TypeOfShape.OVAL, 20, 25, 100, 100);
    easyAnimator.addMoveAnimation(rectangle, TypeOfShape.RECTANGLE, 15, 20, 1, 1);
    easyAnimator.addChangeColorAnimation(circle, 15, 25, Color.RED);

    assertEquals("Create pink oval O with center at (50,50), radius 10 and 5\n"
            + "Create red rectangle R with corner at (0,0), width 10 and height 5\n"
            + "Create pink oval C with center at (50,50), radius 10 and 10\n"
            + "\n"
            + "R appears at time t=12 and disappears at time t=90\n"
            + "O appears at time t=15 and disappears at time t=100\n"
            + "C appears at time t=15 and disappears at time t=90\n"
            + "\n"
            + "R moves from (0,0) to (1,1) from time t=15 to t=20\n"
            + "C changes from pink to red from time t=15 to t=25\n"
            + "O changes radius from 10 and 5 to 100 from time t=20 to t=25\n",
        easyAnimator.toString());
  }
}