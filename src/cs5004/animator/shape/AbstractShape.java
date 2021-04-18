/* CS 5004 - Easy Animator
 * Vandita Attal & Swapnil Mittal
 */

package cs5004.animator.shape;

import java.awt.Color;

/**
 * This is an abstract class which implements the Shape class.
 */
public abstract class AbstractShape implements Shape {

  protected Point2D reference;
  protected String name;
  protected double appearTime;
  protected double disappearTime;
  protected double canvasWidth;
  protected double canvasHeight;
  protected double cornerX;
  protected double cornerY;
  private Color color;
  protected TypeOfShape type;


  /**
   * Constructor to initialize a Shape object with the given parameters.
   *
   * @param reference     Point2D position of the shape
   * @param name          unique name of the shape
   * @param red           RGB red value
   * @param green         RGB green value
   * @param blue          RGB blue value
   * @param appearTime    time at which the shape appears (in milliseconds)
   * @param disappearTime time at which the shape disappears (in milliseconds)
   * @param type          TypeOfShape type
   * @throws IllegalArgumentException if the appear time values are invalid
   */
  public AbstractShape(Point2D reference, String name, int red, int green, int blue,
      double appearTime, double disappearTime, TypeOfShape type) throws IllegalArgumentException {

    if (appearTime < 0 || disappearTime < 0 || disappearTime < appearTime) {
      throw new IllegalArgumentException("Appear or disappear time is invalid.");
    }

    this.color = new Color(red, green, blue);
    this.reference = reference;
    this.name = name;
    this.disappearTime = disappearTime;
    this.appearTime = appearTime;
    this.type = type;
  }

  @Override
  public void setPosition(double newX, double newY) {
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
    this.color = newColor;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public double getAppearTime() {
    return this.appearTime;
  }

  @Override
  public double getDisappearTime() {
    return this.disappearTime;
  }

  @Override
  public TypeOfShape getTypeOfShape() {
    return this.type;
  }

  @Override
  public void setDisappearTime(double disappearTime) {
    this.disappearTime = disappearTime;
  }

}