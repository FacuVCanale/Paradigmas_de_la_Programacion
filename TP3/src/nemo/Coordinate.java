package nemo;

public class Coordinate {
    public int x;
    public int y;
    public int z;

    public Coordinate(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

    public boolean equals(Object anObject) {
        if (anObject instanceof Coordinate) {
            Coordinate aCoordinate = (Coordinate) anObject;
            return x == aCoordinate.x && y == aCoordinate.y && z == aCoordinate.z;
        }
        return false;
    }

    public int hashCode() {
        return Integer.hashCode(x) + Integer.hashCode(y) + Integer.hashCode(z);
    }
}
