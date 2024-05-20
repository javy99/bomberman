package entity;

import gui.Game;
import java.awt.*;
import java.awt.image.BufferedImage;

/** The Entity class represents a game entity, which can be a player or a monster. */
public class Entity {

  protected Game gp;
  public Point position;
  public int speed;
  public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
  public String direction;
  public int spriteCounter = 0;
  public int spriteNum = 1;
  public boolean collisionOn = false;
  public int actionLockCounter = 0;
  public final int solidAreaDefaultX = 10;
  public final int solidAreaDefaultY = 20;

  // 48 as the tile size
  private final int width = 48 - 2 * solidAreaDefaultX;
  private final int height = 48 - solidAreaDefaultY - 1;
  public Rectangle solidArea = new Rectangle(solidAreaDefaultX, solidAreaDefaultY, width, height);
  public boolean shouldBeRemoved = false;

  /**
   * Gets the x-coordinate of the entity.
   *
   * @return The x-coordinate of the entity.
   */
  public int getX() {
    return position.getX();
  }

  /**
   * Gets the y-coordinate of the entity.
   *
   * @return The y-coordinate of the entity.
   */
  public int getY() {
    return position.getY();
  }

  /**
   * Constructs an Entity object.
   *
   * @param gp The Game object associated with the entity.
   */
  public Entity(Game gp) {
    this.gp = gp;
  }

  /** Sets the action of the entity. This method should be overridden by subclasses. */
  public void setAction() {}

  /** Updates the entity. This method should be overridden by subclasses. */
  public void update() {
    setAction();
  }

  /**
   * Draws the entity on the screen.
   *
   * @param g2d The graphics context used for drawing.
   */
  public void draw(Graphics2D g2d) {
    BufferedImage img = null;
    switch (direction) {
      case "up":
        img = (spriteNum == 1) ? up1 : up2;
        break;
      case "down":
        img = (spriteNum == 1) ? down1 : down2;
        break;
      case "left":
        img = (spriteNum == 1) ? left1 : left2;
        break;
      case "right":
        img = (spriteNum == 1) ? right1 : right2;
        break;
    }
    g2d.drawImage(img, getX(), getY(), gp.tileSize, gp.tileSize, null);
    g2d.drawRect(solidArea.x + getX(), solidArea.y + getY(), solidArea.width, solidArea.height);
  }

  /**
   * Moves the entity in the specified direction.
   *
   * @param direction The direction in which to move the entity.
   */
  protected void move(String direction) {
    if (!collisionOn) {
      switch (direction) {
        case "up":
          position.y -= speed;
          break;
        case "down":
          position.y += speed;
          break;
        case "left":
          position.x -= speed;
          break;
        case "right":
          position.x += speed;
          break;
      }
    }
  }
}
