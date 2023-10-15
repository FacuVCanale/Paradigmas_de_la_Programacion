package nemo;

public class FirstLevel extends Depth {
    public void shoot() {}

    public int depth() {
        return 1;
    }

    public Depth goUp() {
        return new Surface();
    }

    public Depth goDown() {
        return new UnshootableLevel();
    }
}
