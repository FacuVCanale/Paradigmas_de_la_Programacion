package nemo;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean equals(Object other) {
        if (other instanceof Position) {
            Position aPosition = (Position) other;
            return this.getX() == aPosition.getX() && this.getY() == aPosition.getY();
        }
        return false;
    }

    public int hashCode() {
        return Integer.hashCode(this.getX()) + Integer.hashCode(this.getY());
    }

    public String toString() {
        return "(" + this.getX() + ", " + this.getY() + ")";
    }
}
