package cs5004.easyanimator.model;

import cs5004.easyanimator.shape.Shape;
import cs5004.easyanimator.shape.TypeOfShape;
import java.awt.Color;
import java.util.List;

/**
 * This class represents the model in an easy animator. It outlines the methods required to
 * store the shapes and animations of an animator instance.
 */
public interface Model {

  /**
   * This function is used to add shape to the canvas.
   * @param shape is the shape to be added.
   */
  void addShape(Shape shape);

  /**
   * This function is used to change color of an existing shape.
   *
   * @param shape is the shape for which the color changes.
   * @param startingTime is the starting time for this color change.
   * @param endingTime is the ending time for this color change.
   * @param newColor is the new color that the color changes to.
   */
  void addChangeColorAnimation(Shape shape, int startingTime, int endingTime,
      Color newColor);

  /**
   * This function is used to scale the object(i.e. change it's width and height).
   *
   * @param shape is the shape that will scale.
   * @param type is the type of shape that will scale.
   * @param startingTime is the starting time for this scaling to happen.
   * @param endingTime is the ending time for this scaling to stop.
   * @param newWidth is the new width of the shape.
   * @param newHeight is the new height of the shape.
   */
  void addScaleAnimation(Shape shape, TypeOfShape type, int startingTime, int endingTime, double newWidth,
      double newHeight);

  /**
   * This function is used to move the shape object.
   * @param shape is the shape to be moved.
   * @param type is the type of shape to be moved.
   * @param startingTime is the starting time of the move.
   * @param endingTime is the ending time of the move.
   * @param toX is the new x coordinate for the shape to move to.
   * @param toY is the new y coordinate for the shape to move to.
   */
  void addMoveAnimation(Shape shape, TypeOfShape type, int startingTime, int endingTime, double toX,
      double toY);

  /**
   * This is a sort function which sorts our list by the increasing order of
   * their time.
   */
  void sortAnimationList();

  /**
   * This is a function that returns us the list of shapes at a certain time.
   * @param tick is the time at which we need to get the shapes.
   * @return the list of shapes at the given tick.
   */
  List<Shape> getShapesAtTick(int tick);


}
