package entity.objects;

import entity.Point;
import gui.Game;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * The ChestObject class represents a chest object in the game. It extends the SuperObject class and
 * initializes chest properties.
 */
public class ChestObject extends SuperObject {

  /**
   * Constructs a ChestObject object.
   *
   * @param position The position of the chest.
   * @param game The Game object associated with the chest.
   */
  public ChestObject(Point position, Game game) {
    super(position, game);
    this.name = "chest";
    try {
      this.image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
