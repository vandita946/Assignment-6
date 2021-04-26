/* CS 5004 - Easy Animator - Model
 * Vandita Attal & Swapnil Mittal
 */

import static org.junit.Assert.assertEquals;

import cs5004.animator.EasyAnimator;
import cs5004.animator.controller.Controller;
import cs5004.animator.controller.ControllerImpl;
import cs5004.animator.model.Model;
import cs5004.animator.model.ModelImpl;
import cs5004.animator.model.ModelImpl.Builder;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.view.SVGView;
import cs5004.animator.view.TextualView;
import cs5004.animator.view.View;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

/**
 * This is a JUnit test class for Controller.
 */
public class ControllerTest {

  private Model model;

  @Before
  public void setUp() {
    this.model = new ModelImpl();
    model = new ModelImpl();
    AnimationBuilder<Model> builder = new Builder(model);
    model.setTicksPerSecond(1);
    builder.declareShape("circly", "ellipse");
    builder.addMotion("circly", 10, 0, 5, 10, 10, 255, 255, 255, 50, 10, 20, 20, 40, 0, 255, 255);
  }

  @Test
  public void testTextController() {
    View textual = new TextualView(model, "toh-3.txt");
    Controller textController = new ControllerImpl(textual);
    EasyAnimator.main(new String[]{"-in test/toh-3.txt -view text -out toh-3-output.txt"});

    BufferedReader r;
    try {
      r = new BufferedReader(new FileReader("toh-3-output.txt"));
      StringBuilder s = new StringBuilder();
      String line = r.readLine();

      while (line != null) {
        s.append(line);
        s.append("\n");
        line = r.readLine();
      }
      assertEquals("Create RGB(0,49,90) rectangle disk1 with corner at (45,130),"
                   + " width 20 and height 30\n"
                   + "Create RGB(6,247,41) rectangle disk2 with corner at (22,160),"
                   + "width 65 and height 30\n"
                   + "Create RGB(11,45,175) rectangle disk3 with corner at (0,190), "
                   + "width 110 and height 30\n"
                   + "\n"
                   + "disk1 appears at time t=1000.0 and disappears at time t=302000.0\n"
                   + "disk2 appears at time t=1000.0 and disappears at time t=302000.0\n"
                   + "disk3 appears at time t=1000.0 and disappears at time t=302000.0\n"
                   + "\n"
                   + "disk1 moves from (45,130) to (45,0) from time t=25000 to t=35000\n"
                   + "disk1 moves from (45,130) to (345,0) from time t=36000 to t=46000\n"
                   + "disk1 moves from (45,130) to (345,190) from time t=47000 to t=57000\n"
                   + "disk2 moves from (22,160) to (22,0) from time t=57000 to t=67000\n"
                   + "disk2 moves from (22,160) to (172,0) from time t=68000 to t=78000\n"
                   + "disk2 moves from (22,160) to (172,190) from time t=79000 to t=89000\n"
                   + "disk1 moves from (45,130) to (345,0) from time t=89000 to t=99000\n"
                   + "disk1 moves from (45,130) to (195,0) from time t=100000 to t=110000\n"
                   + "disk1 moves from (45,130) to (195,160) from time t=111000 to t=121000\n"
                   + "disk3 moves from (0,190) to (0,0) from time t=121000 to t=131000\n"
                   + "disk3 moves from (0,190) to (300,0) from time t=132000 to t=142000\n"
                   + "disk3 moves from (0,190) to (300,190) from time t=143000 to t=153000\n"
                   + "disk1 moves from (45,130) to (195,0) from time t=153000 to t=163000\n"
                   + "disk3 changes from RGB(11,45,175) to RGB(0,255,0) from time t=153000 "
                   + "to t=161000\n"
                   + "disk1 moves from (45,130) to (45,0) from time t=164000 to t=174000\n"
                   + "disk1 moves from (45,130) to (45,190) from time t=175000 to t=185000\n"
                   + "disk2 moves from (22,160) to (172,0) from time t=185000 to t=195000\n"
                   + "disk2 moves from (22,160) to (322,0) from time t=196000 to t=206000\n"
                   + "disk2 moves from (22,160) to (322,160) from time t=207000 to t=217000\n"
                   + "disk1 moves from (45,130) to (45,0) from time t=217000 to t=227000\n"
                   + "disk2 changes from RGB(6,247,41) to RGB(0,255,0) from time t=217000"
                   + " to t=225000\n"
                   + "disk1 moves from (45,130) to (345,0) from time t=228000 to t=238000\n"
                   + "disk1 moves from (45,130) to (345,130) from time t=239000 to t=249000\n"
                   + "disk1 changes from RGB(0,49,90) to RGB(0,255,0) from time t=249000 to"
                   + " t=257000\n", s.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testSVGController() {
    View svg = new SVGView(model, "toh-3.svg");
    Controller svgController = new ControllerImpl(svg);
    EasyAnimator.main(new String[]{"-in test/toh-3.txt -view svg -out toh-3.svg"});

    BufferedReader r;
    try {
      r = new BufferedReader(new FileReader("toh-3.svg"));
      StringBuilder s = new StringBuilder();
      String line = r.readLine();

      while (line != null) {
        s.append(line);
        s.append("\n");
        line = r.readLine();
      }
      assertEquals("<svg width=\"410\" height=\"220\" viewBox=\"0 0 555 270\" "
                   + "version=\"1.1\"\n"
                   + "\txmlns=\"http://www.w3.org/2000/svg\">\n"
                   + "<rect id=\"disk1\" x=\"45\" y=\"130\" width=\"20\" height=\"30\" fill=\""
                   + "rgb(0,49,90)\" visibility=\"visible\" >\n"
                   + "\t<animate attributeType=\"xml\" begin=\"25000ms\" dur=\"10000ms\" "
                   + "attributeName=\"y\" from=\"130.0\" to=\"0.0\" fill=\"freeze\" />\n"
                   + "\t<animate attributeType=\"xml\" begin=\"36000ms\" dur=\"10000ms\""
                   + " attributeName=\"x\" from=\"45.0\" to=\"345.0\" fill=\"freeze\" />\n"
                   + "\t<animate attributeType=\"xml\" begin=\"47000ms\" dur=\"10000ms\""
                   + " attributeName=\"y\" from=\"0.0\" to=\"190.0\" fill=\"freeze\" />\n"
                   + "\t<animate attributeType=\"xml\" begin=\"89000ms\" dur=\"10000ms\""
                   + " attributeName=\"y\" from=\"190.0\" to=\"0.0\" fill=\"freeze\" />\n"
                   + "\t<animate attributeType=\"xml\" begin=\"100000ms\" dur=\"10000ms\""
                   + " attributeName=\"x\" from=\"345.0\" to=\"195.0\" fill=\"freeze\" />\n"
                   + "\t<animate attributeType=\"xml\" begin=\"111000ms\" dur=\"10000ms\""
                   + " attributeName=\"y\" from=\"0.0\" to=\"160.0\" fill=\"freeze\" />\n"
                   + "\t<animate attributeType=\"xml\" begin=\"153000ms\" dur=\"10000ms\" "
                   + "attributeName=\"y\" from=\"160.0\" to=\"0.0\" fill=\"freeze\" />\n"
                   + "\t<animate attributeType=\"xml\" begin=\"164000ms\" dur=\"10000ms\" "
                   + "attributeName=\"x\" from=\"195.0\" to=\"45.0\" fill=\"freeze\" />\n"
                   + "\t<animate attributeType=\"xml\" begin=\"175000ms\" dur=\"10000ms\" "
                   + "attributeName=\"y\" from=\"0.0\" to=\"190.0\" fill=\"freeze\" />\n"
                   + "\t<animate attributeType=\"xml\" begin=\"217000ms\" dur=\"10000ms\""
                   + " attributeName=\"y\" from=\"190.0\" to=\"0.0\" fill=\"freeze\" />\n"
                   + "\t<animate attributeType=\"xml\" begin=\"228000ms\" dur=\"10000ms\""
                   + " attributeName=\"x\" from=\"45.0\" to=\"345.0\" fill=\"freeze\" />\n"
                   + "\t<animate attributeType=\"xml\" begin=\"239000ms\" dur=\"10000ms\" "
                   + "attributeName=\"y\" from=\"0.0\" to=\"130.0\" fill=\"freeze\" />\n"
                   + "\t<animate attributeType=\"xml\" begin=\"249000ms\" dur=\"8000ms\" "
                   + "attributeName=\"fill\" from=\"rgb(0,49,90)\" to=\"rgb(0,255,0)\" "
                   + "fill=\"freeze\" />\n"
                   + "</rect>\n"
                   + "<rect id=\"disk2\" x=\"22\" y=\"160\" width=\"65\" height=\"30\" "
                   + "fill=\"rgb(6,247,41)\" visibility=\"visible\" >\n"
                   + "\t<animate attributeType=\"xml\" begin=\"57000ms\" dur=\"10000ms\""
                   + " attributeName=\"y\" from=\"160.0\" to=\"0.0\" fill=\"freeze\" />\n"
                   + "\t<animate attributeType=\"xml\" begin=\"68000ms\" dur=\"10000ms\""
                   + " attributeName=\"x\" from=\"22.0\" to=\"172.0\" fill=\"freeze\" />\n"
                   + "\t<animate attributeType=\"xml\" begin=\"79000ms\" dur=\"10000ms\""
                   + " attributeName=\"y\" from=\"0.0\" to=\"190.0\" fill=\"freeze\" />\n"
                   + "\t<animate attributeType=\"xml\" begin=\"185000ms\" dur=\"10000ms\""
                   + " attributeName=\"y\" from=\"190.0\" to=\"0.0\" fill=\"freeze\" />\n"
                   + "\t<animate attributeType=\"xml\" begin=\"196000ms\" dur=\"10000ms\" "
                   + "attributeName=\"x\" from=\"172.0\" to=\"322.0\" fill=\"freeze\" />\n"
                   + "\t<animate attributeType=\"xml\" begin=\"207000ms\" dur=\"10000ms\""
                   + " attributeName=\"y\" from=\"0.0\" to=\"160.0\" fill=\"freeze\" />\n"
                   + "\t<animate attributeType=\"xml\" begin=\"217000ms\" dur=\"8000ms\""
                   + " attributeName=\"fill\" from=\"rgb(6,247,41)\" to=\"rgb(0,255,0)\""
                   + " fill=\"freeze\" />\n"
                   + "</rect>\n"
                   + "<rect id=\"disk3\" x=\"0\" y=\"190\" width=\"110\" height=\"30\" "
                   + "fill=\"rgb(11,45,175)\" visibility=\"visible\" >\n"
                   + "\t<animate attributeType=\"xml\" begin=\"121000ms\" dur=\"10000ms\" "
                   + "attributeName=\"y\" from=\"190.0\" to=\"0.0\" fill=\"freeze\" />\n"
                   + "\t<animate attributeType=\"xml\" begin=\"132000ms\" dur=\"10000ms\" "
                   + "attributeName=\"x\" from=\"0.0\" to=\"300.0\" fill=\"freeze\" />\n"
                   + "\t<animate attributeType=\"xml\" begin=\"143000ms\" dur=\"10000ms\" "
                   + "attributeName=\"y\" from=\"0.0\" to=\"190.0\" fill=\"freeze\" />\n"
                   + "\t<animate attributeType=\"xml\" begin=\"153000ms\" dur=\"8000ms\" "
                   + "attributeName=\"fill\" from=\"rgb(11,45,175)\" to=\"rgb(0,255,0)\" "
                   + "fill=\"freeze\" />\n"
                   + "</rect>\n"
                   + "</svg>\n", s.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

