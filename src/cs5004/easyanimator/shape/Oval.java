package cs5004.easyanimator.shape;

import java.awt.Color;

/**
 * This class represents a circle.  It offers all the operations mandated by the cs5004.easyanimator.shape.Shape interface.
 */
public class Oval extends AbstractShape {

  private double xRadius, yRadius;
  private ColorNames color;

  /**
   * Construct a circle object using the given center and radius
   *
   * @param x      x coordinate of the center of this circle
   * @param y      y coordinate of the center of this circle
   * @param radius the radius of this circle
   */
  public Oval(double x, double y, double radius, String name, Color color, double canvasWidth,
      double canvasHeight, int appearTime, int disappearTime) {
    super(new Point2D(x, y), name, color, canvasWidth, canvasHeight, appearTime, disappearTime);

    if ((x - radius) < 0 || (y - radius) < 0 || (x + radius) > canvasWidth || (y + radius) > canvasHeight) {
      throw new IllegalArgumentException("The dimensions of this shape are out of bounds of the canvas.");
    } else if (radius <= 0) {
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
    this.xRadius = this.yRadius = radius;
  }

  public Oval(double x, double y, double xRadius, double yRadius, String name, Color color,
      double canvasWidth, double canvasHeight, int appearTime, int disappearTime) {
    super(new Point2D(x, y), name, color, canvasWidth, canvasHeight, appearTime, disappearTime);

    if ((x - xRadius) < 0 || (y - yRadius) < 0 || (x + xRadius) > canvasWidth || (y + yRadius) > canvasHeight) {
      throw new IllegalArgumentException("The dimensions of this shape are out of bounds of the canvas.");
    } else if (xRadius <= 0 || yRadius <= 0) {
      throw new IllegalArgumentException("Shape dimensions cannot be negative or zero.");
    }
    for (ColorNames c : ColorNames.values()) {
      if (c.getValue().equals(color)) {
        this.color = c;
      }
    }
    if (this.color == null) {
      throw new IllegalArgumentException("Invalid color entered.");
    }    this.xRadius = xRadius;
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
   * @param newXRadius  new width
   * @param newYRadius new height
   * @return the new cs5004.easyanimator.shape.Shape with changed dimensions
   */
  @Override
  public Shape changeDimensions(double newXRadius, double newYRadius) {
//    if (x + newXRadius > canvasWidth) {
//      throw new IllegalArgumentException("New width causes shape to be out of bounds.");
//    } else if (y + newYRadius > canvasHeight) {
//      throw new IllegalArgumentException("New height causes shape to be out of bounds.");
//    } else if (newXRadius < 0 || newYRadius < 0) {
//      throw new IllegalArgumentException("Height and width values have to be positive.");
//    }

    return new Oval(reference.getX(), reference.getY(), newXRadius, newYRadius, name,
        color.getValue(), canvasWidth, canvasHeight, appearTime, disappearTime);
  }

  @Override
  public double getWidth() {
    return this.xRadius;
  }

  @Override
  public double getHeight() {
    return this.yRadius;
  }


  public String toString() {
    return String.format("%s oval %s with center at (%.2f,%.2f), radius %.2f %.2f",
        color.getText(), this.name, this.reference.getX(), this.reference.getY(),
        this.xRadius, this.yRadius);
  }
}