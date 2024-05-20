package entity.effects.powerUps;

import entity.Player;
import entity.Point;
import entity.effects.Effect;
import gui.Game;

public class GhostPowerUp extends Effect {
  public int ghostModeDuration = 15;

  public GhostPowerUp(Point position, Game game) {
    super(position, game);
  }

  @Override
  public boolean isGhostPowerUp() {
    return true;
  }

  @Override
  public void applyEffect(Player player) {
    player.ghostDuration = ghostModeDuration;
    player.activateGhostPowerUp(player.ghostDuration);
  }

  @Override
  protected String getPowerUpType() {
    return "ghost_mode";
  }
}
