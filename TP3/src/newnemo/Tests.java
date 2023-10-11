package newnemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class Tests {
    @BeforeEach
    public void setUp() {
        ArrayList<Command> commands = new ArrayList<>();
        Executable function = (int nola) -> {
            // Perform some operation on nola, e.g., print it
            System.out.println("nola: " + nola);
        };

        commands.add(
                new Command('f',
                            ((Nemo nemo) -> {
                                Position position = nemo.getPosition();
                                Orientation orientation = nemo.getOrientation();
                                nemo.setPosition(orientation.moveForward(position));
                            })));
        commands.add(
                new Command('l',
                            () -> {}));
        commands.add(
                new Command('r',
                            () -> {}));
        commands.add(
                new Command('u',
                            () -> {}));
        commands.add(
                new Command('d',
                            () -> {}));
        commands.add(
                new Command('m',
                            () -> {}));
    }


    @Test
    public void test01NemoHasItsPosition() {
        Position origin = new Position(0, 0, 0);
        Orientation north = new NorthOrientation();
        Nemo nemo = new Nemo(origin, north);
        assertEquals(origin, nemo.getPosition());

    }

    @Test
    public void test02NemoHasItsOrientation() {
        Position origin = new Position(0, 0, 0);
        Orientation north = new NorthOrientation();
        Nemo nemo = new Nemo(origin, north);
        assertEquals(north, nemo.getOrientation());
    }


}
