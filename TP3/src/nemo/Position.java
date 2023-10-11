package nemo;

public class Position {
    public int x;
    public int y;
    public int z;

    public Position(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

    public boolean equals(Object anObject) {
        if (anObject instanceof Position) {
            Position aCoordinate = (Position) anObject;
            return x == aCoordinate.x && y == aCoordinate.y && z == aCoordinate.z;
        }
        return false;
    }





    public int hashCode() {
        return Integer.hashCode(x) + Integer.hashCode(y) + Integer.hashCode(z);
    }
}
