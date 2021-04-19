/*
  CS5004
  Spring 2021
  Easy Animator
  Swapnil Mittal & Vandita Attal
 */

package cs5004.animator.view;

/**
 * This interface represents a View and the methods commonly required for all views.
 */
public interface View {

  /**
   * This method executes the view and publishes its content to the given output channel.
   */
  void publish();

  /**
   * Returns the textual/svg description.
   *
   * @return String representation of the view
   */
  String getDescription();
}
