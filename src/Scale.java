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
  private TypeOfShape shapeType;


  public Scale(Shape shape, TypeOfShape shapeType, int startingTime, int endingTime,
      double newWidth, double newHeight, double canvasWidth, double canvasHeight)
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
        return this.shape.getName() + " changes height from " + shape.getHeight() + " to " + this.newHeight
            + " from time t=" + startingTime + " to t=" + endingTime;
      } else if (this.newHeight == shape.getHeight()) {
        return this.shape.getName() + " changes width from " + shape.getWidth() + " to " + this.newWidth
            + " from time t=" + startingTime + " to t=" + endingTime;
      } else {
        return this.shape.getName() + " changes width from " + shape.getWidth() + " to " + this.newWidth
            + " and height from " + shape.getHeight() + " to " + this.newHeight + " from time t=" + startingTime
            + " to t=" + endingTime;
      }
    } else {
      if(this.newWidth == this.newHeight) {
        return this.shape.getName() + " changes radius from " + shape.getWidth() + " and " + shape.getHeight() + " to " + this.newWidth
            + " and " + this.newHeight + " from time t=" + startingTime + " to t=" + endingTime;
      } else if (this.newWidth == shape.getWidth()) {
        return this.shape.getName() + " changes radius from " + shape.getHeight() + " to " + this.newHeight
            + " from time t=" + startingTime + " to t=" + endingTime;
      } else if (this.newHeight == shape.getHeight()) {
        return this.shape.getName() + " changes radius from " + shape.getWidth() + " to " + this.newWidth
            + " from time t=" + startingTime + " to t=" + endingTime;
      } else {
        return this.shape.getName() + " changes radius from " + shape.getWidth() + " to " + this.newWidth
            + " and other radius from " + shape.getHeight() + " to " + this.newHeight + " from time t=" + startingTime
            + " to t=" + endingTime;
      }
    }

  }
}
