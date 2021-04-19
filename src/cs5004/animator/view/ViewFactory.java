/*
  CS5004
  Spring 2021
  Easy Animator
  Swapnil Mittal & Vandita Attal
 */

package cs5004.animator.view;

import cs5004.animator.model.Model;
import javax.swing.JOptionPane;

/**
 * ViewFactory represents the class that creates the appropriate view based on the given type.
 */
public class ViewFactory {

  /**
   * Constructs a view object based on the given viewType.
   *
   * @param viewType type of view (text, svg or visual)
   * @param model    Model
   * @param outfile  output file
   * @return a View object
   */
  public static View createView(String viewType, Model model, String outfile) {
    switch (viewType) {
      case "text":
        return new TextualView(model, outfile);
      case "svg":
        return new SVGView(model, outfile);
      case "visual":
        return new VisualView(model);
      default:
        JOptionPane.showMessageDialog(null, "Please enter a valid view type", "Error",
            JOptionPane.ERROR_MESSAGE);
        System.exit(0);//
        throw new IllegalArgumentException("Invalid view type.");
    }
  }
}
