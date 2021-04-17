/* CS 5004 - Easy Animator - Model
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
   * This is the constructor to initialize the given parameters.
   *
   * @param reference     is the position reference of the shape.
   * @param name          is the name of the shape.
   * @param red           is the red color value of the shape.
   * @param green         is the green color value of the shape.
   * @param blue          is the blue color value of the shape.
   * @param canvasWidth   is the canvas width.
   * @param canvasHeight  is the canvas height.
   * @param appearTime    is the appear time of the shape.
   * @param disappearTime is the disappear time of the shape.
   * @throws IllegalArgumentException is thrown if any input is invalid.
   */
  public AbstractShape(Point2D reference, String name, int red, int green, int blue,
      double canvasWidth,
      double canvasHeight, double cornerX, double cornerY, double appearTime, double disappearTime, TypeOfShape type)
      throws IllegalArgumentException {

//    if (reference.getX() > (canvasWidth + cornerX) || reference.getY() < cornerY || reference.getX() < cornerX || reference.getY() > (cornerY + canvasHeight)) {
//      throw new IllegalArgumentException("Object cannot be placed outside the canvas.");
//    }

    if (appearTime < 0 || disappearTime < 0 || disappearTime < appearTime) {
      throw new IllegalArgumentException("Appear or disappear time is invalid.");
    }


    this.color = new Color(red, green, blue);
    this.reference = reference;
    this.name = name;
    this.disappearTime = disappearTime;
    this.appearTime = appearTime;
    this.canvasHeight = canvasHeight;
    this.canvasWidth = canvasWidth;
    this.cornerX = cornerX;
    this.cornerY = cornerY;
    this.type = type;
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