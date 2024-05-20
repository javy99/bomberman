package gui;

import custom.CustomButton;
import custom.CustomLabel;
import custom.Slate;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

/**
 * CustomControls is a JPanel that handles the setup and modification of custom controls for
 * players.
 */
class CustomControls extends JPanel {
  CustomLabel settingKey;

  /**
   * Constructs a CustomControls panel.
   *
   * @param navigator the navigator to switch between screens
   */
  public CustomControls(ScreenNavigator navigator) {
    super();
    this.setBackground(Slate._950);

    JPanel grid = new JPanel();
    GridLayout gridlayout = new GridLayout(3, 1, 10, 10);
    grid.setLayout(gridlayout);
    grid.setOpaque(true);
    grid.setBackground(Slate._950);

    JPanel grid_3players = new JPanel();
    GridLayout gridlayout_3players = new GridLayout(1, 3, 10, 10);
    grid_3players.setLayout(gridlayout_3players);
    grid_3players.setOpaque(true);
    grid_3players.setBackground(Slate._950);

    // players
    settingKey = new CustomLabel("");

    ControlEventSource controlEventSource = new ControlEventSource(settingKey, this);

    PlayerControl player1 = new PlayerControl(Color.blue, "player1", 0, controlEventSource);
    PlayerControl player2 = new PlayerControl(Color.red, "player2", 1, controlEventSource);
    PlayerControl player3 = new PlayerControl(Color.green, "player3", 2, controlEventSource);

    grid_3players.add(player1);
    grid_3players.add(player2);
    grid_3players.add(player3);

    grid.add(grid_3players);

    JPanel grid_bottom = new JPanel();
    grid_bottom.setLayout(new GridLayout(2, 1, 10, 20));
    grid_bottom.setBackground(Slate._950);

    JPanel top_info = new JPanel();
    top_info.setLayout(new GridLayout(2, 1, 10, 1));
    top_info.setBackground(Slate._950);

    CustomLabel info =
        new CustomLabel(
            "Click on the control buttons and then press a key to change the defined control key.");
    info.setPreferredSize(new Dimension(700, 20));
    info.setHorizontalAlignment(SwingConstants.CENTER);

    settingKey.setFont(new Font("Arial", Font.PLAIN, 26));
    settingKey.setHorizontalAlignment(SwingConstants.CENTER);
    settingKey.setForeground(Color.yellow);

    top_info.add(info);
    top_info.add(settingKey);

    JPanel bottom_btns = new JPanel();
    FlowLayout flw = new FlowLayout(FlowLayout.CENTER, 100, 1);
    bottom_btns.setLayout(flw);
    bottom_btns.setBackground(Slate._950);

    CustomButton mainMenu = new CustomButton("Back to Main Menu");
    mainMenu.setPreferredSize(new Dimension(200, 50));
    mainMenu.addActionListener(
        e -> {
          navigator.goto_screen_mainMenu();
        });

    CustomButton saveChanges = new CustomButton("Save Changes");
    saveChanges.setPreferredSize(new Dimension(200, 50));

    CustomLabel changesSaved = new CustomLabel("");
    changesSaved.setPreferredSize(new Dimension(200, 50));
    changesSaved.setHorizontalAlignment(SwingConstants.CENTER);

    bottom_btns.add(mainMenu);
    bottom_btns.add(changesSaved);
    bottom_btns.add(saveChanges);

    grid_bottom.add(top_info);
    grid_bottom.add(bottom_btns);

    grid.add(grid_bottom);
    this.add(grid);

    CustomControl[] jsonControls = CustomControlsJson.readControlsFromJson();

    jsonControls[0].changePlayerControlBtnsTexts(player1);
    jsonControls[1].changePlayerControlBtnsTexts(player2);
    jsonControls[2].changePlayerControlBtnsTexts(player3);

    this.addKeyListener(new CustomControlKeyHandler(controlEventSource, settingKey, jsonControls));
    this.setFocusable(true);

    saveChanges.addActionListener(
        e -> {
          changesSaved.setText("Changes are saved ✅");
          CustomControlsJson.saveControlsToJson(jsonControls);
          new Thread(
                  () -> {
                    try {
                      Thread.sleep(500);
                      SwingUtilities.invokeLater(
                          () -> {
                            changesSaved.setText("");
                          });
                    } catch (InterruptedException ee) {
                      ee.printStackTrace();
                    }
                  })
              .start();
        });
  }
}

/** CustomControlKeyHandler handles key events for custom controls. */
class CustomControlKeyHandler implements KeyListener {
  ControlEventSource controlEventSource;
  CustomLabel settingKey;
  CustomControl[] jsonControls;

  /**
   * Constructs a CustomControlKeyHandler.
   *
   * @param controlEventSource the source of control events
   * @param settingKey the label indicating the setting key
   * @param jsonControls the array of custom controls
   */
  public CustomControlKeyHandler(
      ControlEventSource controlEventSource, CustomLabel settingKey, CustomControl[] jsonControls) {
    super();
    this.controlEventSource = controlEventSource;
    this.settingKey = settingKey;
    this.jsonControls = jsonControls;
  }

  @Override
  public void keyTyped(KeyEvent e) {}

  @Override
  public void keyPressed(KeyEvent ev) {}

  @Override
  public void keyReleased(KeyEvent ev) {
    int key = ev.getKeyCode();

    if (key != KeyEvent.VK_ESCAPE && this.controlEventSource.activelyListening) {
      System.out.println("keyCode: " + key + "...keyChar: " + ev.getKeyChar());
      jsonControls[this.controlEventSource.pIndex].changeAttrValue(
          this.controlEventSource.controlKeyOnJson, key);
      String tempText = this.controlEventSource.eventThrowerKey.getText();
      this.controlEventSource.eventThrowerKey.setText(
          tempText.substring(0, tempText.length() - 2) + KeyEvent.getKeyText(key) + ")");
    }

    if (this.controlEventSource.activelyListening) {
      this.controlEventSource.activelyListening = false;
      this.controlEventSource.eventThrowerKey.setBorder(null);
      this.settingKey.setText("");
    }
  }
}

/** ControlEventSource represents the source of a control event. */
class ControlEventSource {
  public String playerId;
  public int pIndex;
  public String controlKeyOnJson;
  public String settingUpKeyLabelText = "Setting Key for Player 1 > UP... (Press Esc to Cancel)";

  public CustomButton eventThrowerKey;
  public CustomButton previous_eventThrowerKey = null;

  public boolean activelyListening = false;

  CustomLabel settingKey;
  CustomControls customControls;

  /**
   * Constructs a ControlEventSource.
   *
   * @param settingKey the label indicating the setting key
   * @param customControls the CustomControls instance
   */
  public ControlEventSource(CustomLabel settingKey, CustomControls customControls) {
    this.settingKey = settingKey;
    this.customControls = customControls;
  }

  /** Initiates the action for setting a key control. */
  public void callAction() {
    this.activelyListening = true;
    this.settingKey.setText(this.settingUpKeyLabelText);
    this.eventThrowerKey.setBorder(BorderFactory.createLineBorder(Color.yellow, 2));
    // regain focus on the panel to be able to continue to listen key events
    this.customControls.setFocusable(true);
    this.customControls.requestFocusInWindow();

    if (this.previous_eventThrowerKey != null
        && this.previous_eventThrowerKey != this.eventThrowerKey) {
      this.previous_eventThrowerKey.setBorder(null);
    }
    this.previous_eventThrowerKey = eventThrowerKey;
  }
}

/** PlayerControl is a JPanel that allows setting custom controls for a player. */
class PlayerControl extends JPanel {
  public CustomButton button_up;
  public int pIndex;
  public CustomButton button_left;
  public CustomButton button_down;
  public CustomButton button_right;
  public CustomButton button_bomb;
  public CustomButton button_brick;

  /**
   * Constructs a PlayerControl panel.
   *
   * @param color the color associated with the player
   * @param playerId the identifier for the player
   * @param pIndex the index of the player
   * @param controlEventSource the source of control events
   */
  public PlayerControl(
      Color color, String playerId, int pIndex, ControlEventSource controlEventSource) {
    super();
    this.pIndex = pIndex;
    GridLayout gridlayout_p2 = new GridLayout(4, 1, 0, 0);
    this.setLayout(gridlayout_p2);
    this.setOpaque(true);

    String playerName =
        playerId.equals("player1")
            ? "Player 1"
            : playerId.equals("player2") ? "Player 2" : "Player 3";

    // label
    JPanel p2_label = new JPanel();
    p2_label.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 25));
    p2_label.setOpaque(true);
    p2_label.setAlignmentY(CENTER_ALIGNMENT);
    p2_label.setBackground(Slate._950);

    JLabel label_p2 = new JLabel(playerName);
    label_p2.setForeground(color);
    p2_label.add(label_p2);

    // upbutton
    JPanel p2_up = new JPanel();
    p2_up.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 25));
    p2_up.setOpaque(true);
    p2_up.setAlignmentY(CENTER_ALIGNMENT);
    p2_up.setBackground(Slate._950);

    this.button_up = new CustomButton("⬆");
    this.button_up.setForeground(color);
    this.button_up.setPreferredSize(new Dimension(80, 25));
    p2_up.add(this.button_up);

    // left bottom right
    JPanel p2_left_bottom_right = new JPanel();
    p2_left_bottom_right.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
    p2_left_bottom_right.setOpaque(true);
    p2_left_bottom_right.setAlignmentY(CENTER_ALIGNMENT);
    p2_left_bottom_right.setBackground(Slate._950);

    this.button_left = new CustomButton("⬅");
    this.button_left.setForeground(color);
    this.button_left.setPreferredSize(new Dimension(80, 25));

    this.button_down = new CustomButton("⬇");
    this.button_down.setForeground(color);
    this.button_down.setPreferredSize(new Dimension(80, 25));

    this.button_right = new CustomButton("➡");
    this.button_right.setForeground(color);
    this.button_right.setPreferredSize(new Dimension(80, 25));

    p2_left_bottom_right.add(this.button_left);
    p2_left_bottom_right.add(this.button_down);
    p2_left_bottom_right.add(this.button_right);

    // bomb button
    JPanel p2_bomb = new JPanel();
    p2_bomb.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 25));
    p2_bomb.setOpaque(true);
    p2_bomb.setAlignmentY(CENTER_ALIGNMENT);
    p2_bomb.setBackground(Slate._950);

    this.button_bomb = new CustomButton("bomb");
    this.button_bomb.setForeground(color);
    this.button_bomb.setPreferredSize(new Dimension(200, 25));
    p2_bomb.add(this.button_bomb);

    // brick button
    JPanel p2_brick = new JPanel();
    p2_brick.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 25));
    p2_brick.setOpaque(true);
    p2_brick.setAlignmentY(CENTER_ALIGNMENT);
    p2_brick.setBackground(Slate._950);

    this.button_brick = new CustomButton("brick");
    this.button_brick.setForeground(color);
    this.button_brick.setPreferredSize(new Dimension(200, 25));
    p2_brick.add(this.button_brick);

    // Add action listeners to the buttons
    button_up.addActionListener(
        e -> {
          controlEventSource.playerId = playerId;
          controlEventSource.pIndex = this.pIndex;
          controlEventSource.controlKeyOnJson = "go_up";
          controlEventSource.eventThrowerKey = button_up;
          controlEventSource.settingUpKeyLabelText = this.settingKeyLabelBuilder(playerName, "UP");
          controlEventSource.callAction();
        });
    button_left.addActionListener(
        e -> {
          controlEventSource.playerId = playerId;
          controlEventSource.pIndex = this.pIndex;
          controlEventSource.controlKeyOnJson = "go_left";
          controlEventSource.eventThrowerKey = button_left;
          controlEventSource.settingUpKeyLabelText =
              this.settingKeyLabelBuilder(playerName, "LEFT");
          controlEventSource.callAction();
        });
    button_down.addActionListener(
        e -> {
          controlEventSource.playerId = playerId;
          controlEventSource.pIndex = this.pIndex;
          controlEventSource.controlKeyOnJson = "go_down";
          controlEventSource.eventThrowerKey = button_down;
          controlEventSource.settingUpKeyLabelText =
              this.settingKeyLabelBuilder(playerName, "DOWN");
          controlEventSource.callAction();
        });
    button_right.addActionListener(
        e -> {
          controlEventSource.playerId = playerId;
          controlEventSource.pIndex = this.pIndex;
          controlEventSource.controlKeyOnJson = "go_right";
          controlEventSource.eventThrowerKey = button_right;
          controlEventSource.settingUpKeyLabelText =
              this.settingKeyLabelBuilder(playerName, "RIGHT");
          controlEventSource.callAction();
        });
    button_bomb.addActionListener(
        e -> {
          controlEventSource.playerId = playerId;
          controlEventSource.pIndex = this.pIndex;
          controlEventSource.controlKeyOnJson = "place_bomb";
          controlEventSource.eventThrowerKey = button_bomb;
          controlEventSource.settingUpKeyLabelText =
              this.settingKeyLabelBuilder(playerName, "BOMB");
          controlEventSource.callAction();
        });
    button_brick.addActionListener(
        e -> {
          controlEventSource.playerId = playerId;
          controlEventSource.pIndex = this.pIndex;
          controlEventSource.controlKeyOnJson = "place_brick";
          controlEventSource.eventThrowerKey = button_brick;
          controlEventSource.settingUpKeyLabelText =
              this.settingKeyLabelBuilder(playerName, "BRICK");
          controlEventSource.callAction();
        });

    // Add components to the panel
    this.add(p2_label);
    this.add(p2_up);
    this.add(p2_left_bottom_right);
    this.add(p2_bomb);
  }

  /**
   * Builds a label text indicating the key setting for a player.
   *
   * @param playerName the name of the player
   * @param btnName the name of the button
   * @return the label text
   */
  private String settingKeyLabelBuilder(String playerName, String btnName) {
    return "Setting Key for " + playerName + " > " + btnName + "... (Press Esc to Cancel)";
  }
}
