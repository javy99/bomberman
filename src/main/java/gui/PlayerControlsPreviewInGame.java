package gui;

import custom.CustomLabel;
import custom.Slate;
import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;

/**
 * The PlayerControlsPreviewInGame class represents a panel for displaying player controls during
 * the game.
 */
public class PlayerControlsPreviewInGame extends JPanel {

  /**
   * Constructs a new PlayerControlsPreviewInGame panel.
   *
   * @param player1_name The name of player 1.
   * @param player2_name The name of player 2.
   * @param player_name3 The name of player 3 (if applicable).
   * @param threePlayers Indicates whether there are three players in the game.
   */
  public PlayerControlsPreviewInGame(
      String player1_name, String player2_name, String player_name3, boolean threePlayers) {
    super();
    this.setBackground(Slate._950);
    this.setLayout(new GridLayout(3, 1, 0, 55));

    CustomControl[] jsonControls = CustomControlsJson.readControlsFromJson();
    CustomControl p1Controls = jsonControls[0];
    CustomControl p2Controls = jsonControls[1];
    CustomControl p3Controls = jsonControls[2];

    PlayerControl2 p1ControlsPanel = new PlayerControl2(Color.blue, player1_name, p1Controls);
    PlayerControl2 p2ControlsPanel = new PlayerControl2(Color.red, player2_name, p2Controls);
    PlayerControl2 p3ControlsPanel = new PlayerControl2(Color.green, player_name3, p3Controls);

    this.add(p1ControlsPanel);
    this.add(p2ControlsPanel);
    if (threePlayers) {
      this.add(p3ControlsPanel);
    }
  }
}

/** The PlayerControl2 class represents the panel for displaying controls of a single player. */
class PlayerControl2 extends JPanel {
  public CustomLabel button_up;
  public CustomLabel button_left;
  public CustomLabel button_down;
  public CustomLabel button_right;
  public CustomLabel button_bomb;

  class CustomControlLabel extends CustomLabel {
    CustomControlLabel(String text, Color color) {
      super(text);
      this.setBorder(BorderFactory.createLineBorder(Slate._800, 1));
      this.setHorizontalAlignment(SwingConstants.CENTER);
      this.setForeground(color);
    }
  }

  public GridBagConstraints gbc;

  /**
   * Constructs a new PlayerControl2 panel for a player.
   *
   * @param color The color associated with the player.
   * @param playerName The name of the player.
   * @param pControls The CustomControl object containing the player's controls.
   */
  public PlayerControl2(Color color, String playerName, CustomControl pControls) {
    super();
    GridBagLayout gridlayout_p2 = new GridBagLayout();
    this.gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1; // Allows components to expand horizontally

    this.setLayout(gridlayout_p2);
    this.setOpaque(true);
    this.setBackground(Slate._950);

    // label
    JPanel p2_label = new JPanel();
    p2_label.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
    p2_label.setOpaque(true);
    p2_label.setAlignmentY(CENTER_ALIGNMENT);
    p2_label.setBackground(Slate._950);

    JLabel label_p2 = new JLabel(playerName);
    label_p2.setForeground(color);
    p2_label.add(label_p2);

    // upbutton
    JPanel p2_up = new JPanel();
    p2_up.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
    p2_up.setOpaque(true);
    p2_up.setAlignmentY(CENTER_ALIGNMENT);
    p2_up.setBackground(Slate._950);

    this.button_up =
        new CustomControlLabel("⬆ (" + KeyEvent.getKeyText(pControls.go_up) + ")", color);
    this.button_up.setPreferredSize(new Dimension(60, 25));
    p2_up.add(this.button_up);

    // left bottom right
    JPanel p2_left_bottom_right = new JPanel();
    p2_left_bottom_right.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
    p2_left_bottom_right.setOpaque(true);
    p2_left_bottom_right.setAlignmentY(CENTER_ALIGNMENT);
    p2_left_bottom_right.setBackground(Slate._950);

    this.button_left =
        new CustomControlLabel("⬅ (" + KeyEvent.getKeyText(pControls.go_left) + ")", color);
    this.button_left.setPreferredSize(new Dimension(60, 25));

    this.button_down =
        new CustomControlLabel("⬇ (" + KeyEvent.getKeyText(pControls.go_down) + ")", color);
    this.button_down.setPreferredSize(new Dimension(60, 25));

    this.button_right =
        new CustomControlLabel("➡ (" + KeyEvent.getKeyText(pControls.go_right) + ")", color);
    this.button_right.setPreferredSize(new Dimension(60, 25));

    p2_left_bottom_right.add(this.button_left);
    p2_left_bottom_right.add(this.button_down);
    p2_left_bottom_right.add(this.button_right);

    // bomb button
    JPanel p2_bomb = new JPanel();
    p2_bomb.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 15));
    p2_bomb.setOpaque(true);
    p2_bomb.setAlignmentY(CENTER_ALIGNMENT);
    p2_bomb.setBackground(Slate._950);

    this.button_bomb =
        new CustomControlLabel("bomb (" + KeyEvent.getKeyText(pControls.place_bomb) + ")", color);
    this.button_bomb.setPreferredSize(new Dimension(190, 25));
    p2_bomb.add(this.button_bomb);

    addComponent(p2_label, gbc, 0, 0);
    addComponent(p2_up, gbc, 0, 1);
    addComponent(p2_left_bottom_right, gbc, 0, 2);
    addComponent(p2_bomb, gbc, 0, 3);
  }

  private void addComponent(JComponent component, GridBagConstraints gbc, int x, int y) {
    gbc.gridx = x;
    gbc.gridy = y;
    this.add(component, gbc);
  }
}
