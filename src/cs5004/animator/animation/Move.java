/* CS 5004 - Easy Animator - Model
 * Vandita Attal & Swapnil Mittal
 */

package cs5004.animator.animation;

import cs5004.animator.shape.Shape;
import cs5004.animator.shape.TypeOfShape;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class extends the AbstractAnimation class and represents the methods needed to move a shape
 * as part of an animation.
 */
public class Move extends AbstractAnimation {

  private final double toX;
  private final double toY;

  /**
   * This is the constructor for initializing the given parameters.
   *
   * @param shape        is the shape that needs to be moved.
   * @param shapeType    is the shape type of the shape that needs to be moved.
   * @param startingTime is the starting time of the move animation.
   * @param endingTime   is the ending time of the move animation.
   * @param toX          is the new X coordinate to move to.
   * @param toY          is the new Y coordinate to move to.
   * @param canvasWidth  is the canvas width.
   * @param canvasHeight is the canvas height.
   * @throws IllegalArgumentException if any of the given arguments is invalid or does not meet the
   *                                  given criteria.
   */
  public Move(Shape shape, TypeOfShape shapeType, double startingTime, double endingTime, double toX,
      double toY, double canvasWidth, double canvasHeight, double cornerX, double cornerY) throws IllegalArgumentException {
    super(shape, startingTime, endingTime, TypeOfAnimation.MOVE);

    if (canvasHeight < 0 || canvasWidth < 0) {
      throw new IllegalArgumentException("Canvas dimensions cannot be negative.");
    }
//    } else if (toX < cornerX || toX > (cornerX + canvasWidth) || toX + shape.getWidth() > (cornerX + canvasWidth) || (
//        shapeType.equals(TypeOfShape.ELLIPSE) && (toX - shape.getWidth() < cornerX))) {
//      throw new IllegalArgumentException("The new x coordinate pushes the shape out of bounds.");
//    } else if (toY < cornerY || toY > (cornerY + canvasHeight) || toY + shape.getHeight() > (cornerY + canvasHeight) || (
//        shapeType.equals(TypeOfShape.ELLIPSE) && (toY - shape.getHeight() < cornerY))) {
//      System.out.println(toX + " " + toY);
//      System.out.println(cornerX + " " + cornerY);
//      System.out.println(shape.getHeight());
//      System.out.println(canvasHeight);
//      throw new IllegalArgumentException("The new y coordinate pushes the shape out of bounds.");
//    }

    this.toX = toX;
    this.toY = toY;
  }

  /**
   * This method will be used by controller to perform the animation.
   */
  public void actionStep() {
    //the toX and toY will be determined by the tween helper.
    this.shape.setPosition(toX, toY);
  }

  @Override
  public String toString() {
    return String
        .format("%s moves from (%.0f,%.0f) to (%.0f,%.0f) from time t=%.0f to t=%.0f",
            this.shape.getName(),
            this.shape.getPosition().getX(), this.shape.getPosition().getY(), toX, toY,
            startingTime, endingTime);
  }

  @Override
  public List<String[]> getChanges() {
    List<String[]> changes = new ArrayList<>();
    if (shape.getPosition().getX() != toX) {
      if (shape.getTypeOfShape().equals(TypeOfShape.RECTANGLE)) {
        changes.add(new String[]{"x", String.valueOf(shape.getPosition().getX()), String.valueOf(toX)});
      } else if (shape.getTypeOfShape().equals(TypeOfShape.ELLIPSE)) {
        changes.add(new String[]{"cx", String.valueOf(shape.getPosition().getX()), String.valueOf(toX)});
      }
    }
    if (shape.getPosition().getY() != toY) {
      if (shape.getTypeOfShape().equals(TypeOfShape.RECTANGLE)) {
        changes.add(new String[]{"y", String.valueOf(shape.getPosition().getY()), String.valueOf(toY)});
      } else if (shape.getTypeOfShape().equals(TypeOfShape.ELLIPSE)) {
        changes.add(new String[]{"cy", String.valueOf(shape.getPosition().getY()), String.valueOf(toY)});
      }
    }
    return changes;
  }
}
