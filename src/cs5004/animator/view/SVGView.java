package cs5004.animator.view;

import cs5004.animator.animation.Animation;
import cs5004.animator.model.Model;
import cs5004.animator.model.ModelImpl;
import cs5004.animator.shape.Oval;
import cs5004.animator.shape.Rectangle;
import cs5004.animator.shape.Shape;
import cs5004.animator.shape.TypeOfShape;
import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SVGView implements View {
  private Model model;
  private String outfile;

  public SVGView (Model model, String outfile) {
    this.model = model;
    this.outfile = outfile;
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
    return null;
  }
}
