package nemo;

public class Launcher {
    private int charges;

    public Launcher(int charges) {
        this.charges = charges;
    }

    public Launcher launch() {
        if (charges > 0) {
            charges--;
        }
        return this;
    }
}
