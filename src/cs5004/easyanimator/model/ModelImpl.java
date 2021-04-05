package cs5004.easyanimator.model;

import cs5004.easyanimator.animation.Animation;
import cs5004.easyanimator.animation.ChangeColor;
import cs5004.easyanimator.animation.Move;
import cs5004.easyanimator.animation.Scale;
import cs5004.easyanimator.animation.TypeOfAnimation;
import cs5004.easyanimator.shape.Shape;
import cs5004.easyanimator.shape.TypeOfShape;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * This class implements the Model interface. It stores the shapes and animations in an animator and
 * contains the methods to manipulate and manage them.
 */
public class ModelImpl implements Model {

  private double canvasWidth;
  private double canvasHeight;
  private List<Shape> shapeList;
  private List<Animation> animationList;


  public ModelImpl(double canvasWidth, double canvasHeight) {
    this.canvasWidth = canvasWidth;
    this.canvasHeight = canvasHeight;
    animationList = new ArrayList<>();
    shapeList = new ArrayList<>();
  }

  /**
   * This function is used to add shape to the canvas.
   * @param shape is the shape to be added.
   */
  private void addShape(Shape shape) {
    if (shape == null) {
      throw new IllegalArgumentException("Shape cannot be null.");
    }
    if (!shapeList.contains(shape)) {
      shapeList.add(shape);
    }
  }

  public void addChangeColorAnimation(Shape shape, int startingTime, int endingTime,
      Color newColor) throws IllegalArgumentException {
    if (checkLegalTime(startingTime, endingTime, TypeOfAnimation.COLOR)) {
      throw new IllegalArgumentException("There is an illegal time overlap with another color change animation.");
    }

    this.addShape(shape);
    Animation colorChange = new ChangeColor(shape, startingTime, endingTime, newColor);
    animationList.add(colorChange);
  }

  public void addScaleAnimation(Shape shape, TypeOfShape type, int startingTime, int endingTime, double newWidth,
      double newHeight) throws IllegalArgumentException {
    if (checkLegalTime(startingTime, endingTime, TypeOfAnimation.SCALE)) {
      throw new IllegalArgumentException("There is an illegal time overlap with another scale animation.");
    }
    this.addShape(shape);
    Animation scale = new Scale(shape, type, startingTime, endingTime, newWidth, newHeight);
    animationList.add(scale);
  }

  public void addMoveAnimation(Shape shape, TypeOfShape type, int startingTime, int endingTime, double toX,
      double toY) throws IllegalArgumentException {
    if (checkLegalTime(startingTime, endingTime, TypeOfAnimation.MOVE)) {
      throw new IllegalArgumentException("There is an illegal time overlap with another move animation.");
    }
    this.addShape(shape);
    Animation move = new Move(shape, type, startingTime, endingTime, toX, toY, canvasWidth, canvasHeight);
    animationList.add(move);
  }

  private boolean checkLegalTime(int startingTime, int endingTime, TypeOfAnimation type) {
    for (Animation each : animationList) {
      if (type.equals(each.getType())) {
        if (startingTime <= each.getStartingTime() && endingTime >= each.getEndingTime()) {
          return true;
        } else if (startingTime > each.getStartingTime() && endingTime < each.getEndingTime()) {
          return true;
        } else if (startingTime > each.getStartingTime() && startingTime < each.getEndingTime()) {
          return true;
        } else if (endingTime > each.getStartingTime() && endingTime < each.getEndingTime()) {
          return true;
        }
      }
    }
    return false;
  }

  private void sortShapeList() {
    Comparator<Shape> comp = Comparator.comparingInt(Shape::getAppearTime);
    shapeList.sort(comp);
  }

  public void sortAnimationList() {
    Comparator<Animation> comp = Comparator.comparingInt(Animation::getStartingTime);
    animationList.sort(comp);
  }

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

}
