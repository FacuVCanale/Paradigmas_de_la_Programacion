package nemo;

public class WestOrientation extends Orientation {
    public Orientation turnLeft() {
        return new SouthOrientation();
    }

    public Orientation turnRight() {
        return new NorthOrientation();
    }

    public Position getForwardStepInThisOrientation() {
        return new Position(-1, 0);
    }

    public boolean equals(Object other) {
        return other instanceof WestOrientation;
    }

    public int hashCode() {
        return 2;
    }
}
