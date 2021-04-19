/* CS 5004 - Easy Animator - Model
 * Vandita Attal & Swapnil Mittal
 */

package cs5004.animator.shape;

/**
 * This class represents a circle. It implements the AbstractShape.
 */
public class Ellipse extends AbstractShape {

  private double xRadius;
  private double yRadius;


  /**
   * Constructs an ellipse (circle) object with the given parameters.
   *
   * @param x             x position
   * @param y             y position
   * @param radius        radius of the ellipse
   * @param name          unique name
   * @param red           RGB red value
   * @param green         RGB green value
   * @param blue          RGB blue value
   * @param appearTime    time the shape appears
   * @param disappearTime time the shape disappears
   */
  public Ellipse(double x, double y, double radius, String name, int red, int green, int blue,
      int appearTime, int disappearTime) {

    super(new Point2D(x, y), name, red, green, blue, appearTime, disappearTime,
        TypeOfShape.ELLIPSE);

    if (radius <= 0) {
      throw new IllegalArgumentException("Shape dimensions cannot be negative or zero.");
    }

    this.xRadius = this.yRadius = radius;
  }

  /**
   * * Constructs an ellipse (oval) object with the given parameters.
   *
   * @param x             x position
   * @param y             y position
   * @param xRadius       x-radius of the ellipse
   * @param yRadius       y-radius of the ellipse
   * @param name          unique name
   * @param red           RGB red value
   * @param green         RGB green value
   * @param blue          RGB blue value
   * @param appearTime    time the shape appears
   * @param disappearTime time the shape disappears
   */
  public Ellipse(double x, double y, double xRadius, double yRadius, String name, int red,
      int green, int blue, double appearTime, double disappearTime) {

    super(new Point2D(x, y), name, red, green, blue, appearTime, disappearTime,
        TypeOfShape.ELLIPSE);

    if (xRadius <= 0 || yRadius <= 0) {
      throw new IllegalArgumentException("Shape dimensions cannot be negative or zero.");
    }

    this.xRadius = xRadius;
    this.yRadius = yRadius;
  }

  @Override
  public void changeDimensions(double newXRadius, double newYRadius) {
    if (newXRadius <= 0 || newYRadius <= 0) {
      throw new IllegalArgumentException("Shape dimensions cannot be negative or zero.");
    }
    this.xRadius = newXRadius;
    this.yRadius = newYRadius;
  }

  @Override
  public double getWidth() {
    return this.xRadius;
  }

  @Override
  public double getHeight() {
    return this.yRadius;
  }

  /**
   * Returns a String representation of the ellipse.
   *
   * @return String representation of the ellipse.
   */
  @Override
  public String toString() {
    return String.format("RGB(%d,%d,%d) oval %s with center at (%.0f,%.0f), radius %.0f and %.0f",
        this.getColor().getRed(), this.getColor().getGreen(), this.getColor().getBlue(), this.name,
        this.reference.getX(), this.reference.getY(), this.xRadius, this.yRadius);
  }
}