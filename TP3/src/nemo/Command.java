package nemo;

import java.util.function.Consumer;

public class Command {
    private String name;

    private Consumer<Nemo> action;

    public Command(String name, Consumer<Nemo> action) {
        this.name = name;
        this.action = action;
    }

    public String name() {
        return name;
    }

    public void runCommand(Nemo nemo) {
        action.accept(nemo);
    }
}
