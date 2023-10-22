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
        return other instanceof Position &&
                this.getX() == ((Position) other).getX() &&
                this.getY() == ((Position) other).getY();
    }

    private int getX() {
        return this.x;
    }

    private int getY() {
        return this.y;
    }

    public int hashCode() {
        return Integer.hashCode(this.getX()) + Integer.hashCode(this.getY());
    }

    public String toString() {
        return "(" + this.getX() + ", " + this.getY() + ")";
    }
}
