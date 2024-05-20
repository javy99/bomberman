package entity;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import gui.Game;
import java.awt.*;
import java.awt.image.BufferedImage;
import org.junit.Before;
import org.junit.Test;

public class EntityTest {
  private Game game;
  private Entity entity;
  private Graphics2D graphics;

  @Before
  public void setUp() {
    game = mock(Game.class);
    when(game.getTileSize()).thenReturn(48); // Assuming tileSize is 48 for these tests
    entity =
        new Entity(game) {
          { // Initializer block to set up required images for testing.
            up1 = new BufferedImage(48, 48, BufferedImage.TYPE_INT_ARGB);
            down1 = new BufferedImage(48, 48, BufferedImage.TYPE_INT_ARGB);
            left1 = new BufferedImage(48, 48, BufferedImage.TYPE_INT_ARGB);
            right1 = new BufferedImage(48, 48, BufferedImage.TYPE_INT_ARGB);
          }
        };
    entity.position = new Point(100, 100);
    entity.speed = 5;

    graphics = mock(Graphics2D.class);
  }

  @Test
  public void testMoveUpWithoutCollision() {
    entity.direction = "up";
    entity.collisionOn = false;
    entity.move(entity.direction);
    assertEquals(95, entity.position.y);
  }

  @Test
  public void testMoveDownWithoutCollision() {
    entity.direction = "down";
    entity.collisionOn = false;
    entity.move(entity.direction);
    assertEquals(105, entity.position.y);
  }

  @Test
  public void testMovementBlockedByCollision() {
    entity.direction = "up";
    entity.collisionOn = true;
    entity.move(entity.direction);
    assertEquals(100, entity.position.y); // Position should not change
  }

  @Test
  public void testDrawWithCorrectSprite() {
    entity.direction = "up";
    entity.spriteNum = 1;
    entity.draw(graphics);
    verify(graphics).drawImage(entity.up1, 100, 100, 48, 48, null);
  }
}
