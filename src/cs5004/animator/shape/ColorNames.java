/* CS 5004 - Easy Animator - Model
 * Vandita Attal & Swapnil Mittal
 */

package cs5004.animator.shape;

import java.awt.Color;

/**
 * This is an enum for the different colors.
 */
public enum ColorNames {
  RED(Color.RED, "red"),
  BLUE(Color.BLUE, "blue"),
  GREEN(Color.GREEN, "green"),
  BLACK(Color.BLACK, "black"),
  WHITE(Color.WHITE, "white"),
  PINK(Color.PINK, "pink"),
  ORANGE(Color.ORANGE, "orange"),
  CYAN(Color.CYAN, "cyan"),
  YELLOW(Color.YELLOW, "yellow");


  private final Color val;
  private final String txt;

  /**
   * This constructor is used to initialize the given values.
   *
   * @param val is the value of the enum option.
   * @param txt is the string text of the enum option.
   */
  ColorNames(Color val, String txt) {
    this.val = val;
    this.txt = txt;
  }

  /**
   * This is a getter function to get the value.
   *
   * @return an int value.
   */
  public Color getValue() {
    return val;
  }

  /**
   * This is a getter function to get the string text.
   *
   * @return a string.
   */
  public String getText() {
    return txt;
  }


}

