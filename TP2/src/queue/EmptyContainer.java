package queue;

public class EmptyContainer extends Container {
    public static String EMPTY_QUEUE_ERROR_MESSAGE = "Queue is empty";

    public Object getContainerItem() {
        throw new Error(EMPTY_QUEUE_ERROR_MESSAGE);
    }


}

