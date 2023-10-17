package nemo;

public class FirstLevel extends Depth {
    public void shoot() {}

    public int depth() {
        return 1;
    }

    public Depth goUp(Nemo nemo) {
        nemo.depth.remove(0);
        return this;
    }

    public Depth goDown(Nemo nemo) {
        nemo.depth.add(0, new UnshootableLevel());
        return this;
    }
}
