package gui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import custom.CustomLabel;
import custom.Slate;
import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomControlsTest {

  private CustomControls customControls;
  private ScreenNavigator navigator;

  @BeforeEach
  public void setUp() {
    navigator = mock(ScreenNavigator.class);
    customControls = new CustomControls(navigator);
  }

  @Test
  public void testInitialSettings() {
    assertEquals(Slate._950, customControls.getBackground());
    assertEquals(1, customControls.getComponentCount());
  }

  @Test
  public void testKeyBindings() {
    CustomControlKeyHandler keyHandler =
        new CustomControlKeyHandler(
            new ControlEventSource(new CustomLabel(""), customControls),
            new CustomLabel(""),
            CustomControlsJson.readControlsFromJson());

    KeyEvent keyEvent =
        new KeyEvent(
            customControls,
            KeyEvent.KEY_RELEASED,
            System.currentTimeMillis(),
            0,
            KeyEvent.VK_A,
            'A');
    keyHandler.keyReleased(keyEvent);
  }
}
