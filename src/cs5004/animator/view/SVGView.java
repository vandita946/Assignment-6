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
import cs5004.animator.shape.TypeOfShape;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * SVGView implements the View interface and represents a SVG-based view.
 */
public class SVGView implements View {

  private final Model model;
  private final String outfile;

  /**
   * Constructs an SVGView object an initializes it to the given model and outfile.
   *
   * @param model   Model
   * @param outfile output file.
   */
  public SVGView(Model model, String outfile) {
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
    StringBuilder description = new StringBuilder();
    description.append(getHeader());
    for (Shape s : model.getShapeList()) {
      description.append(getShapeBlock(s));
    }
    description.append("</svg>");
    return description.toString();
  }

  /**
   * Returns the header of the SVG file.
   *
   * @return header of SVG
   */
  private String getHeader() {

    double boxWidth = model.getCanvasWidth() + Math.abs(model.getCornerX());
    double boxHeight = model.getCanvasHeight() + Math.abs(model.getCornerY());
    return String.format(
        "<svg width=\"%.0f\" height=\"%.0f\" viewBox=\"%.0f %.0f %.0f %.0f\" version=\"1.1\"\n\txmlns=\"http://www.w3.org/2000/svg\">\n",
        model.getCanvasWidth(),model.getCanvasHeight(),0.0,0.0,boxWidth, boxHeight);
  }

  /**
   * Returns the xml code for a given animation.
   *
   * @param animation to be output in code
   * @return xml code for the animation
   */
  private String getAnimateBlock(Animation animation) {
    StringBuilder output = new StringBuilder();

    for (String[] change : animation.getChanges()) {
      output.append(String
          .format("\t<animate attributeType=\"xml\" begin=\"%.0fms\" dur=\"%.0fms\" attributeName=",
              animation.getStartingTime(),
              (animation.getEndingTime() - animation.getStartingTime())));
      output.append(String
          .format("\"%s\" from=\"%s\" to=\"%s\" fill=\"freeze\" />\n", change[0], change[1],
              change[2]));
      animation.actionStep(animation.getEndingTime());
    }
    return output.toString();
  }

  /**
   * Returns the xml code for a given shape.
   *
   * @param shape Shape to be output in code
   * @return xml code for the shape
   */
  private String getShapeBlock(Shape shape) {

    StringBuilder output = new StringBuilder();

    if (shape.getTypeOfShape().equals(TypeOfShape.RECTANGLE)) {

      output.append(String.format(
          "<rect id=\"%s\" x=\"%.0f\" y=\"%.0f\" width=\"%.0f\" height=\"%.0f\" fill=\"rgb(%d,%d,%d)\" visibility=\"visible\" >\n",
          shape.getName(), shape.getPosition().getX(), shape.getPosition().getY(), shape.getWidth(),
          shape.getHeight(), shape.getColor().getRed(),
          shape.getColor().getGreen(), shape.getColor().getBlue()));

      for (Animation a : model.getAnimationsByShape(shape)) {
        output.append(getAnimateBlock(a));
      }

      output.append("</rect>\n");

    } else if (shape.getTypeOfShape().equals(TypeOfShape.ELLIPSE)) {

      output.append(String.format(
          "<ellipse id=\"%s\" cx=\"%.0f\" cy=\"%.0f\" rx=\"%.0f\" ry=\"%.0f\" fill=\"rgb(%d,%d,%d)\" visibility=\"visible\" >\n",
          shape.getName(), shape.getPosition().getX(), shape.getPosition().getY(), shape.getWidth(),
          shape.getHeight(), shape.getColor().getRed(),
          shape.getColor().getGreen(), shape.getColor().getBlue()));

      for (Animation a : model.getAnimationsByShape(shape)) {
        output.append(getAnimateBlock(a));
      }

      output.append("</ellipse>\n");

    }
    return output.toString();
  }
}
