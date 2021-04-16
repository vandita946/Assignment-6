/* CS 5004 - Easy Animator - Model
 * Vandita Attal & Swapnil Mittal
 */

package cs5004.animator.animation;

import cs5004.animator.shape.ColorNames;
import cs5004.animator.shape.Shape;
import cs5004.animator.shape.TypeOfShape;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * This class extends the AbstractAnimation class and represents the methods needed to change the
 * color of a shape as part of an animation.
 */
public class ChangeColor extends AbstractAnimation {

  private Color newColor;

  /**
   * This method is used to change the color of the object.
   *
   * @param shape        is the shape of the object.
   * @param startingTime is the starting time for the color change.
   * @param endingTime   is the ending time for the color change.
   * @param newColor     is the new color to change to.
   * @throws IllegalArgumentException if the entered color is invalid.
   */
  public ChangeColor(Shape shape, int startingTime, int endingTime, int red, int green, int blue)
      throws IllegalArgumentException {
    super(shape, startingTime, endingTime, TypeOfAnimation.COLOR);
    this.newColor = new Color(red, green, blue);
  }

  /**
   * This step will be used by controller to perform the color change.
   */
  public void actionStep() {
    shape.changeColor(this.newColor);
  }



  @Override
  public String toString() {
    return String.format("%s changes from RGB(%d,%d,%d) to RGB(%d,%d,%d) from time t=%d to t=%d", this.shape.getName(), this.shape.getColor().getRed(),
        this.shape.getColor().getGreen(), this.shape.getColor().getBlue(),
           newColor.getRed(), newColor.getGreen(), newColor.getBlue(), startingTime, endingTime);
  }

  @Override
  public Map<String, String[]> getChanges() {
    Map<String, String[]> changes = new HashMap<>();
    changes.put("fill", new String[]{String.format("rgb(%d,%d,%d)", shape.getColor().getRed(), shape.getColor().getGreen(), shape.getColor().getBlue()),
    String.format("rgb(%d,%d,%d)", newColor.getRed(), newColor.getGreen(), newColor.getBlue())});

    return changes;
  }
}
