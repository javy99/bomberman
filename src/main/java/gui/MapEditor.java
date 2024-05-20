package gui;

import custom.CustomButton;
import custom.CustomLabel;
import custom.Slate;
import entity.GameMap;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * The MapEditor class represents the panel for editing game maps. It allows users to create new
 * maps or modify existing ones.
 */
class MapEditor extends JPanel {
  GameMap gameMap;
  GameMap[] allMaps;

  String currentMaterialType = "grass";

  /**
   * Creates a new game map.
   *
   * @return The newly created game map.
   */
  private GameMap createNewMap() {
    String map_id =
        String.valueOf((new Random()).nextInt()) + String.valueOf((new Random()).nextInt());
    String map_name = "New Map " + allMaps.length;
    int[] map_dimensions = new int[] {12, 13};
    String[][] map_cells = new String[12][13];
    for (int i = 0; i < map_dimensions[0]; i++) {
      for (int j = 0; j < map_dimensions[1]; j++) {
        map_cells[i][j] = "grass";
      }
    }
    return new GameMap(map_id, map_name, "", "Easy", map_dimensions, map_cells);
  }

  /**
   * Constructs a new MapEditor panel.
   *
   * @param navigator The ScreenNavigator object for navigating between screens.
   * @param selectedMapIndex The index of the selected map for editing, or -1 to create a new map.
   */
  public MapEditor(ScreenNavigator navigator, int selectedMapIndex) {
    /***
     * selectedMapIndex
     * if -1 ::: create new map
     * if 0 or  more ::: edit existing map
     * */
    super();
    this.setBackground(Slate._950);
    allMaps = MapsController.readGameMapsFromJson();

    if (selectedMapIndex == -1) {
      gameMap = createNewMap();
    } else {
      gameMap = allMaps[selectedMapIndex];
    }
    // ***************** Editor ********************
    JPanel grid = new JPanel();
    grid.setLayout(new GridLayout(1, 2, 20, 20));
    grid.setBackground(Slate._950);

    // ***** map grid
    JPanel map_grid_wrapper = new JPanel();
    map_grid_wrapper.setLayout(new BoxLayout(map_grid_wrapper, BoxLayout.Y_AXIS));
    map_grid_wrapper.setPreferredSize(new Dimension(450, 450));

    JPanel map_grid = new JPanel();
    map_grid.setLayout(new GridLayout(12, 13, 1, 1));
    map_grid.setBackground(Slate._950);
    map_grid.setBorder(BorderFactory.createLineBorder(Slate._950, 1));
    for (int i = 0; i < 12; i++) {
      for (int j = 0; j < 13; j++) {
        JLabel cell_btn = new JLabel("");
        cell_btn.setForeground(Slate._400);
        cell_btn.setVerticalAlignment(SwingConstants.CENTER);
        cell_btn.setHorizontalAlignment(SwingConstants.CENTER);
        cell_btn.setOpaque(true);
        cell_btn.setBorder(null);
        cell_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        String cell_value = gameMap.mapCells[i][j];
        changeCellButtonStyle(cell_btn, cell_value);
        int finalI = i;
        int finalJ = j;
        cell_btn.addMouseListener(
            new MouseAdapter() {
              @Override
              public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                gameMap.mapCells[finalI][finalJ] = currentMaterialType;
                changeCellButtonStyle(cell_btn, currentMaterialType);
              }
            });
        map_grid.add(cell_btn);
      }
    }

    // ***************** right side

    JPanel rightSideGrid = new JPanel();
    rightSideGrid.setLayout(new GridLayout(11, 1, 0, 0));
    rightSideGrid.setBackground(Slate._950);
    //        rightSideGrid.setBorder(BorderFactory.createLineBorder(custom.Slate._950, 4));

    JPanel mt_labelwrapper = new JPanel();
    mt_labelwrapper.setLayout(new FlowLayout(FlowLayout.LEFT));
    mt_labelwrapper.setBackground(Slate._950);
    mt_labelwrapper.add(new CustomLabel("Material - "));
    CustomLabel material_label = new CustomLabel(currentMaterialType);
    material_label.setOpaque(true);
    material_label.setPreferredSize(new Dimension(70, 30));
    material_label.setHorizontalAlignment(SwingConstants.CENTER);
    material_label.setVerticalAlignment(SwingConstants.CENTER);
    material_label.setBackground(new Color(5, 46, 22));
    mt_labelwrapper.add(material_label);
    rightSideGrid.add(mt_labelwrapper);

    JPanel materialSelector = new JPanel();
    materialSelector.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
    materialSelector.setBackground(Slate._950);
    JButton mt_grass = new CustomButton("Grass");
    mt_grass.setPreferredSize(new Dimension(100, 30));
    mt_grass.addActionListener(
        e -> {
          this.currentMaterialType = "grass";
          material_label.setText(currentMaterialType);
          material_label.setBackground(new Color(5, 46, 22));
        });
    JButton mt_box = new CustomButton("Brick");
    mt_box.setPreferredSize(new Dimension(100, 30));
    mt_box.addActionListener(
        e -> {
          this.currentMaterialType = "brick";
          material_label.setText(currentMaterialType);
          material_label.setBackground(new Color(120, 53, 15));
        });
    JButton mt_wall = new CustomButton("Wall");
    mt_wall.setPreferredSize(new Dimension(100, 30));
    mt_wall.addActionListener(
        e -> {
          this.currentMaterialType = "wall";
          material_label.setText(currentMaterialType);
          material_label.setBackground(new Color(41, 37, 36));
        });
    materialSelector.add(mt_grass);
    materialSelector.add(mt_box);
    materialSelector.add(mt_wall);
    rightSideGrid.add(materialSelector);

    CustomLabel difficulty_label = new CustomLabel("Difficulty - " + gameMap.difficulty);
    rightSideGrid.add(difficulty_label);

    JPanel difficultySelector = new JPanel();
    difficultySelector.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
    difficultySelector.setBackground(Slate._950);
    JButton df_easy = new CustomButton("Easy");
    df_easy.setPreferredSize(new Dimension(100, 30));
    df_easy.addActionListener(
        e -> {
          gameMap.difficulty = "Easy";
          difficulty_label.setText("Difficulty - " + gameMap.difficulty);
        });
    JButton df_medium = new CustomButton("Medium");
    df_medium.setPreferredSize(new Dimension(100, 30));
    df_medium.addActionListener(
        e -> {
          gameMap.difficulty = "Medium";
          difficulty_label.setText("Difficulty - " + gameMap.difficulty);
        });
    JButton df_hard = new CustomButton("Hard");
    df_hard.setPreferredSize(new Dimension(100, 30));
    df_hard.addActionListener(
        e -> {
          gameMap.difficulty = "Hard";
          difficulty_label.setText("Difficulty - " + gameMap.difficulty);
        });
    difficultySelector.add(df_easy);
    difficultySelector.add(df_medium);
    difficultySelector.add(df_hard);
    rightSideGrid.add(difficultySelector);

    rightSideGrid.add(new CustomLabel("Map Name"));

    JTextField txt_mapname = new JTextField(gameMap.name);
    txt_mapname.setForeground(Slate._300);
    txt_mapname.setOpaque(true);
    txt_mapname.setBackground(Slate._800);
    txt_mapname.setCaretColor(Slate._300);
    rightSideGrid.add(txt_mapname);

    rightSideGrid.add(new CustomLabel("Description"));

    JTextArea txt_description = new JTextArea(gameMap.description);
    txt_description.setForeground(Slate._300);
    txt_description.setOpaque(true);
    txt_description.setBackground(Slate._800);
    txt_description.setCaretColor(Slate._300);
    txt_description.setBorder(BorderFactory.createLineBorder(Slate._800, 5));
    rightSideGrid.add(txt_description);

    Border topBorder = BorderFactory.createMatteBorder(10, 0, 0, 0, Slate._950);

    JButton btn_save = new CustomButton("Save");
    btn_save.addActionListener(
        e -> {
          gameMap.name = txt_mapname.getText();
          gameMap.description = txt_description.getText();
          if (selectedMapIndex == -1) {
            // TODO: handle new map addition
            GameMap[] new_allmaps = Arrays.copyOf(allMaps, allMaps.length + 1);
            new_allmaps[new_allmaps.length - 1] = gameMap;
            MapsController.saveGameMapsToJson(new_allmaps);
          } else {
            // TODO: handle edit existing map
            allMaps[selectedMapIndex] = gameMap;
            MapsController.saveGameMapsToJson(allMaps);
          }

          navigator.goto_screen_mapsMenu();
        });
    btn_save.setBorder(BorderFactory.createCompoundBorder(topBorder, btn_save.getBorder()));
    rightSideGrid.add(btn_save);

    JButton btn_cancel = new CustomButton("Cancel");
    btn_cancel.addActionListener(
        e -> {
          navigator.goto_screen_mapsMenu();
        });
    btn_cancel.setBorder(BorderFactory.createCompoundBorder(topBorder, btn_cancel.getBorder()));
    rightSideGrid.add(btn_cancel);

    map_grid_wrapper.add(map_grid);
    grid.add(map_grid_wrapper);
    grid.add(rightSideGrid);
    this.add(grid);
  }

  public void changeCellButtonStyle(JLabel cell_btn, String cell_value) {
    switch (cell_value) {
      case "grass" -> {
        cell_btn.setBackground(new Color(5, 46, 22));
        cell_btn.setText("");
      }
      case "brick" -> {
        cell_btn.setBackground(new Color(120, 53, 15));
        cell_btn.setText("brick");
      }
      case "wall" -> {
        cell_btn.setBackground(new Color(41, 37, 36));
        cell_btn.setText("wall");
      }
    }
  }
}
