/* CS 5004 - Easy Animator - Model
 * Vandita Attal & Swapnil Mittal
 */

package cs5004.animator.shape;

/**
 * This class represents a circle. It implements the AbstractShape.
 */
public class Ellipse extends AbstractShape {

  private final double xRadius;
  private final double yRadius;

  /**
   * Construct a circle object using the given parameters.
   *
   * @param x      x coordinate of the center of this circle
   * @param y      y coordinate of the center of this circle
   * @param radius the radius of this circle
   */
  public Ellipse(double x, double y, double radius, String name, int red, int green, int blue, double canvasWidth,
      double canvasHeight, double cornerX, double cornerY, int appearTime, int disappearTime) {
    super(new Point2D(x, y), name, red, green, blue, canvasWidth, canvasHeight, cornerX, cornerY, appearTime, disappearTime, TypeOfShape.ELLIPSE);

    if ((x - radius) < cornerX || (y - radius) < cornerY || (x + radius) > (cornerX + canvasWidth)
        || (y + radius) > (cornerY + canvasHeight)) {
      throw new IllegalArgumentException(
          "The dimensions of this shape are out of bounds of the canvas.");
    } else if (radius <= 0) {
      throw new IllegalArgumentException("Shape dimensions cannot be negative or zero.");
    }

//    this.color = new Color(red, green, blue);
    this.xRadius = this.yRadius = radius;
  }

  /**
   * This is a class representing an Ellipse.
   *  @param color         is the color of the oval.
   * @param x             is the x coordinate of the centre of oval.
   * @param y             is the y coordinate of the centre of oval.
   * @param xRadius       is the x radius or the width of the oval.
   * @param yRadius       is the y radius or the height of the oval.
   * @param name          is the name of the oval.
   * @param canvasWidth   is the canvas width.
   * @param canvasHeight  is the canvas height.
   * @param appearTime    is the appear time for oval.
   * @param disappearTime is the disappear time for oval.
   */
  public Ellipse(double x, double y, double xRadius, double yRadius, String name, int red, int green, int blue,
      double canvasWidth, double canvasHeight, double cornerX, double cornerY, double appearTime, double disappearTime) {
    super(new Point2D(x, y), name, red, green, blue, canvasWidth, canvasHeight, cornerX, cornerY, appearTime, disappearTime, TypeOfShape.ELLIPSE);

//    if ((x - xRadius) < cornerX || (y - yRadius) < cornerY || (x + xRadius) > (cornerX + canvasWidth)
//        || (y + yRadius) > (cornerY + canvasHeight)) {
//      throw new IllegalArgumentException(
//          "The dimensions of this shape are out of bounds of the canvas.");
//    } else
      if (xRadius <= 0 || yRadius <= 0) {
      throw new IllegalArgumentException("Shape dimensions cannot be negative or zero.");
    }

    this.xRadius = xRadius;
    this.yRadius = yRadius;
  }

  @Override
  public double area() {
    return Math.PI * this.xRadius * this.yRadius;
  }

  @Override
  public double perimeter() {
    return 2 * Math.PI * Math.sqrt((Math.pow(xRadius, 2) + Math.pow(yRadius, 2)) / 2);
  }

  /**
   * Create and return a shape of the same kind as this one, with its dimensions changed by the
   * provided new width and height.
   *
   * @param newXRadius new width
   * @param newYRadius new height
   * @return the new Shape with changed dimensions
   */
  @Override
  public Shape changeDimensions(double newXRadius, double newYRadius) {

    return new Ellipse(reference.getX(), reference.getY(), newXRadius, newYRadius, name,
        this.getColor().getRed(), this.getColor().getGreen(), this.getColor().getBlue(), canvasWidth, canvasHeight, cornerX, cornerY,appearTime, disappearTime);
  }

  @Override
  public double getWidth() {
    return this.xRadius;
  }

  @Override
  public double getHeight() {
    return this.yRadius;
  }

  @Override
  public String toString() {
    return String.format("RGB(%d,%d,%d) oval %s with center at (%.0f,%.0f), radius %.0f and %.0f",
        this.getColor().getRed(), this.getColor().getGreen(), this.getColor().getBlue(), this.name, this.reference.getX(), this.reference.getY(),
        this.xRadius, this.yRadius);
  }
}