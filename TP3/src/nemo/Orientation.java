package nemo;

public abstract class Orientation {
    public abstract Orientation turnLeft();

    public abstract Orientation turnRight();

    public abstract Position getForwardStepInThisOrientation();

    public abstract boolean equals(Object other);

    public abstract int hashCode();
}
