package gui;

import custom.Slate;
import java.awt.*;
import javax.swing.*;

/** The ScreenNavigator class manages the navigation between different screens in the game GUI. */
public class ScreenNavigator {
  MainJFrame frame;
  MainJPanel mainPanel;

  /**
   * Constructs a new ScreenNavigator with the specified frame and main panel.
   *
   * @param frame The main frame of the application.
   * @param mainPanel The main panel where screens are displayed.
   */
  public ScreenNavigator(MainJFrame frame, MainJPanel mainPanel) {
    this.frame = frame;
    this.mainPanel = mainPanel;
  }

  /** Navigates to the main menu screen. */
  public void goto_screen_mainMenu() {
    mainPanel.removeAll();
    frame.headerPanel.changeHeaderText("Main Menu");
    MainMenu _mainMenu = new MainMenu();
    _mainMenu.btn_newGame.addActionListener(
        e -> {
          frame.headerPanel.changeHeaderText("New Game");
          goto_screen_new_game_screen();
        });
    _mainMenu.btn_mapsSettings.addActionListener(
        e -> {
          goto_screen_mapsMenu();
        });
    _mainMenu.btn_customControls.addActionListener(
        e -> {
          goto_screen_customControls();
        });
    _mainMenu.btn_gameInfo.addActionListener(
        e -> {
          goto_screen_gameInfo();
        });
    _mainMenu.btn_exit.addActionListener(e -> frame.dispose());

    mainPanel.add(_mainMenu, BorderLayout.CENTER);

    mainPanel.revalidate();
    mainPanel.repaint();
  }

  /** Navigates to the game info screen. */
  public void goto_screen_gameInfo() {
    mainPanel.removeAll();
    frame.headerPanel.changeHeaderText("Game Info");

    GameInfo _gameInfo = new GameInfo();
    _gameInfo.btn_newGoBack.addActionListener(
        e -> {
          frame.headerPanel.changeHeaderText("Main Menu");
          goto_screen_mainMenu();
        });

    mainPanel.add(_gameInfo, BorderLayout.CENTER);

    mainPanel.revalidate();
    mainPanel.repaint();
  }

  /** Navigates to the maps menu screen. */
  public void goto_screen_mapsMenu() {
    mainPanel.removeAll();
    frame.headerPanel.changeHeaderText("Maps Settings");

    MapsMenu _mapsMenu = new MapsMenu(this);

    mainPanel.add(_mapsMenu, BorderLayout.CENTER);

    mainPanel.revalidate();
    mainPanel.repaint();
  }

  /**
   * Navigates to the map editor screen.
   *
   * @param selectedMapIndex The index of the selected map.
   */
  public void goto_screen_mapEditor(int selectedMapIndex) {
    mainPanel.removeAll();
    frame.headerPanel.changeHeaderText("Map Editor");

    MapEditor _mapEditor = new MapEditor(this, selectedMapIndex);

    mainPanel.add(_mapEditor, BorderLayout.CENTER);

    mainPanel.revalidate();
    mainPanel.repaint();
  }

  /** Navigates to the custom controls screen. */
  public void goto_screen_customControls() {
    mainPanel.removeAll();
    frame.headerPanel.changeHeaderText("Custom Controls");

    CustomControls _customControls = new CustomControls(this);

    mainPanel.add(_customControls, BorderLayout.CENTER);

    mainPanel.revalidate();
    mainPanel.repaint();

    mainPanel.transferFocus();
  }

  /** Navigates to the new game screen. */
  public void goto_screen_new_game_screen() {
    mainPanel.removeAll();
    frame.headerPanel.changeHeaderText("New Game");

    NewGameScreen newGameScreen = new NewGameScreen(this);

    mainPanel.add(newGameScreen, BorderLayout.CENTER);

    mainPanel.revalidate();
    mainPanel.repaint();
  }

  /**
   * Navigates to the actual game screen.
   *
   * @param player1_name The name of player 1.
   * @param player2_name The name of player 2.
   * @param threePlayers Indicates whether there are three players in the game.
   * @param player_name3 The name of player 3.
   * @param selected_map_id The ID of the selected map.
   * @param intelligent_monsters Indicates whether intelligent monsters are enabled.
   * @param advanced_powerups Indicates whether advanced power-ups are enabled.
   */
  public void goto_screen_ACTUAL_GAME(
      String player1_name,
      String player2_name,
      boolean threePlayers,
      String player_name3,
      String selected_map_id,
      boolean intelligent_monsters,
      boolean advanced_powerups
      //      boolean hindering_curses
      ) {
    mainPanel.removeAll();
    frame.headerPanel.changeHeaderText("In Game Screen");

    // ******************************************
    // ******************************************
    // ******************************************
    JPanel gameWrapper = new JPanel(new GridBagLayout());
    gameWrapper.setBackground(Slate._950);

    GridBagConstraints gbc = new GridBagConstraints();

    // ==== left side
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 0; // 1st column width
    gbc.fill = GridBagConstraints.HORIZONTAL;

    PlayerControlsPreviewInGame leftSide =
        new PlayerControlsPreviewInGame(player1_name, player2_name, player_name3, threePlayers);

    leftSide.setPreferredSize(new Dimension(237, 575));

    gameWrapper.add(leftSide, gbc);

    // ==== game
    gbc.gridx = 1;
    gbc.weightx = 1; // 2nd column width
    gbc.fill = GridBagConstraints.BOTH;

    Game game =
        new Game(
            this,
            player1_name,
            player2_name,
            threePlayers,
            player_name3,
            selected_map_id,
            intelligent_monsters,
            advanced_powerups
            //            hindering_curses
            );
    gameWrapper.add(game, gbc);

    // ==== right side
    gbc.gridx = 2;
    gbc.insets = new Insets(0, 20, 0, 0); // margin left
    gbc.weightx = 0;
    gbc.weighty = 1; // Take all vertical space
    gbc.fill = GridBagConstraints.BOTH; // Fill both horizontally and vertically
    gbc.anchor = GridBagConstraints.NORTH;

    TimerAndActivePowerUpsPreview rightSide = new TimerAndActivePowerUpsPreview(game);
    rightSide.setPreferredSize(new Dimension(237, 575));
    gameWrapper.add(rightSide, gbc);

    mainPanel.add(gameWrapper, BorderLayout.CENTER);

    // ******************************************
    // ******************************************
    // ******************************************

    mainPanel.revalidate();
    mainPanel.repaint();

    mainPanel.transferFocus();

    game.startGameThread();
  }
}
