package gui;

import custom.CustomButton;
import custom.Slate;
import java.awt.*;
import javax.swing.*;

/**
 * The MainMenu class represents the main menu panel of the application. It contains buttons for
 * various functionalities and a title label.
 */
class MainMenu extends JPanel {
  CustomButton btn_newGame;
  CustomButton btn_mapsSettings;
  CustomButton btn_gameInfo;
  CustomButton btn_exit;

  CustomButton btn_customControls;

  /** Constructs a new MainMenu panel. */
  public MainMenu() {
    super();
    this.setLayout(new GridLayout(1, 2, 25, 25));
    this.setBackground(Slate._950);

    JPanel mainMenuSidebar = new JPanel();
    mainMenuSidebar.setLayout(new GridLayout(5, 1, 25, 25));
    mainMenuSidebar.setBackground(Slate._950);
    mainMenuSidebar.setBorder(BorderFactory.createLineBorder(Slate._950, 25));

    btn_newGame = new CustomButton("New Game ->");
    btn_newGame.setPreferredSize(new Dimension(100, 100));

    btn_mapsSettings = new CustomButton("Maps Settings");
    btn_mapsSettings.setPreferredSize(new Dimension(100, 100));

    btn_customControls = new CustomButton("Customize Controls");
    btn_customControls.setPreferredSize(new Dimension(100, 100));

    btn_gameInfo = new CustomButton("Game Info");
    btn_gameInfo.setPreferredSize(new Dimension(100, 100));

    btn_exit = new CustomButton("Exit");
    btn_exit.setPreferredSize(new Dimension(100, 100));

    mainMenuSidebar.add(btn_newGame);
    mainMenuSidebar.add(btn_mapsSettings);
    mainMenuSidebar.add(btn_customControls);
    mainMenuSidebar.add(btn_gameInfo);
    mainMenuSidebar.add(btn_exit);

    this.add(mainMenuSidebar);

    JLabel blahaText = new JLabel("Blaha Blaster");
    blahaText.setOpaque(true);
    blahaText.setForeground(Slate._300);
    blahaText.setBackground(Slate._950);
    blahaText.setPreferredSize(new Dimension(450, 100));
    blahaText.setHorizontalAlignment(SwingConstants.CENTER);
    blahaText.setFont(new Font("Bradley Hand", Font.PLAIN, 50));

    this.add(blahaText);
  }
}
