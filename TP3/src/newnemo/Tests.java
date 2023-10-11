package newnemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Tests {

    //NO VA ACÁ
    private String chocolateError = "Releasing the capsule at this depth turns Nemo into a delicious but ill-fated chocolate fountain!";

    private ArrayList<Command> commands;
    private Position origin;
    private Orientation north;

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
                                nemo.setPosition(nemo.getPosition().turnUp());
                            }));
        commands.add(
                new Command("d",
                            (Nemo nemo) -> {
                                nemo.setPosition(nemo.getPosition().turnDown());
                            }));
        commands.add(
                new Command("m",
                            (Nemo nemo) -> {
                                if (nemo.getPosition().getZ() <= -2)
                                    throw new RuntimeException(chocolateError);
                            }));

        origin = new Position(0, 0, 0);
        north = new NorthOrientation();
        nemo = new Nemo(origin, north, commands);
    }


    @Test void testNemoHasItsPosition() {
        assertEquals(origin, nemo.getPosition());
    }

    @Test void testNemoHasItsOrientation() {
        assertEquals(north, nemo.getOrientation());
    }

    @Test void testPositionsCanBeCompared() {
        assertEquals(new Position(0, 0, 0),  origin);
    }

    @Test void testOrientationCanBeCompared() {
        assertEquals(new NorthOrientation(), north);
    }

    @Test void testNemoCanGoDown() {
        nemo.runCommands("d");
        assertEquals(new Position(0, 0, -1), nemo.getPosition());
    }

    @Test void testNemoCanGoUp() {
        nemo.runCommands("du");
        assertEquals(origin, nemo.getPosition());
    }

    @Test void testNemoCanGoForward() {
        nemo.runCommands("f");
        assertEquals(new Position(0, 1, 0), nemo.getPosition());
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
        assertEquals(new Position(0, 0, 0), nemo.getPosition());
    }

    @Test void testNemoExplodesLaunchingInDepth() {
        assertEquals(chocolateError,assertThrows(Exception.class, () -> nemo.runCommands("ddm")).getMessage());
    }

    @Test void testNemoCanTurnRightAndGoForward() {
        nemo.runCommands("rf");
        assertEquals(new Position(1, 0, 0), nemo.getPosition());
    }

    @Test void testNemoCanGoBackwardsRotatingTwice() {
        nemo.runCommands("llf");
        assertEquals(new Position(0, -1, 0), nemo.getPosition());
    }

    @Test void testNemoCanFollowCommandsAndThenExplode() {
        nemo.runCommands("ffdd");
        assertEquals(new Position(0, 2, -2), nemo.getPosition());
        assertEquals(chocolateError,assertThrows(Exception.class, () -> nemo.runCommands("ffm")).getMessage());
    }
}
