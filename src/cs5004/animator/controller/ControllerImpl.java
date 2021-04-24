package cs5004.animator.controller;

import cs5004.animator.model.Model;
import cs5004.animator.view.View;

public class ControllerImpl implements Controller {
  private Model model;
  private View view;

  public ControllerImpl(Model model, View view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void publishView() {
    view.publish();
  }

}
