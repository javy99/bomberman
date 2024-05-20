package entity.effects.powerUps;

import entity.Player;
import entity.Point;
import entity.effects.Effect;
import gui.Game;

public class BlastRangePowerUp extends Effect {

  public BlastRangePowerUp(Point position, Game game) {
    super(position, game);
    this.blastRangeChange = 1;
  }

  @Override
  public boolean isBlastRangePowerUp() {
    return true;
  }

  @Override
  public void applyEffect(Player player) {
    player.blastRange += 1;
    player.notifyAttributeChange();
  }

  @Override
  protected String getPowerUpType() {
    return "blast_range";
  }
}
