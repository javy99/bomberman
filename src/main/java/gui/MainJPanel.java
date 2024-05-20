package gui;

import custom.Slate;
import javax.swing.*;

/** JPanel representing the main content area of the application. */
public class MainJPanel extends JPanel {

  /**
   * Constructs a new MainJPanel. Sets the background color to Slate._950 and adds a border with the
   * same color.
   */
  public MainJPanel() {
    super();
    this.setBackground(Slate._950);
    this.setBorder(BorderFactory.createLineBorder(Slate._950, 25));
  }
}
