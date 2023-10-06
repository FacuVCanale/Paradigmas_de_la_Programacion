package nemo;

public class Map {

    public Nemo submarine;
    public Coordinate submarineCoords = new Coordinate(0, 0, 0);

    public String orientation = "N";

    public Map(Nemo nemo) {
        submarine = nemo;
    }



}
