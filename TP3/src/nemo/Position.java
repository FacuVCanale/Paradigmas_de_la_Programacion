package nemo;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position sum(Position other) {
        return new Position(this.getX() + other.getX(), this.getY() + other.getY());
    }

    public boolean equals(Object other) {
        if (other instanceof Position) {
            Position aPosition = (Position) other;
            return this.getX() == aPosition.getX() && this.getY() == aPosition.getY();
        }
        return false;
    }

    private int getX() {
        return x;
    }
    private int getY() {
        return y;
    }


    public int hashCode() {
        return Integer.hashCode(this.getX()) + Integer.hashCode(this.getY());
    }

    public String toString() {
        return "(" + this.getX() + ", " + this.getY() + ")";
    }
}
