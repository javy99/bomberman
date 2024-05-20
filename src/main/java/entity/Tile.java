package entity;

import java.awt.image.BufferedImage;

/** Represents a tile in the game world. */
public class Tile {
  public BufferedImage image;
  public boolean collision = false;

  /**
   * Constructs a new tile with the specified image and collision status.
   *
   * @param image The image representing the tile.
   * @param collision Indicates if the tile is collidable or not.
   */
  public Tile(BufferedImage image, boolean collision) {
    this.image = image;
    this.collision = collision;
  }
}
