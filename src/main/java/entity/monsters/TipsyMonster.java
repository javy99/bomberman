package entity.monsters;

import entity.Player;
import entity.Point;
import entity.objects.BombObject;
import entity.objects.SuperObject;
import gui.Game;
import java.util.List;
import java.util.Random;

/**
 * The TipsyMonster class represents a monster that sometimes makes random moves instead of chasing
 * players directly. It extends the Monster class and implements its behavior.
 */
public class TipsyMonster extends Monster {
  private final Game game;
  private final List<Player> players;
  private final Random random;

  /**
   * Constructs a TipsyMonster object.
   *
   * @param gp The Game object associated with the monster.
   * @param players The list of players in the game.
   * @param id The ID of the monster.
   * @param position The initial position of the monster.
   */
  public TipsyMonster(Game gp, List<Player> players, int id, Point position) {
    super(gp, id, position);
    this.game = gp;
    this.players = players;
    speed = 2;
    random = new Random();
  }

  /**
   * Gets the type of the monster.
   *
   * @return The type of the monster.
   */
  @Override
  protected String getMonsterType() {
    return "tipsy_monster";
  }

  /** Updates the monster's behavior. */
  @Override
  public void update() {
    setAction();
  }

  /** Sets the action of the monster. */
  @Override
  public void setAction() {
    actionLockCounter++;
    if (actionLockCounter == 60 || collisionOn) {
      if (!isNearBomb()) {
        Point closestPlayerPosition = findClosestPlayer();
        boolean movedTowardsPlayer = false;

        if (closestPlayerPosition != null) {
          Point nextMove = calculateNextMoveTowardsPlayer(closestPlayerPosition);

          if (random.nextInt(100) < 20) { // 20% chance to make a wrong move
            nextMove = calculateRandomMove(); // Choose a random direction instead
          }

          adjustDirectionBasedOnNextMove(nextMove);
          collisionOn = false;
          gp.collisionChecker.checkTile(this);
          gp.collisionChecker.checkEntityToEntity(this);
          gp.collisionChecker.checkMonsterToPlayer(this);

          if (!collisionOn) {
            movedTowardsPlayer = true;
          }
        }

        if (!movedTowardsPlayer) {
          adjustDirectionBasedOnNextMove(calculateRandomMove());
        }
        actionLockCounter = 0;
      }
    }
    collisionOn = false;
    gp.collisionChecker.checkTile(this);
    gp.collisionChecker.checkObject(this); // This  was removed in master version during the merge
    gp.collisionChecker.checkEntityToEntity(this);
    gp.collisionChecker.checkMonsterToPlayer(this);

    if (!collisionOn) {
      move();
    }
    updateSpriteImage();
  }

  /**
   * Checks if the monster is near a bomb object.
   *
   * @return true if the monster is near a bomb object, otherwise false.
   */
  private boolean isNearBomb() {
    for (SuperObject obj : game.getObjects()) {
      if (obj instanceof BombObject) {
        BombObject bomb = (BombObject) obj;
        if (position.distance(bomb.position) <= bomb.blowRadius * game.tileSize) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Calculates the next move towards the closest player.
   *
   * @param playerPosition The position of the closest player.
   * @return The next move towards the closest player.
   */
  private Point calculateNextMoveTowardsPlayer(Point playerPosition) {
    int diffX = playerPosition.getX() - position.getX();
    int diffY = playerPosition.getY() - position.getY();

    int moveX = 0, moveY = 0;

    if (diffX > 0) moveX = speed;
    else if (diffX < 0) moveX = -speed;

    if (diffY > 0) moveY = speed;
    else if (diffY < 0) moveY = -speed;

    if (Math.abs(diffX) > Math.abs(diffY)) {
      return new Point(position.getX() + moveX, position.getY());
    } else {
      // Otherwise, prioritize vertical movement
      return new Point(position.getX(), position.getY() + moveY);
    }
  }

  /**
   * Calculates a random move.
   *
   * @return A random move.
   */
  private Point calculateRandomMove() {
    int i = random.nextInt(4) + 1;
    switch (i) {
      case 1:
        return new Point(position.getX(), position.getY() - speed); // Move up
      case 2:
        return new Point(position.getX(), position.getY() + speed); // Move down
      case 3:
        return new Point(position.getX() - speed, position.getY()); // Move left
      default:
        return new Point(position.getX() + speed, position.getY()); // Move right
    }
  }

  /**
   * Adjusts the direction based on the next move.
   *
   * @param nextMove The next move to adjust the direction towards.
   */
  private void adjustDirectionBasedOnNextMove(Point nextMove) {
    int diffX = nextMove.getX() - position.getX();
    int diffY = nextMove.getY() - position.getY();

    if (Math.abs(diffX) > Math.abs(diffY)) {
      direction = diffX > 0 ? "right" : "left";
    } else if (Math.abs(diffY) > 0) {
      direction = diffY > 0 ? "down" : "up";
    }
  }

  /**
   * Finds the closest player to the monster.
   *
   * @return The position of the closest player.
   */
  private Point findClosestPlayer() {
    double minDistance = Double.MAX_VALUE;
    Point closestPlayerPosition = null;
    for (Player player : players) {
      Point playerPosition = player.position;
      double distance = this.position.distance(playerPosition);
      if (distance < minDistance) {
        minDistance = distance;
        closestPlayerPosition = playerPosition;
      }
    }
    return closestPlayerPosition;
  }
}
