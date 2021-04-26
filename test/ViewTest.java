/* CS 5004 - Easy Animator - Model
 * Vandita Attal & Swapnil Mittal
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import cs5004.animator.controller.Controller;
import cs5004.animator.controller.ControllerImpl;
import cs5004.animator.model.Model;
import cs5004.animator.model.ModelImpl;
import cs5004.animator.model.ModelImpl.Builder;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.view.PlaybackView;
import cs5004.animator.view.SVGView;
import cs5004.animator.view.TextualView;
import cs5004.animator.view.View;
import cs5004.animator.view.VisualView;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import org.junit.Before;
import org.junit.Test;

/**
 * This is a JUnit Test class for the textual and SVG views.
 */
public class ViewTest {

  private Model model;

  @Before
  public void setUp() {
    model = new ModelImpl();
    AnimationBuilder<Model> builder = new Builder(model);
    model.setTicksPerSecond(1);
    builder.declareShape("circly", "ellipse");
    builder.addMotion("circly", 10, 0, 5, 10, 10, 255, 255, 255, 50, 10, 20, 20, 40, 0, 255, 255);
  }

  @Test
  public void testSVGGetDescription() {
    View svg = new SVGView(model, "System.out");
    assertEquals("<svg width=\"0\" height=\"0\" viewBox=\"0 0 0 0\" version=\"1.1\"\n"
                 + "\txmlns=\"http://www.w3.org/2000/svg\">\n"
                 + "<ellipse id=\"circly\" cx=\"0\" cy=\"5\" rx=\"10\" ry=\"10\" fill=\""
                 + "rgb(255,255,255)\" visibility=\"visible\" >\n"
                 + "\t<animate attributeType=\"xml\" begin=\"10000ms\" dur=\"40000ms\" "
                 + "attributeName=\"cx\" from=\"0.0\" to=\"10.0\" fill=\"freeze\" />\n"
                 + "\t<animate attributeType=\"xml\" begin=\"10000ms\" dur=\"40000ms\" "
                 + "attributeName=\"cy\" from=\"5.0\" to=\"20.0\" fill=\"freeze\" />\n"
                 + "\t<animate attributeType=\"xml\" begin=\"10000ms\" dur=\"40000ms\" "
                 + "attributeName=\"fill\" from=\"rgb(255,255,255)\" to=\"rgb(0,255,255)\" "
                 + "fill=\"freeze\" />\n"
                 + "\t<animate attributeType=\"xml\" begin=\"10000ms\" dur=\"40000ms\" "
                 + "attributeName=\"cx\" from=\"10.0\" to=\"20.0\" fill=\"freeze\" />\n"
                 + "\t<animate attributeType=\"xml\" begin=\"10000ms\" dur=\"40000ms\" "
                 + "attributeName=\"cy\" from=\"10.0\" to=\"40.0\" fill=\"freeze\" />\n"
                 + "</ellipse>\n"
                 + "</svg>", svg.getDescription());
  }

  @Test
  public void testTextGetDescription() {
    View textual = new TextualView(model, "System.out");
    assertEquals("Create RGB(255,255,255) oval circly with center at (0,5), radius 10 and 10\n"
                 + "\n"
                 + "circly appears at time t=10000.0 and disappears at time t=50000.0\n"
                 + "\n"
                 + "circly moves from (0,5) to (10,20) from time t=10000 to t=50000\n"
                 + "circly changes from RGB(255,255,255) to RGB(0,255,255) from time t=10000 to "
                 + "t=50000\n"
                 + "circly changes radius from 10 to 20 and other radius from 10 to 40 from time "
                 + "t=10000 to t=50000\n",
        textual.getDescription());
  }

  @Test
  public void testPlaybackVisualGetDescription() {
    View playback = new PlaybackView(model);
    try {
      playback.getDescription();
      fail("UnsupportedOperationException was not called.");
    } catch (UnsupportedOperationException e) {
      assertEquals("This method is not applicable to views of visual type.", e.getMessage());
    }

    View visual = new VisualView(model);
    try {
      visual.getDescription();
      fail("UnsupportedOperationException was not called.");
    } catch (UnsupportedOperationException e) {
      assertEquals("This method is not applicable to views of visual type.", e.getMessage());
    }
  }

  @Test
  public void testPlaybackButtons() {
    PlaybackView playback = new PlaybackView(model);
    Controller c = new ControllerImpl(playback);
    c.publishView();

    JButton start = new JButton("Start");
    ActionEvent e = new ActionEvent(start, 1, "Start");
    playback.actionPerformed(e);
    assertTrue(playback.getTimer().isRunning());

    JButton pause = new JButton("Pause");
    e = new ActionEvent(pause,1,"Pause");
    playback.actionPerformed(e);
    assertFalse(playback.getTimer().isRunning());

    JButton resume = new JButton("Resume");
    e = new ActionEvent(resume, 1, "Resume");
    playback.actionPerformed(e);
    assertTrue(playback.getTimer().isRunning());

    JButton reset = new JButton("Reset");
    e = new ActionEvent(reset, 1, "Reset");
    playback.actionPerformed(e);
    assertTrue(playback.getTimer().isRunning());

  }

}
