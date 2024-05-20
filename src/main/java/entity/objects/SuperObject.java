package entity.objects;

import entity.Point;
import gui.Game;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The SuperObject class represents a generic object in the game. It contains basic properties and
 * functionality common to all objects.
 */
public class SuperObject {
  public BufferedImage image;
  public String name;
  public Game game;
  public boolean collision = true;
  public Point position;
  public boolean shouldBeRemoved = false;
  public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
  public int solidAreaDefaultX = 0;
  public int solidAreaDefaultY = 0;

  /**
   * Constructs a SuperObject object.
   *
   * @param position The position of the object.
   * @param game The Game object associated with the object.
   */
  public SuperObject(Point position, Game game) {
    this.position = position;
    this.game = game;
  }

  /**
   * Gets the x-coordinate of the object.
   *
   * @return The x-coordinate.
   */
  public int getX() {
    return position.getX();
  }

  /**
   * Gets the y-coordinate of the object.
   *
   * @return The y-coordinate.
   */
  public int getY() {
    return position.getY();
  }

  /**
   * Draws the object on the screen.
   *
   * @param g2d The graphics context.
   */
  public void draw(Graphics2D g2d) {
    g2d.drawImage(image, getX(), getY(), game.tileSize, game.tileSize, null);
  }
}
