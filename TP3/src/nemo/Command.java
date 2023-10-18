package nemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

public class Command {
    private String name;
    private Consumer<Nemo> action;

    public static ArrayList<Command> commands = new ArrayList<>(Arrays.asList(
            new Command("f", Nemo::forward),
            new Command("l", Nemo::left),
            new Command("r", Nemo::right),
            new Command("u", Nemo::up),
            new Command("d", Nemo::down),
            new Command("m", Nemo::shoot)));

    public static Command commandFor(String commandLetter) {
        return commands.stream().filter(command -> command.applies(commandLetter)).findFirst().get();
    }

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
