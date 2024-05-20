package entity.effects.powerUps;

import entity.Player;
import entity.Point;
import entity.effects.Effect;
import gui.Game;

public class ObstaclePowerUp extends Effect {

  public ObstaclePowerUp(Point position, Game game) {
    super(position, game);
  }

  @Override
  public boolean isObstaclePowerUp() {
    return true;
  }

  public void applyEffect(Player player) {
    player.canPutObstacles = true;
  }

  @Override
  protected String getPowerUpType() {
    return "obstacle";
  }
}
