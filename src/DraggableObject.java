/**
 * This class builds draggable objects
 *
 */
public class DraggableObject extends InteractiveObject {
  private boolean isDragging;//indicates whether this object is being dragged or not
  private int oldMouseX;//old x-position of the mouse
  private int oldMouseY;//old y-position of the mouse

  /**
   * Creates a new Draggable object with a given name, x and y position, and a specific message.
   * When created a new draggable object is NOT dragging.
   *
   * @param name    name to be assigned to this draggable object
   * @param x       x-position of this draggable object in the display window
   * @param y       y-position of this draggable object in the display window
   * @param message message to be displayed when this draggable object is clicked. We assume that
   *                message is VALID (meaning it is NOT null and NOT an empty string).
   */
  public DraggableObject(String name, int x, int y, String message) {
    super(name, x, y, message);
  }

  /**
   * Creates a new Draggable object with a given name, x and y position, and "Drag Me!" as a default
   * message. When created a new draggable object is NOT dragging.
   *
   * @param name name to be assigned to this draggable object
   * @param x    x-position of this draggable object in the display window
   * @param y    y-position of this draggable object in the display window
   */
  public DraggableObject(String name, int x, int y) {
    super(name, x, y, "Drag Me!");
  }

  /**
   * Checks whether this draggable object is being dragged.
   *
   * @return true if this object is being dragged, false otherwise
   */
  public boolean isDragging() {
    return isDragging;
  }

  /**
   * Starts dragging this draggable object and updates the oldMouseX and oldMouseY positions to the
   * current position of the mouse.
   */
  public void startDragging() {
    isDragging = true;
    this.oldMouseX = processing.mouseX;
    this.oldMouseY = processing.mouseY;
  }

  /**
   * Stops dragging this draggable object
   */
  public void stopDragging() {
    this.isDragging = false;
  }

  /**
   * Draws this draggable object to the display window. If this object is dragging, this method sets
   * its position to follow the mouse moves. Then, it draws its image to the its current position.
   */
  @Override
  public void draw() {
    if (isDragging) {
      super.move(processing.mouseX - oldMouseX, processing.mouseY - oldMouseY);
      startDragging();
    }
    processing.image(image, super.getX(), super.getY());
  }

  /**
   * Starts dragging this object when it is clicked (meaning when the mouse is over it).
   */
  @Override
  public void mousePressed() {
    if (isMouseOver() == true) {
      isDragging = true;
      startDragging();
    }
  }

  /**
   * Stops dragging this object if the mouse is released
   */
  @Override
  public void mouseReleased() {
    stopDragging();
  }
}
