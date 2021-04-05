/* CS 5004 - Easy Animator - Model
 * Vandita Attal & Swapnil Mittal
 */

package cs5004.easyanimator.shape;

import java.awt.Color;

/**
 * This is an abstract class which implements the Shape class.
 */
public abstract class AbstractShape implements Shape {

  protected Point2D reference;
  protected String name;
  protected int appearTime;
  protected int disappearTime;
  protected double canvasWidth;
  protected double canvasHeight;
  private ColorNames color;

  /**
   * This is the constructor to initialize the given parameters.
   *
   * @param reference     is the position reference of the shape.
   * @param name          is the name of the shape.
   * @param color         is the color of the shape.
   * @param canvasWidth   is the canvas width.
   * @param canvasHeight  is the canvas height.
   * @param appearTime    is the appear time of the shape.
   * @param disappearTime is the disappear time of the shape.
   * @throws IllegalArgumentException is thrown if any input is invalid.
   */
  public AbstractShape(Point2D reference, String name, Color color, double canvasWidth,
      double canvasHeight, int appearTime, int disappearTime) throws IllegalArgumentException {

    if (canvasHeight < 0 || canvasWidth < 0) {
      throw new IllegalArgumentException("Canvas dimensions cannot be negative.");
    } else if (reference.getX() > canvasWidth || reference.getY() > canvasHeight) {
      throw new IllegalArgumentException("Object cannot be placed outside the canvas.");
    }

    if (appearTime < 0 || disappearTime < 0 || disappearTime < appearTime) {
      throw new IllegalArgumentException("Appear or disappear time is invalid.");
    }

    for (ColorNames c : ColorNames.values()) {
      if (c.getValue().equals(color)) {
        this.color = c;
      }
    }
    if (this.color == null) {
      throw new IllegalArgumentException("Invalid color entered.");
    }
    this.reference = reference;
    this.name = name;
    this.disappearTime = disappearTime;
    this.appearTime = appearTime;
    this.canvasHeight = canvasHeight;
    this.canvasWidth = canvasWidth;
  }

  @Override
  public double distanceFromOrigin() {
    return reference.distToOrigin();
  }


  @Override
  public int compareTo(Shape s) {
    double areaThis = this.area();
    double areaOther = s.area();

    return Double.compare(areaThis, areaOther);
  }

  @Override
  public void setPosition(double newX, double newY) {
    if (newX > canvasWidth || newY > canvasHeight) {
      throw new IllegalArgumentException("Object cannot be placed outside the canvas.");
    }
    this.reference = new Point2D(newX, newY);
  }

  @Override
  public Point2D getPosition() {
    return this.reference;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void changeColor(Color newColor) {
    ColorNames temp = null;
    for (ColorNames c : ColorNames.values()) {
      if (c.getValue().equals(newColor)) {
        temp = c;
      }
    }
    if (temp == null) {
      throw new IllegalArgumentException("Invalid color entered.");
    }
    this.color = temp;
  }

  @Override
  public String getColor() {
    return this.color.getText();
  }

  @Override
  public int getAppearTime() {
    return this.appearTime;
  }

  @Override
  public int getDisappearTime() {
    return this.disappearTime;
  }

}