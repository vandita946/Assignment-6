/* CS 5004 - Easy Animator - Model
 * Vandita Attal & Swapnil Mittal
 */

package cs5004.animator.animation;

import cs5004.animator.shape.Shape;

/**
 * This class implements the Animation interface and represents a shape and the methods available to
 * it to make it an animation.
 */
public abstract class AbstractAnimation implements Animation {

  protected Shape shape;
  protected double startingTime;
  protected double endingTime;
  protected TypeOfAnimation type;

  /**
   * This is a constructor to initialize the given parameters.
   *
   * @param shape        is the object on which the animation will occur.
   * @param startingTime is the starting time for the animation.
   * @param endingTime   is the ending time for the animation.
   * @param type         is the type of animation.
   * @throws IllegalArgumentException if shape is null or the time entered is invalid.
   */
  public AbstractAnimation(Shape shape, double startingTime, double endingTime, TypeOfAnimation type)
      throws IllegalArgumentException {
    if (shape == null) {
      throw new IllegalArgumentException("Shape cannot be null.");
    }
    if (startingTime < shape.getAppearTime() || endingTime > shape.getDisappearTime()) {
      System.out.println(shape.getName());
      System.out.println(shape.getAppearTime() + " " + shape.getDisappearTime());
      System.out.println(startingTime + " " + endingTime);
      throw new IllegalArgumentException(
          "Starting and ending time must be within shape's appear and disappear time.");
    }
    this.shape = shape;
    this.startingTime = startingTime;
    this.endingTime = endingTime;
    this.type = type;

  }

  /**
   * This step is left empty since we believe we will use it later when we work with controller and
   * view.
   */
  @Override
  public void actionStep() {
    //Will be used later.
  }

  public double getStartingTime() {
    return this.startingTime;
  }

  public double getEndingTime() {
    return this.endingTime;
  }

  public Shape getShape() {
    return this.shape;
  }

  public TypeOfAnimation getType() {
    return this.type;
  }




}
