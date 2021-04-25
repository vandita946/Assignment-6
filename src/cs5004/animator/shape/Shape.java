/* CS 5004 - Easy Animator
 * Vandita Attal & Swapnil Mittal
 */

package cs5004.animator.shape;

import java.awt.Color;

/**
 * This interface contains all operations that all types of shapes should support.
 */
public interface Shape {

  /**
   * Sets the dimensions of the shape to a given new width and/or height.
   *
   * @param newWidth  new width
   * @param newHeight new height
   */
  void changeDimensions(double newWidth, double newHeight);

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
  Color getColor();

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
  double getAppearTime();

  /**
   * This is a getter to get the disappear time of the object.
   *
   * @return an integer.
   */
  double getDisappearTime();

  void setAppearTime(double appearTime);

  /**
   * Sets the disappear time to a new given disappearTime.
   *
   * @param disappearTime new disappear time
   */
  void setDisappearTime(double disappearTime);

  /**
   * Gets the type of shape enum of the object.
   *
   * @return TypeOfShape type
   */
  TypeOfShape getTypeOfShape();
}