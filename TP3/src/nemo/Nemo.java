package nemo;

import java.util.ArrayList;
import java.util.Objects;

public class Nemo {
    private Position position;
    private Orientation orientation;

    public ArrayList<Depth> depth;


    private ArrayList<Command> commands;

    public Nemo(Position position, Orientation orientation, ArrayList<Command> commands) {
        this.position = position;
        this.orientation = orientation;
        this.commands = commands;
        this.depth = new ArrayList<Depth>();
        this.depth.add(0, new Surface());

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
        return depth.get(0);
    }

    public Nemo setDepth(Depth depth) {
        this.depth.add(0, depth);
        return this;
    }

    public Depth goUp() {
        Depth UpperDepth = this.depth.remove(0).goUp();
        this.depth.add(0, UpperDepth);
        return UpperDepth;
    }

    public Depth goDown() {
        Depth LowerDepth = this.depth.get(0).goDown();
        this.depth.add(0, LowerDepth);
        return LowerDepth;
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
