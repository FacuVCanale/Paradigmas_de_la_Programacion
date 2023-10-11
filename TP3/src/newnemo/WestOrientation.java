package newnemo;

public class WestOrientation extends Orientation {
    public Orientation turnLeft() {
        return new SouthOrientation();
    }

    public Orientation turnRight() {
        return new NorthOrientation();
    }

    public Position moveForward(Position position) {
        return new Position(position.getX() - 1, position.getY(), position.getZ());
    }

    public boolean equals(Object other) {
        return other instanceof WestOrientation;
    }

    public int hashCode() {
        return 2;
    }
}
