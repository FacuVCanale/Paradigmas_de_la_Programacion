package nemo;

public class EastOrientation extends Orientation {

    public EastOrientation() {
        this.orientationName = "E";
    }
    public Orientation rotateLeft() {
        return new NorthOrientation();
    }

    public Orientation rotateRight() {
        return new SouthOrientation();
    }

    public Position moveForward(Position position) {
        return new Position(position.x + 1, position.y, position.z);
    }
}
