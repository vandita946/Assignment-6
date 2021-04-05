/* CS 5004 - Easy Animator - Model
 * Vandita Attal & Swapnil Mittal
 */

package cs5004.easyanimator.shape;

import java.awt.Color;

/**
 * This interface contains all operations that all types of shapes should support.
 */
public interface Shape extends Comparable<Shape> {

  /**
   * Returns the distance of this shape from the origin. The distance is measured from whatever
   * reference position a shape is (e.g. a center for a circle)
   *
   * @return the distance from the origin
   */
  double distanceFromOrigin();

  /**
   * Computes and returns the area of this shape.
   *
   * @return the area of the shape
   */
  double area();

  /**
   * Computes and returns the perimeter of this shape.
   *
   * @return the perimeter of the shape
   */
  double perimeter();


  /**
   * Create and return a shape of the same kind as this one, with its dimensions changed by the
   * provided new width and height.
   *
   * @param newWidth  new width
   * @param newHeight new height
   * @return Shape with changed dimensions
   */
  Shape changeDimensions(double newWidth, double newHeight);

  /**
   * This is a setter function to set the position of object.
   *
   * @param newX is the new x coordinate to move to.
   * @param newY is the new y coordinate to move to.
   */
  void setPosition(double newX, double newY);

  /**
   * This is a getter function to get position of the object.
   *
   * @return a Point2D position.
   */
  Point2D getPosition();

  /**
   * This function is a helper function to change color of the object.
   *
   * @param newColor is the new color.
   */
  void changeColor(Color newColor);

  /**
   * This is a getter function to get the current color of the object.
   *
   * @return a string of the color name.
   */
  String getColor();

  /**
   * This is a getter function to get the name of the object.
   *
   * @return a string of the name.
   */
  String getName();

  /**
   * This is a getter to get the width of the object.
   *
   * @return a width.
   */
  double getWidth();

  /**
   * This is a getter to get the height of the object.
   *
   * @return a height.
   */
  double getHeight();

  /**
   * This is a getter to get the appear time of the object.
   *
   * @return an integer.
   */
  int getAppearTime();

  /**
   * This is a getter to get the disappear time of the object.
   *
   * @return an integer.
   */
  int getDisappearTime();
}