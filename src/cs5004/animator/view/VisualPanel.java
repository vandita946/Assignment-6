package cs5004.animator.view;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import cs5004.animator.animation.Animation;
import cs5004.animator.animation.TypeOfAnimation;
import cs5004.animator.model.Model;
import cs5004.animator.shape.Shape;
import cs5004.animator.shape.TypeOfShape;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class VisualPanel extends JPanel {
  private Model model;
  private List<Shape> shapeList;


  public VisualPanel(Model model) {
    this.model = model;
    this.shapeList = model.getShapeList();
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.setBackground(Color.WHITE);

    Graphics2D g2D = (Graphics2D) g;

    for (Shape shape : shapeList) {
      g2D.setColor(shape.getColor());
      //g2D.fillOval(x,y,100,100);

      if (shape.getTypeOfShape().equals(TypeOfShape.RECTANGLE)) {
        //g2D.setColor(shape.getColor());
        g2D.fillRect((int)shape.getPosition().getX(),(int)shape.getPosition().getY(),(int)shape.getWidth(),(int)shape.getHeight());
        g2D.drawRect((int)shape.getPosition().getX(),(int)shape.getPosition().getY(),(int)shape.getWidth(),(int)shape.getHeight());
      } else if (shape.getTypeOfShape().equals(TypeOfShape.ELLIPSE)) {
        g2D.fillOval((int)shape.getPosition().getX(),(int)shape.getPosition().getY(),(int)shape.getWidth(),(int)shape.getHeight());
        g2D.drawOval((int)shape.getPosition().getX(),(int)shape.getPosition().getY(),(int)shape.getWidth(),(int)shape.getHeight());
      }
    }



  }

  public void refresh(int t) {
    this.shapeList = model.getShapesAtTick((int)model.getMilliseconds(t));
    this.repaint();
  }
}
