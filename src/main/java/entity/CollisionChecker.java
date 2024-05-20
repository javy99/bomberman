package entity;

import entity.effects.Effect;
import entity.monsters.Monster;
import entity.objects.BombObject;
import entity.objects.BrickObject;
import entity.objects.SuperObject;
import gui.Game;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The CollisionChecker class handles collision detection between entities and objects in the game.
 * It provides methods to check collisions with tiles, objects, and other entities.
 */
public class CollisionChecker {

  Game game;

  /**
   * Constructs a CollisionChecker object.
   *
   * @param game The Game object associated with the collision checker.
   */
  public CollisionChecker(Game game) {
    this.game = game;
  }

  /**
   * Checks collision with tiles for a given entity.
   *
   * @param entity The entity to check collision for.
   */
  public void checkTile(Entity entity) {
    int entityLeftX = entity.getX() + entity.solidArea.x;
    int entityRightX = entityLeftX + entity.solidArea.width;
    int entityTopY = entity.getY() + entity.solidArea.y;
    int entityBottomY = entityTopY + entity.solidArea.height;

    int leftCol = entityLeftX / game.tileSize;
    int rightCol = entityRightX / game.tileSize;
    int topRow = entityTopY / game.tileSize;
    int bottomRow = entityBottomY / game.tileSize;

    String tile1 = null, tile2 = null;

    switch (entity.direction) {
      case "up":
        topRow = (entityTopY - entity.speed) / game.tileSize;
        tile1 = game.gameMap.mapCells[topRow][leftCol];
        tile2 = game.gameMap.mapCells[topRow][rightCol];
        break;
      case "down":
        bottomRow = (entityBottomY + entity.speed) / game.tileSize;
        tile1 = game.gameMap.mapCells[bottomRow][leftCol];
        tile2 = game.gameMap.mapCells[bottomRow][rightCol];
        break;
      case "left":
        leftCol = (entityLeftX - entity.speed) / game.tileSize;
        tile1 = game.gameMap.mapCells[topRow][leftCol];
        tile2 = game.gameMap.mapCells[bottomRow][leftCol];
        break;
      case "right":
        rightCol = (entityRightX + entity.speed) / game.tileSize;
        tile1 = game.gameMap.mapCells[topRow][rightCol];
        tile2 = game.gameMap.mapCells[bottomRow][rightCol];
        break;
    }
    checkCollision(tile1, tile2, entity);
  }

  /**
   * Checks collision with objects for a given entity.
   *
   * @param entity The entity to check collision for.
   * @return The index of the collided object, or 999 if no collision occurred.
   */
  public int checkObject(Entity entity) {
    int index = 999;

    for (SuperObject obj : game.obj) {
      if (obj != null) {
        if (entity instanceof Player && obj instanceof BombObject) {
          Player player = (Player) entity;
          BombObject bomb = (BombObject) obj;
          long leaveTime = bomb.timeToLeave - System.currentTimeMillis();
          if (leaveTime > 0 && bomb.owner != null && bomb.owner.equals(player.name)) {
            continue;
          }
        }

        // Extend the handling to include BrickObject with similar logic as BombObject
        if (entity instanceof Player && obj instanceof BrickObject) {
          Player player = (Player) entity;
          BrickObject brick = (BrickObject) obj;
          if (brick.owner != null && brick.owner.equals(player.name)) {
            continue; // Skip if the brick is owned by the player
          }
        }

        // Update solid areas to current position
        entity.solidArea.x = entity.getX() + entity.solidArea.x;
        entity.solidArea.y = entity.getY() + entity.solidArea.y;

        obj.solidArea.x = obj.getX() + obj.solidArea.x;
        obj.solidArea.y = obj.getY() + obj.solidArea.y;

        switch (entity.direction) {
          case "up":
            entity.solidArea.y -= entity.speed;
            if (entity.solidArea.intersects(obj.solidArea)) {
              index = game.obj.indexOf(obj);
              entity.collisionOn = true;
            }
            break;

          case "down":
            entity.solidArea.y += entity.speed;
            if (entity.solidArea.intersects(obj.solidArea)) {
              index = game.obj.indexOf(obj);
              entity.collisionOn = true;
            }
            break;

          case "left":
            entity.solidArea.x -= entity.speed;
            if (entity.solidArea.intersects(obj.solidArea)) {
              index = game.obj.indexOf(obj);
              entity.collisionOn = true;
            }
            break;

          case "right":
            entity.solidArea.x += entity.speed;
            if (entity.solidArea.intersects(obj.solidArea)) {
              index = game.obj.indexOf(obj);
              entity.collisionOn = true;
            }
            break;
        }

        // For DRY method we need to move if block from the top cases to here
        //                if (entity.solidArea.intersects(obj.solidArea)) {
        //                    index = game.obj.indexOf(obj);
        //                    entity.collisionOn = true;
        //                    break; // Exit on first collision found
        //                }

        // Reset solid areas after check
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        obj.solidArea.x = obj.solidAreaDefaultX;
        obj.solidArea.y = obj.solidAreaDefaultY;
      }
    }

    return index;
  }

  /**
   * Checks collision with tiles and objects for a given entity.
   *
   * @param tile1 The first tile to check collision for.
   * @param tile2 The second tile to check collision for.
   * @param entity The entity to check collision for.
   */
  private void checkCollision(String tile1, String tile2, Entity entity) {
    entity.collisionOn =
        game.tileManager.isTileCollision(tile1) || game.tileManager.isTileCollision(tile2);
  }

  /**
   * Checks collision between an entity and a list of monsters.
   *
   * @param entity The entity to check collision for.
   * @param target The list of monsters to check collision with.
   * @return The index of the collided monster, or 999 if no collision occurred.
   */
  public int checkEntityToMonsters(Entity entity, ArrayList<Monster> target) {
    int i = 999;

    for (Entity e : target) {
      // Get entity's position on the map
      entity.solidArea.x = entity.getX() + entity.solidArea.x;
      entity.solidArea.y = entity.getY() + entity.solidArea.y;

      // Get target's position on the map
      e.solidArea.x = e.getX() + e.solidArea.x;
      e.solidArea.y = e.getY() + e.solidArea.y;

      // Check if the two entities collide
      switch (entity.direction) {
        case "up":
          entity.solidArea.y -= entity.speed;
          if (entity.solidArea.intersects(e.solidArea)) {
            i = target.indexOf(e);
            entity.collisionOn = true;
          }
          break;

        case "down":
          entity.solidArea.y += entity.speed;
          if (entity.solidArea.intersects(e.solidArea)) {
            i = target.indexOf(e);
            entity.collisionOn = true;
          }
          break;

        case "left":
          entity.solidArea.x -= entity.speed;
          if (entity.solidArea.intersects(e.solidArea)) {
            i = target.indexOf(e);
            entity.collisionOn = true;
          }
          break;

        case "right":
          entity.solidArea.x += entity.speed;
          if (entity.solidArea.intersects(e.solidArea)) {
            i = target.indexOf(e);
            entity.collisionOn = true;
          }
          break;
      }
      entity.solidArea.x = entity.solidAreaDefaultX;
      entity.solidArea.y = entity.solidAreaDefaultY;
      e.solidArea.x = e.solidAreaDefaultX;
      e.solidArea.y = e.solidAreaDefaultY;
    }
    return i;
  }

  /**
   * Checks collision between entities.
   *
   * @param entity The entity to check collision for.
   */
  public void checkEntityToEntity(Entity entity) {
    ArrayList<Entity> entities = new ArrayList<>();
    if (entity instanceof Player) {
      entities.addAll(game.players);
    } else if (entity instanceof Monster) {
      entities.addAll(game.monsters);
    }

    for (Entity e : entities) {
      if (e != entity) {
        // Get entity's position on the map
        entity.solidArea.x = entity.getX() + entity.solidArea.x;
        entity.solidArea.y = entity.getY() + entity.solidArea.y;

        // Get target's position on the map
        e.solidArea.x = e.getX() + e.solidArea.x;
        e.solidArea.y = e.getY() + e.solidArea.y;

        // Check if the two entities collide
        switch (entity.direction) {
          case "up":
            entity.solidArea.y -= entity.speed;
            if (entity.solidArea.intersects(e.solidArea)) {
              entity.collisionOn = true;
            }
            break;

          case "down":
            entity.solidArea.y += entity.speed;
            if (entity.solidArea.intersects(e.solidArea)) {
              entity.collisionOn = true;
            }
            break;

          case "left":
            entity.solidArea.x -= entity.speed;
            if (entity.solidArea.intersects(e.solidArea)) {
              entity.collisionOn = true;
            }
            break;

          case "right":
            entity.solidArea.x += entity.speed;
            if (entity.solidArea.intersects(e.solidArea)) {
              entity.collisionOn = true;
            }
            break;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        e.solidArea.x = e.solidAreaDefaultX;
        e.solidArea.y = e.solidAreaDefaultY;
      }
    }
  }

  /**
   * Checks collision between monsters and players.
   *
   * @param monster The monster to check collision for.
   */
  public void checkMonsterToPlayer(Monster monster) {
    for (Player p : game.players) {
      monster.solidArea.x = monster.getX() + monster.solidArea.x;
      monster.solidArea.y = monster.getY() + monster.solidArea.y;

      // Get target's position on the map
      p.solidArea.x = p.getX() + p.solidArea.x;
      p.solidArea.y = p.getY() + p.solidArea.y;

      boolean playerDies = false;
      switch (monster.direction) {
        case "up":
          monster.solidArea.y -= monster.speed;
          if (monster.solidArea.intersects(p.solidArea)) {
            if (!(p.invincibilityDuration > 0)) {
              playerDies = true;
            }
          }
          break;
        case "down":
          monster.solidArea.y += monster.speed;
          if (monster.solidArea.intersects(p.solidArea)) {
            if (!(p.invincibilityDuration > 0)) {
              playerDies = true;
            }
          }
          break;
        case "left":
          monster.solidArea.x -= monster.speed;
          if (monster.solidArea.intersects(p.solidArea)) {
            if (!(p.invincibilityDuration > 0)) {
              playerDies = true;
            }
          }
          break;
        case "right":
          monster.solidArea.x += monster.speed;
          if (monster.solidArea.intersects(p.solidArea)) {
            if (!(p.invincibilityDuration > 0)) {
              playerDies = true;
            }
          }
          break;
      }

      monster.solidArea.x = monster.solidAreaDefaultX;
      monster.solidArea.y = monster.solidAreaDefaultY;

      p.shouldBeRemoved = playerDies;
      if (!playerDies) {
        p.solidArea.x = p.solidAreaDefaultX;
        p.solidArea.y = p.solidAreaDefaultY;
      }
    }
  }

  public void checkPlayerToEffect(Player player) {
    Iterator<Effect> effectIterator = game.effects.iterator();
    while (effectIterator.hasNext()) {
      Effect effect = effectIterator.next();

      // Adjusting solid areas for collision detection
      effect.solidArea.x = effect.position.getX() + effect.solidArea.x;
      effect.solidArea.y = effect.position.getY() + effect.solidArea.y;

      player.solidArea.x = player.getX() + player.solidArea.x;
      player.solidArea.y = player.getY() + player.solidArea.y;

      // Check if the player collides with an effect
      switch (player.direction) {
        case "up":
          player.solidArea.y -= player.speed;
          break;
        case "down":
          player.solidArea.y += player.speed;
          break;
        case "left":
          player.solidArea.x -= player.speed;
          break;
        case "right":
          player.solidArea.x += player.speed;
          break;
      }

      // Check if the effect already exists in the player's active effects
      boolean effectAlreadyActive =
          player.activeEffects.stream()
              .anyMatch(activeEffect -> activeEffect.getClass().equals(effect.getClass()));

      // Apply the effect if there's a collision and the effect isn't already active
      if (player.solidArea.intersects(effect.solidArea)) {
        if (!effectAlreadyActive) {
          player.addPowerUp(effect);
        }
        effect.applyEffect(player);
        effectIterator.remove(); // Remove effect from game list
        System.out.println(
            "Effect " + effect.effectType() + " applied: " + effect.getClass().getSimpleName());
        System.out.println("\n\ninvincibilityDuration: " + player.invincibilityDuration + "\n\n");

        for (Effect activeEffect : player.activeEffects) {
          System.out.println("Active effect: " + activeEffect.effectType());
        }

        // Break after applying effect, assuming only one effect can be applied at a time
        break;
      }

      // Reset the areas after checking
      player.solidArea.x = player.solidAreaDefaultX;
      player.solidArea.y = player.solidAreaDefaultY;
      effect.solidArea.x = effect.solidAreaDefaultX;
      effect.solidArea.y = effect.solidAreaDefaultY;
    }
  }
}
