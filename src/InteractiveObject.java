import processing.core.PImage;
import java.util.NoSuchElementException;
import java.io.File;

/**
 * This class builds interactive objects
 *
 */
public class InteractiveObject implements Clickable {
  // reference to the PApplet where this object will be drawn
  protected static TreasureHunt processing;
  private final String NAME;// name of this interactive object
  protected PImage image; // reference to the image of this object
  private int x; // x-position of this interactive object in the screen
  private int y; // y-position of this interactive object in the screen
  // Note that the position (x,y) of the interactive object is the position
  // of the upper-left corner of its image (and NOT the center of the image).
  private boolean isActive; // tells whether or not this object is active
  private boolean wasClicked; // tells whether this object was already clicked
  private String message; // message to be displayed when this object is clicked
  private InteractiveObject nextClue; // Object to be activated when this object is
  // clicked the first time, if any

  /**
   * Creates a new interactive object with no next clue (nextClue == null) and sets its image, name,
   * x and y positions, its message, and activation status.
   *
   * @param name    name to be assigned to this interactive object. We assume that name is VALID (not
   *                null and not equal to an empty string)
   * @param x       x-position to be assigned to this interactive object
   * @param y       y-position to be assigned to this interactive object
   * @param message message to be displayed on the console each time this interactive object is
   *                clicked. We assume that message is VALID (not null and not equal to an empty
   *                string)
   */
  public InteractiveObject(java.lang.String name, int x, int y, java.lang.String message) {
    this.NAME = name;
    this.x = x;
    this.y = y;
    this.message = message;
    image = processing.loadImage("images" + File.separator + name + ".png");
    isActive = true; // set the activation status,when created, an interactive object must be set to
    // be active and
    wasClicked = false;//not yet clicked
    this.nextClue = null;
  }

  /**
   * Creates a new interactive object with a next clue (nextClue != null) to be activated when this
   * interactive object is clicked for the first time.
   *
   * @param name     name to be assigned to this interactive object. We assume that name is VALID (not
   *                 null and not equal to an empty string)
   * @param x        x-position to be assigned to this interactive object
   * @param y        y-position to be assigned to this interactive object
   * @param message  message to be displayed on the console each time this interactive object is
   *                 clicked. We assume that message is VALID (not null and not equal to an empty
   *                 string)
   * @param nextClue a reference to a non-null InteractiveObject which represents the next clue
   *                 associated to this interactive object.
   */
  public InteractiveObject(java.lang.String name, int x, int y, java.lang.String message,
    InteractiveObject nextClue) {
    this(name, x, y, message);
    this.nextClue = nextClue;
    this.nextClue.isActive =
      false;//If the constructor is passed a next clue as input, that next clue must
    // be deactivated by the constructor.
    image = processing.loadImage("images" + File.separator + name + ".png");
    wasClicked = false;
  }

  /**
   * Sets the next clue associated to this interactive object
   *
   * @param nextClue the next clue to be assigned to this interactive object
   */
  public void setNextClue(InteractiveObject nextClue) {
    this.nextClue = nextClue;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public boolean isActive() {
    return isActive;
  }

  /**
   * Gets the message of this interactive object.
   *
   * @return the message to be displayed each time this interactive object is clicked.
   */
  public java.lang.String message() {
    return message;
  }

  /**
   * Draws this interactive object (meaning drawing its image) to the display window at positions x and y.
   */
  @Override public void draw() {
    processing.image(image, x, y);
  }

  @Override
  /**
   This method operates each time the mouse is pressed. This interactive object responds to the
   mouse clicks as follows. If the mouse is clicked (meaning the mouse is over it) two operations
   will be performed as follows: (1) The message of this interactive object must be displayed to the
   console. (2) If this interactive object has a next clue and was not clicked, its next clue will
   be activated and its wasClicked setting will be updated.
   */ public void mousePressed() {
    if (isMouseOver()) {
      System.out.println(message());
      if (nextClue != null && wasClicked == false) {
        this.activateNextClue();
        wasClicked = true;
      }
    }
  }

  /**
   * Activates the next clue of this interactive object and adds it to the treasure hunt application
   *
   * @throws NoSuchElementException with a descriptive error message if the nextClue of this interactive object is null
   */
  protected void activateNextClue() throws NoSuchElementException {
    if (nextClue == null) {
      throw new NoSuchElementException("no next clue");
    }
    nextClue.activate();
    processing.add(nextClue);
  }

  /**
   * Activates this interactive object, meaning setting its isActive data field to false.
   */
  public void activate() {
    this.isActive = true;
  }

  /**
   * Deactivates this interactive object, meaning setting its isActive data field to false.
   */
  public void deactivate() {
    this.isActive = false;
  }

  /**
   * Checks whether the name of this interactive object equals to the name passed as input parameter.
   * We consider a case-sensitive comparison.
   *
   * @param name name to compare to
   * @return true if the name of this interactive object equals the provided name, false otherwise.
   */
  public boolean hasName(java.lang.String name) {
    return this.NAME.equals(name);
  }

  /**
   * This method operates each time the mouse is released. It implements a default behavior for an
   * interactive object which is DO NOTHING (meaning that the InteractiveObject.mouseReleased has a blank implementation).
   */
  @Override public void mouseReleased() {
  }

  /**
   * Checks whether the mouse is over the image of this interactive object
   *
   * @return true if the mouse is over the image of this interactive object, and false otherwise
   */
  @Override public boolean isMouseOver() {
    return processing.mouseX >= getX() && processing.mouseX <= getX() + image.width
      && processing.mouseY >= getY() && processing.mouseY <= getY() + image.height;
  }

  /**
   * Sets the PApplet object of the treasure hunt application where all the interactive objects will
   * be drawn. Note that a graphic application has ONLY one display window of type PApplet where all
   * graphic objects interact. In p05, the TreasureHunt class is of type PApplet.
   *
   * @param processing represents the reference to the TreasureHunt PApplet object where all the
   *                   interactive objects will be drawn.
   */
  public static void setProcessing(TreasureHunt processing) {
    InteractiveObject.processing = processing;
  }

  /**
   * Moves the current x and y positions of this interactive object with dx and dy, respectively
   *
   * @param dx move to be added to the x position of this interactive object
   * @param dy move to be added to the y position of this interactive object
   */
  public void move(int dx, int dy) {
    this.x += dx;
    this.y += dy;
  }
}
