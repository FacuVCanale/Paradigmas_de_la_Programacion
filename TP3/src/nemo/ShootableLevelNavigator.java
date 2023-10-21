package nemo;

import java.util.ArrayList;

public class ShootableLevelNavigator extends DiveNavigator {

    public ShootableLevelNavigator() {
        this.z = 1;
    }

    public ArrayList<DiveNavigator> goDown(Submarine submarine) {
        ArrayList<DiveNavigator> newDiveNavigatorList =  submarine.diveNavigatorList();
        newDiveNavigatorList.add(0, new UnshootableLevelNavigator(this.z+1));
        return newDiveNavigatorList;
    }
    public boolean equals(Object other) {
        return other instanceof ShootableLevelNavigator;
    }
}
