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
 * This class implements the cs5004.easyanimator.model.Model interface. It stores the shapes and animations in an animator and
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
    animationList = new ArrayList<Animation>();
    shapeList = new ArrayList<Shape>();
  }

  public void addShape(Shape shape) {
    if (shape == null) {
      throw new IllegalArgumentException("cs5004.easyanimator.shape.Shape cannot be null.");
    }
    if (!shapeList.contains(shape)) {
      shapeList.add(shape);
    } else {
      throw new IllegalArgumentException("cs5004.easyanimator.shape.Shape already exists in the model.");
    }
  }

  public void addChangeColorAnimation(Shape shape, int startingTime, int endingTime,
      Color newColor) {
    this.addShape(shape);
    Animation colorChange = new ChangeColor(shape, startingTime, endingTime, newColor);
    animationList.add(colorChange);
  }

  public void addScaleAnimation(Shape shape, TypeOfShape type, int startingTime, int endingTime, double newWidth,
      double newHeight) {
    this.addShape(shape);
    Animation scale = new Scale(shape, type, startingTime, endingTime, newWidth, newHeight);
    animationList.add(scale);
  }

  public void addMoveAnimation(Shape shape, TypeOfShape type, int startingTime, int endingTime, double toX,
      double toY) {
    this.addShape(shape);
    Animation move = new Move(shape, type, startingTime, endingTime, toX, toY, canvasWidth, canvasHeight);
    animationList.add(move);
  }

  private boolean checkLegalTime(int startingTime, int endingTime, TypeOfAnimation type) {
    for (Animation each : animationList) {
      if (type.equals(each.getType())) {
        if (startingTime >= each.getStartingTime() && endingTime <= each.getEndingTime()) {
          return false;
        }
      }
    }
    return true;
  }

  public void sortAnimationList() {
    Comparator<Animation> comp = new Comparator<Animation>() {
      @Override
      public int compare(Animation o1, Animation o2) {
        return o1.getStartingTime() - o2.getStartingTime();
      }
    };
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
