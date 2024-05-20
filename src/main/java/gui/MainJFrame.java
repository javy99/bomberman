package gui;

import custom.Slate;
import java.awt.*;
import javax.swing.*;

/** The main JFrame for the Blaha Blaster game application. */
public class MainJFrame extends JFrame {
  /** The header panel displayed at the top of the frame. */
  HeaderPanel headerPanel;

  /**
   * Constructs a new MainJFrame. Sets up the frame with default settings and adds the header panel.
   */
  public MainJFrame() {
    super();
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("Blaha Blaster");
    this.setResizable(false);
    this.setSize(1150, 700);
    this.setLocationRelativeTo(null);
    this.setLayout(new BorderLayout());
    this.getContentPane().setBackground(Slate._950);

    headerPanel = new HeaderPanel();
    this.add(headerPanel, BorderLayout.NORTH);
  }
}
