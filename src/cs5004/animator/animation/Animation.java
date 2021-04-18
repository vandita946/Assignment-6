/* CS 5004 - Easy Animator - Model
 * Vandita Attal & Swapnil Mittal
 */

package cs5004.animator.animation;

import cs5004.animator.shape.Shape;
import java.util.List;

/**
 * This class is an interface that outlines the methods available to a shape such that it can be
 * animated.
 */
public interface Animation {

  /**
   * Executes the action of the animation at the given tick.
   */
  void actionStep(double tick);

  /**
   * Gets the starting time of an animation.
   *
   * @return the starting time.
   */
  double getStartingTime();

  /**
   * Gets the ending time of an animation.
   *
   * @return the ending time.
   */
  double getEndingTime();


  /**
   * This method is a getter function to get the shape.
   *
   * @return the shape that is being animated
   */
  Shape getShape();

  /**
   * This is a getter function to get the type of animation.
   *
   * @return the type of animation.
   */
  TypeOfAnimation getType();

  /**
   * Returns an List of String[] documenting what the changes are in the animation, providing its
   * before and after values. This function is a helper for our SVG mainly.
   *
   * @return List of String[] of the changes.
   */
  List<String[]> getChanges();

}
