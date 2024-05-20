package entity.effects.powerUps;

import entity.Player;
import entity.Point;
import entity.effects.Effect;
import gui.Game;

public class RollerSkatePowerUp extends Effect {

  //    private int speedIncrease = 2;

  public RollerSkatePowerUp(Point position, Game game) {
    super(position, game);
  }

  @Override
  public boolean isRollerSkatePowerUp() {
    return true;
  }

  public void applyEffect(Player player) {
    player.activateRollerSkatePowerUp();
    player.notifyAttributeChange();
  }

  @Override
  protected String getPowerUpType() {
    return "roller_skate";
  }
}
