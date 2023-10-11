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

    public Position turnUp() {
        if (z == 0) {
            return new Position(this.getX(), this.getY(), 0);
        }
        return new Position(this.getX(), this.getY(), this.getZ() + 1);
    }

    public Position turnDown() {
        return new Position(this.getX(), this.getY(), this.getZ() - 1);
    }

    /*public boolean canShoot() {
        return this.getZ() == 0 || this.getZ() == 1;
    }*/

    public boolean equals(Object other) {
        if (other instanceof Position) {
            Position aPosition = (Position) other;
            return this.getX() == aPosition.getX() && this.getY() == aPosition.getY() && this.getZ() == aPosition.getZ();
        }
        return false;
    }

    public int hashCode() {
        return Integer.hashCode(this.getX()) + Integer.hashCode(this.getY()) + Integer.hashCode(this.getZ());
    }

    public String toString() {
        return "(" + this.getX() + ", " + this.getY() + ", " + this.getZ() + ")";
    }
}
