package nemo;

import java.util.ArrayList;

public class Nemo {
    private Position position;
    private Orientation orientation;
    private ArrayList<Depth> depth = new ArrayList<Depth>();

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

    public ArrayList<Depth> getDepthList() {
        return depth;
    }

    public Nemo runCommands(String instructions) {
        instructions.chars().forEach(instruction -> {
            Command.commandFor(String.valueOf((char) instruction)).runCommand(this);
        });
        return this;
    }

    public Nemo forward() {
        setPosition(getPosition().sum(getOrientation().getForwardStepInThisOrientation()));
        return this;
    }

    public Nemo left() {
        setOrientation(getOrientation().turnLeft());
        return this;
    }

    public Nemo right() {
        setOrientation(getOrientation().turnRight());
        return this;
    }

    public Nemo shoot() {
        getDepth().shoot();
        return this;
    }

    public Nemo up() {
        setDepth(getDepth().goUp(this));
        return this;
    }

    public Nemo down() {
        getDepth().goDown(this);
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

    private Nemo setDepth(ArrayList<Depth> depths) {
        this.depth = depths;
        return this;
    }

}
