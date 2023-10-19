package nemo;

public abstract class Orientation {

    public abstract Orientation turnLeft();

    public abstract Orientation turnRight();

    public abstract Position getForwardStepInThisOrientation();

    public abstract boolean equals(Object other);

    public abstract int hashCode();
}

class NorthOrientation extends Orientation {
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

class EastOrientation extends Orientation {
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

class SouthOrientation extends Orientation {
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

class WestOrientation extends Orientation {
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

