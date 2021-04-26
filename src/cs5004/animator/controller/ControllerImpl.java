/* CS 5004 - Easy Animator
 * Vandita Attal & Swapnil Mittal
 */

package cs5004.animator.controller;

import cs5004.animator.view.View;

/**
 * ControllerImpl implements the controller interface and outlines the method needed to publish the
 * view to the user.
 */
public class ControllerImpl implements Controller {

  private View view;

  /**
   * Constructs the controller object and initializes it to the given view.
   *
   * @param view View object
   */
  public ControllerImpl(View view) {
    this.view = view;
  }


  @Override
  public void publishView() {
    view.publish();
  }

}
