package nemo;

public class UnshootableLevelNavigator extends DiveNavigator {
    public static String chocolateError = "Releasing the capsule at this depth turns Nemo into a delicious but ill-fated chocolate fountain!";

    public void shoot() {
        throw new RuntimeException(chocolateError);
    }

    public boolean equals(Object other) {
        return other instanceof UnshootableLevelNavigator;
    }
}
