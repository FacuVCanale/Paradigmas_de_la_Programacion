package nemo;

import java.util.ArrayList;

public class Submarine {
    private Position position;
    private Orientation orientation;
    private ArrayList<DiveNavigator> diveNavigatorList = new ArrayList<DiveNavigator>();

    public Submarine(Position position, Orientation orientation) {
        this.position = position;
        this.orientation = orientation;
        this.diveNavigatorList.add(new SurfaceNavigator());
    }

    public Position position() {
        return position;
    }

    public Orientation orientation() {
        return orientation;
    }

    public DiveNavigator diveNavigator() {
        return diveNavigatorList.get(0);
    }

    public ArrayList<DiveNavigator> diveNavigatorList() {
        return diveNavigatorList;
    }

    public int depth() {
        return diveNavigator().depth();
    }

    public Submarine runCommands(String instructions) {
        instructions.chars().forEach(instruction -> {
            Command.commandFor(String.valueOf((char) instruction)).runCommand(this);
        });
        return this;
    }

    protected Submarine forward() {
        setPosition(position().sum(orientation().getForwardStepInThisOrientation()));
        return this;
    }

    protected Submarine left() {
        setOrientation(orientation().turnLeft());
        return this;
    }

    protected Submarine right() {
        setOrientation(orientation().turnRight());
        return this;
    }

    protected Submarine shoot() {
        diveNavigator().shoot();
        return this;
    }

    protected Submarine up() {
        setDepth(diveNavigator().goUp(this));
        return this;
    }

    protected Submarine down() {
        diveNavigator().goDown(this);
        return this;
    }

    private Submarine setPosition(Position position) {
        this.position = position;
        return this;
    }

    private Submarine setOrientation(Orientation orientation) {
        this.orientation = orientation;
        return this;
    }

    private Submarine setDepth(ArrayList<DiveNavigator> diveNavigators) {
        this.diveNavigatorList = diveNavigators;
        return this;
    }

}
