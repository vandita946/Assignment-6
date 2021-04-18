package cs5004.animator.view;

import cs5004.animator.animation.Animation;
import cs5004.animator.model.Model;
import cs5004.animator.shape.Shape;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TextualView implements View {

  private Model model;
  private String outfile;

  public TextualView(Model model, String outfile) {
    this.model = model;
    this.outfile = outfile;
  }

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
    // create (shape.toString) for loop through the shape list
    for (Shape s : model.getShapeList()) {
      returnString.append("Create ").append(s.toString()).append("\n");
    }

    returnString.append("\n");

    // appear and disappear times for loop through the shape list
    for (Shape s : model.getShapeList()) {
      returnString.append(s.getName()).append(" appears at time t=").append(s.getAppearTime())
          .append(" and disappears at time t=").append(s.getDisappearTime()).append("\n");
    }

    returnString.append("\n");

    // for loop through animation list
    for (Animation a : model.getAnimationList()) {
      returnString.append(a.toString()).append("\n");
    }

    //created getters for animation list and shape list, is that the best thing to do?

    return returnString.toString();
  }

//  public static void main(String[] args) {
//    Model model = new ModelImpl(500, 500);
//    Shape rectangle = new Rectangle(5, 5, 50, 40, "recty", , 500, 500, 30, 60);
//    Shape circle = new Ellipse(20, 20, 5, 5, "cricle", Color.BLUE, 500, 500, 20, 50);
//    model.addMoveAnimation(rectangle, TypeOfShape.RECTANGLE, 30, 37, 10, 12);
//    model.addChangeColorAnimation(circle, 29, 35, Color.GREEN);
//
//    View textual = new TextualView(model, "test1.txt");
//    textual.publish();
//  }
}


