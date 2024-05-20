package entity.objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import entity.Player;
import entity.Point;
import gui.Game;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ObjectTest {
  private Game game;
  private Graphics2D graphics;
  private Point position;
  private Player player;

  @BeforeEach
  public void setUp() {
    game = mock(Game.class);
    when(game.getTileSize()).thenReturn(48);
    graphics = mock(Graphics2D.class);
    position = new Point(100, 100);
    player = mock(Player.class);
    when(player.getName()).thenReturn("TestOwner");
  }

  @Test
  public void testBombObjectInitialization() {
    BombObject bomb = new BombObject(position, game, player, 1);
    assertEquals("bomb", bomb.name);
    assertNotNull(bomb.image);
    assertTrue(bomb.blowTime > System.currentTimeMillis());
    assertTrue(bomb.timeToLeave > System.currentTimeMillis());
  }

  @Test
  public void testChestObjectInitialization() {
    ChestObject chest = new ChestObject(position, game);
    assertEquals("chest", chest.name);
    assertNotNull(chest.image);
  }

  @Test
  public void testSuperObjectDraw() {
    SuperObject obj = new BombObject(position, game, player, 1);
    obj.image = new BufferedImage(48, 48, BufferedImage.TYPE_INT_ARGB);
    obj.draw(graphics);
    verify(graphics).drawImage(obj.image, 100, 100, 48, 48, null);
  }

  @Test
  public void testBombObjectImageLoading() throws IOException {
    BombObject bomb = new BombObject(position, game, player, 1);
    BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/objects/bomb.png"));
    assertNotNull(image);
    assertNotNull(bomb.image);
  }

  @Test
  public void testChestObjectImageLoading() throws IOException {
    ChestObject chest = new ChestObject(position, game);
    BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
    assertNotNull(image);
    assertNotNull(chest.image);
  }
}
