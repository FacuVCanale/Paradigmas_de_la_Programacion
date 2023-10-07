package nemo;

import javax.naming.NameNotFoundException;

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

    public Coordinate moveForward(Coordinate position) {
        return new Coordinate(position.x - 1, position.y, position.z);
    }
}
