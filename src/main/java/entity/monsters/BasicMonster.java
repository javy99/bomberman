package entity.monsters;

import entity.Point;
import gui.Game;
import java.util.Random;

/**
 * The BasicMonster class represents a basic type of monster in the game. It extends the Monster
 * class and implements its behavior.
 */
public class BasicMonster extends Monster {

  /**
   * Constructs a BasicMonster object.
   *
   * @param gp The Game object associated with the monster.
   * @param id The ID of the monster.
   * @param position The initial position of the monster.
   */
  public BasicMonster(Game gp, int id, Point position) {
    super(gp, id, position);
    speed = 2;
  }

  /**
   * Gets the type of the monster.
   *
   * @return The type of the monster.
   */
  @Override
  protected String getMonsterType() {
    return "basic_monster";
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

    if (actionLockCounter == 60) {
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
      actionLockCounter = 0;
    }

    collisionOn = false;
    gp.collisionChecker.checkTile(this);
    gp.collisionChecker.checkObject(this);
    gp.collisionChecker.checkEntityToEntity(this);
    gp.collisionChecker.checkMonsterToPlayer(this);

    if (!collisionOn) {
      move();
    }
    updateSpriteImage();
  }
}
