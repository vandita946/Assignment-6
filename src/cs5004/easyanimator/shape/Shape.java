package cs5004.easyanimator.shape;

import java.awt.Color;
/**
 * This interface contains all operations that all types of shapes
 * should support.
 */
public interface Shape extends Comparable<Shape>{

  /**
   * Returns the distance of this shape from the origin. The distance is
   * measured from whatever reference position a shape is (e.g. a center for
   * a circle)
   * @return the distance from the origin
   */
  double distanceFromOrigin();
  /**
   * Computes and returns the area of this shape.
   * @return the area of the shape
   */
  double area();

  /**
   * Computes and returns the perimeter of this shape.
   * @return the perimeter of the shape
   */
  double perimeter();


  /**
   * Create and return a shape of the same kind as this one, with its
   * dimensions changed by the provided new width and height.
   * @param newWidth new width
   * @param newHeight new height
   * @return the new cs5004.easyanimator.shape.Shape with changed dimensions
   */
  Shape changeDimensions(double newWidth, double newHeight);

  void setPosition(double newX, double newY);

  Point2D getPosition();

  void changeColor(Color newColor);

  String getColor();

  String getName();

  double getWidth();

  double getHeight();

  int getAppearTime();

  int getDisappearTime();
}