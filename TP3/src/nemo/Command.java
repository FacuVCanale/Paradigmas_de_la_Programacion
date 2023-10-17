package nemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

public class Command {
    private String name;

    private Consumer<Nemo> action;


    private static ArrayList commands = Arrays.asList(
            new Command("f", nemo -> nemo.setPosition(nemo.getPosition().forward(nemo.getOrientation()))),
            new Command("b", nemo -> nemo.setPosition(nemo.getPosition().backward(nemo.getOrientation()))),
            new Command("l", nemo -> nemo.setOrientation(nemo.getOrientation().left())),
            new Command("r", nemo -> nemo.setOrientation(nemo.getOrientation().right())),
            new Command("u", nemo -> nemo.setDepth(nemo.getDepth().goUp(nemo))),
            new Command("d", nemo -> nemo.setDepth(nemo.getDepth().goDown(nemo))),
            new Command("s", nemo -> nemo.getDepth().shoot()));
    public Command(String name, Consumer<Nemo> action) {
        this.name = name;
        this.action = action;
    }

    public boolean applies( String commandName ) {
        return name.equals(commandName);
    }

    public void runCommand(Nemo nemo) {
        action.accept(nemo);
    }
}
