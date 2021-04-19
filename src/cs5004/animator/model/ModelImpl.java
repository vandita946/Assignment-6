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
  private final List<Shape> shapeList;
  private final Map<String, String> shapeLedger;
  private final List<Animation> animationList;
  private int ticksPerSecond;

  /**
   * Constructor to initialize the model. Creates empty animationList, shapeList and shapeLedger.
   */
  public ModelImpl() {
    //Stores the animations of the model
    animationList = new ArrayList<>();

    //Stores the shapes of the model
    shapeList = new ArrayList<>();

    //Stores the unique shape names with their corresponding types
    shapeLedger = new HashMap<>();
  }

  @Override
  public double getMilliseconds(double t) {
    return (t / ticksPerSecond) * 1000;
  }

  /**
   * Returns the x coordinate offset by the corner x value.
   *
   * @param x x value
   * @return offset x value
   */
  private int getOffsetX(int x) {
    return x - cornerX;
  }

  /**
   * Returns the y coordinate offset by the corner y value.
   *
   * @param y y value
   * @return offset y value
   */
  private int getOffsetY(int y) {
    return y- cornerY;
  }

  @Override
  public Shape createShape(String shapeName, String type, int x, int y, double width, double height,
      int startingTime, int endingTime, int r, int g, int b) {

    //Checks if the shape exists already. Will be null if it doesn't.
    Shape shape = findShape(shapeName);

    if (shape != null) {
      //Updates the disappear time of the shape with each new animation added from the builder.
      if (shape.getDisappearTime() < getMilliseconds(endingTime)) {
        shape.setDisappearTime(getMilliseconds(endingTime));
      }
      return shape;
    }

    if (type.equalsIgnoreCase("ellipse")) {
      shape = new Ellipse(getOffsetX(x), getOffsetY(y), width, height, shapeName, r, g, b,
          getMilliseconds(startingTime), getMilliseconds(endingTime));
    } else if (type.equalsIgnoreCase("rectangle")) {
      shape = new Rectangle(getOffsetX(x), getOffsetY(y), width, height, shapeName, r, g, b,
          getMilliseconds(startingTime), getMilliseconds(endingTime));
    }

    //Adds the shape to the shapeList.
    this.addShape(shape);
    return shape;
  }


  /**
   * Returns the shape with the given name.
   *
   * @param shapeName to be found
   * @return Shape with the given name.
   */
  private Shape findShape(String shapeName) {
    for (Shape s : shapeList) {
      if (s.getName().equals(shapeName)) {
        return s;
      }
    }
    return null;
  }

  /**
   * Adds a shape to the list of shapes.
   *
   * @param shape to be added
   */
  private void addShape(Shape shape) {
    if (!shapeList.contains(shape)) {
      shapeList.add(shape);
    }
  }

  @Override
  public void updateShapeLedger(String shapeName, String shapeType) {
    if (!shapeLedger.containsKey(shapeName)) {
      shapeLedger.put(shapeName, shapeType);
    }
  }

  @Override
  public void addChangeColorAnimation(Shape shape, int startingTime, int endingTime, int red,
      int green, int blue) throws IllegalArgumentException {

    if (checkLegalTime(startingTime, endingTime, TypeOfAnimation.COLOR, shape)) {
      throw new IllegalArgumentException(
          "There is an illegal time overlap with another color change animation.");
    }

    Animation colorChange = new ChangeColor(shape, getMilliseconds(startingTime),
        getMilliseconds(endingTime), red, green, blue);

    animationList.add(colorChange);
  }

  @Override
  public void addScaleAnimation(Shape shape, TypeOfShape type, int startingTime, int endingTime,
      double newWidth, double newHeight) throws IllegalArgumentException {

    if (checkLegalTime(startingTime, endingTime, TypeOfAnimation.SCALE, shape)) {
      throw new IllegalArgumentException(
          "There is an illegal time overlap with another scale animation.");
    }

    Animation scale = new Scale(shape, type, getMilliseconds(startingTime),
        getMilliseconds(endingTime), newWidth, newHeight);

    animationList.add(scale);
  }

  @Override
  public void addMoveAnimation(Shape shape, TypeOfShape type, int startingTime, int endingTime,
      int toX, int toY) throws IllegalArgumentException {

    if (checkLegalTime(getMilliseconds(startingTime), getMilliseconds(endingTime),
        TypeOfAnimation.MOVE, shape)) {
      throw new IllegalArgumentException(
          "There is an illegal time overlap with another move animation.");
    }

    Animation move = new Move(shape, getMilliseconds(startingTime), getMilliseconds(endingTime),
        getOffsetX(toX), getOffsetY(toY));

    animationList.add(move);
  }

  /**
   * This is a boolean to see if the animation being performed is within the allowed time.
   *
   * @param startingTime is the starting time of the animation.
   * @param endingTime   is the ending time of the animation.
   * @param type         is the type of animation.
   * @param shape        shape that we're checking for
   * @return a boolean.
   */
  private boolean checkLegalTime(double startingTime, double endingTime, TypeOfAnimation type,
      Shape shape) {

    for (Animation each : animationList) {
      if (type.equals(each.getType()) && shape.equals(each.getShape())) {
        if (getMilliseconds(startingTime) <= each.getStartingTime()
            && getMilliseconds(endingTime) >= each.getEndingTime()) {
          return true;
        } else if (getMilliseconds(startingTime) > each.getStartingTime()
            && getMilliseconds(endingTime) < each.getEndingTime()) {
          return true;
        } else if (getMilliseconds(startingTime) > each.getStartingTime()
            && getMilliseconds(endingTime) < each.getEndingTime()) {
          return true;
        } else if (getMilliseconds(endingTime) > each.getStartingTime()
            && getMilliseconds(endingTime) < each.getEndingTime()) {
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
    Comparator<Shape> comp = Comparator.comparingDouble(Shape::getAppearTime);
    shapeList.sort(comp);
  }

  /**
   * This is a helper function to sort our list according to starting time.
   */
  public void sortAnimationList() {
    Comparator<Animation> comp = Comparator.comparingDouble(Animation::getStartingTime);
    animationList.sort(comp);
  }

  /**
   * Carries out all the animations at the given tick.
   *
   * @param tick given tick
   */
  private void animateAtTick(double tick) {
    for (Animation a : animationList) {
      if (a.getStartingTime() <= tick && tick <= a.getEndingTime()) {
        a.actionStep(tick);
      }
    }
  }

  @Override
  public List<Shape> getShapesAtTick(double tick) {

    tick = getMilliseconds(tick);
    animateAtTick(tick);
    List<Shape> tickShapes = new ArrayList<>();

    for (Shape s : shapeList) {
      if (s.getAppearTime() <= tick && s.getDisappearTime() >= tick) {
        tickShapes.add(s);
      }
    }

    return tickShapes;
  }

  /**
   * Returns a string representation of the model.
   *
   * @return String representation of the model
   */
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

  @Override
  public List<Shape> getShapeList() {
    sortShapeList();
    return this.shapeList;
  }

  @Override
  public List<Animation> getAnimationList() {
    sortAnimationList();
    return this.animationList;
  }

  @Override
  public String getTypeByName(String shapeName) {
    return shapeLedger.get(shapeName);
  }

  @Override
  public double getCanvasWidth() {
    return this.canvasWidth;
  }

  @Override
  public double getCanvasHeight() {
    return this.canvasHeight;
  }

  @Override
  public List<Animation> getAnimationsByShape(Shape shape) {
    return animationList.stream().filter(a -> a.getShape().equals(shape)).collect(
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

  @Override
  public void setCanvasWidth(int newWidth) throws IllegalArgumentException {
    if (canvasWidth < 0) {
      throw new IllegalArgumentException("Canvas width cannot be negative.");
    }
    this.canvasWidth = newWidth;
  }

  @Override
  public void setCanvasHeight(int newHeight) {
    if (canvasHeight < 0) {
      throw new IllegalArgumentException("Canvas height cannot be negative.");
    }
    this.canvasHeight = newHeight;
  }

  @Override
  public void setCornerValues(int x, int y) {
    this.cornerX = x;
    this.cornerY = y;
  }

  public static final class Builder implements AnimationBuilder<Model> {

    private final Model model;

    /**
     * Constructs a builder object and initializes it to the given model.
     *
     * @param model Model
     */
    public Builder(Model model) {
      this.model = model;
    }

    @Override
    public Model build() {
      return model;
    }


    @Override
    public AnimationBuilder<Model> setBounds(int x, int y, int width, int height) {
      model.setCanvasWidth(width);
      model.setCanvasHeight(height);
      model.setCornerValues(x, y);
      return this;
    }


    @Override
    public AnimationBuilder<Model> declareShape(String name, String type) {
      model.updateShapeLedger(name, type);
      return this;
    }

    @Override
    public AnimationBuilder<Model> addMotion(String name, int t1, int x1, int y1, int w1, int h1,
        int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {

      //Gets the type of the shape as it is needed to add animations to the model.
      String shapeType = model.getTypeByName(name);
      Shape shape = model.createShape(name, shapeType, x1, y1, w1, h1, t1, t2, r1, g1, b1);
      if (x1 != x2 || y1 != y2) {
        model.addMoveAnimation(shape, shape.getTypeOfShape(), t1, t2, x2, y2);
      }
      if (r1 != r2 || g1 != g2 || b1 != b2) {
        model.addChangeColorAnimation(shape, t1, t2, r2, g2, b2);
      }
      if (w1 != w2 || h1 != h2) {
        model.addScaleAnimation(shape, shape.getTypeOfShape(), t1, t2, w2, h2);
      }
      return this;
    }
  }
}
