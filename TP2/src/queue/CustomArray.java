package queue;

public abstract class CustomArray {

    public CustomArray EmptyArray() {
        return new EmptyArray();
    }

    public CustomArray NonEmptyArray( Object cargo, CustomArray next ) {
        return new NonEmptyArray( cargo, next );
    }

    public abstract CustomArray add(Object cargo);
    public abstract CustomArray remove_head();
    public abstract Object head();
    public abstract int size();

}
