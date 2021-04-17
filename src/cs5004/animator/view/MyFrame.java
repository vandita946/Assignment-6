package cs5004.animator.view;

import javax.swing.JFrame;
import cs5004.animator.animation.Animation;
import cs5004.animator.animation.TypeOfAnimation;
import cs5004.animator.model.Model;
import cs5004.animator.shape.Shape;
import cs5004.animator.shape.TypeOfShape;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MyFrame extends JFrame {
  private Model model;


  public MyFrame(Model model) {
    this.model = model;
    this.setSize(840,840);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    GraphicsDemo graphicsDemo = new GraphicsDemo(model);
    this.add(graphicsDemo);
    this.setVisible(true);
  }





}
