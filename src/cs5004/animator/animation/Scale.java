/* CS 5004 - Easy Animator - Model
 * Vandita Attal & Swapnil Mittal
 */

package cs5004.animator.animation;

import cs5004.animator.shape.Shape;
import cs5004.animator.shape.TypeOfShape;
import java.util.ArrayList;
import java.util.List;

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
  public Scale(Shape shape, TypeOfShape shapeType, double startingTime, double endingTime,
      double newWidth, double newHeight)
      throws IllegalArgumentException {
    super(shape, startingTime, endingTime, TypeOfAnimation.SCALE);

    this.shapeType = shapeType;
    this.newWidth = newWidth;
    this.newHeight = newHeight;
  }

  @Override
  public void actionStep(double tick) {
    double tweenWidth = (shape.getWidth() * (endingTime - tick) / (endingTime - startingTime)) + (
        newWidth * (tick - startingTime) / (endingTime - startingTime));
    double tweenHeight = (shape.getHeight() * (endingTime - tick) / (endingTime - startingTime)) + (
        newHeight * (tick - startingTime) / (endingTime - startingTime));
    this.shape.changeDimensions(tweenWidth, tweenHeight);
  }

  @Override
  public List<String[]> getChanges() {
    List<String[]> changes = new ArrayList<>();
    if (shape.getWidth() != newWidth) {
      if (shape.getTypeOfShape().equals(TypeOfShape.RECTANGLE)) {
        changes
            .add(new String[]{"width", String.valueOf(shape.getWidth()), String.valueOf(newWidth)});
      } else if (shape.getTypeOfShape().equals(TypeOfShape.ELLIPSE)) {
        changes.add(new String[]{"cx", String.valueOf(shape.getWidth()), String.valueOf(newWidth)});
      }
    }
    if (shape.getHeight() != newHeight) {
      if (shape.getTypeOfShape().equals(TypeOfShape.RECTANGLE)) {
        changes.add(
            new String[]{"height", String.valueOf(shape.getHeight()), String.valueOf(newHeight)});
      } else if (shape.getTypeOfShape().equals(TypeOfShape.ELLIPSE)) {
        changes
            .add(new String[]{"cy", String.valueOf(shape.getHeight()), String.valueOf(newHeight)});
      }
    }
    return changes;
  }

  /**
   * Returns a string representation of the scale animation.
   *
   * @return string representation of the scale animation.
   */
  @Override
  public String toString() {

    if (shapeType.equals(TypeOfShape.RECTANGLE)) {
      if (this.newWidth == shape.getWidth()) {
        return String
            .format("%s changes height from %.0f to %.0f from time t=%.0f to t=%.0f",
                shape.getName(),
                shape.getHeight(), newHeight, startingTime, endingTime);
      } else if (this.newHeight == shape.getHeight()) {
        return String
            .format("%s changes width from %.0f to %.0f from time t=%.0f to t=%.0f",
                shape.getName(),
                shape.getWidth(), newWidth, startingTime, endingTime);
      } else {
        return String.format(
            "%s changes width from %.0f to %.0f and height from %.0f to %.0f from time t=%.0f to "
            + "t=%.0f", shape.getName(), shape.getWidth(), newWidth, shape.getHeight(),
            newHeight,
            startingTime, endingTime);
      }
    } else {
      if (this.newWidth == this.newHeight) {
        return String.format(
            "%s changes radius from %.0f and %.0f to %.0f from time t=%.0f to t=%.0f",
            shape.getName(), shape.getWidth(), shape.getHeight(), newHeight, startingTime,
            endingTime);
      } else if (this.newWidth == shape.getWidth()) {
        return String
            .format("%s changes radius from %.0f to %.0f from time t=%.0f to t=%.0f",
                shape.getName(),
                shape.getHeight(), newHeight, startingTime, endingTime);
      } else if (this.newHeight == shape.getHeight()) {
        return String
            .format("%s changes radius from %.0f to %.0f from time t=%.0f to t=%.0f",
                shape.getName(),
                shape.getWidth(), newWidth, startingTime, endingTime);
      } else {
        return String.format(
            "%s changes radius from %.0f to %.0f and other radius from %.0f to %.0f from time "
                + "t=%.0f to t=%.0f",
            shape.getName(), shape.getWidth(), newWidth, shape.getHeight(), newHeight, startingTime,
            endingTime);
      }
    }

  }
}
