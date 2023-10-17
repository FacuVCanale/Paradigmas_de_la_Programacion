package nemo;

public class SouthOrientation extends Orientation {
    public Orientation turnLeft() {
        return new EastOrientation();
    }

    public Orientation turnRight() {
        return new WestOrientation();
    }

    public Position getForwardStepInThisOrientation() {
        return new Position(0, -1);
    }

    public boolean equals(Object other) {
        return other instanceof SouthOrientation;
    }

    public int hashCode() {
        return 3;
    }
}
