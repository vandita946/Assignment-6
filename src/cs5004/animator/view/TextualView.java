/*
  CS5004
  Spring 2021
  Easy Animator
  Swapnil Mittal & Vandita Attal
 */

package cs5004.animator.view;

import cs5004.animator.animation.Animation;
import cs5004.animator.model.Model;
import cs5004.animator.shape.Shape;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * TextualView implements the view class and represents a text-based view of an animation.
 */
public class TextualView implements View {

  private final Model model;
  private final String outfile;

  /**
   * Constructs a TextualView object and initializes it to the given model and outfile.
   *
   * @param model   Model
   * @param outfile output file
   */
  public TextualView(Model model, String outfile) {
    this.model = model;
    this.outfile = outfile;
  }

  @Override
  public void publish() {
    if (outfile.equals("System.out")) {
      System.out.println(this.getDescription());
    } else {
      try {
        java.io.File file = new File(outfile);
        FileWriter output = new FileWriter(file);
        output.write(this.getDescription());
        output.close();
        System.out.println("Written to file " + file.getAbsolutePath());
      } catch (IOException e) {
        System.out.println("Encountered an error with output file " + outfile);
        System.out.println(e.getMessage());
      }
    }

  }

  @Override
  public String getDescription() {
    StringBuilder returnString = new StringBuilder();

    for (Shape s : model.getShapeList()) {
      returnString.append("Create ").append(s.toString()).append("\n");
    }

    returnString.append("\n");

    for (Shape s : model.getShapeList()) {
      returnString.append(s.getName()).append(" appears at time t=").append(s.getAppearTime())
          .append(" and disappears at time t=").append(s.getDisappearTime()).append("\n");
    }

    returnString.append("\n");

    for (Animation a : model.getAnimationList()) {
      returnString.append(a.toString()).append("\n");
    }

    return returnString.toString();
  }
}


