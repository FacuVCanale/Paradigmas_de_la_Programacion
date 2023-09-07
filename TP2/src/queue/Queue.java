package queue;

public class Queue {
	private CustomArray queue = new EmptyArray();


  public boolean isEmpty() {
		return size()==0;
	}

	public Queue add( Object  cargo ) {
		queue = queue.add( cargo );
		return this;
	}

	public Object take() {
	  	Object head = head();
		queue = queue.remove_head();
		return head;
	}

	public Object head() {
		return queue.head();
	}

	public int size() {
		return queue.size();
	}

}
