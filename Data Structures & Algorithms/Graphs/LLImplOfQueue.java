/*
 * Linked list implementation of a Queue in Java which serves as a much better learning experience as compared
 * to this data structure in Python. The Queue is a First In First Out (FIFO) data structure.
 * Every operation is done in constant time, however O(n) space complexity.
 */

public class LLImplOfQueue<T> implements Queue<T> {
	private Node head;
	private Node tail;

	public LLImplOfQueue() {
		head = null;
		tail = null;
	}

	/*
	 * Stores the generic data element in the back (or tail) of the queue in constant time
	 */
	@Override
	public boolean enqueue(T data) {
		if(isEmpty()) {
			head = new Node(data);
			tail = head;
		} else {
			tail.setNext(new Node(data));
			tail = tail.getNext();
		}
		return true;
	}

	/*
	 * Returns the element at the front (or head) in a First In First Out (FIFO) fashion
	 * The tail pointer points to the most recently inserted element, whereas the head points to the last
	 */
	@Override
	public T dequeue() {
		if(isEmpty()) {
			return null;
		} else {
			T data = (T) head.getData();
			head = head.getNext();
			if(head == null) {
				tail = null;
			}
			return data;
		}
	}

	/*
	 * View the element sitting in the front (head) without adjusting the pointers
	 */
	@Override
	public T peek() {
		if(isEmpty()){
			return null;
		} else {
			return (T) head.getData();
		}
	}

	/*
	 * Check to see if the queue is empty
	 */
	@Override
	public boolean isEmpty() {
		return (head == null) && (tail == null);
	}

	/*
	 * May be required if the interface you implement has the method, but the linked list implementation should
	 * not get full anytime soon.
	 */
	@Override
	public boolean isFull() {
		return false;
	}

	//Inner class: https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html
	//Represents each node of the linked list which we make a functional queue
	public static class Node<T> {
		private T data;
		private Node next;

		public Node(T data){
			this.data = data;
			this.next = null;
		}

		public T getData() {
			return data;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node n) {
			this.next = n;
		}		
	}
	
	 //Sample run to test the expected functionality
        public static void main(String[] args) throws Exception {

                LLImplOfQueue<String> q = new LLImplOfQueue<String>();
                //crude but convenient tests 
                if(!q.isEmpty()) {
                        throw new Exception();
                }       
                
                for(int i=0; i<4; i++) {
                        q.enqueue(i + "hello");
                }       
                
                if(!q.peek().equals("0hello")) {
                        throw new Exception(); 
                }       
                
                if(!q.dequeue().equals("0hello")) {
                        throw new Exception();
                }       
                
                if(!q.peek().equals("1hello")) {
                        throw new Exception(); 
                }       
                
                for(int i=0; i<10; i++) {
			q.enqueue(Integer.toString(i));
                }       
                
                if(q.isFull() != false) {
                        throw new Exception();
		}

		if(!q.dequeue().equals("1hello")) {
                        throw new Exception();
                }

		while(!q.isEmpty()){
			String data = q.dequeue();
		}

                System.out.println("Queue works as expected, nice job!");

	}
}
