package nemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Tests {
    private Position origin;
    private Orientation north;
    private Nemo nemo;

    @BeforeEach
    public void setUp() {
        origin = new Position(0, 0);
        north = new NorthOrientation();
        nemo = new Nemo(origin, north);
    }


    @Test void testNemoHasItsPosition() {
        assertEquals(origin, nemo.getPosition());
    }

    @Test void testNemoHasItsOrientation() {
        assertEquals(north, nemo.getOrientation());
    }

    @Test void testPositionsCanBeCompared() {
        assertEquals(new Position(0, 0),  origin);
    }

    @Test void testOrientationCanBeCompared() {
        assertEquals(new NorthOrientation(), north);
    }

    @Test void testNemoCanGoDown() {
        nemo.runCommands("d");
        assertTrue(new Surface() != nemo.getDepth());
        assertEquals(new FirstLevel(), nemo.getDepth());
    }

    @Test void testNemoCanGoUpAndRemainAtTheSamePlace() {
        nemo.runCommands("u");
        assertEquals(origin, nemo.getPosition());
    }

    @Test void testNemoCanGoDownAndUp() {
        nemo.runCommands("du");
        assertEquals(new Surface(), nemo.getDepth());
    }

    @Test void testNemoCanGoToUnshootableLevelAndGoUpAndRemainThere() {
        nemo.runCommands("ddddu");
        assertEquals(new UnshootableLevel(), nemo.getDepth());

    }

    @Test void testNemoCanGoToUnshootableLevelAndThenToSurface() {
        nemo.runCommands("dddduuuuuu");
        assertEquals(new Surface(), nemo.getDepth());

    }

    @Test void testNemoCanGoForward() {
        nemo.runCommands("f");
        assertEquals(new Position(0, 1), nemo.getPosition());
    }

    @Test void testNemoCanTurnLeft() {
        nemo.runCommands("l");
        assertEquals(new WestOrientation(), nemo.getOrientation());
    }

    @Test void testNemoCanTurnRight() {
        nemo.runCommands("r");
        assertEquals(new EastOrientation(), nemo.getOrientation());
    }

    @Test void testNemoCanLaunchInOrigin() {
        nemo.runCommands("m");
        assertEquals(new Position(0, 0), nemo.getPosition());
    }


    @Test void testNemoExplodesLaunchingInDepth() {
        assertThrowsLike(() -> nemo.runCommands("ddm"), UnshootableLevel.chocolateError);
    }

    @Test void testNemoCanTurnRightAndGoForward() {
        nemo.runCommands("rf");
        assertEquals(new Position(1, 0), nemo.getPosition());
    }

    @Test void testNemoCanGoBackwardsRotatingTwice() {
        nemo.runCommands("llf");
        assertEquals(new Position(0, -1), nemo.getPosition());
    }

    @Test void testNemoCanFollowCommandsAndThenExplode() {
        nemo.runCommands("ffdd");
        assertEquals(new Position(0, 2), nemo.getPosition());
        assertThrowsLike(() -> nemo.runCommands("ffm"), UnshootableLevel.chocolateError);
    }

    private void assertThrowsLike(Executable executable, String message) {
        assertEquals(message, assertThrows(Exception.class, executable).getMessage());
    }
}
