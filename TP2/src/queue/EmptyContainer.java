package queue;

public class EmptyContainer extends Container {
    public static String EMPTY_QUEUE = "Queue is empty";

    public Error item() {
        return ErrorWhenQueueIsEmpty();
    }

    public static Error ErrorWhenQueueIsEmpty() {
        throw new Error(EMPTY_QUEUE);
    }


}

