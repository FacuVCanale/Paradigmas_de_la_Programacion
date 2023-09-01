package queue;

import java.util.ArrayList;

public class Queue {
	ArrayList<String> queue = new ArrayList<>();
  public boolean isEmpty() {
		// TODO Auto-generated method stub

		return queue.isEmpty();
	}

	public Queue add( String  cargo ) { // Object cargo y despues (String) cargo
		// TODO Auto-generated method stub
		queue.add( cargo);
		return this;
	}

	public Object take() {
    // TODO Auto-generated method stub
		if (!queue.isEmpty()) {
			return queue.remove(0);
		}
		else{
			throw new RuntimeException("Queue is empty");
		}
	}

	public Object head() {
		// TODO Auto-generated method stub
    return queue.get(0);
	}

	public int size() {
		// TODO Auto-generated method stub
		return queue.size();
	}

}
