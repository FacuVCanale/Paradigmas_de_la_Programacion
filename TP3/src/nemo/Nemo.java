package nemo;

import java.util.ArrayList;
import java.util.Objects;

public class Nemo {
    private Position position;
    private Orientation orientation;

    private Depth depth;

    private ArrayList<Command> commands;

    public Nemo(Position position, Orientation orientation, ArrayList<Command> commands, Depth depth) {
        this.position = position;
        this.orientation = orientation;
        this.commands = commands;
        this.depth = depth;
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

    public Depth getDepth() {
        return depth;
    }

    public Nemo setDepth(Depth depth) {
        this.depth = depth;
        return this;
    }

    public Nemo runCommands(String instructions) {
        instructions.chars().forEach((instruction -> {
            commands.stream().
                    filter(command -> Objects.equals(command.name(),
                                                String.valueOf( (char) instruction)))
                    .forEach(command -> {
                    command.runCommand(this);
            });
        }));
        return this;
    }

}
