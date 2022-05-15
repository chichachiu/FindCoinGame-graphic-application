/**
 * This class resets progress and restart the game
 *
 */
public class RestartGameButton extends Button {

  public RestartGameButton(int xPosition, int yPosition) {
    super("Restart Game", xPosition, yPosition);
  }

  /**
   * Overloads the mousePressed() method
   *
   */
  @Override public void mousePressed() {
    if (isMouseOver()) {
      System.out.println("Restart the game");
      ((TreasureHunt)processing).initGame();
    }
  }
}
