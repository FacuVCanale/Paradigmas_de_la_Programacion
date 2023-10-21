package nemo;

import java.util.ArrayList;

public class SurfaceNavigator extends DiveNavigator {

    public SurfaceNavigator() {
        this.z = 0;
    }

    public boolean equals(Object other) {
        return other instanceof SurfaceNavigator;
    }

    public ArrayList<DiveNavigator> goUp(Submarine submarine) {
        return submarine.diveNavigatorList();
    }

    public ArrayList<DiveNavigator> goDown(Submarine submarine) {
        ArrayList<DiveNavigator> newDiveNavigatorList = submarine.diveNavigatorList();
        newDiveNavigatorList.add(0, new ShootableLevelNavigator());
        return newDiveNavigatorList;
    }
}

