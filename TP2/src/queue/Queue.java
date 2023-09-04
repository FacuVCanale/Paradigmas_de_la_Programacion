package queue;

import java.util.ArrayList;

public class Queue {
	private final ArrayList<Object> queue = new ArrayList<>();
  public boolean isEmpty() {

		return queue.isEmpty();
	}

	public Queue add( Object  cargo ) {

		queue.add( cargo);
		return this;
	}

	public Object take() {
		try {
			return queue.remove(0);
		} catch (IndexOutOfBoundsException e) {
			throw new Error("Queue is empty");
		}
	}

	public Object head() {

		try {
			return queue.get(0);
		} catch (IndexOutOfBoundsException e) {
			throw new Error("Queue is empty");
		}
	}

	public int size() {
		// TODO Auto-generated method stub
		return queue.size();
	}

}
