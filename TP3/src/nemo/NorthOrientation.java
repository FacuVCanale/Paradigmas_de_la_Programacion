package nemo;

public class NorthOrientation extends Orientation {
    public Orientation turnLeft() {
        return new WestOrientation();
    }

    public Orientation turnRight() {
        return new EastOrientation();
    }

    public Position getForwardStepInThisOrientation() {
        return new Position(0, 1);
    }

    public boolean equals(Object other) {
        return other instanceof NorthOrientation;
    }

    public int hashCode() {
        return 0;
    }
}

