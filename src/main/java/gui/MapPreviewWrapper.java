package gui;

import java.awt.*;
import javax.swing.*;

/**
 * The MapPreviewWrapper class represents a panel that wraps a MapPreview component. It provides a
 * method to set and display a MapPreview within it.
 */
class MapPreviewWrapper extends JPanel {

  /** Constructs a new MapPreviewWrapper panel. */
  public MapPreviewWrapper() {
    super();
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
  }

  /**
   * Sets the MapPreview to be displayed within this wrapper panel.
   *
   * @param mapPreview The MapPreview component to be displayed.
   */
  public void setMapPreview(MapPreview mapPreview) {
    this.removeAll();
    this.add(mapPreview);
    this.revalidate();
    this.repaint();
  }
}
