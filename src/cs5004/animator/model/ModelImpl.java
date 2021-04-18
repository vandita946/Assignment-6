/* CS 5004 - Easy Animator - Model
 * Vandita Attal & Swapnil Mittal
 */

package cs5004.animator.model;

import cs5004.animator.animation.Animation;
import cs5004.animator.animation.ChangeColor;
import cs5004.animator.animation.Move;
import cs5004.animator.animation.Scale;
import cs5004.animator.animation.TypeOfAnimation;
import cs5004.animator.shape.Ellipse;
import cs5004.animator.shape.Rectangle;
import cs5004.animator.shape.Shape;
import cs5004.animator.shape.TypeOfShape;
import cs5004.animator.util.AnimationBuilder;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class implements the Model interface. It stores the shapes and animations in an animator and
 * contains the methods to manipulate and manage them.
 */
public final class ModelImpl implements Model {

  private double canvasWidth;
  private double canvasHeight;
  private int cornerX;
  private int cornerY;
  private List<Shape> shapeList;
  private Map<String, String> shapeLedger;
  private List<Animation> animationList;
  private int ticksPerSecond;

  /**
   * This is the constructor to initialize the given parameters.
   *
   * @param canvasWidth  is the canvas width.
   * @param canvasHeight is the canvas height.
   */
  public ModelImpl() {
    animationList = new ArrayList<>();
    shapeList = new ArrayList<>();
    shapeLedger = new HashMap<>();
  }

  public double getMilliseconds(double t) {
    return ((double)t/ticksPerSecond) * 1000;
  }

  private int getOffsetX(int x){
    return x - cornerX;
  }

  private int getOffsetY(int y) {
    return y - cornerY;
  }

  public Shape createShape(String shapeName, String type, int x, int y, double width, double height, int startingTime, int endingTime, int r, int g, int b) {
    Shape shape = findShape(shapeName);

    if (shape != null) {
      if (shape.getDisappearTime() < getMilliseconds(endingTime)) {
        shape.setDisappearTime(getMilliseconds(endingTime));
      }
      return shape;
    }

    if (type.equalsIgnoreCase("ellipse")) {
      shape = new Ellipse(getOffsetX(x), getOffsetY(y), width, height, shapeName, r, g, b, canvasWidth, canvasHeight, cornerX, cornerY, getMilliseconds(startingTime), getMilliseconds(endingTime));
    } else if (type.equalsIgnoreCase("rectangle")) {
      shape = new Rectangle(getOffsetX(x), getOffsetY(y), width, height, shapeName, r,g, b, canvasWidth, canvasHeight, cornerX, cornerY, getMilliseconds(startingTime), getMilliseconds(endingTime));
    }
    this.addShape(shape);
    return shape;
  }

  public Shape findShape(String shapeName) {
    for (Shape s : shapeList) {
      if (s.getName().equals(shapeName)) {
        return s;
      }
    }
    return null;
  }

  /**
   * This function is used to add shape to the canvas.
   *
   * @param shape is the shape to be added.
   */
  public void addShape(Shape shape) {
//    if (shape == null) {
//      throw new IllegalArgumentException("Shape cannot be null.");
//    }
    if (!shapeList.contains(shape)) {
      shapeList.add(shape);
//      updateShapeLedger(shape.getName(), shape.getTypeOfShape().toString());
    }
  }

  public void updateShapeLedger(String shapeName, String shapeType) {
    if (!shapeLedger.containsKey(shapeName)) {
      shapeLedger.put(shapeName, shapeType);
    }
  }

  /**
   * This function is used to change the color of the shape.
   *
   * @param shape        is the shape for which the color changes.
   * @param startingTime is the starting time for this color change.
   * @param endingTime   is the ending time for this color change.
   * @param newColor     is the new color that the color changes to.
   * @throws IllegalArgumentException if given parameters are invalid.
   */
  public void addChangeColorAnimation(Shape shape, int startingTime, int endingTime, int red, int green, int blue) throws IllegalArgumentException {
    if (checkLegalTime(startingTime, endingTime, TypeOfAnimation.COLOR, shape)) {
      throw new IllegalArgumentException(
          "There is an illegal time overlap with another color change animation.");
    }
    Animation colorChange = new ChangeColor(shape, getMilliseconds(startingTime), getMilliseconds(endingTime), red, green, blue);
    animationList.add(colorChange);
  }

  /**
   * This function is used to scale the shape.
   *
   * @param shape        is the shape that will scale.
   * @param type         is the type of shape that will scale.
   * @param startingTime is the starting time for this scaling to happen.
   * @param endingTime   is the ending time for this scaling to stop.
   * @param newWidth     is the new width of the shape.
   * @param newHeight    is the new height of the shape.
   * @throws IllegalArgumentException if given parameters are invalid.
   */
  public void addScaleAnimation(Shape shape, TypeOfShape type, int startingTime, int endingTime,
      double newWidth,
      double newHeight) throws IllegalArgumentException {
    if (checkLegalTime(startingTime, endingTime, TypeOfAnimation.SCALE, shape)) {
      throw new IllegalArgumentException(
          "There is an illegal time overlap with another scale animation.");
    }
    Animation scale = new Scale(shape, type, getMilliseconds(startingTime), getMilliseconds(endingTime), newWidth, newHeight);
    animationList.add(scale);
  }

  /**
   * This function is used to move the shape.
   *
   * @param shape        is the shape to be moved.
   * @param type         is the type of shape to be moved.
   * @param startingTime is the starting time of the move.
   * @param endingTime   is the ending time of the move.
   * @param toX          is the new x coordinate for the shape to move to.
   * @param toY          is the new y coordinate for the shape to move to.
   * @throws IllegalArgumentException if given parameters are invalid.
   */
  public void addMoveAnimation(Shape shape, TypeOfShape type, int startingTime, int endingTime,
      int toX,
      int toY) throws IllegalArgumentException {
    if (checkLegalTime(getMilliseconds(startingTime), getMilliseconds(endingTime), TypeOfAnimation.MOVE, shape)) {
      System.out.println(getMilliseconds(startingTime) + " " + getMilliseconds(endingTime));
      throw new IllegalArgumentException(
          "There is an illegal time overlap with another move animation.");
    }
    Animation move = new Move(shape, type, getMilliseconds(startingTime), getMilliseconds(endingTime), getOffsetX(toX), getOffsetY(toY), canvasWidth,
        canvasHeight, cornerX, cornerY);
    animationList.add(move);
  }

  /**
   * This is a boolean to see if the animation being performed is within an allowed time.
   *
   * @param startingTime is the starting time of the animation.
   * @param endingTime   is the ending time of the animation.
   * @param type         is the type of animation.
   * @return a boolean.
   */
  private boolean checkLegalTime(double startingTime, double endingTime, TypeOfAnimation type, Shape shape) {
    for (Animation each : animationList) {
      if (type.equals(each.getType()) && shape.equals(each.getShape())) {
        if (getMilliseconds(startingTime) <= each.getStartingTime() && getMilliseconds(endingTime) >= each.getEndingTime()) {
          return true;
        } else if (getMilliseconds(startingTime) > each.getStartingTime() && getMilliseconds(endingTime) < each.getEndingTime()) {
          return true;
        } else if (getMilliseconds(startingTime) > each.getStartingTime() && getMilliseconds(endingTime) < each.getEndingTime()) {
          return true;
        } else if (getMilliseconds(endingTime) > each.getStartingTime() && getMilliseconds(endingTime) < each.getEndingTime()) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * This is a helper function to sort our list according to appear time.
   */
  private void sortShapeList() {
    Comparator<Shape> comp = new Comparator<Shape>() {
      @Override
      public int compare(Shape o1, Shape o2) {
        if (o1.getAppearTime() > o2.getAppearTime()) {
          return 1;
        } else if (o2.getAppearTime() > o1.getAppearTime()) {
          return -1;
        } else {
          return 0;
        }
      }
    };
    shapeList.sort(comp);
  }

  /**
   * This is a helper function to sort our list according to starting time.
   */
  public void sortAnimationList() {
    Comparator<Animation> comp = new Comparator<Animation>() {
      @Override
      public int compare(Animation o1, Animation o2) {
        if (o1.getStartingTime() > o2.getStartingTime()) {
          return 1;
        } else if (o2.getStartingTime() > o1.getStartingTime()) {
          return -1;
        } else {
          return 0;
        }
      }
    };
    animationList.sort(comp);
  }

  /**
   * This is a function to get the shapes at a given time on canvas.
   *
   * @param tick is the time at which we need to get the shapes.
   * @return the list of the shapes.
   */
  public List<Shape> getShapesAtTick(int tick) {
    List<Shape> tickShapes = new ArrayList<>();
    for (Shape s : shapeList) {
      if (s.getAppearTime() <= tick && s.getDisappearTime() >= tick) {
        tickShapes.add(s);
      }
    }
    return tickShapes;
  }

  @Override
  public String toString() {
    StringBuilder returnString = new StringBuilder();
    // create (shape.toString) for loop through the shape list
    for (Shape s : shapeList) {
      returnString.append("Create ").append(s.toString()).append("\n");
    }

    returnString.append("\n");

    // appear and disappear times for loop through the shape list
    sortShapeList();
    for (Shape s : shapeList) {
      returnString.append(s.getName()).append(" appears at time t=").append(s.getAppearTime())
          .append(" and disappears at time t=").append(s.getDisappearTime()).append("\n");
    }

    returnString.append("\n");

    // for loop through animation list
    sortAnimationList();
    for (Animation a : animationList) {
      returnString.append(a.toString()).append("\n");
    }

    return returnString.toString();
  }

  public List<Shape> getShapeList() {
    sortShapeList();
    return this.shapeList;
  }


  public String getTypeByName(String shapeName) {
    return shapeLedger.get(shapeName);
  }

  public List<Animation> getAnimationList() {
    sortAnimationList();
    return this.animationList;
  }

  public double getCanvasWidth() {
    return this.canvasWidth;
  }

  public double getCanvasHeight() {
    return this.canvasHeight;
  }

  @Override
  public List<Animation> getAnimationsByShape(Shape shape) {
    return animationList.stream().filter(a-> a.getShape().equals(shape)).collect(
        Collectors.toList());
  }

  @Override
  public void setTicksPerSecond(int ticksPerSecond) {
    this.ticksPerSecond = ticksPerSecond;
  }

  @Override
  public int getTicksPerSecond() {
    return this.ticksPerSecond;
  }

  @Override
  public double getCornerX() {
    return this.cornerX;
  }

  @Override
  public double getCornerY() {
    return this.cornerY;
  }

  public void setCanvasWidth(int newWidth) {
    this.canvasWidth = newWidth;
  }

  public void setCanvasHeight(int newHeight) {
    this.canvasHeight = newHeight;
  }

  public void setCornerValues(int x, int y) {
    this.cornerX = x;
    this.cornerY = y;
  }

  public static final class Builder implements AnimationBuilder<Model> {
    private Model model;

    public Builder(Model model) {
      this.model = model;
    }
    /**
     * Constructs a final document.
     *
     * @return the newly constructed document
     */
    @Override
    public Model build() {
      return model;
    }

    /**
     * Specify the bounding box to be used for the animation.
     *
     * @param x      The leftmost x value
     * @param y      The topmost y value
     * @param width  The width of the bounding box
     * @param height The height of the bounding box
     * @return This {@link AnimationBuilder}
     */
    @Override
    public AnimationBuilder<Model> setBounds(int x, int y, int width, int height) {
      model.setCanvasWidth(width);
      model.setCanvasHeight(height);
      model.setCornerValues(x, y);
      return this;
    }

    /**
     * Adds a new shape to the growing document.
     *
     * @param name The unique name of the shape to be added. No shape with this name should already
     *             exist.
     * @param type The type of shape (e.g. "ellipse", "rectangle") to be added. The set of supported
     *             shapes is unspecified, but should include "ellipse" and "rectangle" as a
     *             minimum.
     * @return This {@link AnimationBuilder}
     */
    @Override
    public AnimationBuilder<Model> declareShape(String name, String type) {
      model.updateShapeLedger(name, type);
      return this;
    }

    /**
     * Adds a transformation to the growing document.
     *
     * @param name The name of the shape (added with {@link AnimationBuilder#declareShape})
     * @param t1   The start time of this transformation
     * @param x1   The initial x-position of the shape
     * @param y1   The initial y-position of the shape
     * @param w1   The initial width of the shape
     * @param h1   The initial height of the shape
     * @param r1   The initial red color-value of the shape
     * @param g1   The initial green color-value of the shape
     * @param b1   The initial blue color-value of the shape
     * @param t2   The end time of this transformation
     * @param x2   The final x-position of the shape
     * @param y2   The final y-position of the shape
     * @param w2   The final width of the shape
     * @param h2   The final height of the shape
     * @param r2   The final red color-value of the shape
     * @param g2   The final green color-value of the shape
     * @param b2   The final blue color-value of the shape
     * @return This {@link AnimationBuilder}
     */
    @Override
    public AnimationBuilder<Model> addMotion(String name, int t1, int x1, int y1, int w1, int h1,
        int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {

      //inside the add animation method of each type of animation, we have create shape
      // that is only IF the shape doesn't exist in the model already

      //createShape method : checks if the shape exists in our shapeList/shapeLedger and then creates the shape if it doesn't exist
      //model.createShape() then model.addMove


      //1. get the shape type by searching in the shapeLedger (getTypeByName method)
      //2. createShape call and pass shape object into add___Animation method as per before.

      String shapeType = model.getTypeByName(name);
      Shape shape = model.createShape(name, shapeType, x1, y1, w1, h1, t1, t2, r1, g1, b1);
      if (x1 != x2 || y1 != y2) {
        model.addMoveAnimation(shape, shape.getTypeOfShape(), t1, t2, x2, y2);
      }
      if (r1 != r2 || g1 != g2 || b1 != b2) {
        Color newColor = new Color(r2, g2, b2);
        model.addChangeColorAnimation(shape, t1, t2, r2, g2, b2);
      }
      if (w1 != w2 || h1 != h2) {
        model.addScaleAnimation(shape, shape.getTypeOfShape(), t1, t2, w2, h2);
      }

      return this;
    }
  }
}
