package cs5004.animator.view;

import cs5004.animator.model.Model;

public class VisualView implements View {
  private Model model;

  public VisualView(Model model) {
    this.model = model;

  }
  @Override
  public void publish() {
    MyFrame myFrame = new MyFrame(this.model);
    myFrame.setTitle("VS Animation");
    System.out.println(model.getShapeList().size());

  }

  @Override
  public String getDescription() {
    return null;
  }
}

