/* CS 5004 - Easy Animator - Model
 * Vandita Attal & Swapnil Mittal
 */

package cs5004.animator.animation;

import cs5004.animator.shape.Shape;
import java.util.List;
import java.util.Map;

/**
 * This class is an interface that outlines the methods available to a shape such that it can be
 * animated.
 */
public interface Animation {

  /**
   * This step is left empty since we believe it will be useful in view and controller.
   */
  void actionStep(double tick);

  /**
   * This is a function for the starting time of an animation.
   *
   * @return an integer representing the starting time.
   */
  double getStartingTime();

  /**
   * This is a function for the ending time of an animation.
   *
   * @return an integer representing the ending time.
   */
  double getEndingTime();

  /**
   * Returns a string representation of the animation.
   *
   * @return String representation of the animation.
   */
  String toString();

  /**
   * This method is a getter function to get the shape.
   *
   * @return the shape on which the animation takes place.
   */
  Shape getShape();

  /**
   * This is a getter function to get the type of animation.
   *
   * @return the type of animation.
   */
  TypeOfAnimation getType();

  List<String[]> getChanges();

}
