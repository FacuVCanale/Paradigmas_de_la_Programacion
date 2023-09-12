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
        queue.add(queue.size()-2, new NonEmptyContainer(cargo));
        return this;
    }

    public Object take() {
        this.head();
        Container removed_container = queue.remove(0);
        return removed_container.head();
    }

    public Object head() {
        Container contain = queue.get(0);
        return contain.head();
    }

    public boolean isEmpty() {
        return queue.size()==1;
    }

    public int size() {
        return queue.size()-1;
    }
}