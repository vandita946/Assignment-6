/*
  CS5004
  Spring 2021
  Easy Animator
  Swapnil Mittal & Vandita Attal
 */

package cs5004.animator.view;

import cs5004.animator.model.Model;

/**
 * ViewFactory represents the class that creates the appropriate view based on the given type.
 */
public class ViewFactory {

  /**
   * Constructs a view object based on the given viewType.
   * @param viewType type of view (text, svg or visual)
   * @param model Model
   * @param outfile output file
   * @return a View object
   */
  public static View createView(String viewType, Model model, String outfile) {
    return switch (viewType) {
      case "text" -> new TextualView(model, outfile);
      case "svg" -> new SVGView(model, outfile);
      case "visual" -> new VisualView(model);
      default -> throw new IllegalArgumentException("Invalid view type.");
    };
  }
}
