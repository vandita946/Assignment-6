import java.awt.Color;

/**
 * This class represents a circle.  It offers all the operations mandated by the Shape interface.
 */
public class Oval extends AbstractShape {

  private double xRadius;
  private double yRadius;
  private String name;
  private Color color;

  /**
   * Construct a circle object using the given center and radius
   *
   * @param x      x coordinate of the center of this circle
   * @param y      y coordinate of the center of this circle
   * @param radius the radius of this circle
   */
  public Oval(double x, double y, double radius, String name, String colorName) {
    super(new Point2D(x, y), name, colorName);
    this.xRadius = this.yRadius = radius;
  }

  public Oval(double x, double y, double xRadius, double yRadius, String name, String colorName) {
    super(new Point2D(x, y), name, colorName);
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

  @Override
  public Shape resize(double factor) {
    return new Oval(reference.getX(), reference.getY(), Math.sqrt(factor) *
        xRadius, Math.sqrt(factor) * yRadius, name, color.toString());
  }

  /**
   * Create and return a shape of the same kind as this one, with its dimensions changed by the
   * provided new width and height.
   *
   * @param newWidth  new width
   * @param newHeight new height
   * @return the new Shape with changed dimensions
   */
  @Override
  public Shape changeDimensions(double newWidth, double newHeight) {
    return new Oval(reference.getX(), reference.getY(), newWidth, newHeight, name,
        color.toString());
  }


  public String toString() {
    return String.format("%s oval %s with center at (%.2f,%.2f), radius %.2f %.2f",
        this.color.toString(), this.name, this.reference.getX(), this.reference.getY(),
        this.xRadius, this.yRadius);
  }
}