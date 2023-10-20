package nemo;

public class ShootableLevelNavigator extends DiveNavigator {
    public boolean equals(Object other) {
        return other instanceof ShootableLevelNavigator;
    }
}
