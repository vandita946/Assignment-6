/* CS 5004 - Easy Animator - Model
 * Vandita Attal & Swapnil Mittal
 */

package cs5004.animator.view;

import cs5004.animator.model.Model;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This class implements the view interface and outlines the methods required to produce a playback
 * view with controls.
 */
public class PlaybackView extends JFrame implements View, ActionListener, ItemListener,
    ChangeListener {

  private final Model model;
  ActionListener actionListener;
  private int ticksPerSecond;
  private int t;
  private VisualPanel panel;
  private JButton reset;
  private JButton start;
  private JButton pause;
  private JLabel sliderLabel;
  private JButton resume;
  private Timer timer;
  private JCheckBox loop;
  private JSlider slider;

  /**
   * Constructs the PlaybackView object and initializes it to the given model.
   *
   * @param model Model
   */
  public PlaybackView(Model model) {

    this.model = model;
    this.t = 1;
    this.ticksPerSecond = model.getTicksPerSecond();
    this.panel = new VisualPanel(model);
    JPanel control = new JPanel();

    this.panel.setPreferredSize(
        new Dimension((int) model.getCanvasWidth() + 120, (int) model.getCanvasHeight()));

    this.setTitle("Swapnil and Vandita's Visual View");
    this.setSize((int) model.getCanvasWidth(), (int) model.getCanvasHeight());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());
    this.add(panel, BorderLayout.SOUTH);

    JScrollPane pane = new JScrollPane(panel);
    pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    this.add(pane, BorderLayout.CENTER);

    reset = new JButton("Reset");
    start = new JButton("Start");

    pause = new JButton("Pause");
    resume = new JButton("Resume");
    loop = new JCheckBox("Loop");
    slider = new JSlider(JSlider.VERTICAL, 1, 200, model.getTicksPerSecond());
    sliderLabel = new JLabel();
    sliderLabel.setText("Speed: " + slider.getValue());
    start.addActionListener(this);
    reset.addActionListener(this);
    pause.addActionListener(this);
    resume.addActionListener(this);
    loop.addItemListener(this);
    panel.setLayout(null);
    control.setBounds((int) model.getCanvasWidth(), 0, 100, (int) model.getCanvasHeight());
    control.setLayout(new BoxLayout(control, BoxLayout.Y_AXIS));

    slider.setPaintTrack(true);
    slider.setPaintTicks(true);
    slider.setPaintLabels(true);

    slider.addChangeListener(this);

    slider.setBounds(5, (int) model.getCanvasHeight() + 60, 200, 50);
    sliderLabel.setBounds(220, (int) model.getCanvasHeight() + 65, 100, 50);

    control.add(start);
    control.add(reset);
    control.add(pause);
    control.add(resume);
    control.add(loop);
    control.add(sliderLabel);
    control.add(slider);
    panel.add(control);

    pack();
    this.setVisible(true);
  }

  @Override
  public void publish() {
    double endingTick = model.getShapeList().get(model.getShapeList().size() - 1)
        .getDisappearTime();
    actionListener = event -> {
      if (model.getMilliseconds(t) < endingTick) {
        panel.refresh(t);
        t++;
      }
    };
    timer = new Timer((1000 / ticksPerSecond), actionListener);

  }

  @Override
  public String getDescription() {
    //Design choice: not required for visual view, only for SVG and textual.
    throw new UnsupportedOperationException(
        "This method is not applicable to views of visual type.");
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == start) {
      timer.start();
    } else if (e.getSource() == reset) {
      //clear the panel/ make a new one
      timer.stop();
      t = 1;
      panel.repaint();
      timer.start();
    } else if (e.getSource() == pause) {
      timer.stop();
    } else if (e.getSource() == resume) {
      timer.start();
    }
  }

  /**
   * Invoked when an item has been selected or deselected by the user. The code written for this
   * method performs the operations that need to occur when an item is selected (or deselected).
   *
   * @param e the event to be processed
   */
  @Override
  public void itemStateChanged(ItemEvent e) {

    double endingTick = model.getShapeList().get(model.getShapeList().size() - 1)
        .getDisappearTime();
    if (e.getSource() == loop) {
      if (e.getStateChange() == ItemEvent.SELECTED && model.getMilliseconds(t) == endingTick) {
        timer.stop();
        t = 1;
        panel.repaint();
        timer.start();
      }
    }
  }

  /**
   * Invoked when the target of the listener has changed its state.
   *
   * @param e a ChangeEvent object
   */
  @Override
  public void stateChanged(ChangeEvent e) {
    sliderLabel.setText("Speed: " + slider.getValue());
    timer.setDelay(1000 / slider.getValue());
  }

  /**
   * Returns the timer of the class. Mainly for testing purposes.
   *
   * @return Timer timer
   */
  public Timer getTimer() {
    return this.timer;
  }
}
