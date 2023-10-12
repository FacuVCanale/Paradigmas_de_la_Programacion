package nemo;

public abstract class Depth {
    public abstract void shoot();

    public abstract int depth();

    public abstract Depth turnUp();

    public abstract Depth turnDown();

    public boolean equals(Depth other) {
        return this.depth() == other.depth();
    }

    public int hashCode() {
        return this.depth();
    }

    public String toString() {
        return "Depth: " + this.depth();
    }
}
