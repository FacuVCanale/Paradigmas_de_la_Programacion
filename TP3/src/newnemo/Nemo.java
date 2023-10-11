package newnemo;

import java.util.ArrayList;

public class Nemo {
    private Position position;
    private Orientation orientation;

    private ArrayList<Command> commands;

    public Nemo(Position position, Orientation orientation) {
        this.position = position;
        this.orientation = orientation;
    }

    public Position getPosition() {
        return position;
    }

    public Nemo setPosition(Position position) {
        this.position = position;
        return this;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public Nemo setOrientation(Orientation orientation) {
        this.orientation = orientation;
        return this;
    }

    public Nemo receiveCommand(char commandName) {
        Command command = commands.stream().filter(each -> each.getName() == commandName).findFirst().orElseThrow();
        command.execute();
        return this;
    }

}
