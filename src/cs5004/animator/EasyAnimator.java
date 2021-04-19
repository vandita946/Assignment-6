/*
  CS5004
  Spring 2021
  Easy Animator
  Swapnil Mittal & Vandita Attal
 */

package cs5004.animator;

import cs5004.animator.model.Model;
import cs5004.animator.model.ModelImpl;
import cs5004.animator.model.ModelImpl.Builder;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.View;
import cs5004.animator.view.ViewFactory;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * EasyAnimator contains the main function from which the animator runs.
 */
public final class EasyAnimator {

  public static void main(String[] args) throws FileNotFoundException {
    Model model = new ModelImpl();
    AnimationBuilder<Model> builder = new Builder(model);

    //Trying to read command line
    Readable cmdLine = new StringReader(String.join(" ", args));
    Scanner scan = new Scanner(cmdLine);
    String inFile = "";
    String outFile = "";
    String viewType = "";
    String line = "";
    int speed = 1;

    //Read the cmd line till it has any arguments
    while(scan.hasNext()) {
      String arg = scan.next();
      line = line + arg + " ";
      if (arg.equalsIgnoreCase("-in")) {
        if (scan.hasNext()) {
          inFile = scan.next();
        } else {
          throw new FileNotFoundException("The input file is invalid or not available");
        }
      } else if (arg.equalsIgnoreCase("-view")) {
        if (scan.hasNext()) {
          viewType = scan.next();
        } else {
          throw new IllegalArgumentException("Please specify the correct view type");
        }
      } else if (arg.equalsIgnoreCase("-out")) {
        if (scan.hasNext()) {
          outFile = scan.next();
        } else {
          throw new FileNotFoundException("Please specify an output file name");
        }
      } else if (arg.equalsIgnoreCase("-speed")) {
        if (scan.hasNext()) {
          speed = Integer.parseInt(scan.next());
        } else {
          throw new IllegalArgumentException("Please specify valid speed/ticksPerSecond");
        }
      }
    }

    if (outFile.equals("")) {
      outFile = "System.out";
    }
    model.setTicksPerSecond(speed);

    //If there is no -in or -view argument, we show a error message.
    if (!line.contains("-in")) {
      JOptionPane.showMessageDialog(null,"Please enter an input file","Error",JOptionPane.ERROR_MESSAGE);
      System.exit(0);
    } else if (!line.contains("-view")) {
      JOptionPane.showMessageDialog(null,"Please enter the view type","Error",JOptionPane.ERROR_MESSAGE);
      System.exit(0);
    }

    try {
      model = AnimationReader.parseFile(new FileReader(inFile), builder);
    } catch (IOException e) {
      JOptionPane.showMessageDialog(null,"Please enter a valid input file","Error",JOptionPane.ERROR_MESSAGE);
      System.exit(0);
    }

    View view = ViewFactory.createView(viewType, model, outFile);
    view.publish();
  }
}
