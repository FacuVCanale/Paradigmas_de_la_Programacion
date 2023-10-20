package nemo;

import java.util.ArrayList;

public class SurfaceNavigator extends DiveNavigator {
    public boolean equals(Object other) {
        return other instanceof SurfaceNavigator;
    }

    public ArrayList<DiveNavigator> goUp(Submarine nemo) {
        return nemo.diveNavigatorList();
    }

    public ArrayList<DiveNavigator> goDown(Submarine nemo) {
        ArrayList<DiveNavigator> newDiveNavigatorList = nemo.diveNavigatorList();
        newDiveNavigatorList.add(0, new ShootableLevelNavigator());
        return newDiveNavigatorList;
    }
}

