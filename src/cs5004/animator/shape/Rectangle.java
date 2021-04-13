/* CS 5004 - Easy Animator - Model
 * Vandita Attal & Swapnil Mittal
 */

package cs5004.animator.shape;

import java.awt.Color;

/**
 * This class represents a rectangle.  It defines all the operations mandated by the Shape
 * interface
 */
public class Rectangle extends AbstractShape {

  private final double width;
  private final double height;
  private ColorNames color;

  /**
   * Constructs a rectangle object with the given location of its lower-left corner and dimensions.
   *
   * @param x      x coordinate of the lower-left corner of this rectangle.
   * @param y      y coordinate of the lower-left corner of this rectangle.
   * @param width  width of this rectangle.
   * @param height height of this rectangle.
   */
  public Rectangle(double x, double y, double width, double height, String name, Color color,
      double canvasWidth, double canvasHeight, int appearTime, int disappearTime) {
    super(new Point2D(x, y), name, color, canvasWidth, canvasHeight, appearTime, disappearTime);

    if (x + width > canvasWidth || y + height > canvasHeight) {
      throw new IllegalArgumentException(
          "The dimensions of this shape are out of bounds of the canvas.");
    } else if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Shape dimensions cannot be negative or zero.");
    }
    for (ColorNames c : ColorNames.values()) {
      if (c.getValue().equals(color)) {
        this.color = c;
      }
    }
    if (this.color == null) {
      throw new IllegalArgumentException("Invalid color entered.");
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
    return new Rectangle(
        this.reference.getX(), this.reference.getY(),
        newWidth, newHeight,
        name, color.getValue(),
        canvasWidth, canvasHeight,
        appearTime, disappearTime
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

  @Override
  public String toString() {

    return String.format("%s rectangle %s with corner at (%.0f,%.0f), width %.0f and height %.0f",
        this.color.getText(), this.name, this.reference.getX(), this.reference.getY(),
        this.width, this.height);
  }
}