import processing.core.PApplet;

/**
 * This class builds buttons to take screen shot
 *
 */
public class ScreenshotButton extends Button {

  /**
   * Capture a screenshot of progress in the game
   */
  public ScreenshotButton(int xPosition, int yPosition) {
    super("Take a screenshot", xPosition, yPosition);
  }

  /**
   * Each time the ScreenshotButton is pressed, the current state of the game should be saved
   * in an image with the filename screenshot.png.
   */
  @Override public void mousePressed() {
    processing.save("screenshot.png");
  }
}
