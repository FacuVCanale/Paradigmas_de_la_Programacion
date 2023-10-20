package nemo;

public class EastOrientation extends Orientation {
    public Orientation turnLeft() {
        return new NorthOrientation();
    }

    public Orientation turnRight() {
        return new SouthOrientation();
    }

    public Position getForwardStepInThisOrientation() {
        return new Position(1, 0);
    }

    public boolean equals(Object other) {
        return other instanceof EastOrientation;
    }

    public int hashCode() {
        return 1;
    }
}

