package gui;

import java.awt.event.KeyEvent;

/**
 * CustomControl class represents a custom control scheme for a player. It includes key bindings for
 * various actions such as moving and placing objects.
 */
public class CustomControl {
  /** Identifier for the control scheme */
  public String id;

  /** Key code for moving up */
  public int go_up;

  /** Key code for moving left */
  public int go_left;

  /** Key code for moving down */
  public int go_down;

  /** Key code for moving right */
  public int go_right;

  /** Key code for placing a bomb */
  public int place_bomb;

  /** Key code for placing a brick */
  public int place_brick;

  /**
   * Constructor to create a CustomControl with specified key bindings.
   *
   * @param id Identifier for the control scheme
   * @param go_up Key code for moving up
   * @param go_left Key code for moving left
   * @param go_down Key code for moving down
   * @param go_right Key code for moving right
   * @param place_bomb Key code for placing a bomb
   * @param place_brick Key code for placing a brick
   */
  public CustomControl(
      String id,
      int go_up,
      int go_left,
      int go_down,
      int go_right,
      int place_bomb,
      int place_brick) {
    this.id = id;
    this.go_up = go_up;
    this.go_left = go_left;
    this.go_down = go_down;
    this.go_right = go_right;
    this.place_bomb = place_bomb;
    this.place_brick = place_brick;
  }

  /**
   * Returns a string representation of the CustomControl object.
   *
   * @return a string containing the id and key codes for the actions
   */
  @Override
  public String toString() {
    return this.id
        + ": "
        + this.go_up
        + ", "
        + this.go_left
        + ", "
        + this.go_down
        + ", "
        + this.go_right
        + ", "
        + this.place_bomb
        + ", "
        + this.place_brick;
  }

  /**
   * Changes the value of a specified attribute.
   *
   * @param attr the attribute to change (e.g., "go_up", "go_left")
   * @param value the new key code value for the attribute
   */
  public void changeAttrValue(String attr, int value) {
    if (attr.equals("go_up")) {
      this.go_up = value;
    } else if (attr.equals("go_left")) {
      this.go_left = value;
    } else if (attr.equals("go_down")) {
      this.go_down = value;
    } else if (attr.equals("go_right")) {
      this.go_right = value;
    } else if (attr.equals("place_bomb")) {
      this.place_bomb = value;
    } else if (attr.equals("place_brick")) {
      this.place_brick = value;
    }
  }

  /**
   * Updates the button texts in the PlayerControl to display the current key bindings.
   *
   * @param playerControl the PlayerControl object whose button texts will be updated
   */
  public void changePlayerControlBtnsTexts(PlayerControl playerControl) {
    playerControl.button_up.setText("⬆ (" + KeyEvent.getKeyText(this.go_up) + ")");
    playerControl.button_left.setText("⬅ (" + KeyEvent.getKeyText(this.go_left) + ")");
    playerControl.button_down.setText("⬇ (" + KeyEvent.getKeyText(this.go_down) + ")");
    playerControl.button_right.setText("➡ (" + KeyEvent.getKeyText(this.go_right) + ")");
    playerControl.button_bomb.setText("bomb (" + KeyEvent.getKeyText(this.place_bomb) + ")");
    playerControl.button_brick.setText("brick (" + KeyEvent.getKeyText(this.place_brick) + ")");
  }
}
