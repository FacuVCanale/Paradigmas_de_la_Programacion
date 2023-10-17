package nemo;

public abstract class Depth {
    public abstract void shoot();

    public abstract int depth();

    public abstract Depth goUp(Nemo nemo);

    public abstract Depth goDown(Nemo nemo);

    @Override
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
