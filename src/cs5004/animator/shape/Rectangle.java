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

  /**
   * Constructs a rectangle object with the given location of its lower-left corner and dimensions.
   *
   * @param x      x coordinate of the top-left corner of this rectangle.
   * @param y      y coordinate of the top-left corner of this rectangle.
   * @param width  width of this rectangle.
   * @param height height of this rectangle.
   */
  public Rectangle(double x, double y, double width, double height, String name, int red, int green, int blue,
      double canvasWidth, double canvasHeight, double cornerX, double cornerY, int appearTime, int disappearTime) {
    super(new Point2D(x, y), name, red, green, blue, canvasWidth, canvasHeight, cornerX, cornerY, appearTime, disappearTime, TypeOfShape.RECTANGLE);

    if (x + width > (cornerX + canvasWidth) || y + height > (canvasHeight + cornerY)) {
      throw new IllegalArgumentException(
          "The dimensions of this shape are out of bounds of the canvas.");
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
    return new Rectangle(
        this.reference.getX(), this.reference.getY(),
        newWidth, newHeight,
        name, this.getColor().getRed(),
        this.getColor().getGreen(), this.getColor().getBlue(),
        canvasWidth, canvasHeight,
        cornerX, cornerY,
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

    return String.format("RGB(%d,%d,%d) rectangle %s with corner at (%.0f,%.0f), width %.0f and height %.0f",
        this.getColor().getRed(), this.getColor().getGreen(), this.getColor().getBlue(), this.name, this.reference.getX(), this.reference.getY(),
        this.width, this.height);
  }
}