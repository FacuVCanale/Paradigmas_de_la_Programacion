package newnemo;

public class SouthOrientation extends Orientation {
    public Orientation turnLeft() {
        return new EastOrientation();
    }

    public Orientation turnRight() {
        return new WestOrientation();
    }

    public Position moveForward(Position position) {
        return new Position(position.getX(), position.getY() - 1, position.getZ());
    }

    public boolean equals(Object other) {
        return other instanceof SouthOrientation;
    }

    public int hashCode() {
        return 3;
    }
}
