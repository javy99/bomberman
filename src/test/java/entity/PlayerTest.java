package entity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import gui.Game;
import handler.KeyHandler;
import java.awt.*;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {
  private Game game;
  private KeyHandler keyHandler;
  private Player player;
  private Point startPosition;
  private Graphics2D graphics;
  private CollisionChecker collisionChecker;

  @BeforeEach
  public void setUp() {
    game = mock(Game.class);
    keyHandler = mock(KeyHandler.class);
    collisionChecker = mock(CollisionChecker.class);

    when(game.getTileSize()).thenReturn(48);
    game.collisionChecker = collisionChecker;

    startPosition = new Point(100, 100);
    player = new Player(game, keyHandler, startPosition, "TestPlayer", 1);

    graphics = mock(Graphics2D.class);

    game.obj = new ArrayList<>();
  }

  @Test
  public void testInitialization() {
    assertEquals("down", player.direction);
    assertEquals(3, player.speed);
    assertEquals(startPosition, player.position);
    assertEquals("TestPlayer", player.name);
    assertEquals(1, player.playerNumber);
  }

  @Test
  public void testMoveUpWithoutCollision() {
    keyHandler.w = true;
    player.update();
    assertEquals(97, player.getPosition().getY());
  }

  @Test
  public void testMoveDownWithoutCollision() {
    keyHandler.s = true;
    player.update();
    assertEquals(-93, player.getPosition().getY());
  }

  @Test
  public void testMoveLeftWithoutCollision() {
    keyHandler.a = true;
    player.update();
    assertEquals(97, player.getPosition().getX());
  }

  @Test
  public void testMoveRightWithoutCollision() {
    keyHandler.d = true;
    player.update();
    assertEquals(-93, player.getPosition().getX());
  }

  @Test
  public void testMovementBlockedByCollision() {
    keyHandler.w = true;
    player.collisionOn = true;

    doAnswer(
            invocation -> {
              player.collisionOn = true;
              return null;
            })
        .when(collisionChecker)
        .checkTile(player);

    player.update();
    assertEquals(100, player.getPosition().getY());
  }

  @Test
  public void testCannotPlantMoreThanAllowedBombs() {
    when(game.positionOccupied(anyInt(), anyInt())).thenReturn(false);
    player.bombsNum = 1;
    player.plantBomb();
    player.plantBomb();
    assertEquals(1, game.obj.size());
  }

  @Test
  public void testDrawWithCorrectSprite() {
    player.direction = "up";
    player.spriteNum = 1;
    player.draw(graphics);
    verify(graphics).drawImage(player.up1, 100, 100, 48, 48, null);
  }

  @Test
  public void testUpdateSpriteImage() {
    player.spriteCounter = 12;
    player.spriteNum = 1;
    player.updateSpriteImage();
    assertEquals(2, player.spriteNum);
  }

  @Test
  public void testInteractWithMonster() {
    player.interactWithMonster(0);
    assertTrue(player.shouldBeRemoved);
  }
}
