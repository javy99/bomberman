import gui.MainJFrame;
import gui.MainJPanel;
import gui.ScreenNavigator;
import java.awt.*;
import javax.swing.*;

public class Main {
  public static void main(String[] args) {
    MainJFrame frame = new MainJFrame();

    MainJPanel mainPanel = new MainJPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // keep this
    frame.add(mainPanel, BorderLayout.CENTER);

    // ---- Screen Navigator ----
    ScreenNavigator navigator = new ScreenNavigator(frame, mainPanel);

    // ********************* Main Menu Screen *************************
          navigator.goto_screen_mainMenu();

    // *********************** Game Info Screen **********************
    //        navigator.goto_screen_gameInfo();

    // *********************** Maps Menu Screen **********************
    //        navigator.goto_screen_mapsMenu();

    // *********************** Map Editor Screen **********************
    //        navigator.goto_screen_mapEditor(0);

    // *********************** Map Editor Screen **********************
    // navigator.goto_screen_customControls();

    // *********************** New Game Screen **********************
    //    navigator.goto_screen_new_game_screen();

    // *********************** IN GAME SCREEN *************************
    /** NOTE: map with given id should exist in maps.json file */
    // 2 players
    //    navigator.goto_screen_ACTUAL_GAME("player1", "player2", false, "",
    //            "default_map_unique_id",true, true, true);
    // 3 players
//    navigator.goto_screen_ACTUAL_GAME(
//        "player1", "player2", true, "player3", "default_map_unique_id", true, true);

    frame.setVisible(true);
  }
}
