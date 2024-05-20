package gui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.GameMap;
import java.io.*;

/** The MapsController class provides methods for reading, retrieving, and saving game maps. */
class MapsController {

  /**
   * Reads game maps from a JSON file.
   *
   * @return An array of GameMap objects read from the JSON file.
   */
  public static GameMap[] readGameMapsFromJson() {
    GameMap[] gameMaps = new GameMap[0];
    // Read from the JSON file
    try (Reader reader = new FileReader("maps.json")) {
      // Convert JSON to List of Game objects
      Gson gson = new Gson();
      gameMaps = gson.fromJson(reader, GameMap[].class);
      return gameMaps;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return gameMaps;
  }

  /**
   * Retrieves a game map by its ID.
   *
   * @param mapId The ID of the map to retrieve.
   * @return The GameMap object with the specified ID, or null if not found.
   */
  public static GameMap getMapById(String mapId) {
    GameMap[] gameMaps = readGameMapsFromJson();
    for (GameMap gameMap : gameMaps) {
      if (gameMap.id.equals(mapId)) {
        return gameMap;
      }
    }
    return null;
  }

  /**
   * Saves game maps to a JSON file.
   *
   * @param gameMaps An array of GameMap objects to be saved.
   */
  public static void saveGameMapsToJson(GameMap[] gameMaps) {
    // Create Gson instance with pretty printing
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Convert the array to JSON
    String json = gson.toJson(gameMaps);

    // Write the JSON to a file
    try (Writer writer = new FileWriter("maps.json")) {
      writer.write(json);
      System.out.println("Data written to JSON successfully.");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
