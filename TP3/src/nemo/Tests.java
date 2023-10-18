package nemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.function.Executable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Tests {
    private Position origin;
    private Orientation north;
    private Submarine nemo;

    @BeforeEach
    public void setUp() {
        origin = new Position(0, 0);
        north = new NorthOrientation();
        nemo = new Submarine(origin, north);
    }

    @Test void testPositionsCanBeCompared() {
        assertEquals(new Position(0, 0),  origin);
    }

    @Test void testNemoHasItsPosition() {
        assertEquals(origin, nemo.position());
    }

    @Test void testOrientationCanBeCompared() {
        assertEquals(new NorthOrientation(), north);
    }

    @Test void testNemoHasItsOrientation() {
        assertEquals(north, nemo.orientation());
    }

    @Test void testNavigatorsCanBeCompared() {
        DiveNavigator surfaceNavigator = new SurfaceNavigator();
        DiveNavigator firstLevelNavigator = new ShootableLevelNavigator();
        assertEquals(new SurfaceNavigator(), surfaceNavigator);
        assertNotEquals(surfaceNavigator, firstLevelNavigator);
    }

    @Test void testNemoIsInSurface() {
        assertEquals(0, nemo.depth());
        assertEquals(new SurfaceNavigator(), nemo.diveNavigator());
    }

    @Test void testNemoCanGoForward() {
        nemo.runCommands("f");
        assertEquals(new Position(0, 1), nemo.position());
    }

    @Test void testNemoCanTurnLeft() {
        nemo.runCommands("l");
        assertEquals(new WestOrientation(), nemo.orientation());
    }

    @Test void testNemoCanTurnRight() {
        nemo.runCommands("r");
        assertEquals(new EastOrientation(), nemo.orientation());
    }

    @Test void testNemoCanTurnRightAndGoForward() {
        nemo.runCommands("rf");
        assertEquals(new Position(1, 0), nemo.position());
    }

    @Test void testNemoCanGoBackwardsRotatingTwice() {
        nemo.runCommands("llf");
        assertEquals(new Position(0, -1), nemo.position());
    }

    @Test void testNemoCanLaunchInSurface() {
        nemo.runCommands("m");
        assertEquals(0, nemo.depth());
        assertEquals(new SurfaceNavigator(), nemo.diveNavigator());
    }

    @Test void testNemoCanGoDown() {
        nemo.runCommands("d");
        assertEquals(1, nemo.depth());
        assertEquals(new ShootableLevelNavigator(), nemo.diveNavigator());
    }

    @Test void testNemoCanGoDownAndUp() {
        nemo.runCommands("du");
        assertEquals(0, nemo.depth());
        assertEquals(new SurfaceNavigator(), nemo.diveNavigator());
    }

    @Test void testNemoRemainAtSurfaceWhenGoingUp() {
        nemo.runCommands("u");
        assertEquals(0, nemo.depth());
        assertEquals(new SurfaceNavigator(), nemo.diveNavigator());
    }

    @Test void testNemoCanGoToUnshootableLevelAndGoUpAndRemainThere() {
        nemo.runCommands("ddddu");
        assertEquals(3, nemo.depth());
        assertEquals(new UnshootableLevelNavigator(), nemo.diveNavigator());
    }

    @Test void testNemoCanGoToUnshootableLevelAndThenToSurface() {
        nemo.runCommands("dddduuuuu");
        assertEquals(0, nemo.depth());
        assertEquals(new SurfaceNavigator(), nemo.diveNavigator());
    }

    @Test void testNemoExplodesLaunchingInUnshootableDepth() {
        assertThrowsLike(() -> nemo.runCommands("ddm"), UnshootableLevelNavigator.chocolateError);
    }

    @Test void testNemoCanFollowCommandsAndThenExplode() {
        nemo.runCommands("ffdd");
        assertEquals(new Position(0, 2), nemo.position());
        assertThrowsLike(() -> nemo.runCommands("ffm"), UnshootableLevelNavigator.chocolateError);
    }

    private void assertThrowsLike(Executable executable, String message) {
        assertEquals(message, assertThrows(Exception.class, executable).getMessage());
    }

}
