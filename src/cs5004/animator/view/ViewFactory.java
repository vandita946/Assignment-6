package cs5004.animator.view;

import cs5004.animator.model.Model;

public class ViewFactory {

  public static View createView(String viewType, Model model, String outfile,int ticksPerSecond) {
    View view;
    if (viewType.equals("text")) {
      view = new TextualView(model, outfile);
    } else if (viewType.equals("svg")) {
      view = new SVGView(model, outfile,ticksPerSecond);
    } else if (viewType.equals("visual")) {
      view = new VisualView();
    } else {
      throw new IllegalArgumentException("Invalid view type.");
    }
    return view;
  }
}
