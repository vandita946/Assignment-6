import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class implements the Model interface. It stores the shapes and animations in an animator and
 * contains the methods to manipulate and manage them.
 */
public class ModelImpl implements Model {

  //check that canvas width and height do not conflict when doing move and scale animations
  private double canvasWidth;
  private double canvasHeight;
  private Map<String, Shape> shapeList;
  private List<Animation> animationList;

  public enum TypeOfAnimation {
    MOVE, SCALE, COLOR;
  }

  public ModelImpl(double canvasWidth, double canvasHeight) {
    this.canvasWidth = canvasWidth;
    this.canvasHeight = canvasHeight;
    animationList = new ArrayList<Animation>();
  }

  public void addChangeColorAnimation(Shape shape, int startingTime, int endingTime,
      String newColorName) {
    Animation colorChange = new ChangeColor(shape, startingTime, endingTime, newColorName);
    animationList.add(colorChange);
  }

  public void addScaleAnimation(Shape shape, TypeOfShape type, int startingTime, int endingTime, double newWidth,
      double newHeight) {
    Animation scale = new Scale(shape, type, startingTime, endingTime, newWidth, newHeight, canvasWidth,
        canvasHeight);
    animationList.add(scale);
  }

  public void addMoveAnimation(Shape shape, TypeOfShape type, int startingTime, int endingTime, double toX,
      double toY) {
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

//  public void sortAnimationList() {
//    //idk how to do this yet
//    animationList.stream().sorted(a -> a.getStartingTime());
//  }

  //function to check that the starting time doesn't overlap with ending time of previous one. with like a temp or something

}
