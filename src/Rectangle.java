import java.awt.Color;

/**
 * This class represents a rectangle.  It defines all the operations mandated by
 * the Shape interface
 */
public class Rectangle extends AbstractShape {
  private double x, y;
  private double width, height;
  private String name;
  private Color color;
  private double canvasWidth;
  private double canvasHeight;
  private int appearTime, disappearTime;

  /**
   * Constructs a rectangle object with the given location of its lower-left
   * corner and dimensions
   *
   * @param x      x coordinate of the lower-left corner of this rectangle
   * @param y      y coordinate of the lower-left corner of this rectangle
   * @param width  width of this rectangle
   * @param height height of this rectangle
   */
  public Rectangle(double x, double y, double width, double height, String name, Color color,
      double canvasWidth, double canvasHeight, int appearTime, int disappearTime) {
    super(new Point2D(x, y), name, color, canvasWidth, canvasHeight, appearTime, disappearTime);

    if (x + width > canvasWidth || y + height > canvasHeight) {
      throw new IllegalArgumentException("The dimensions of this shape are out of bounds of the canvas.");
    } else if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Shape dimensions cannot be negative or zero.");
    }

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
  public Shape changeDimensions(double newWidth, double newHeight) {
//    if (x + newWidth > canvasWidth) {
//      throw new IllegalArgumentException("New width causes shape to be out of bounds.");
//    } else if (y + newHeight > canvasHeight) {
//      throw new IllegalArgumentException("New height causes shape to be out of bounds.");
//    } else if (newWidth < 0 || newHeight < 0) {
//      throw new IllegalArgumentException("Height and width values have to be positive.");
//    }

    return new Rectangle(
        this.reference.getX(), this.reference.getY(),
        newWidth, newHeight,
        name, color,
        canvasWidth, canvasHeight,
        appearTime,disappearTime
    );
  }


  @Override
  public double getWidth() {
    return this.width;
  }

  @Override
  public double getHeight() {
    return this.height;
  }

  public String toString() {
    return String.format("%s rectangle %s with corner at (%.2f,%.2f), width %.2f and height %.2f",
        this.color.toString(), this.name, this.reference.getX(), this.reference.getY(),
        this.width, this.height);
  }
}