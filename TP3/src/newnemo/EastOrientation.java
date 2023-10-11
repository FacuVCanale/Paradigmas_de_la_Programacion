package newnemo;
public class EastOrientation extends Orientation {
    public Orientation turnLeft() {
        return new NorthOrientation();
    }

    public Orientation turnRight() {
        return new SouthOrientation();
    }

    public Position moveForward(Position position) {
        return new Position(position.getX() + 1, position.getY(), position.getZ());
    }

    public boolean equals(Object other) {
        return other instanceof EastOrientation;
    }

    public int hashCode() {
        return 1;
    }
}
