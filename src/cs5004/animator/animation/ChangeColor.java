/* CS 5004 - Easy Animator - Model
 * Vandita Attal & Swapnil Mittal
 */

package cs5004.animator.animation;

import cs5004.animator.shape.Shape;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This class extends the AbstractAnimation class and represents the methods needed to change the
 * color of a shape as part of an animation.
 */
public class ChangeColor extends AbstractAnimation {

  private final Color newColor;

  /**
   * This method is used to change the color of the object.
   *
   * @param shape        is the shape of the object.
   * @param startingTime is the starting time for the color change.
   * @param endingTime   is the ending time for the color change.
   * @param red          RGB red value
   * @param green        RGB green value
   * @param blue         RGB blue value
   * @throws IllegalArgumentException if the entered color is invalid.
   */
  public ChangeColor(Shape shape, double startingTime, double endingTime, int red, int green,
      int blue) throws IllegalArgumentException {
    super(shape, startingTime, endingTime, TypeOfAnimation.COLOR);
    this.newColor = new Color(red, green, blue);
  }

  @Override
  public void actionStep(double tick) {
    double tweenR =
        (shape.getColor().getRed() * (endingTime - tick) / (endingTime - startingTime)) + (
            newColor.getRed() * (tick - startingTime) / (endingTime - startingTime));
    double tweenG =
        (shape.getColor().getGreen() * (endingTime - tick) / (endingTime - startingTime)) + (
            newColor.getGreen() * (tick - startingTime) / (endingTime - startingTime));
    double tweenB =
        (shape.getColor().getBlue() * (endingTime - tick) / (endingTime - startingTime)) + (
            newColor.getBlue() * (tick - startingTime) / (endingTime - startingTime));
    shape.changeColor(new Color((int) tweenR, (int) tweenG, (int) tweenB));
  }

  /**
   * Returns a string representation of the color change animation.
   *
   * @return String representation of color change animation
   */
  @Override
  public String toString() {
    return String
        .format("%s changes from RGB(%d,%d,%d) to RGB(%d,%d,%d) from time t=%.0f to t=%.0f",
            this.shape.getName(), this.shape.getColor().getRed(),
            this.shape.getColor().getGreen(), this.shape.getColor().getBlue(),
            newColor.getRed(), newColor.getGreen(), newColor.getBlue(), startingTime, endingTime);
  }

  @Override
  public List<String[]> getChanges() {
    List<String[]> changes = new ArrayList<>();
    changes.add(new String[]{"fill",
        String.format("rgb(%d,%d,%d)", shape.getColor().getRed(), shape.getColor().getGreen(),
            shape.getColor().getBlue()),
        String.format("rgb(%d,%d,%d)", newColor.getRed(), newColor.getGreen(),
            newColor.getBlue())});

    return changes;
  }
}
