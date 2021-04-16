package cs5004.animator.view;

import cs5004.animator.animation.Animation;
import cs5004.animator.animation.TypeOfAnimation;
import cs5004.animator.model.Model;
import cs5004.animator.shape.Shape;
import cs5004.animator.shape.TypeOfShape;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SVGView implements View {
  private Model model;
  private String outfile;
  private int ticksPerSecond;

  public SVGView (Model model, String outfile,int ticksPerSecond) {
    this.model = model;
    this.outfile = outfile;
    this.ticksPerSecond = ticksPerSecond;
  }

  @Override
  public void publish() {
    if (outfile.equals("System.out")) {
      System.out.println(this.getDescription());
    } else {
      try {
        java.io.File file = new File("resources/" + outfile);
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
    String description = "";
    description += getHeader();
    for (Shape s : model.getShapeList()){
      description += getShapeBlock(s);
    }
    description += "</svg>";
    return description;
  }

  private String getHeader() {
    return String.format("<svg width=\"%.0f\" height=\"%.0f\" version=\"1.1\"\n\txmlns=\"http://www.w3.org/2000/svg\">\n",
        model.getCanvasWidth(),model.getCanvasHeight());
  }

  private String getAnimateBlock(Animation animation) {
    String output = "";

    for (String change : animation.getChanges().keySet()) {
      output += String.format("\t<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\" attributeName=",
          animation.getStartingTime()*1000/ticksPerSecond,(animation.getEndingTime()- animation.getStartingTime())*1000/ticksPerSecond);
      output += String.format("\"%s\" from=\"%s\" to=\"%s\" fill=\"freeze\" />\n",
          change, animation.getChanges().get(change)[0], animation.getChanges().get(change)[1]);
    }
    return output;
  }

  private String getShapeBlock(Shape shape) {
    String output = "";
    if (shape.getTypeOfShape().equals(TypeOfShape.RECTANGLE)) {
      output += String.format("<rect id=\"%s\" x=\"%.0f\" y=\"%.0f\" width=\"%.0f\" height=\"%.0f\" fill=\"rgb(%d,%d,%d)\" visibility=\"visible\" >\n",
          shape.getName(),shape.getPosition().getX(),shape.getPosition().getY(),shape.getWidth(),shape.getHeight(),shape.getColor().getRed(),
          shape.getColor().getGreen(),shape.getColor().getBlue());
      for (Animation a : model.getAnimationsByShape(shape)) {
        output += getAnimateBlock(a);
      }
      //insert animate call here
      output += "</rect>\n";
    } else if (shape.getTypeOfShape().equals(TypeOfShape.ELLIPSE)) {
      output += String.format("<ellipse id=\"%s\" cx=\"%.0f\" cy=\"%.0f\" rx=\"%.0f\" ry=\"%.0f\" fill=\"rgb(%d,%d,%d)\" visibility=\"visible\" >\n",
          shape.getName(),shape.getPosition().getX(),shape.getPosition().getY(),shape.getWidth(),shape.getHeight(),shape.getColor().getRed(),
          shape.getColor().getGreen(),shape.getColor().getBlue());
      for (Animation a : model.getAnimationsByShape(shape)) {
        output += getAnimateBlock(a);
      }
      //insert animate call here
      output += "</ellipse>\n";

    }
    return output;
  }
}
