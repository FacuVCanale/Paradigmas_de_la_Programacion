package nemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Tests {
    private ArrayList<Command> commands;
    private Position origin;
    private Orientation north;

    private Depth surface;

    private Nemo nemo;

    @BeforeEach
    public void setUp() {
        ArrayList<Command> commands = new ArrayList<>();

        commands.add(
                new Command("f",
                            (Nemo nemo) -> {
                                nemo.setPosition(nemo.getOrientation().moveForward(nemo.getPosition()));
                            }));
        commands.add(
                new Command("l",
                            (Nemo nemo) -> {
                                nemo.setOrientation(nemo.getOrientation().turnLeft());
                            }));
        commands.add(
                new Command("r",
                            (Nemo nemo) -> {
                                nemo.setOrientation(nemo.getOrientation().turnRight());
                            }));
        commands.add(
                new Command("u",
                            (Nemo nemo) -> {
                                nemo.setDepth(nemo.getDepth().turnUp());
                            }));
        commands.add(
                new Command("d",
                            (Nemo nemo) -> {
                                nemo.setDepth(nemo.getDepth().turnDown());
                            }));
        commands.add(
                new Command("m",
                            (Nemo nemo) -> {
                                nemo.getDepth().shoot();
                            }));

        origin = new Position(0, 0);
        north = new NorthOrientation();
        surface = new Surface();
        nemo = new Nemo(origin, north, commands, surface);
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
        assertEquals(new FirstLevel(), nemo.getDepth());
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

    @Test void testNemoCanGoUp() {
        nemo.runCommands("du");
        assertEquals(origin, nemo.getPosition());
    }

    @Test void testNemoExplodesLaunchingInDepth() {
        assertEquals(UnshootableLevel.chocolateError,assertThrows(Exception.class, () -> nemo.runCommands("ddm")).getMessage());
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
        assertEquals(UnshootableLevel.chocolateError,assertThrows(Exception.class, () -> nemo.runCommands("ffm")).getMessage());
    }


}
