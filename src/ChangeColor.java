import java.awt.Color;

/**
 * This class extends the AbstractAnimation class and represents the methods needed to change the
 * color of a shape as part of an animation.
 */
public class ChangeColor extends AbstractAnimation {

  private Shape shape;
  private int startingTime;
  private int endingTime;
  private Color newColor;

  public ChangeColor(Shape shape, int startingTime, int endingTime, String newColorName)
      throws IllegalArgumentException {
    super(shape, startingTime, endingTime, TypeOfAnimation.COLOR);
    if (Color.getColor(newColorName) != null) {
      this.newColor = Color.getColor(newColorName);
    } else {
      throw new IllegalArgumentException("Color is invalid.");
    }
  }

  public void actionStep() {
    this.shape.changeColor(this.newColor);
  }

  @Override
  public String toString() {
    return this.shape.getName() + "changes from " + this.shape.getColor().toString() + " to "
        + this.newColor.toString() + " from time t=" + startingTime + " to t=" + endingTime;
  }
}
