package cs5004.animator.view;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class GraphicsDemo extends JPanel implements ActionListener {

  Timer timer = new Timer(10,this);
  JLabel label;
  private Model model;


  public GraphicsDemo(Model model) {
    timer.start();
    this.model = model;

  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.setBackground(Color.WHITE);

    Graphics2D g2D = (Graphics2D) g;

    for (Shape shape : model.getShapeList()) {
      g2D.setColor(shape.getColor());
      //g2D.fillOval(x,y,100,100);

      if (shape.getTypeOfShape().equals(TypeOfShape.RECTANGLE)) {
        //g2D.setColor(shape.getColor());
        g2D.fillRect((int)shape.getPosition().getX(),(int)shape.getPosition().getY(),(int)shape.getWidth(),(int)shape.getHeight());
      } else if (shape.getTypeOfShape().equals(TypeOfShape.ELLIPSE)) {
        g2D.fillOval((int)shape.getPosition().getX(),(int)shape.getPosition().getY(),(int)shape.getWidth(),(int)shape.getHeight());
      }
    }



  }

  @Override
  public void actionPerformed(ActionEvent e) {

//    //to set boundary
//    if (x>=740 || x <= 0) {
//      velocityX = -velocityX;
//    }
//    if (y>=740 || y <= 0) {
//      velocityY = -velocityY;
//    }

    x = x + velocityX;
    y = y + velocityY;

    repaint();
  }
}
