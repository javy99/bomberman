package entity.objects;

import entity.Player;
import entity.Point;
import gui.Game;
import javax.swing.*;

public class ExplosionObject {

  public Point position;
  public Game game;
  public Player player;
  public ImageIcon image;

  public ExplosionObject(Point position, Game game) {
    this.position = position;
    this.game = game;
    try {
      this.image = new ImageIcon(getClass().getResource("/tiles/explosion.gif"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
