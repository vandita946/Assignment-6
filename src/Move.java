/**
 * This class extends the AbstractAnimation class and represents the methods needed to move a shape
 * as part of an animation.
 */
public class Move extends AbstractAnimation {

  private Shape shape;
  private int startingTime;
  private int endingTime;
  private double toX;
  private double toY;

  public Move(Shape shape, int startingTime, int endingTime, double toX, double toY) {
    super(shape, startingTime, endingTime);

    this.toX = toX;
    this.toY = toY;
  }

  public void actionStep() {
    this.shape.setPosition(toX, toY);
  }

  @Override
  public String toString() {
    return this.shape.getName() + " moves from (" + this.shape.getPosition().getX() + ","
        + this.shape.getPosition().getY() + ") to (" + toX + "," + toY
        + ") from time t=" + startingTime + " to t=" + endingTime;
  }
}
