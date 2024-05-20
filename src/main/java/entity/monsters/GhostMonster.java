package entity.monsters;

import entity.Point;
import gui.Game;
import java.util.Random;

/**
 * The GhostMonster class represents a ghost type of monster in the game. It extends the Monster
 * class and implements its behavior.
 */
public class GhostMonster extends Monster {
  private final Game game;

  /**
   * Constructs a GhostMonster object.
   *
   * @param gp The Game object associated with the monster.
   * @param id The ID of the monster.
   * @param position The initial position of the monster.
   */
  public GhostMonster(Game gp, int id, Point position) {
    super(gp, id, position);
    this.game = gp;
    speed = 1;
  }

  /**
   * Gets the type of the monster.
   *
   * @return The type of the monster.
   */
  @Override
  protected String getMonsterType() {
    return "ghost_monster";
  }

  /** Updates the monster's behavior. */
  @Override
  public void update() {
    setAction();
  }

  /** Sets the action of the monster. */
  @Override
  public void setAction() {
    if (shouldChangeDirection()) {
      Random random = new Random();
      int i = random.nextInt(4) + 1;
      switch (i) {
        case 1:
          direction = "up";
          break;
        case 2:
          direction = "down";
          break;
        case 3:
          direction = "left";
          break;
        case 4:
          direction = "right";
          break;
      }
    }

    gp.collisionChecker.checkMonsterToPlayer(this);
    adjustDirectionAtMapEdges();
    move();
    updateSpriteImage();
  }

  /** Adjusts the direction of the monster when it reaches map edges. */
  private void adjustDirectionAtMapEdges() {
    int mapWidth = game.screenWidth;
    int mapHeight = game.screenHeight;
    int tileSize = game.tileSize;

    // Right edge
    if (position.getX() >= mapWidth - 2 * tileSize && direction.equals("right")) {
      direction = "left";
    }
    // Left edge
    else if (position.getX() <= tileSize && direction.equals("left")) {
      direction = "right";
    }
    // Bottom edge
    if (position.getY() >= mapHeight - 2 * tileSize && direction.equals("down")) {
      direction = "up";
    }
    // Top edge
    else if (position.getY() <= tileSize && direction.equals("up")) {
      direction = "down";
    }
  }

  /**
   * Determines if the monster should change its direction randomly.
   *
   * @return true if the monster should change its direction, otherwise false.
   */
  private boolean shouldChangeDirection() {
    Random random = new Random();
    return random.nextInt(100) < 5;
  }
}
