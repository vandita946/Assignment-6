
/**
 * This class is an interface that outlines the methods available to a shape such that it can be
 * animated.
 */
public interface Animation {

  void actionStep();

  int getStartingTime();

  int getEndingTime();

  /**
   * Returns a string representation of the animation.
   * @return String representation of the animation.
   */
  String toString();
}
