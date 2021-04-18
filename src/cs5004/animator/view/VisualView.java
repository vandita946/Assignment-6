package cs5004.animator.view;

import cs5004.animator.model.Model;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.Timer;

public class VisualView extends JFrame implements View {
  private VisualPanel panel;
  private int ticksPerSecond;
  private int t = 1;
  private Timer timer;
  private Model model;

  public VisualView(Model model) {
    this.model = model;
    ticksPerSecond = model.getTicksPerSecond();
    this.setTitle("Swapnil and Vandita's Visual View");
    this.setSize((int)model.getCanvasWidth(), (int)model.getCanvasHeight());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    panel = new VisualPanel(model);
    panel.setPreferredSize(new Dimension((int)model.getCanvasWidth(), (int)model.getCanvasHeight()));
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
    double lastTick = model.getShapeList().get(model.getShapeList().size() - 1).getDisappearTime();
    ActionListener actionListener = event -> {
      if (model.getMilliseconds(t) < lastTick) {
        panel.refresh(t);
        t++;
      }
    };
    timer = new Timer((1000/ticksPerSecond), actionListener);
    timer.start();
  }


  @Override
  public String getDescription() {
    return null;
  }
}

