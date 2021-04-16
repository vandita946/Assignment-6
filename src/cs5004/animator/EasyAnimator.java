package cs5004.animator;

import cs5004.animator.model.Model;
import cs5004.animator.model.ModelImpl;
import cs5004.animator.model.ModelImpl.Builder;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.View;
import cs5004.animator.view.ViewFactory;
import java.io.FileReader;
import java.io.IOException;

public final class EasyAnimator {

  public static void main(String[] args) {
    Model model = new ModelImpl();
    AnimationBuilder<Model> builder = new Builder(model);

    try {
      model = AnimationReader.parseFile(new FileReader("EasyAnimatorViewStarter_v1/code/toh-3.txt"), builder);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }

    View view = ViewFactory.createView("svg", model, "output-toh3.svg",2);
    view.publish();
  }
}
