package cs5004.easyanimator.animation;

import cs5004.easyanimator.animation.AbstractAnimation;
import cs5004.easyanimator.animation.TypeOfAnimation;
import cs5004.easyanimator.shape.Shape;
import cs5004.easyanimator.shape.TypeOfShape;

/**
 * This class extends the cs5004.easyanimator.animation.AbstractAnimation class and represents the
 * methods needed to move a shape as part of an animation.
 */
public class Move extends AbstractAnimation {

  private double toX;
  private double toY;

  public Move(Shape shape, TypeOfShape shapeType, int startingTime, int endingTime, double toX,
      double toY, double canvasWidth, double canvasHeight) throws IllegalArgumentException {
    super(shape, startingTime, endingTime, TypeOfAnimation.MOVE);

    if (canvasHeight < 0 || canvasWidth < 0) {
      throw new IllegalArgumentException("Canvas dimensions cannot be negative.");
    } else if (toX < 0 || toX > canvasWidth || toX + shape.getWidth() > canvasWidth || (
        shapeType.equals(TypeOfShape.OVAL) && (toX - shape.getWidth() < 0))) {
      throw new IllegalArgumentException("The new x coordinate pushes the shape out of bounds.");
    } else if (toY < 0 || toY > canvasHeight || toY + shape.getHeight() > canvasHeight || (
        shapeType.equals(TypeOfShape.OVAL) && (toY - shape.getHeight() < 0))) {
      throw new IllegalArgumentException("The new y coordinate pushes the shape out of bounds.");
    }

    this.toX = toX;
    this.toY = toY;
  }

  public void actionStep() {
    this.shape.setPosition(toX, toY);
  }

  @Override
  public String toString() {
    return String
        .format("%s moves from (%.0f,%.0f) to (%.0f,%.0f) from time t=%d to t=%d", this.shape.getName(),
            this.shape.getPosition().getX(), this.shape.getPosition().getY(), toX, toY,
            startingTime, endingTime);
  }
}
