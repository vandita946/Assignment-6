package cs5004.animator.view;

import cs5004.animator.model.Model;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class PlaybackView extends JFrame implements ActionListener, View {
  private VisualPanel panel;
  private int ticksPerSecond;
  private final Model model;
  private int t;

  public PlaybackView(Model model) {

    this.model = model;
    this.t = 1;
    this.ticksPerSecond = model.getTicksPerSecond();
    this.panel = new VisualPanel(model);

    JFrame frame = new JFrame("Playback view");
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(300,300);
    frame.add(this.panel);
    







  }

  @Override
  public void publish() {

  }

  @Override
  public String getDescription() {
    return null;
  }

  @Override
  public void actionPerformed(ActionEvent e) {

  }
}
