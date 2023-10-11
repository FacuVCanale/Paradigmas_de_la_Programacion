package newnemo;

public abstract class Orientation {
    public abstract Orientation turnLeft();

    public abstract Orientation turnRight();

    public abstract Position moveForward(Position position);

    public abstract boolean equals(Object other);

    public abstract int hashCode();
}
