package gui;

import custom.CustomButton;
import custom.Slate;
import java.awt.*;
import javax.swing.*;

/** A JPanel representing the Game Information screen. */
class GameInfo extends JPanel {
  /** The "Go Back" button. */
  CustomButton btn_newGoBack;

  /**
   * Constructs a new GameInfo panel. Initializes the panel with rules and other game-related
   * information.
   */
  public GameInfo() {
    super();
    this.setBackground(Slate._950);

    btn_newGoBack = new CustomButton("Go Back");
    btn_newGoBack.setPreferredSize(new Dimension(100, 50));

    JPanel contentWrap = new JPanel();
    contentWrap.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 5));
    contentWrap.setBackground(Slate._950);
    contentWrap.setPreferredSize(new Dimension(900, 500));

    /** Inner class representing a styled JTextArea for displaying game information text. */
    class GameInfoText extends JTextArea {
      /**
       * Constructs a new GameInfoText.
       *
       * @param text the text to display
       * @param textType the type of text (e.g., "h2" for headers, "p" for paragraphs)
       */
      public GameInfoText(String text, String textType) {
        super(text);
        this.setOpaque(true);
        this.setForeground(Slate._300);
        this.setBackground(Slate._950);
        this.setEditable(false);
        this.setPreferredSize(new Dimension(900, 50));
        this.setLineWrap(true);
        this.setAlignmentY(TOP_ALIGNMENT);

        this.setSizes(textType);
      }

      /**
       * Sets the font size and preferred size based on the type of text.
       *
       * @param textType the type of text (e.g., "h2" for headers, "p" for paragraphs)
       */
      private void setSizes(String textType) {
        if (textType.equals("h2")) {
          this.setFont(new Font("Arial", Font.PLAIN, 26));
          this.setPreferredSize(new Dimension(900, 30));
        } else if (textType.equals("p")) {
          this.setFont(new Font("Arial", Font.PLAIN, 14));
          this.setPreferredSize(new Dimension(900, 90));
        }
      }
    }

    String description =
        "Lorem ipsum dolor sit amet consectetur. Tempor urna ut at scelerisque adipiscing elementum leo feugiat.\n"
            + "1. Condimentum nunc urna enim risus tortor a lobortis vestibulum. Eget odio mollis volutpat ipsum. Amet tincidunt vel facilisi. \n"
            + "2. Habitant lorem ac nulla est vestibulum nulla. Sed viverra velit a id ut ac. Malesuada et in placerat egestas eu vitae. \n";

    GameInfoText txt_rules = new GameInfoText("Rules", "header");
    txt_rules.setFont(new Font("Arial", Font.PLAIN, 40));

    GameInfoText txt_h2_1 = new GameInfoText("Monster Types", "h2");

    GameInfoText txt_p_1 = new GameInfoText(description, "p");

    GameInfoText txt_h2_2 = new GameInfoText("Advanced PowerUps", "h2");

    GameInfoText txt_p_2 = new GameInfoText(description, "p");

    GameInfoText txt_h2_3 = new GameInfoText("Hindering Curses", "h2");

    GameInfoText txt_p_3 = new GameInfoText(description, "p");

    contentWrap.add(btn_newGoBack);
    contentWrap.add(txt_rules);
    contentWrap.add(txt_h2_1);
    contentWrap.add(txt_p_1);
    contentWrap.add(txt_h2_2);
    contentWrap.add(txt_p_2);
    contentWrap.add(txt_h2_3);
    contentWrap.add(txt_p_3);

    this.add(contentWrap, BorderLayout.CENTER);
  }
}
