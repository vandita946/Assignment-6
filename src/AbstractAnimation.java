/**
 * This class implements the Animation interface and represents a shape and the methods available
 * to it to make it an animation.
 */
public class AbstractAnimation implements Animation {
  private Shape shape;
  private int startingTime;
  private int endingTime;
  private TypeOfAnimation type;

  public AbstractAnimation(Shape shape, int startingTime, int endingTime, TypeOfAnimation type) throws IllegalArgumentException{
    if(startingTime < shape.getAppearTime() || endingTime > shape.getDisappearTime() ) {
      throw new IllegalArgumentException("Starting and ending time must be within shape's appear and disappear time.");
    }
    this.shape = shape;
    this.startingTime = startingTime;
    this.endingTime = endingTime;
    this.type = type;
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

  public TypeOfAnimation getType() {
    return this.type;
  }
}
