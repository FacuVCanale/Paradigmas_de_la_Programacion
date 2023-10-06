package nemo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NemoTests {


    @Test
    public void test01NemoCoordsAreOrigin() {
        Nemo nemo = new Nemo();
        assertEquals(new Coordinate(0,0,0), nemo.coordinates);
    }

    @Test
    public void test02SubmarineOrientationIsNorth() {
        Nemo nemo = new Nemo();
        assertEquals("N", nemo.orientation);
    }

    @Test
    public void test03SubmarineCanGoDown() {
        Nemo nemo = new Nemo();
        nemo.d();
        assertEquals(new Coordinate(0,0,-1), nemo.coordinates);
    }

    @Test
    public void test04SubmarineCanGoUp() {
        Nemo nemo = new Nemo();
        nemo.u();
        assertEquals(new Coordinate(0,0,1), nemo.coordinates);
    }

    @Test
    public void test05SubmarineCanTurnLeft() {
        Nemo nemo = new Nemo();
        nemo.l();
        assertEquals("W", nemo.orientation);
    }

    @Test
    public void test06SubmarineCanTurnRight() {
        Nemo nemo = new Nemo();
        nemo.r();
        assertEquals("E", nemo.orientation);
    }

    @Test
    public void test07SubmarineCanGoForward() {
        Nemo nemo = new Nemo();
        nemo.f();
        assertEquals(new Coordinate(0,1,0), nemo.coordinates);
    }

    @Test
    public void test08SubmarineCanGoDownAndUp() {
        Nemo nemo = new Nemo();
        nemo.d().u();
        assertEquals(new Coordinate(0,0,0), nemo.coordinates);
    }

    @Test
    public void test10SubmarineCanGoLeftAndRight() {
        Nemo nemo = new Nemo();
        nemo.l().r();
        assertEquals("N", nemo.orientation);
    }

}
