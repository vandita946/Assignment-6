/* CS 5004 - Easy Animator - Model
 * Vandita Attal & Swapnil Mittal
 */

package cs5004.animator.animation;

import cs5004.animator.shape.Shape;
import cs5004.animator.shape.TypeOfShape;

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
  public Move(Shape shape, TypeOfShape shapeType, int startingTime, int endingTime, double toX,
      double toY, double canvasWidth, double canvasHeight, double cornerX, double cornerY) throws IllegalArgumentException {
    super(shape, startingTime, endingTime, TypeOfAnimation.MOVE);

    if (canvasHeight < 0 || canvasWidth < 0) {
      throw new IllegalArgumentException("Canvas dimensions cannot be negative.");
    } else if (toX < cornerX || toX > (cornerX + canvasWidth) || toX + shape.getWidth() > (cornerX + canvasWidth) || (
        shapeType.equals(TypeOfShape.OVAL) && (toX - shape.getWidth() < cornerX))) {
      throw new IllegalArgumentException("The new x coordinate pushes the shape out of bounds.");
    } else if (toY < cornerY || toY > (cornerY + canvasHeight) || toY + shape.getHeight() > (cornerY + canvasHeight) || (
        shapeType.equals(TypeOfShape.OVAL) && (toY - shape.getHeight() < cornerY))) {
      throw new IllegalArgumentException("The new y coordinate pushes the shape out of bounds.");
    }

    this.toX = toX;
    this.toY = toY;
  }

  /**
   * This method will be used by controller to perform the animation.
   */
  public void actionStep() {
    this.shape.setPosition(toX, toY);
  }

  @Override
  public String toString() {
    return String
        .format("%s moves from (%.0f,%.0f) to (%.0f,%.0f) from time t=%d to t=%d",
            this.shape.getName(),
            this.shape.getPosition().getX(), this.shape.getPosition().getY(), toX, toY,
            startingTime, endingTime);
  }
}
