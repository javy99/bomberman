package entity.objects;

import entity.Player;
import entity.Point;
import gui.Game;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * The BombObject class represents a bomb object in the game. It extends the SuperObject class and
 * initializes bomb properties.
 */
public class BombObject extends SuperObject {

  public String owner;
  public Long blowTime = System.currentTimeMillis() + 3000;
  public Long timeToLeave = System.currentTimeMillis() + 1000;

  public int blowRadius = 1;

  /**
   * Constructs a BombObject object.
   *
   * @param position The position of the bomb.
   * @param game The Game object associated with the bomb.
   * @param owner The owner of the bomb.
   * @param blowRadius The blow-up radius of the bomb
   */
  public BombObject(Point position, Game game, Player owner, int blowRadius) {
    super(position, game);
    this.name = "bomb";
    this.owner = owner.name;
    this.blowRadius = blowRadius;
    try {
      this.image = ImageIO.read(getClass().getResourceAsStream("/objects/bomb.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void detonate() {
    game.blowEntities(this); // Handles the explosion effects
    shouldBeRemoved = true; // Flag this bomb to be removed from game objects
  }
}
