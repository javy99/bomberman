package entity;

/** The GameMap class represents a game map with various properties. */
public class GameMap {
  public String id;
  public String name;
  public String description;
  public String difficulty; // Easy, Medium, Hard
  public int[] mapDimensions;
  public String[][] mapCells; // grass, box, wall

  /**
   * Constructs a GameMap object.
   *
   * @param id The unique identifier of the map.
   * @param name The name of the map.
   * @param description The description of the map.
   * @param difficulty The difficulty level of the map (e.g., Easy, Medium, Hard).
   * @param mapDimensions An array representing the dimensions of the map.
   * @param mapCells A 2D array representing the cells of the map (e.g., grass, box, wall).
   */
  public GameMap(
      String id,
      String name,
      String description,
      String difficulty,
      int[] mapDimensions,
      String[][] mapCells) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.difficulty = difficulty;
    this.mapDimensions = mapDimensions;
    this.mapCells = mapCells;
  }

  /**
   * Returns the name of the map.
   *
   * @return The name of the map.
   */
  @Override
  public String toString() {
    return name;
  }
}
