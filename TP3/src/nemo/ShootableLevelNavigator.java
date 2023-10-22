package nemo;

public class ShootableLevelNavigator extends DiveNavigator {

    public ShootableLevelNavigator() {
        this.depth = 1;
    }

    public boolean equals(Object other) {
        return other instanceof ShootableLevelNavigator;
    }
}
