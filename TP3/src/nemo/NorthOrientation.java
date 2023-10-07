package nemo;

public class NorthOrientation extends Orientation {

    public NorthOrientation() {
        this.orientationName = "N";
    }
    public Orientation rotateLeft() {
        return new WestOrientation();
    }

    public Orientation rotateRight() {
        return new EastOrientation();
    }

    public Coordinate moveForward(Coordinate position) {
        return new Coordinate(position.x, position.y + 1, position.z);
    }

}

