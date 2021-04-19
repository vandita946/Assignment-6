/* CS 5004 - Easy Animator - Model
 * Vandita Attal & Swapnil Mittal
 */

package cs5004.animator.shape;

/**
 * This class represents a rectangle.  It defines all the operations mandated by the Shape
 * interface
 */
public class Rectangle extends AbstractShape {

  private double width;
  private double height;

  /**
   * Constructs a rectangle object with the given location of its lower-left corner and dimensions.
   *
   * @param x             x coordinate of the top-left corner of this rectangle.
   * @param y             y coordinate of the top-left corner of this rectangle.
   * @param width         width of this rectangle.
   * @param height        height of this rectangle.
   * @param name          unique name of the rectangle
   * @param red           RGB red value
   * @param green         RGB green value
   * @param blue          RGB blue value
   * @param appearTime    time that the rectangle appears
   * @param disappearTime time that the rectangle disappears
   */
  public Rectangle(double x, double y, double width, double height, String name, int red, int green,
      int blue, double appearTime, double disappearTime) {

    super(new Point2D(x, y), name, red, green, blue, appearTime, disappearTime,
        TypeOfShape.RECTANGLE);

    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Shape dimensions cannot be negative or zero.");
    }

    this.width = width;
    this.height = height;
  }

  @Override
  public void changeDimensions(double newWidth, double newHeight) {
    if (newWidth <= 0 || newHeight <= 0) {
      throw new IllegalArgumentException("Shape dimensions cannot be negative or zero.");
    }
    this.width = newWidth;
    this.height = newHeight;
  }


  @Override
  public double getWidth() {
    return this.width;
  }

  @Override
  public double getHeight() {
    return this.height;
  }

  /**
   * Returns a string representation of the rectangle.
   *
   * @return String representation of rectangle
   */
  @Override
  public String toString() {
    return String
        .format("RGB(%d,%d,%d) rectangle %s with corner at (%.0f,%.0f), width %.0f and height %.0f",
            this.getColor().getRed(), this.getColor().getGreen(), this.getColor().getBlue(),
            this.name, this.reference.getX(), this.reference.getY(),
            this.width, this.height);
  }
}