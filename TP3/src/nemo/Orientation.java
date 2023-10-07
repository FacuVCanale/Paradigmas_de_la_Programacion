package nemo;

public abstract class Orientation {

    public String orientationName;

    public abstract Orientation rotateLeft();
    public abstract Orientation rotateRight();
    public abstract Coordinate moveForward(Coordinate position);

    @Override
    public boolean equals(Object other) {
        if (other instanceof Orientation) {
            Orientation otherOrientation = (Orientation) other;
            return this.orientationName.equals(otherOrientation.orientationName);
        }
        return false;
    }

}
