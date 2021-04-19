/* CS 5004 - Easy Animator - Model
 * Vandita Attal & Swapnil Mittal
 */

package cs5004.animator.animation;

import cs5004.animator.shape.Shape;
import cs5004.animator.shape.TypeOfShape;
import java.util.ArrayList;
import java.util.List;

/**
 * This class extends the AbstractAnimation class and represents the methods needed to move a shape
 * as part of an animation.
 */
public class Move extends AbstractAnimation {

  private final double toX;
  private final double toY;

  /**
   * This is the constructor for initializing a Move animation to the given parameters.
   *
   * @param shape        is the shape that needs to be moved.
   * @param startingTime is the starting time of the move animation.
   * @param endingTime   is the ending time of the move animation.
   * @param toX          is the new X coordinate to move to.
   * @param toY          is the new Y coordinate to move to.
   */
  public Move(Shape shape, double startingTime, double endingTime, double toX, double toY) {
    super(shape, startingTime, endingTime, TypeOfAnimation.MOVE);
    this.toX = toX;
    this.toY = toY;
  }

  @Override
  public void actionStep(double tick) {
    double tweenX =
        (shape.getPosition().getX() * (endingTime - tick) / (endingTime - startingTime)) + (
            toX * (tick - startingTime) / (endingTime - startingTime));
    double tweenY =
        (shape.getPosition().getY() * (endingTime - tick) / (endingTime - startingTime)) + (
            toY * (tick - startingTime) / (endingTime - startingTime));

    this.shape.setPosition(tweenX, tweenY);
  }


  /**
   * Returns a string representation of the move animation.
   *
   * @return string representation of the move animation
   */
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
        changes.add(
            new String[]{"x", String.valueOf(shape.getPosition().getX()), String.valueOf(toX)});
      } else if (shape.getTypeOfShape().equals(TypeOfShape.ELLIPSE)) {
        changes.add(
            new String[]{"cx", String.valueOf(shape.getPosition().getX()), String.valueOf(toX)});
      }
    }

    if (shape.getPosition().getY() != toY) {
      if (shape.getTypeOfShape().equals(TypeOfShape.RECTANGLE)) {
        changes.add(
            new String[]{"y", String.valueOf(shape.getPosition().getY()), String.valueOf(toY)});
      } else if (shape.getTypeOfShape().equals(TypeOfShape.ELLIPSE)) {
        changes.add(
            new String[]{"cy", String.valueOf(shape.getPosition().getY()), String.valueOf(toY)});
      }
    }

    return changes;
  }
}
