package newnemo;

import org.junit.jupiter.api.function.Executable;

public class Command {
    private char name;

    private Executable action;

    public Command(char name, Executable action) {
        this.name = name;
        this.action = action;
    }

    public char getName() {
        return name;
    }

    public void execute() throws Throwable {
        action.execute();
    }
}
