package nemo;

public class Surface extends Depth {
    public void shoot() {}

    public int depth() {
        return 0;
    }

    public Depth goUp(Nemo nemo) {
        return this;
    }

    public Depth goDown(Nemo nemo) {
        nemo.depth.add(0, new FirstLevel());
        return this;
    }
}
