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





