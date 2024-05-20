package entity.effects.powerUps;

import entity.Player;
import entity.Point;
import entity.effects.Effect;
import gui.Game;

public class BombSlotIncreasePowerUp extends Effect {

  private int bombSlotIncrease = 1;

  public BombSlotIncreasePowerUp(Point position, Game game) {
    super(position, game);
  }

  @Override
  public boolean isBombSlotIncreasePowerUp() {
    return true;
  }

  public void applyEffect(Player player) {
    player.bombsNum += bombSlotIncrease;
    player.notifyAttributeChange();
  }

  @Override
  protected String getPowerUpType() {
    return "bomb_slot";
  }
}
