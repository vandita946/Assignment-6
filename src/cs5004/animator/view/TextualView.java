package cs5004.animator.view;

import cs5004.animator.animation.Animation;
import cs5004.animator.animation.Move;
import cs5004.animator.model.Model;
import cs5004.animator.model.ModelImpl;
import cs5004.animator.shape.Rectangle;
import cs5004.animator.shape.Shape;
import cs5004.animator.shape.TypeOfShape;
import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TextualView implements View {
  private Model model;
  private String outfile;
  private double ticksPerSecond;

  public TextualView(Model model, String outfile, double ticksPerSecond) {
    this.model = model;
    this.outfile = outfile;
    this.ticksPerSecond = ticksPerSecond;
  }

  public void publish() {
    if (outfile.equals("System.out")) {
      System.out.println(model.toString());
    } else {
      try {
        java.io.File file = new File("resources/" + outfile);
        FileWriter output = new FileWriter(file);
        output.write(model.toString());
        output.close();
        System.out.println("Written to file " + file.getAbsolutePath());
      } catch (IOException e) {
        System.out.println("Encountered an error with output file " + outfile);
        System.out.println(e.getMessage());
      }
    }

  }

  public static void main(String[] args) {
    Model model = new ModelImpl(500, 500);
    Shape rectangle = new Rectangle(5,5,50,40,"recty", Color.BLUE,500,500,1,100);
    model.addMoveAnimation(rectangle, TypeOfShape.RECTANGLE, 5, 10, 10, 12);
    View textual = new TextualView(model, "output.txt", 10);
    textual.publish();
  }
}


