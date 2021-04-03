import java.util.List;
import java.util.Map;

/**
 * This class implements the Model interface. It stores the shapes and animations in an animator and
 * contains the methods to manipulate and manage them.
 */
public class ModelImpl implements Model {

  private double canvasWidth;
  private double canvasHeight;
  private Map<String, Shape> shapeList;
  private List<Animation> animationList;

  public ModelImpl(double canvasWidth, double canvasHeight) {
    this.canvasWidth = canvasWidth;
    this.canvasHeight = canvasHeight;
  }

  public void addChangeColorAnimation(Shape shape, int startingTime, int endingTime,
      String newColorName) {
    Animation colorChange = new ChangeColor(shape, startingTime, endingTime, newColorName);
    animationList.add(colorChange);
  }

  public void addScaleAnimation(Shape shape, int startingTime, int endingTime, double factor) {
    Animation scale = new Scale(shape, startingTime, endingTime, factor);
    animationList.add(scale);
  }

  public void addScaleAnimation(Shape shape, int startingTime, int endingTime, double newWidth,
      double newHeight) {
    Animation scale = new Scale(shape, startingTime, endingTime, newWidth, newHeight);
    animationList.add(scale);
  }

  public void addMoveAnimation(Shape shape, int startingTime, int endingTime, double toX,
      double toY) {
    Animation move = new Move(shape, startingTime, endingTime, toX, toY);
    animationList.add(move);
  }

//  private boolean checkLegalAnimation(int startingTime, int endingTime) {
//
//  }
//
//  public void sortAnimationList() {
//    //idk how to do this yet
//    animationList.stream().sorted(a -> a.getStartingTime());
//  }

}
