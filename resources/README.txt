Assignment 8 Design:

Under the main animator package, there are four sub-packages for
animation, model, shape, util, controller, and view, along with the main class EasyAnimator.

shape package contains:
- Shape interface (Downloaded from canvas)
- AbstractShape class (Downloaded from canvas)
- Ellipse class (Downloaded from canvas) - changed name to Ellipse to conform to assignment.
- Rectangle class (Downloaded from canvas)
- Point2D class (Downloaded from canvas)
- *REMOVED* ColorNames enum - did not require this in the end, used RGB values instead.
- TypeOfShape enum - we use this instead of instanceof for certain methods
  that require information on what type the Shape is.

animation package contains:
- Animation interface - outlines all the methods required for a shape to become
  animated
- AbstractAnimation class - implements the Animation interface and its methods
- ChangeColor class - extends the AbstractAnimation class and contains methods
  needed to make a shape change color in time
- Move class - extends the AbstractAnimation class and contains methods needed
  to make a shape move in time
- Scale class - extends the AbstractAnimation class and contains methods
  needed to make a shape move in time
- TypeOfAnimation enum - we use this instead of instanceof for certain methods
  that require information on what type the Animation is.

model package contains:
- Model interface - outlines all the methods required to store and handle
  shapes and their respective animations
- ModelImpl class - implements the Model interface and its methods
- Builder class - implements AnimationBuilder, a static final class placed within
  the ModelImpl as described on canvas

util package contains:
- AnimationBuilder interface - downloaded from canvas
- AnimationReader class - downloaded from canvas

view package contains:
- View interface - outlines methods needed in each of the implementing view classes
- ViewFactory class - generates the appropriate view depending on the view specified by the user
- SVGView class - implements the view interface and has methods needed to output an SVG
- TextualView class - implements the view interface and has methods needed to output text
- VisualPanel class - extends JPanel and has the methods needed to update the frame and the
  shapes according to the animations
- VisualView class - implements the view interface and extends JFrame. Has the methods
  needed to set up the visual view frame and change its frames over time.
- *NEW* PlaybackView class - implements the view interface and contains the methods
  needed to set up a version of the visual view, enhanced with controls like start,
  resume, pause, change speed, etc.

*NEW*
controller package contains:
- Controller interface - outlines method needed in the implementing ControllerImpl class.
- ControllerImpl class - contains the method required to publish the view to the user.

EasyAnimator class:
- contains the main method that takes in command line arguments from the user and parses them.


