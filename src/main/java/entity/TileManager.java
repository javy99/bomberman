package entity;

import gui.Game;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;

/** Manages the tiles in the game world. */
public class TileManager {

  Game game;
  GameMap gameMap;
  public HashMap<String, Tile> tile;

  /**
   * Constructs a TileManager with the specified game and game map.
   *
   * @param game The game instance.
   * @param gameMap The game map containing tile information.
   */
  public TileManager(Game game, GameMap gameMap) {
    this.game = game;
    tile = new HashMap<>();
    this.gameMap = gameMap;
    getTileImages();
  }

  /** Loads tile images from resources and initializes the tile map. */
  public void getTileImages() {
    try {
      tile.put(
          "grass",
          new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png")), false));
      tile.put(
          "wall", new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png")), true));
      tile.put(
          "brick",
          new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/brick.png")), true));
      tile.put(
          "explosion",
          new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/explosion.gif")), false));

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public boolean checkMaterial(Integer x, Integer y, String material) {
    return gameMap.mapCells[y][x].equals(material);
  }

  /**
   * Checks if a tile type has collision.
   *
   * @param tileType The type of tile to check.
   * @return True if the tile has collision, false otherwise.
   */
  public boolean isTileCollision(String tileType) {
    return tile.get(tileType).collision;
  }

  /**
   * Draws the tiles on the game world.
   *
   * @param g2d The graphics context.
   */
  public void draw(Graphics2D g2d) {
    for (int row = 0; row < gameMap.mapDimensions[0]; row++) {
      for (int col = 0; col < gameMap.mapDimensions[1]; col++) {
        BufferedImage img = tile.get(gameMap.mapCells[row][col]).image;
        g2d.drawImage(
            img, col * game.tileSize, row * game.tileSize, game.tileSize, game.tileSize, null);
      }
    }
  }
}
