package nemo;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Surface extends Depth {
    public void shoot() {}

    public int depth() {
        return 0;
    }

    public ArrayList<Depth> goUp(Nemo nemo) {
        return nemo.getDepthList();
    }

    public ArrayList<Depth> goDown(Nemo nemo) {
        ArrayList<Depth> newDepthList = nemo.getDepthList();
        newDepthList.add(0, new FirstLevel());
        return newDepthList;
    }
}
