package nemo;

public class WestOrientation extends Orientation {

    public WestOrientation() {
        this.orientationName = "W";
    }

    public Orientation rotateLeft() {
        return new SouthOrientation();
    }

    public Orientation rotateRight() {
        return new NorthOrientation();
    }

    public Position moveForward(Position position) {
        return new Position(position.x - 1, position.y, position.z);
    }
}
