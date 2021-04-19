/* CS 5004 - Easy Animator - Model
 * Vandita Attal & Swapnil Mittal
 */

package cs5004.animator.model;

import cs5004.animator.animation.Animation;
import cs5004.animator.shape.Shape;
import cs5004.animator.shape.TypeOfShape;
import java.util.List;
import java.util.Map;

/**
 * This class represents the model in an easy animator. It outlines the methods required to store
 * the shapes and animations of an animator instance.
 */
public interface Model {

  /**
   * This function is used to create a color change animation.
   *
   * @param shape        is the shape for which the color changes.
   * @param startingTime is the starting time for this color change.
   * @param endingTime   is the ending time for this color change.
   * @param red          new RGB red value
   * @param green        new RGB green value
   * @param blue         new RGB blue value
   */
  void addChangeColorAnimation(Shape shape, int startingTime, int endingTime,
      int red, int green, int blue);

  /**
   * This function is used to create a scale animation (i.e. change it's width and height).
   *
   * @param shape        is the shape that will scale.
   * @param type         is the type of shape that will scale.
   * @param startingTime is the starting time for this scaling to happen.
   * @param endingTime   is the ending time for this scaling to stop.
   * @param newWidth     is the new width of the shape.
   * @param newHeight    is the new height of the shape.
   */
  void addScaleAnimation(Shape shape, TypeOfShape type, int startingTime, int endingTime,
      double newWidth,
      double newHeight);

  /**
   * This function is used to create a move animation.
   *
   * @param shape        is the shape to be moved.
   * @param type         is the type of shape to be moved.
   * @param startingTime is the starting time of the move.
   * @param endingTime   is the ending time of the move.
   * @param toX          is the new x coordinate for the shape to move to.
   * @param toY          is the new y coordinate for the shape to move to.
   */
  void addMoveAnimation(Shape shape, TypeOfShape type, int startingTime, int endingTime, int toX,
      int toY);


  /**
   * This is a function that returns us the list of shapes at a given tick.
   *
   * @param tick is the time at which we need to get the shapes.
   * @return the list of shapes at the given tick.
   */
  List<Shape> getShapesAtTick(double tick);

  /**
   * Returns the list of shapes in the model.
   *
   * @return list of shapes
   */
  List<Shape> getShapeList();

  /**
   * Returns the list of animations in the model.
   *
   * @return list of animations
   */
  List<Animation> getAnimationList();

  /**
   * Sets the canvas width of the animator.
   *
   * @param newWidth new width to be set.
   */
  void setCanvasWidth(int newWidth) throws IllegalArgumentException;

  /**
   * Sets the canvas height of the animator.
   *
   * @param newHeight new height to be set.
   */
  void setCanvasHeight(int newHeight) throws IllegalArgumentException;


  /**
   * Updates the shape ledger (list of unique shape names and their types). Mainly for use by the
   * builder.
   *
   * @param shapeName name of the shape
   * @param shapeType type of the shape
   */
  void updateShapeLedger(String shapeName, String shapeType);


  /**
   * Returns the shape type of a shape with a given name.
   *
   * @param shapeName name of the shape
   * @return String type of the shape
   */
  String getTypeByName(String shapeName);

  /**
   * Creates a shape object. Mainly for use by the builder.
   *
   * @param shapeName    unique name of the shape
   * @param type         type of the shape
   * @param x            coordinate
   * @param y            coordinate
   * @param width        width of the shape
   * @param height       height of the shape
   * @param startingTime time that the shape appears
   * @param endingTime   time that the shape disappears
   * @param r            RGB red value
   * @param g            RGB green value
   * @param b            RGB blue value
   * @return the created shape object, for use of the builder
   */
  Shape createShape(String shapeName, String type, int x, int y, double width, double height,
      int startingTime, int endingTime, int r, int g, int b);

  /**
   * Sets the topmost y and leftmost x (corner values).
   *
   * @param x topmost x value
   * @param y leftmost y value
   */
  void setCornerValues(int x, int y);

  /**
   * Gets the canvas width.
   *
   * @return canvas width
   */
  double getCanvasWidth();

  /**
   * Gets the canvas height.
   *
   * @return canvas height
   */
  double getCanvasHeight();

  /**
   * Returns the shapeLedger from the model.
   * @return Map of Strings shapeLedger
   */
  Map<String, String> getShapeLedger();

  /**
   * Returns the List of animations for a given shape.
   *
   * @param shape for which the animations need to be found.
   * @return List of animations for a certain shape
   */
  List<Animation> getAnimationsByShape(Shape shape);

  /**
   * Sets the ticksPerSecond speed of the animator.
   *
   * @param ticksPerSecond speed
   */
  void setTicksPerSecond(int ticksPerSecond);

  /**
   * Gets the ticksPerSecond speed of the animator.
   *
   * @return ticksPerSecond speed
   */
  int getTicksPerSecond();

  /**
   * Converts a tick into milliseconds based on the speed of the animation.
   *
   * @param t tick
   * @return milliseconds
   */
  double getMilliseconds(double t);

  /**
   * Gets the corner X value.
   *
   * @return leftmost x value.
   */
  double getCornerX();

  /**
   * Gets the corner Y value.
   *
   * @return topmost Y value
   */
  double getCornerY();

}
