/* CS 5004 - Easy Animator - Model
 * Vandita Attal & Swapnil Mittal
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import cs5004.animator.animation.Animation;
import cs5004.animator.animation.TypeOfAnimation;
import cs5004.animator.model.Model;
import cs5004.animator.model.ModelImpl;
import cs5004.animator.model.ModelImpl.Builder;
import cs5004.animator.util.AnimationBuilder;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

/**
 * This is a class to test the model and the static final class Builder that is inside it.
 */
public class ModelTest {

  private Model easyAnimator;
  private AnimationBuilder<Model> builder;

  @Before
  public void setUp() {
    easyAnimator = new ModelImpl();
    builder = new Builder(easyAnimator);
    easyAnimator.setTicksPerSecond(1);
  }

  @Test
  public void testModelImplConstructor() {
    assertEquals("\n\n", easyAnimator.toString());
  }

  @Test
  public void testBuild() {
    assertEquals(easyAnimator, builder.build());
  }

  @Test
  public void testSetBounds() {
    builder.setBounds(0, 50, 200, 100);
    assertEquals(100, easyAnimator.getCanvasHeight(), 0.01);
    assertEquals(200, easyAnimator.getCanvasWidth(), 0.01);
    assertEquals(0, easyAnimator.getCornerX(), 0.01);
    assertEquals(50, easyAnimator.getCornerY(), 0.01);
  }

  @Test
  public void testDeclareShape() {
    builder.declareShape("circly", "ellipse");
    builder.declareShape("squarey", "rectangle");
    builder.declareShape("circly", "ellipse");

    Map<String, String> shapeLedger = easyAnimator.getShapeLedger();
    assertEquals("ellipse", shapeLedger.get("circly"));
    assertEquals("rectangle", shapeLedger.get("squarey"));
    assertEquals(2, shapeLedger.size());
  }

  @Test
  public void testAddMotion() {
    builder.declareShape("circly", "ellipse");
    builder.addMotion("circly", 10, 0, 5, 10, 10, 255, 255, 255, 50, 10, 20, 20, 40, 0, 255, 255);

    List<Animation> animationList = easyAnimator.getAnimationList();

    assertEquals(3, animationList.size());

    assertEquals(TypeOfAnimation.MOVE, animationList.get(0).getType());
    assertEquals(TypeOfAnimation.COLOR, animationList.get(1).getType());
    assertEquals(TypeOfAnimation.SCALE, animationList.get(2).getType());

    assertEquals("circly moves from (0,5) to (10,20) from time t=10000 to t=50000",
        animationList.get(0).toString());
    assertEquals(
        "circly changes from RGB(255,255,255) to RGB(0,255,255) from time t=10000 to t=50000",
        animationList.get(1).toString());
    assertEquals(
        "circly changes radius from 10 to 20 and other radius from 10 to 40 from time"
            + " t=10000 to t=50000",
        animationList.get(2).toString());
  }

  @Test
  public void testIllegalTimeOverlapAddMotion() {
    builder.declareShape("circly", "ellipse");
    builder.addMotion("circly", 10, 0, 5, 10, 10, 255, 255, 255, 50, 10, 20, 20, 40, 0, 255, 255);

    try {
      builder.addMotion("circly", 10, 0, 5, 10, 10, 255, 255, 255, 50, 0, 5, 10, 10, 255, 0, 255);
      fail("IllegalArgumentException was not called.");
    } catch (IllegalArgumentException e) {
      assertEquals("There is an illegal time overlap with another color change animation.",
          e.getMessage());
    }

    try {
      builder.addMotion("circly", 10, 0, 5, 10, 10, 255, 255, 255, 50, 0, 5, 20, 50, 255, 255, 255);
      fail("IllegalArgumentException was not called.");
    } catch (IllegalArgumentException e) {
      assertEquals("There is an illegal time overlap with another scale animation.",
          e.getMessage());
    }

    try {
      builder
          .addMotion("circly", 10, 0, 5, 10, 10, 255, 255, 255, 50, 75, 75, 10, 10, 255, 255, 255);
      fail("IllegalArgumentException was not called.");
    } catch (IllegalArgumentException e) {
      assertEquals("There is an illegal time overlap with another move animation.", e.getMessage());
    }
  }

  @Test
  public void testGetShapesAtTick() {
    //adding animations so it adds to the internal shapeList
    builder.declareShape("circly", "ellipse");
    builder.declareShape("squarey", "rectangle");
    builder.addMotion("circly", 10, 0, 5, 10, 10, 255, 255, 255, 50, 10, 20, 20, 40, 0, 255, 255);
    builder.addMotion("squarey", 5, 0, 5, 10, 10, 255, 255, 255, 45, 10, 20, 20, 40, 0, 255, 255);

    assertEquals("[]", easyAnimator.getShapesAtTick(1).toString());
    assertEquals(
        "[RGB(248,255,255) rectangle squarey with corner at (0,5), width 10 and height 11]",
        easyAnimator.getShapesAtTick(6).toString());
    assertEquals(
        "[RGB(63,255,255) oval circly with center at (8,16), radius 18 and 33, "
            + "RGB(31,255,255) rectangle squarey with corner at (9,18), width 19 and height 36]",
        easyAnimator.getShapesAtTick(40).toString());
    assertEquals("[RGB(1,255,255) oval circly with center at (10,20), radius 20 and 40]",
        easyAnimator.getShapesAtTick(49).toString());
    assertEquals("[]",
        easyAnimator.getShapesAtTick(60).toString());
  }

  @Test
  public void testGetShapeList() {
    builder.declareShape("circly", "ellipse");
    builder.declareShape("squarey", "rectangle");
    builder.addMotion("circly", 10, 0, 5, 10, 10, 255, 255, 255, 50, 10, 20, 20, 40, 0, 255, 255);
    builder.addMotion("squarey", 5, 0, 5, 10, 10, 255, 255, 255, 45, 10, 20, 20, 40, 0, 255, 255);

    assertEquals(2, easyAnimator.getShapeList().size());
    assertEquals(
        "[RGB(255,255,255) rectangle squarey with corner at (0,5), width 10 and height 10,"
            + " RGB(255,255,255) oval circly with center at (0,5), radius 10 and 10]",
        easyAnimator.getShapeList().toString());

    //Checking the shape list doesn't take duplicate shapes
    builder.addMotion("squarey", 51, 0, 5, 10, 10, 255, 255, 255, 60, 10, 20, 20, 40, 0, 255, 255);
    assertEquals(2, easyAnimator.getShapeList().size());
    assertEquals(
        "[RGB(255,255,255) rectangle squarey with corner at (0,5), width 10 and height 10,"
            + " RGB(255,255,255) oval circly with center at (0,5), radius 10 and 10]",
        easyAnimator.getShapeList().toString());

  }

  @Test
  public void testGetTypeByName() {
    easyAnimator.updateShapeLedger("circly", "ellipse");
    assertEquals("ellipse", easyAnimator.getTypeByName("circly"));
  }

  @Test
  public void testGetAnimationsByShape() {
    builder.declareShape("circly", "ellipse");
    builder.declareShape("squarey", "rectangle");
    builder.addMotion("circly", 10, 0, 5, 10, 10, 255, 255, 255, 50, 10, 20, 20, 40, 0, 255, 255);
    builder.addMotion("squarey", 5, 0, 5, 10, 10, 255, 255, 255, 45, 10, 20, 20, 40, 0, 255, 255);

    assertEquals(
        "[squarey moves from (0,5) to (10,20) from time t=5000 to t=45000, squarey changes"
            + " from RGB(255,255,255) to RGB(0,255,255) from time t=5000 to t=45000, squarey "
            + "changes width from 10 to 20 and height from 10 to 40 from time t=5000 to t=45000]",
        easyAnimator.getAnimationsByShape(easyAnimator.getShapeList().get(0)).toString());
    assertEquals(3, easyAnimator.getAnimationsByShape(easyAnimator.getShapeList().get(0)).size());

    assertEquals(
        "[circly moves from (0,5) to (10,20) from time t=10000 to t=50000, circly changes"
            + " from RGB(255,255,255) to RGB(0,255,255) from time t=10000 to t=50000, circly "
            + "changes radius from 10 to 20 and other radius from 10 to 40 from time t=10000 "
            + "to t=50000]",
        easyAnimator.getAnimationsByShape(easyAnimator.getShapeList().get(1)).toString());
    assertEquals(3, easyAnimator.getAnimationsByShape(easyAnimator.getShapeList().get(1)).size());
  }

  @Test
  public void testGetMilliseconds() {
    assertEquals(1000, easyAnimator.getMilliseconds(1), 0.01);
    easyAnimator.setTicksPerSecond(2);
    assertEquals(500, easyAnimator.getMilliseconds(1), 0.01);
  }

}