package nemo;

public class SouthOrientation extends Orientation {

    public SouthOrientation() {
        this.orientationName = "S";
    }
    public Orientation rotateLeft() {
        return new EastOrientation();
    }

    public Orientation rotateRight() {
        return new WestOrientation();
    }

    public Coordinate moveForward(Coordinate position) {
        return new Coordinate(position.x, position.y - 1, position.z);
    }
}
