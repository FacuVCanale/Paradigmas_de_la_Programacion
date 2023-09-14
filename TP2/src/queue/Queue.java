package queue;

import java.util.ArrayList;

public class Queue {

    public ArrayList<Container> queue;

    public Queue() {
        queue = new ArrayList<>();
        EmptyContainer emptyContainer = new EmptyContainer();
        queue.add(emptyContainer);
    }

    public Queue add(Object cargo) {
        queue.add(size(), new FilledContainer(cargo));
        return this;
    }

    public Object take() {;
        Container removedContainer = queue.remove(0);
        return removedContainer.item();
    }

    public Object head() {
        Container firstContainer = queue.get(0);
        return firstContainer.item();
    }

    public boolean isEmpty() {
        return queue.size() == 1;
    }

    public int size() {
        return queue.size() - 1;
    }

}