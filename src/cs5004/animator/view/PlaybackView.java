package cs5004.animator.view;

import cs5004.animator.model.Model;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class PlaybackView extends JFrame implements ActionListener, View {
  private Model model;

  public PlaybackView(Model model) {
    this.model = model;
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
