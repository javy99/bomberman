package entity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import entity.monsters.BasicMonster;
import entity.monsters.ChasingMonster;
import entity.monsters.GhostMonster;
import entity.monsters.Monster;
import gui.Game;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MonsterTest {
  Game mockGame;
  Point startPosition;
  Monster monster;

  @BeforeEach
  public void setUp() {
    mockGame = mock(Game.class);
    // Mock method
    when(mockGame.getTileSize()).thenReturn(48);

    startPosition = new Point(5, 5);
    monster = new BasicMonster(mockGame, 1, startPosition);
  }

  @Test
  public void testMonsterInitialization() {
    assertEquals(5, monster.getX());
    assertEquals(5, monster.getY());
  }

  @Test
  public void testMonsterMovement() {
    monster.direction = "right";
    monster.speed = 2;
    monster.move();
    assertEquals(7, monster.getX(), "Monster should move right by 2 tiles");

    monster.direction = "down";
    monster.move();
    assertEquals(7, monster.getY(), "Monster should move down by 2 tiles");
  }

  @Test
  public void testDirectionChange() {
    monster.direction = "up";
    monster.move();
    assertEquals(3, monster.getY(), "Monster should move up");

    monster.direction = "left";
    monster.move();
    assertEquals(3, monster.getX(), "Monster should move left");
  }

  @Test
  public void testCollisionHandling() {
    // Simulating a collision scenario
    monster.collisionOn = true;
    int initialX = monster.getX();
    int initialY = monster.getY();

    // Directly setting the direction
    monster.direction = "right";
    monster.move();

    assertEquals(initialX, monster.getX(), "Monster should not move on X axis after collision");
    assertEquals(initialY, monster.getY(), "Monster should not move on Y axis after collision");
  }

  // Tests specific to types of monsters could also be implemented
  @Test
  public void testGhostMonsterBehavior() {
    Monster ghostMonster = new GhostMonster(mockGame, 2, new Point(5, 5));
    ghostMonster.direction = "up";
    ghostMonster.speed = 1;
    ghostMonster.move();
    assertEquals(4, ghostMonster.getY(), "Ghost monster should move up by 1 tile");
  }

  @Test
  public void testChasingMonsterBehavior() {
    // Setup a player for chasing monster to target
    Player mockPlayer = mock(Player.class);
    when(mockPlayer.getPosition()).thenReturn(new Point(10, 10));

    ChasingMonster chasingMonster =
        new ChasingMonster(mockGame, List.of(mockPlayer), 3, new Point(5, 5));
    chasingMonster.direction = "down";
    chasingMonster.speed = 3;
    chasingMonster.move();
    assertEquals(8, chasingMonster.getY(), "Chasing monster should move down towards player");
  }

  // More detailed tests could include mocking interactions with game environments,
  // checking sprite updates, etc.
}
