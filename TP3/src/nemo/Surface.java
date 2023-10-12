package nemo;

public class Surface extends Depth {
    public void shoot() {}

    public int depth() {
        return 0;
    }

    public Depth turnUp() {
        return this;
    }

    public Depth turnDown() {
        return new FirstLevel();
    }
}
