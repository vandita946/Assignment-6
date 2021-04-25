package cs5004.animator.view;

import cs5004.animator.model.Model;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PlaybackView extends JFrame implements View, ActionListener, ItemListener,
    ChangeListener {

  //private VisualPanel panel;
  private int ticksPerSecond;
  private final Model model;
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
  ActionListener actionListener;

  public PlaybackView(Model model) {

    this.model = model;
    this.t = 1;
    this.ticksPerSecond = model.getTicksPerSecond();
    this.panel = new VisualPanel(model);

    this.panel.setPreferredSize(
        new Dimension((int) model.getCanvasWidth()+120, (int) model.getCanvasHeight() + 120));

    this.setTitle("Swapnil and Vandita's Visual View");
    this.setSize((int) model.getCanvasWidth(), (int) model.getCanvasHeight());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());
    this.add(panel, BorderLayout.CENTER);

    JScrollPane pane = new JScrollPane(panel);
    pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    this.add(pane, BorderLayout.CENTER);

    reset = new JButton("Reset");
    start = new JButton("Start");

    pause = new JButton("Pause");
    resume = new JButton("Resume");
    loop = new JCheckBox("Loop");
    slider = new JSlider(1,200,model.getTicksPerSecond());
    sliderLabel = new JLabel();
    sliderLabel.setText("Speed: "+ slider.getValue());
    start.addActionListener(this);
    reset.addActionListener(this);
    pause.addActionListener(this);
    resume.addActionListener(this);
    loop.addItemListener(this);
    panel.setLayout(null);
    start.setBounds(10, (int)model.getCanvasHeight() + 40,50,20);
    reset.setBounds(70, (int)model.getCanvasHeight() + 40,50,20);
    pause.setBounds(130, (int)model.getCanvasHeight() + 40,50,20);
    resume.setBounds(190, (int)model.getCanvasHeight() + 40,70,20);
    loop.setBounds(280, (int)model.getCanvasHeight() + 40,100,20);

    slider.setPaintTrack(true);
    slider.setPaintTicks(true);
    slider.setPaintLabels(true);

    // set spacing
    slider.setMajorTickSpacing(50);
    slider.setMinorTickSpacing(1);

    slider.addChangeListener(this);

    slider.setBounds(5,(int) model.getCanvasHeight() + 60 ,200,50);
    sliderLabel.setBounds(220,(int) model.getCanvasHeight() + 65 ,100,50);
    panel.add(slider);
    panel.add(sliderLabel);

    panel.add(start);
    panel.add(reset);
    panel.add(pause);
    panel.add(resume);
    panel.add(loop);




    // setChangeListener


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
//    timer.start();

  }

  @Override
  public String getDescription() {
    return null;
  }


    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == start) {
        timer.start();
      } else if (e.getSource() == reset) {
        //clear the panel/ make a new one
        timer.stop();
        t=1;
        panel.reset();
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
    if (e.getSource() == loop) {
      if (e.getStateChange() == ItemEvent.SELECTED) {
          timer.stop();
      } else if (e.getStateChange() == ItemEvent.DESELECTED) {
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
    timer.setDelay(1000/slider.getValue());
  }
}
