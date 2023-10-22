package nemo;

import java.util.ArrayList;

public abstract class DiveNavigator {
    protected int depth;

    public void shoot() {}

    public ArrayList<DiveNavigator> goUp(Submarine submarine) {
        ArrayList<DiveNavigator> newDiveNavigatorList =  submarine.diveNavigatorList();
        newDiveNavigatorList.remove(0);
        return newDiveNavigatorList;
    }

    public ArrayList<DiveNavigator> goDown(Submarine submarine) {
        ArrayList<DiveNavigator> newDiveNavigatorList =  submarine.diveNavigatorList();
        newDiveNavigatorList.add(0, new UnshootableLevelNavigator(this.depth +1));
        return newDiveNavigatorList;
    }

    public abstract boolean equals(Object other);

    public int depth() {
        return this.depth;
    }
}





