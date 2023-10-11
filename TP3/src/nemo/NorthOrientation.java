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

    public Position moveForward(Position position) {
        return new Position(position.x, position.y + 1, position.z);
    }

}

