package nemo;

public class UnshootableLevel extends Depth {

    public static String chocolateError = "Releasing the capsule at this depth turns Nemo into a delicious but ill-fated chocolate fountain!";


    public void shoot() {
        throw new RuntimeException(chocolateError);
    }

    public int depth() {
        return 2;
    }

    public Depth turnUp() {
        return new FirstLevel();
    }

    public Depth turnDown() {
        return new UnshootableLevel();
    }
}
