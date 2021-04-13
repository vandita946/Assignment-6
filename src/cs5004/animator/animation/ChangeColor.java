/* CS 5004 - Easy Animator - Model
 * Vandita Attal & Swapnil Mittal
 */

package cs5004.animator.animation;

import cs5004.animator.shape.ColorNames;
import cs5004.animator.shape.Shape;
import java.awt.Color;

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
   * @param newColor     is the new color to change to.
   * @throws IllegalArgumentException if the entered color is invalid.
   */
  public ChangeColor(Shape shape, int startingTime, int endingTime, Color newColor)
      throws IllegalArgumentException {
    super(shape, startingTime, endingTime, TypeOfAnimation.COLOR);
    if (newColor == null) {
      throw new IllegalArgumentException("New color is null.");
    }
    this.newColor = newColor;
  }

  /**
   * This step will be used by controller to perform the color change.
   */
  public void actionStep() {
    shape.changeColor(this.newColor);
  }

  @Override
  public String toString() {
    String newColorString = "";
    for (ColorNames c : ColorNames.values()) {
      if (c.getValue() == newColor) {
        newColorString = c.getText();
      }
    }
    return this.shape.getName() + " changes from " + this.shape.getColor() + " to "
           + newColorString + " from time t=" + startingTime + " to t=" + endingTime;
  }
}
