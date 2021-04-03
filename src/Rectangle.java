import java.awt.Color;

/**
 * This class represents a rectangle.  It defines all the operations mandated by
 * the Shape interface
 */
public class Rectangle extends AbstractShape {
  private double width, height;
  private String name;
  private Color color;

  /**
   * Constructs a rectangle object with the given location of its lower-left
   * corner and dimensions
   *
   * @param x      x coordinate of the lower-left corner of this rectangle
   * @param y      y coordinate of the lower-left corner of this rectangle
   * @param width  width of this rectangle
   * @param height height of this rectangle
   */
  public Rectangle(double x, double y, double width, double height, String name, String colorName) {
    super(new Point2D(x, y), name, colorName);
    this.width = width;
    this.height = height;
  }

  @Override
  public double area() {
    return this.width * this.height;
  }

  @Override
  public double perimeter() {
    return 2 * (this.width + this.height);
  }

  @Override
  public Shape resize(double factor) {
    double sqrtFactor = Math.sqrt(factor);
    return new Rectangle(
            this.reference.getX(),
            this.reference.getY(), sqrtFactor *
            this.width,
            sqrtFactor * this.height,
            name,
            color.toString());
  }

  @Override
  public Shape changeDimensions(double newWidth, double newHeight) {
    return new Rectangle(
        this.reference.getX(),
        this.reference.getY(),
        newWidth,
        newHeight,
        name,
        color.toString()
    );
  }

  public double getWidth() {
    return this.width;
  }

  public double getHeight() {
    return this.height;
  }

  public String toString() {
    return String.format("%s rectangle %s with corner at (%.2f,%.2f), width %.2f and height %.2f",
        this.color.toString(), this.name, this.reference.getX(), this.reference.getY(),
        this.width, this.height);
  }
}