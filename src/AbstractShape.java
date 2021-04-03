import java.awt.Color;

/**
 * Created by ashesh on 1/26/2017.
 */
public abstract class AbstractShape implements Shape {

  protected Point2D reference;
  private String name;
  private Color color;

  public AbstractShape(Point2D reference, String name, String colorName)
      throws IllegalArgumentException {
    this.reference = reference;
    this.name = name;
    if (Color.getColor(colorName) == null) {
      throw new IllegalArgumentException("Color is invalid.");
    }
    this.color = Color.getColor(colorName);
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
    this.color = newColor;
  }

  @Override
  public Color getColor() {
    return this.color;
  }
}