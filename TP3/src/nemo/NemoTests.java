package nemo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;

public class NemoTests {

    private Orientation north;

    private Coordinate origin;

    private Nemo nemo;

    @BeforeEach
    public void setUp() {
        north  = new NorthOrientation();
        origin = new Coordinate(0,0,0);
        nemo = new Nemo(origin, north, 1);
    }

    @Test
    public void test01NemoCoordsAreOrigin() {
        assertEquals(new Coordinate(0,0,0), nemo.position);
    }

    @Test
    public void test02SubmarineOrientationIsNorth() {
        assertEquals(new NorthOrientation(), nemo.orientation);
    }

    @Test
    public void test03SubmarineCanGoDown() {
        nemo.receiveCommands("d");
        assertEquals(new Coordinate(0,0,-1), nemo.position);
    }

    @Test
    public void test04SubmarineCanGoUp() {
        nemo.receiveCommands("u");
        assertEquals(new Coordinate(0,0,1), nemo.position);
    }

    @Test
    public void test05SubmarineCanTurnLeft() {
        nemo.receiveCommands("l");
        assertEquals(new WestOrientation(), nemo.orientation);
    }

    @Test
    public void test06SubmarineCanTurnRight() {
        nemo.receiveCommands("r");
        assertEquals(new EastOrientation(), nemo.orientation);
    }

    @Test
    public void test07SubmarineCanGoForward() {
      nemo.receiveCommands("f");
      assertEquals(new Coordinate(0,1,0), nemo.position);
    }

    @Test
    public void test08SubmarineCanGoDownAndUp() {
        nemo.receiveCommands("du");
        assertEquals(new Coordinate(0,0,0), nemo.position);
    }

    @Test
    public void test10SubmarineCanGoLeftAndRight() {
        Orientation originalOrientation = nemo.orientation;
        nemo.receiveCommands("lr");
        assertEquals(originalOrientation, nemo.orientation);
    }

}
