package gui;

import custom.Slate;
import entity.Player;
import entity.effects.Effect;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * The TimerAndActivePowerUpsPreview class provides a panel to display the game timer and active
 * power-ups of players during gameplay.
 */
public class TimerAndActivePowerUpsPreview extends JPanel {

  Game game;
  private JLabel timerLabel;
  private ArrayList<JPanel> playerPanels = new ArrayList<>();
  private Timer timer;

  /**
   * Constructs a TimerAndActivePowerUpsPreview object with the specified game.
   *
   * @param game The game object.
   */
  public TimerAndActivePowerUpsPreview(Game game) {
    super(new BorderLayout());
    this.game = game;
    setBackground(Slate._950);
    ArrayList<Player> players = game.getPlayers();
    updatePlayers(players);
    startTimer();
  }

  /**
   * Initializes the components of the panel based on the provided list of players.
   *
   * @param players The list of players.
   */
  private void initializeComponents(ArrayList<Player> players) {
    if (game == null) {
      throw new IllegalStateException(
          "Game object must be initialized before initializing components.");
    }

    resetTimer();

    long elapsed = System.currentTimeMillis() - game.getStartTime();
    long timeLeft = Math.max(game.gameDuration - elapsed, 0); // Ensuring timeLeft is not negative

    timerLabel = new JLabel(formatTime(timeLeft), SwingConstants.LEFT);
    timerLabel.setFont(new Font("Arial", Font.BOLD, 24));
    timerLabel.setForeground(Color.WHITE);
    add(timerLabel, BorderLayout.NORTH);

    JPanel playersPanel = new JPanel(new GridLayout(players.size(), 1));
    playersPanel.setBackground(Slate._950);

    for (Player player : players) {
      JPanel panel = createPlayerPanel(player);
      playersPanel.add(panel);
      playerPanels.add(panel); // Storing the panel for dynamic updates

      player.addDeathListener(
          deceasedPlayer -> {
            SwingUtilities.invokeLater(() -> removePlayerPanel(panel));
          });

      if (!player.isAlive) {
        removePlayerPanel(panel);
      }
    }

    add(playersPanel, BorderLayout.LINE_START);
  }

  /**
   * Creates a panel to display player information and active power-ups.
   *
   * @param player The player object.
   * @return The created panel.
   */
  private JPanel createPlayerPanel(Player player) {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBackground(Slate._950);
    panel.setBorder(new EmptyBorder(10, 5, 10, 5));

    JLabel nameLabel = new JLabel(player.name.toUpperCase(), SwingConstants.LEFT);
    nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
    nameLabel.setForeground(Color.WHITE);
    nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    panel.add(nameLabel);

    // separator
    JSeparator separator = new JSeparator();
    separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
    separator.setForeground(Color.GRAY);
    panel.add(separator);

    player.addPowerUpChangeListener(
        newPowerUps -> {
          SwingUtilities.invokeLater(() -> updatePlayerPowerUps(panel, newPowerUps));
        });

    player.addDeathListener(
        deceasedPlayer -> {
          SwingUtilities.invokeLater(() -> removePlayerPanel(panel));
        });

    JLabel speedLabel = addStatusLabel(panel, "SPEED", player.speed);
    JLabel blastRangeLabel = addStatusLabel(panel, "BLAST RANGE", player.blastRange);
    JLabel bombsNumLabel = addStatusLabel(panel, "SLOT", player.bombsNum);

    player.addAttributeChangeListener(
        () -> {
          SwingUtilities.invokeLater(
              () -> {
                speedLabel.setText("SPEED: " + player.speed);
                blastRangeLabel.setText("BLAST RANGE: " + player.blastRange);
                bombsNumLabel.setText("SLOT: " + player.bombsNum);
              });
        });

    return panel;
  }

  /**
   * Removes the specified player panel from the view.
   *
   * @param panel The player panel to be removed.
   */
  private void removePlayerPanel(JPanel panel) {
    playerPanels.remove(panel);
    this.remove(panel);
    this.revalidate();
    this.repaint();
  }

  /**
   * Adds a status label to the player panel.
   *
   * @param panel The player panel.
   * @param attribute The attribute name.
   * @param value The attribute value.
   * @return The added label.
   */
  private JLabel addStatusLabel(JPanel panel, String attribute, int value) {
    JLabel label = new JLabel(attribute + ": " + value, SwingConstants.LEFT);
    label.setFont(new Font("Arial", Font.PLAIN, 12));
    label.setForeground(Color.LIGHT_GRAY);
    panel.add(label);
    return label;
  }

  /**
   * Updates the power-ups displayed on the player panel.
   *
   * @param panel The player panel.
   * @param powerUps The list of power-ups.
   */
  private void updatePlayerPowerUps(JPanel panel, ArrayList<Effect> powerUps) {
    SwingUtilities.invokeLater(
        () -> {
          JPanel iconPanel = null;
          if (panel.getComponentCount() > 2) {
            Component possibleIconPanel = panel.getComponent(2);
            if (possibleIconPanel instanceof JPanel) {
              iconPanel = (JPanel) possibleIconPanel;
            }
          }

          int rows = (int) Math.ceil(powerUps.size() / 4.0);

          if (iconPanel == null) {
            iconPanel = new JPanel(new GridLayout(rows, 4, 5, 5));
            iconPanel.setBackground(panel.getBackground());
            if (panel.getComponentCount() > 2) {
              panel.add(iconPanel, 2);
            } else {
              panel.add(iconPanel);
            }
          } else {
            iconPanel.removeAll();
            iconPanel.setLayout(new GridLayout(rows, 4, 5, 5));
          }

          for (int i = 0; i < powerUps.size(); i++) {
            Effect effect = powerUps.get(i);
            ImageIcon icon =
                new ImageIcon(
                    effect.getPowerupIcon().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
            JLabel label = new JLabel(icon);
            iconPanel.add(label);
          }

          int totalCells = rows * 4;
          for (int i = powerUps.size(); i < totalCells; i++) {
            iconPanel.add(new JLabel());
          }

          panel.revalidate();
          panel.repaint();
        });
  }

  /** Resets the timer. */
  public void resetTimer() {
    if (timer != null) {
      timer.stop();
    }
    startTimer();
  }

  /** Starts the timer to update the game time. */
  private void startTimer() {
    int delay = 1000;
    ActionListener taskPerformer =
        new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            long elapsed = System.currentTimeMillis() - game.getStartTime();
            long timeLeft = game.gameDuration - elapsed;
            if (timeLeft < 0) {
              timeLeft = 0;
              ((Timer) evt.getSource()).stop();
            }
            updateTimer(formatTime(timeLeft), timeLeft);
          }
        };
    timer = new Timer(delay, taskPerformer);
    timer.start();
  }

  private String formatTime(long millis) {
    long seconds = (millis / 1000) % 60;
    long minutes = (millis / (1000 * 60)) % 60;
    long hours = (millis / (1000 * 60 * 60)) % 24;

    if (hours > 0) {
      return String.format("Time: %02d:%02d:%02d", hours, minutes, seconds);
    } else {
      return String.format("Time: %02d:%02d", minutes, seconds);
    }
  }

  public void updateTimer(String time, long timeLeft) {
    if (timeLeft <= 11000) { // less than or equal to 10 seconds
      long secondsLeft = (timeLeft / 1000) % 60;
      if (secondsLeft % 2 == 0) {
        timerLabel.setForeground(Color.RED);
      } else {
        timerLabel.setForeground(Color.WHITE);
      }
    } else {
      timerLabel.setForeground(Color.WHITE);
    }
    timerLabel.setText("Time: " + time);
  }

  public void clearPlayerPanels() {
    for (JPanel panel : playerPanels) {
      this.remove(panel);
    }
    playerPanels.clear();
    this.revalidate();
    this.repaint();
  }

  public void updatePlayers(ArrayList<Player> players) {
    clearPlayerPanels();
    initializeComponents(players);
  }
}
