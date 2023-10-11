package newnemo;

public class NorthOrientation extends Orientation {
    public Orientation turnLeft() {
        return new WestOrientation();
    }

    public Orientation turnRight() {
        return new EastOrientation();
    }

    public Position moveForward(Position position) {
        return new Position(position.getX(), position.getY() + 1, position.getZ());
    }

    public boolean equals(Object other) {
        return other instanceof NorthOrientation;
    }

    public int hashCode() {
        return 0;
    }
}
