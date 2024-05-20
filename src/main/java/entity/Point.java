package entity;

/** Represents a point in a two-dimensional coordinate system. */
public class Point {
  int x;
  int y;

  /**
   * Constructs a new point with the specified coordinates.
   *
   * @param x The x-coordinate of the point.
   * @param y The y-coordinate of the point.
   */
  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Gets the x-coordinate of the point.
   *
   * @return The x-coordinate of the point.
   */
  public int getX() {
    return x;
  }

  /**
   * Gets the y-coordinate of the point.
   *
   * @return The y-coordinate of the point.
   */
  public int getY() {
    return y;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  /**
   * Calculates the Euclidean distance between this point and another point.
   *
   * @param other The other point.
   * @return The distance between this point and the other point.
   */
  public double distance(Point other) {
    int dx = this.x - other.x;
    int dy = this.y - other.y;
    return Math.sqrt(dx * dx + dy * dy);
  }
}
