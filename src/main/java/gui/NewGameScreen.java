package gui;

import custom.CustomButton;
import custom.Slate;
import entity.GameMap;
import java.awt.*;
import java.util.Arrays;
import javax.swing.*;

/** The NewGameScreen class represents the screen for starting a new game. */
public class NewGameScreen extends JPanel {
  /**
   * Constructs a NewGameScreen object with the specified navigator.
   *
   * @param navigator The ScreenNavigator object.
   */
  public NewGameScreen(ScreenNavigator navigator) {
    super();
    this.setBackground(Slate._950);
    GameMap[] gameMaps = MapsController.readGameMapsFromJson();

    JPanel maingrid = new JPanel();
    maingrid.setLayout(new GridLayout(1, 2, 0, 0));
    maingrid.setOpaque(true);
    maingrid.setBackground(Slate._950);

    JPanel grid = new JPanel();
    GridLayout gridlayout = new GridLayout(9, 2, 20, 0);
    gridlayout.setVgap(20);
    grid.setLayout(gridlayout);
    grid.setOpaque(true);
    grid.setBackground(Slate._950);

    /** MAP SELECTION */
    JPanel map_labelcombo = new JPanel();
    map_labelcombo.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 0));
    map_labelcombo.setOpaque(true);
    map_labelcombo.setAlignmentY(CENTER_ALIGNMENT);
    map_labelcombo.setBackground(Slate._950);

    JLabel map_label = new JLabel("Select Map");
    map_label.setForeground(Slate._300);

    JComboBox map_combobox = new JComboBox(gameMaps);
    map_combobox.setEditable(false);

    map_labelcombo.add(map_label);
    map_labelcombo.add(map_combobox);

    /** PLAYER COUNT SELECTION */
    JPanel playercount_labelcombo = new JPanel();
    playercount_labelcombo.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 0));
    playercount_labelcombo.setOpaque(true);
    playercount_labelcombo.setAlignmentY(CENTER_ALIGNMENT);
    playercount_labelcombo.setBackground(Slate._950);

    JLabel playercount_label = new JLabel("Select Player Count");
    playercount_label.setForeground(Slate._300);

    int[] intArray = {3, 2};

    // Convert int array to Integer array using Java 8 Stream API
    Integer[] integerArray = Arrays.stream(intArray).boxed().toArray(Integer[]::new);

    // Create JintegerArrayComboBox
    JComboBox<Integer> playercount_combobox = new JComboBox<>(integerArray);
    map_combobox.setEditable(false);

    playercount_labelcombo.add(playercount_label);
    playercount_labelcombo.add(playercount_combobox);

    /** PLAYER 1 */
    JPanel p1_labelinput = new JPanel();
    p1_labelinput.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 0));
    p1_labelinput.setOpaque(true);
    p1_labelinput.setAlignmentY(CENTER_ALIGNMENT);
    p1_labelinput.setBackground(Slate._950);

    JLabel p1_label = new JLabel("Player 1 Name:");
    p1_label.setForeground(Slate._300);

    JTextField p1_input = new JTextField();
    p1_input.setText("player1");
    p1_input.setForeground(Slate._300);
    p1_input.setOpaque(true);
    p1_input.setBackground(Slate._800);
    p1_input.setCaretColor(Slate._300);
    p1_input.setPreferredSize(new Dimension(100, 35));

    p1_labelinput.add(p1_label);
    p1_labelinput.add(p1_input);

    /** PLAYER 2 */
    JPanel p2_labelinput = new JPanel();
    p2_labelinput.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 0));
    p2_labelinput.setOpaque(true);
    p2_labelinput.setAlignmentY(CENTER_ALIGNMENT);
    p2_labelinput.setBackground(Slate._950);

    JLabel p2_label = new JLabel("Player 2 Name:");
    p2_label.setForeground(Slate._300);

    JTextField p2_input = new JTextField();
    p2_input.setText("player2");
    p2_input.setForeground(Slate._300);
    p2_input.setOpaque(true);
    p2_input.setBackground(Slate._800);
    p2_input.setCaretColor(Slate._300);
    p2_input.setPreferredSize(new Dimension(100, 35));

    p2_labelinput.add(p2_label);
    p2_labelinput.add(p2_input);

    /** PLAYER 3 */
    JPanel p3_labelinput = new JPanel();
    p3_labelinput.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 0));
    p3_labelinput.setOpaque(true);
    p3_labelinput.setAlignmentY(CENTER_ALIGNMENT);
    p3_labelinput.setBackground(Slate._950);

    JLabel p3_label = new JLabel("Player 3 Name:");
    p3_label.setForeground(Slate._300);

    JTextField p3_input = new JTextField();
    p3_input.setText("player3");
    p3_input.setForeground(Slate._300);
    p3_input.setOpaque(true);
    p3_input.setBackground(Slate._800);
    p3_input.setCaretColor(Slate._300);
    p3_input.setPreferredSize(new Dimension(100, 35));

    p3_labelinput.add(p3_label);
    p3_labelinput.add(p3_input);

    /*** INTELLIGENT MONSTERS */
    JPanel intelligent_flow = new JPanel();
    intelligent_flow.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 0));
    intelligent_flow.setOpaque(true);
    intelligent_flow.setAlignmentY(CENTER_ALIGNMENT);
    intelligent_flow.setBackground(Slate._950);

    JCheckBox intelligentmonster_checkbox = new JCheckBox("Intelligent Monsters");
    intelligentmonster_checkbox.setForeground(Slate._300);
    intelligentmonster_checkbox.setOpaque(true);
    intelligentmonster_checkbox.setBackground(Slate._950);

    intelligent_flow.add(intelligentmonster_checkbox);

    /*** ADVANCED POWERUPS */

    JPanel advanced_flow = new JPanel();
    advanced_flow.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 0));
    advanced_flow.setOpaque(true);
    advanced_flow.setAlignmentY(CENTER_ALIGNMENT);
    advanced_flow.setBackground(Slate._950);

    JCheckBox advancedpowerups_checkbox = new JCheckBox("Advanced Powerups");
    advancedpowerups_checkbox.setForeground(Slate._300);
    advancedpowerups_checkbox.setOpaque(true);
    advancedpowerups_checkbox.setBackground(Slate._950);

    advanced_flow.add(advancedpowerups_checkbox);

    /*** HINDERING CURSES */

    //    JPanel hindering_flow = new JPanel();
    //    hindering_flow.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 0));
    //    hindering_flow.setOpaque(true);
    //    hindering_flow.setAlignmentY(CENTER_ALIGNMENT);
    //    hindering_flow.setBackground(Slate._950);
    //
    //    JCheckBox hinderingcurse_checkbox = new JCheckBox("Hindering Curses");
    //    hinderingcurse_checkbox.setForeground(Slate._300);
    //    hinderingcurse_checkbox.setOpaque(true);
    //    hinderingcurse_checkbox.setBackground(Slate._950);
    //
    //    hindering_flow.add(hinderingcurse_checkbox);

    /*** MAIN MENU & START GAME */
    JPanel buttons_flow = new JPanel();
    buttons_flow.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 0));
    buttons_flow.setOpaque(true);
    buttons_flow.setAlignmentY(CENTER_ALIGNMENT);
    buttons_flow.setBackground(Slate._950);

    CustomButton mainmenu_btn = new CustomButton("Main Menu");
    mainmenu_btn.setPreferredSize(new Dimension(100, 30));
    mainmenu_btn.addActionListener(
        e -> {
          navigator.goto_screen_mainMenu();
        });

    CustomButton startgame_btn = new CustomButton("Start Game");
    startgame_btn.setPreferredSize(new Dimension(100, 30));
    startgame_btn.addActionListener(
        e -> {
          String p1_name = p1_input.getText();
          String p2_name = p2_input.getText();
          String p3_name = p3_input.getText();
          String selected_map_id = gameMaps[map_combobox.getSelectedIndex()].id;
          boolean intelligent_monsters = intelligentmonster_checkbox.isSelected();
          boolean advanced_powerups = advancedpowerups_checkbox.isSelected();
          //          boolean hindering_curses = hinderingcurse_checkbox.isSelected();

          if (p1_name.length() < 1 || p2_name.length() < 1) {
            JOptionPane.showMessageDialog(this, "Player names cannot be empty");
          } else {
            if (playercount_combobox.getSelectedItem().toString().equals("3")) {
              navigator.goto_screen_ACTUAL_GAME(
                  p1_name,
                  p2_name,
                  true,
                  p3_name,
                  selected_map_id,
                  intelligent_monsters,
                  advanced_powerups
                  //                  hindering_curses
                  );
            } else {
              navigator.goto_screen_ACTUAL_GAME(
                  p1_name,
                  p2_name,
                  false,
                  "",
                  selected_map_id,
                  intelligent_monsters,
                  advanced_powerups
                  //                  hindering_curses
                  );
            }
          }
        });

    buttons_flow.add(mainmenu_btn);
    buttons_flow.add(startgame_btn);

    playercount_combobox.addActionListener(
        e -> {
          if (!playercount_combobox.getSelectedItem().toString().equals("3")) {
            p3_input.setEnabled(false);
            p3_label.setForeground(Slate._900);
          } else {
            p3_input.setEnabled(true);
            p3_label.setForeground(Slate._300);
          }
        });

    grid.add(playercount_labelcombo);
    grid.add(p1_labelinput);
    grid.add(p2_labelinput);
    grid.add(p3_labelinput);
    grid.add(map_labelcombo);
    grid.add(intelligent_flow);
    grid.add(advanced_flow);
    //    grid.add(hindering_flow);
    grid.add(buttons_flow);

    MapPreviewWrapper previewWrapper = new MapPreviewWrapper();
    previewWrapper.setPreferredSize(new Dimension(450, 450));
    previewWrapper.setMapPreview(new MapPreview(gameMaps[0]));

    map_combobox.addActionListener(
        e -> {
          previewWrapper.setMapPreview(new MapPreview(gameMaps[map_combobox.getSelectedIndex()]));
        });

    maingrid.add(grid);
    maingrid.add(previewWrapper);

    this.add(maingrid);
  }
}
