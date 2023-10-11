package newnemo;

public class Position {
    private int x;
    private int y;
    private int z;
    public Position(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public boolean equals(Object other) {
        if (other instanceof Position) {
            Position aPosition = (Position) other;
            return x == aPosition.x && y == aPosition.y && z == aPosition.z;
        }
        return false;
    }

    public int hashCode() {
        return Integer.hashCode(x) + Integer.hashCode(y) + Integer.hashCode(z);
    }
}
