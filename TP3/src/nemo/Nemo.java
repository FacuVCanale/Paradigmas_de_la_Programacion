package nemo;

public class Nemo {
    public Coordinate coordinates = new Coordinate(0, 0, 0);
    public String orientation = "N";

    public Launcher launcher = new Launcher(10); //hardcoded

    public Nemo() {
    }

    public Nemo d() {
        coordinates.z--;
        return this;
    }

    public Nemo u() {
        coordinates.z++;
        return this;
    }

    public Nemo l() {
        switch (orientation) {
            case "N":
                orientation = "W";
                break;
            case "W":
                orientation = "S";
                break;
            case "S":
                orientation = "E";
                break;
            case "E":
                orientation = "N";
                break;
        }
        return this;
    }

    public Nemo r() {
        switch (orientation) {
            case "N":
                orientation = "E";
                break;
            case "E":
                orientation = "S";
                break;
            case "S":
                orientation = "W";
                break;
            case "W":
                orientation = "N";
                break;
        }
        return this;
    }

    public Nemo f() {
        switch (orientation) {
            case "N":
                coordinates.y++;
                break;
            case "E":
                coordinates.x++;
                break;
            case "S":
                coordinates.y--;
                break;
            case "W":
                coordinates.x--;
                break;
        }
        return this;
    }

    public Nemo m() {
        launcher.launch();
        return this;
    }
}
