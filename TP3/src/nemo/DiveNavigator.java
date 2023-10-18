package nemo;

import java.util.ArrayList;

public abstract class DiveNavigator {
    public void shoot() {}

    public ArrayList<DiveNavigator> goUp(Submarine nemo) {
        ArrayList<DiveNavigator> newDiveNavigatorList =  nemo.diveNavigatorList();
        newDiveNavigatorList.remove(0);
        return newDiveNavigatorList;
    }

    public ArrayList<DiveNavigator> goDown(Submarine nemo) {
        ArrayList<DiveNavigator> newDiveNavigatorList =  nemo.diveNavigatorList();
        newDiveNavigatorList.add(0, new UnshootableLevelNavigator());
        return newDiveNavigatorList;
    }

    public abstract boolean equals(Object other);
}

class SurfaceNavigator extends DiveNavigator {
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

class ShootableLevelNavigator extends DiveNavigator {
    public boolean equals(Object other) {
        return other instanceof ShootableLevelNavigator;
    }
}

class UnshootableLevelNavigator extends DiveNavigator {
    public static String chocolateError = "Releasing the capsule at this depth turns Nemo into a delicious but ill-fated chocolate fountain!";

    public void shoot() {
        throw new RuntimeException(chocolateError);
    }

    public boolean equals(Object other) {
        return other instanceof UnshootableLevelNavigator;
    }
}

