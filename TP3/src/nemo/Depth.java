package nemo;

import java.util.ArrayList;

public abstract class Depth {
    public abstract void shoot();

    public abstract int depth();

    public ArrayList<Depth> goUp(Nemo nemo) {
        ArrayList<Depth> newDepthList =  nemo.getDepthList();
        newDepthList.remove(0);
        return newDepthList;
    }

    public ArrayList<Depth> goDown(Nemo nemo) {
        ArrayList<Depth> newDepthList =  nemo.getDepthList();
        newDepthList.add(0, new UnshootableLevel());
        return newDepthList;
    }

    public boolean equals(Object other) {
        return other instanceof Depth && areObjectsEqual((Depth) other);
    }

    public boolean areObjectsEqual(Depth other) {
        return this.depth() == other.depth();
    }

    public int hashCode() {
        return this.depth();
    }

    public String toString() {
        return "Depth: " + this.depth();
    }
}
