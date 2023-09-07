package queue;

public class EmptyArray extends CustomArray {

    public static final String QUEUE_IS_EMPTY = "Queue is empty";

    public CustomArray add(Object cargo) {
        return NonEmptyArray( cargo, this );
    }

    public CustomArray remove_head() {
        throw new Error(QUEUE_IS_EMPTY);
    }

    public Object head() {
        throw new Error(QUEUE_IS_EMPTY);
    }

    public int size() {
        return 0;
    }
}
