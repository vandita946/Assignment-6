/* CS 5004 - Easy Animator - Model
 * Vandita Attal & Swapnil Mittal
 */

package cs5004.easyanimator.animation;

import cs5004.easyanimator.shape.Shape;
import cs5004.easyanimator.shape.TypeOfShape;

/**
 * This class extends the AbstractAnimation class and represents the methods needed to scale a shape
 * as part of an animation.
 */
public class Scale extends AbstractAnimation {

  private final double newWidth;
  private final double newHeight;
  private final TypeOfShape shapeType;

  /**
   * This is the constructor for the scale class.
   *
   * @param shape        is the shape to scale.
   * @param shapeType    is the type of shape.
   * @param startingTime is the starting time for the scaling.
   * @param endingTime   is the ending time for the scaling.
   * @param newWidth     is the new width to scale to.
   * @param newHeight    is the new height to scale to.
   * @throws IllegalArgumentException is the exception thrown if given parameters are invalid.
   */
  public Scale(Shape shape, TypeOfShape shapeType, int startingTime, int endingTime,
      double newWidth, double newHeight)
      throws IllegalArgumentException {
    super(shape, startingTime, endingTime, TypeOfAnimation.SCALE);

    if (newWidth == shape.getWidth() && newHeight == shape.getHeight()) {
      throw new IllegalArgumentException("The shape is already at the given dimensions.");
    }

    this.shapeType = shapeType;
    this.newWidth = newWidth;
    this.newHeight = newHeight;
  }

  @Override
  public void actionStep() {
    this.shape.changeDimensions(this.newWidth, this.newHeight);
  }

  @Override
  public String toString() {

    if (shapeType.equals(TypeOfShape.RECTANGLE)) {
      if (this.newWidth == shape.getWidth()) {
        return String
            .format("%s changes height from %.0f to %.0f from time t=%d to t=%d", shape.getName(),
                shape.getHeight(), newHeight, startingTime, endingTime);
      } else if (this.newHeight == shape.getHeight()) {
        return String
            .format("%s changes width from %.0f to %.0f from time t=%d to t=%d", shape.getName(),
                shape.getWidth(), newWidth, startingTime, endingTime);
      } else {
        return String.format(
            "%s changes width from %.0f to %.0f and height from %.0f to %.0f from time t=%d to "
            + "t=%d", shape.getName(), shape.getWidth(), newWidth, shape.getHeight(), newHeight,
            startingTime, endingTime);
      }
    } else {
      if (this.newWidth == this.newHeight) {
        return String.format(
            "%s changes radius from %.0f and %.0f to %.0f from time t=%d to t=%d",
            shape.getName(), shape.getWidth(), shape.getHeight(), newHeight, startingTime,
            endingTime);
      } else if (this.newWidth == shape.getWidth()) {
        return String
            .format("%s changes radius from %.0f to %.0f from time t=%d to t=%d", shape.getName(),
                shape.getHeight(), newHeight, startingTime, endingTime);
      } else if (this.newHeight == shape.getHeight()) {
        return String
            .format("%s changes radius from %.0f to %.0f from time t=%d to t=%d", shape.getName(),
                shape.getWidth(), newWidth, startingTime, endingTime);
      } else {
        return String.format(
            "%s changes radius from %.0f to %.0f and other radius from %.0f to %.0f from time t=%d to t=%d",
            shape.getName(), shape.getWidth(), newWidth, shape.getHeight(), newHeight, startingTime,
            endingTime);
      }
    }

  }
}
