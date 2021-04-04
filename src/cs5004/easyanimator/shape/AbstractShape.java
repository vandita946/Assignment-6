package cs5004.easyanimator.shape;

import java.awt.Color;

/**
 * Created by ashesh on 1/26/2017.
 */
public abstract class AbstractShape implements Shape {

  protected Point2D reference;
  protected String name;
  private ColorNames color;
  protected int appearTime;
  protected int disappearTime;
  protected double canvasWidth;
  protected double canvasHeight;

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

    changeColor(color);
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

    if (areaThis < areaOther) {
      return -1;
    } else if (areaOther < areaThis) {
      return 1;
    } else {
      return 0;
    }
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
    for (ColorNames c : ColorNames.values()) {
      if (c.getValue().equals(newColor)) {
        this.color = c;
      }
    }
    if (this.color == null) {
      throw new IllegalArgumentException("Invalid color entered.");
    }
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