/*
  CS5004
  Spring 2021
  Easy Animator
  Swapnil Mittal & Vandita Attal
 */

package cs5004.animator.view;

import cs5004.animator.model.Model;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.Timer;

/**
 * VisualView implements the View interface and extends the JFrame class. It represents a visual
 * animation based on a model.
 */
public class VisualView extends JFrame implements View {

  private final VisualPanel panel;
  private final int ticksPerSecond;
  private final Model model;
  private int t;

  /**
   * Constructs a VisualView object and initializes it to the given model. Sets up the JFrame.
   *
   * @param model Model
   */
  public VisualView(Model model) {
    this.model = model;
    this.t = 1;
    this.ticksPerSecond = model.getTicksPerSecond();
    this.panel = new VisualPanel(model);

    this.panel.setPreferredSize(
        new Dimension((int) model.getCanvasWidth(), (int) model.getCanvasHeight()));

    this.setTitle("Swapnil and Vandita's Visual View");
    this.setSize((int) model.getCanvasWidth(), (int) model.getCanvasHeight());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());
    this.add(panel, BorderLayout.CENTER);

    JScrollPane pane = new JScrollPane(panel);
    pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    this.add(pane, BorderLayout.CENTER);

    pack();
    this.setVisible(true);
  }

  @Override
  public void publish() {
    double endingTick = model.getShapeList().get(model.getShapeList().size() - 1)
        .getDisappearTime();
    ActionListener actionListener = event -> {
      if (model.getMilliseconds(t) < endingTick) {
        panel.refresh(t);
        t++;
      }
    };
    Timer timer = new Timer((1000 / ticksPerSecond), actionListener);
    timer.start();
  }


  @Override
  public String getDescription() {
    //Design choice: not required for visual view, only for SVG and textual.
    throw new UnsupportedOperationException(
        "This method is not applicable to views of visual type.");
  }
}

