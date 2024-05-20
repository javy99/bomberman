package custom;

import javax.swing.*;

/** CustomLabel class extends JLabel to create a custom-styled label with a specific text color. */
public class CustomLabel extends JLabel {

  /**
   * Constructor to create a CustomLabel with specified text.
   *
   * @param text the text to be displayed on the label.
   */
  public CustomLabel(String text) {
    super(text);
    this.setForeground(Slate._300);
  }
}
