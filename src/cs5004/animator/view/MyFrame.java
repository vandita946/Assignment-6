package cs5004.animator.view;

import javax.swing.JFrame;
import cs5004.animator.model.Model;

public class MyFrame extends JFrame {
  private Model model;


  public MyFrame(Model model) {
    this.model = model;
    this.setSize(840,840);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    VisualPanel graphicsDemo = new VisualPanel(model);
    this.add(graphicsDemo);
    this.setVisible(true);
  }





}
