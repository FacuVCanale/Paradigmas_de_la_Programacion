package queue;

public class EmptyContainer extends Container {
    public static String EMPTY_QUEUE = "Queue is empty";

    public Object getContainerItem() {
        throw new Error(EMPTY_QUEUE);
    }


}

