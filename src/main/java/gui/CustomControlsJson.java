package gui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;

/** Utility class for reading and writing player controls to and from a JSON file. */
public class CustomControlsJson {

  /**
   * Reads player controls from a JSON file.
   *
   * @return an array of {@link CustomControl} objects
   */
  public static CustomControl[] readControlsFromJson() {
    CustomControl[] customControls = new CustomControl[0];
    // Read from the JSON file
    try (Reader reader = new FileReader("player_controls.json")) {
      // Convert JSON to List of Game objects
      Gson gson = new Gson();
      customControls = gson.fromJson(reader, CustomControl[].class);

      return customControls;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return customControls;
  }

  /**
   * Saves player controls to a JSON file.
   *
   * @param customControls an array of {@link CustomControl} objects to be saved
   */
  public static void saveControlsToJson(CustomControl[] customControls) {
    // Create Gson instance with pretty printing
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Convert the array to JSON
    String json = gson.toJson(customControls);

    // Write the JSON to a file
    try (Writer writer = new FileWriter("player_controls.json")) {
      writer.write(json);
      System.out.println("Data written to JSON successfully.");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
