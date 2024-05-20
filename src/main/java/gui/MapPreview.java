package gui;

import custom.Slate;
import entity.GameMap;
import java.awt.*;
import javax.swing.*;

/**
 * The MapPreview class represents a panel for previewing a game map. It displays the cells of the
 * map with their respective materials.
 */
class MapPreview extends JPanel {

  /**
   * Constructs a new MapPreview panel with the given GameMap.
   *
   * @param gameMap The GameMap object representing the map to be previewed.
   */
  public MapPreview(GameMap gameMap) {
    super();
    this.setLayout(new GridLayout(12, 13, 1, 1));
    this.setBackground(Slate._950);
    this.setBorder(BorderFactory.createLineBorder(Slate._950, 1));
    for (int i = 0; i < 12; i++) {
      for (int j = 0; j < 13; j++) {
        JLabel tempbtn = new JLabel();
        tempbtn.setForeground(Slate._400);
        tempbtn.setVerticalAlignment(SwingConstants.CENTER);
        tempbtn.setHorizontalAlignment(SwingConstants.CENTER);
        tempbtn.setOpaque(true);
        tempbtn.setSize(new Dimension(10, 10));

        String value = gameMap.mapCells[i][j];
        switch (value) {
          case "grass" -> tempbtn.setBackground(new Color(5, 46, 22));
          case "brick" -> {
            tempbtn.setBackground(new Color(120, 53, 15));
            tempbtn.setText("brick");
          }
          case "wall" -> {
            tempbtn.setBackground(new Color(41, 37, 36));
            tempbtn.setText("wall");
          }
        }
        this.add(tempbtn);
      }
    }
  }
}
