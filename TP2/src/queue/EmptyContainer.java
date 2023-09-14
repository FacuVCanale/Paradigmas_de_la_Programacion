package queue;

public class EmptyContainer extends Container {

    public Object item() {
        throw new Error(EMPTY_QUEUE);
    }

    public static String EMPTY_QUEUE = "Queue is empty";

}

