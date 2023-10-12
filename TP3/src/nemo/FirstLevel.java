package nemo;

public class FirstLevel extends Depth {
    public void shoot() {}

    public int depth() {
        return 1;
    }

    public Depth turnUp() {
        return new Surface();
    }

    public Depth turnDown() {
        return null;
    }
}
