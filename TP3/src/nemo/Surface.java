package nemo;

public class Surface extends Depth {
    public void shoot() {}

    public int depth() {
        return 0;
    }

    public Depth goUp() {
        return this;
    }

    public Depth goDown() {
        return new FirstLevel();
    }
}
