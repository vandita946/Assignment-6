/**
 * This class implements the Animation interface and represents a shape and the methods available
 * to it to make it an animation.
 */
public class AbstractAnimation implements Animation {
  private Shape shape;
  private int startingTime;
  private int endingTime;

  public enum TypeOfAnimation {
    MOVE, SCALE, COLOR;
  }

  public AbstractAnimation(Shape shape, int startingTime, int endingTime) {
    this.shape = shape;
    this.startingTime = startingTime;
    this.endingTime = endingTime;
  }

  @Override
  public void actionStep() {

  }

  public int getStartingTime() {
    return this.startingTime;
  }

  public int getEndingTime() {
    return this.endingTime;
  }

  public Shape getShape() {
    return this.shape;
  }
}
