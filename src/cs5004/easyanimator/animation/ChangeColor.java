package cs5004.easyanimator.animation;

import cs5004.easyanimator.animation.AbstractAnimation;
import cs5004.easyanimator.animation.TypeOfAnimation;
import cs5004.easyanimator.shape.ColorNames;
import cs5004.easyanimator.shape.Shape;
import java.awt.Color;

/**
 * This class extends the AbstractAnimation class and represents the methods needed to change the
 * color of a shape as part of an animation.
 */
public class ChangeColor extends AbstractAnimation {

  private Color newColor;

  public ChangeColor(Shape shape, int startingTime, int endingTime, Color newColor) {
    super(shape, startingTime, endingTime, TypeOfAnimation.COLOR);
    this.newColor = newColor;
  }

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
