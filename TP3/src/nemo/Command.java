package nemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

public class Command {
    private String name;
    private Consumer<Submarine> action;

    public static ArrayList<Command> commands = new ArrayList<>(Arrays.asList(
            new Command("f", Submarine::forward),
            new Command("l", Submarine::left),
            new Command("r", Submarine::right),
            new Command("u", Submarine::up),
            new Command("d", Submarine::down),
            new Command("m", Submarine::shoot)));

    public static Command commandFor(String commandLetter) {
        return commands.stream().filter(command -> command.applies(commandLetter)).findFirst().get();
    }

    public Command(String name, Consumer<Submarine> action) {
        this.name = name;
        this.action = action;
    }

    public boolean applies( String commandName ) {
        return name.equals(commandName);
    }

    public void runCommand(Submarine nemo) {
        action.accept(nemo);
    }

}
