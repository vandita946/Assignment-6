/**
 * This class extends the AbstractAnimation class and represents the methods needed to scale a shape
 * as part of an animation.
 */
public class Scale extends AbstractAnimation {

  private double newWidth;
  private double newHeight;
  private Shape shape;
  private int startingTime;
  private int endingTime;
  private double factor;
  private boolean isResize;

  public Scale(Shape shape, int startingTime, int endingTime, double newWidth, double newHeight) {
    super(shape, startingTime, endingTime);
    this.newWidth = newWidth;
    this.newHeight = newHeight;
    this.isResize = false;
  }

  public Scale(Shape shape, int startingTime, int endingTime, double factor) {
    super(shape, startingTime, endingTime);
    this.factor = factor;
    this.isResize = true;
  }

  //are we supposed to override?
  public void actionStep() {
    if (this.isResize) {
      this.shape.resize(this.factor);
    } else {
      this.shape.changeDimensions(this.newWidth, this.newHeight);
    }
  }

  //how to get the original height and width of the shape?
  @Override
  public String toString() {
    if (this.isResize) {
      return this.shape.getName() + " scales by a factor of " + this.factor + " from time t="
          + startingTime + " to t=" + endingTime;
    } else if (this.newWidth == 0) {
      return this.shape.getName() + " changes height from something to " + this.newHeight
          + " from time t=" + startingTime + " to t=" + endingTime;
    } else if (this.newHeight == 0) {
      return this.shape.getName() + " changes width from something to " + this.newWidth
          + " from time t=" + startingTime + " to t=" + endingTime;
    } else {
      return this.shape.getName() + " changes width from something to " + this.newWidth
          + " and height from something to " + this.newHeight + " from time t=" + startingTime
          + " to t=" + endingTime;
    }
  }
}
