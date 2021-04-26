/*
  CS5004
  Spring 2021
  Easy Animator
  Swapnil Mittal & Vandita Attal
 */

package cs5004.animator.view;

import cs5004.animator.model.Model;
import cs5004.animator.shape.Shape;
import cs5004.animator.shape.TypeOfShape;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import javax.swing.JPanel;

/**
 * VisualPanel extends JPanel and represents the panel of the visual animation.
 */
public class VisualPanel extends JPanel {

  private Model model;
  private List<Shape> shapeList;

  /**
   * Constructs a VisualPanel object initialized to the given model.
   *
   * @param model Model
   */
  public VisualPanel(Model model) {
    this.model = model;
    this.shapeList = model.getShapeList();
  }

  @Override
  public void paintComponent(Graphics g) {

    super.paintComponent(g);
    this.setBackground(Color.WHITE);
    Graphics2D g2D = (Graphics2D) g;
    for (Shape shape : shapeList) {
      g2D.setColor(shape.getColor());
      if (shape.getTypeOfShape().equals(TypeOfShape.RECTANGLE)) {
        g2D.fillRect((int) shape.getPosition().getX(), (int) shape.getPosition().getY(),
            (int) shape.getWidth(), (int) shape.getHeight());
      } else if (shape.getTypeOfShape().equals(TypeOfShape.ELLIPSE)) {
        g2D.fillOval((int) shape.getPosition().getX(), (int) shape.getPosition().getY(),
            (int) shape.getWidth(), (int) shape.getHeight());
      }
    }
  }

  /**
   * Updates the shapeList according to the given tick and repaints the panel.
   *
   * @param t tick
   */
  public void refresh(double t) {
    this.shapeList = model.getShapesAtTick(t);
    this.repaint();
  }


}
