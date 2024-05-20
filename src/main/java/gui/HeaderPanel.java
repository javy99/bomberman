package gui;

import custom.Slate;
import java.awt.*;
import javax.swing.*;

/** A JPanel representing the header of a menu screen. */
class HeaderPanel extends JPanel {
  /** The label displaying the header text. */
  JLabel headerText;

  /** Constructs a new HeaderPanel with default header text "Main Menu". */
  public HeaderPanel() {
    super();
    this.setBackground(Slate._900);
    this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
    this.setPreferredSize(new Dimension(0, 40));
    headerText = new JLabel("Main Menu");
    headerText.setForeground(Slate._300);
    headerText.setPreferredSize(new Dimension(300, 40));
    headerText.setHorizontalAlignment(SwingConstants.CENTER);
    this.add(headerText);
  }

  /**
   * Changes the header text to the specified text.
   *
   * @param text the new header text
   */
  public void changeHeaderText(String text) {
    headerText.setText(text);
  }
}
