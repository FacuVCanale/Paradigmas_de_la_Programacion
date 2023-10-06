package queue;

import java.util.ArrayList;

public class Queue {

    public ArrayList<Container> queue;

    public Queue() {
        queue = new ArrayList<>();
        queue.add(new EmptyContainer());
    }

    public Queue add(Object cargo) {
        queue.add(size(), new FilledContainer(cargo));
        return this;
    }

    public Object take() {
        return queue.remove(0).getContainerItem();
    }

    public Object head() {
        return queue.get(0).getContainerItem();
    }

    public boolean isEmpty() {
        return queue.size() == 1;
    }

    public int size() {
        return queue.size() - 1;
    }

}