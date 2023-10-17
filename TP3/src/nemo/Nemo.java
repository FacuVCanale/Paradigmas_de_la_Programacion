package nemo;

import java.util.ArrayList;
import java.util.Objects;

public class Nemo {
    private Position position;
    private Orientation orientation;
    public ArrayList<Depth> depth = new ArrayList<Depth>();

    private ArrayList<Command> commands = new ArrayList<Command>();

    public Nemo(Position position, Orientation orientation) {
        this.position = position;
        this.orientation = orientation;
        this.depth.add(new Surface());


    }

    public Position getPosition() {
        return position;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public Depth getDepth() {
        return depth.get(0);
    }

    public Nemo runCommands(String instructions) {
        instructions.chars().forEach((instruction -> {
            commands.stream().
                    filter(command -> command.applies((String.valueOf((char) instruction))))
                    .forEach(command -> {
                    command.runCommand(this);
            });
        }));
        return this;
    }

    private Nemo setPosition(Position position) {
        this.position = position;
        return this;
    }

    private Nemo setOrientation(Orientation orientation) {
        this.orientation = orientation;
        return this;
    }

    private Nemo setDepth(Depth depth) {
        this.depth.add(0, depth);
        return this;
    }

    /*private Depth goUp() {
        Depth UpperDepth = this.depth.get(0).goUp(this);
        this.depth.add(0, UpperDepth);
        return UpperDepth;
    }

    private Depth goDown() {
        Depth LowerDepth = this.depth.get(0).goDown();
        this.depth.add(0, LowerDepth);
        return LowerDepth;
    }*/
}
