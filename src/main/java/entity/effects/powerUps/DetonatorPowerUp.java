package entity.effects.powerUps;

import entity.Player;
import entity.Point;
import entity.effects.Effect;
import gui.Game;

public class DetonatorPowerUp extends Effect {

  public DetonatorPowerUp(Point position, Game game) {
    super(position, game);
  }

  @Override
  public boolean isDetonatorPowerUp() {
    return true;
  }

  @Override
  public void applyEffect(Player player) {
    player.hasDetonator = true;
  }

  @Override
  protected String getPowerUpType() {
    return "detonator";
  }
}
