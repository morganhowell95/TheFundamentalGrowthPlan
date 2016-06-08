/*
 * A double-ended queue (dequeue, often abbreviated to deque, pronounced deck) is an abstract data type that generalizes a 
 * queue, for which elements can be added to or removed from either the front (head) or back (tail).
 * It is also often called a head-tail linked list, though properly this refers to this specific implementation which uses 
 * a linked list for internal storage, as opposed to an array as seen in the other examples. All operations are constant time O(1) with linear space complexity O(N)
 */

public class LLImplOfDequeue<T> implements Dequeue<T> {
	private Node tail;
	private Node head;

	public LLImplOfDequeue() {
		tail = null;
		head = null;
	}

	/* 
	 * Add an element to the front of the deque, at the head, which would be the next element to be dequeued
	 * in a normal queue. Would also be the next element to be popped if using the deque as a stack. Thus, use this 
	 * method in conjunction with the dequeueFront() method to make a functional stack.
	 *
         * @param1: the data to be put at the head (front of queue)
	 * @rvalue: boolean denoting success of insertion
	 */
	@Override
        public boolean enqueueFront(T data) {
		if(isEmpty()){
			head = this.new Node<T>(data);
			tail = head;
		} else {
			Node prevHead = head;
			head = this.new Node<T>(data);
			head.next = prevHead;
			prevHead.prev = head;
		}
		return true;
	}

	/*
	 * Add an element to the back of the deque, which would make it the last one to be dequeued if it were a normal queue,
	 * thus incrementing the tail pointer. This should make it obvious for why we need double linked lists instead of 
	 * singly linked at this point, because we need the ability to "decrement" the tail or move backwards after 
	 * incrementing forward to stay within a constant time operations O(1). This method would be used in conjunction with
	 * the dequeueFront() method to make a normal queue operation.
	 *
	 * @param1: the data to be inserted at the tail
	 * @rvalue: boolean denoting success of insertion
	 */
	@Override
        public boolean enqueueBack(T data) {
		if(isEmpty()){
			head = this.new Node<T>(data);
			tail = head;
		} else {
			Node prevTail = tail;
			tail = this.new Node<T>(data);
			tail.prev = prevTail;
			prevTail.next = tail;
		}
		return true;
	}

	/*
	 *  Removes the element from the front, at the head pointer, which would be equivalent to a stack's pop operation used
	 *  in conjunction with the enqueueFront() method. The head pointer will increment forward, via next, the forward ->
	 *  link in constant time.
	 *
	 *  rvalue: data sitting at the head pointer, front or left of the list
	 */
	@Override
	public T dequeueFront() {
		if(isEmpty()){
			return null;
		} else {
			T data = (T) head.data;
			head = head.next;
			if(head == null){
				tail = null;
			} else {
				head.prev = null;
			}
			return (T) data;
		}
	}

	/*
	 * Removes element from the back, at the tail pointer.
	 *
	 * @rvalue: data sitting at the end, at the tail pointer, to the right most point of the list
	 */
	@Override
        public T dequeueBack() {
		if(isEmpty()) {
			return null;
		} else {
			T data = (T) tail.data;
			tail = tail.prev;
			if(tail == null){
				head = null;
			} else {
				tail.next = null;
			}
			return (T) data;
		}

	}

	/*
	 * Fetch the element at the head pointer, front of the list, without actually incrementing any pointers
	 *
	 * @rvalue: element at the front of the list
	 */
	@Override
        public T peekFront() {
		if(isEmpty()){
			return null;
		} else {
			return (T) head.data;
		}
	}

	/*
	 * Fetch the element at the tail pointer, back of the list, without actually decrementing any pointers
	 *
	 * @rvalue: element at the back of the list
	 */
	@Override
        public T peekBack() {
		if(isEmpty()){
			return null;
		} else {
			return (T) tail.data;
		}
	}

	/*
	 * Checks whether the deque is empty or not
	 *
	 * @rvalue: boolean denoting whether the list is empty or not
	 */
	@Override
        public boolean isEmpty() {
		return (tail==null) && (head==null);
	}

	/*
	 * Check whether the deque is full, which shouldn't happen in the linked list implementation.
	 * If you would like to see the array implementation of the dequeue, please just shoot me a message
	 * and I would be more than happy to provide it (using a circular buffer or dynamic sizing)
	 */
	@Override
        public boolean isFull(){
		return false;
	}


	/* Internal data storage object which links to other Node objects to create doubly linked lists */
	public class Node<T> {
		public Node next;
		public Node prev;
		public T data;

		public Node(T data) {
			this.data = data;
			this.next = null;
			this.prev = null;
		}
	}

 	//Sample run to test the expected functionality
        public static void main(String[] args) throws Exception {

		//TEST-> treating the deque as a queue
                LLImplOfDequeue<String> q = new LLImplOfDequeue<String>();
                //crude but convenient tests 
                if(!q.isEmpty()) {
                        throw new Exception();
                }    
    
                for(int i=0; i<4; i++) {
                        q.enqueueBack(i + "hello");
                }    
    
                if(!q.peekFront().equals("0hello")) {
                        throw new Exception(); 
                }    
    
                if(!q.dequeueFront().equals("0hello")) {
                        throw new Exception();
                }    
    
                if(!q.peekFront().equals("1hello")) {
                        throw new Exception(); 
                }    
    
                for(int i=0; i<10; i++) {
                        q.enqueueBack(Integer.toString(i));
                }    
    
                if(q.isFull() != false) {
                        throw new Exception();
                }   

                if(!q.dequeueFront().equals("1hello")) {
                        throw new Exception();
		}
		System.out.println("Deque can function as a queue");

		//TEST-> treating the deque as a stack
                LLImplOfDequeue<Integer> s = new LLImplOfDequeue<Integer>();

                //crude tests are better than no tests
                if(!s.isEmpty()) {
                        throw new Exception();
                }   

                for(int i=0; i<100; i++) {
                        s.enqueueFront(i);
                }   

                if(s.isEmpty()) {
                        throw new Exception();
                }   

                if(s.isFull()) {
                        throw new Exception();
                }   

                if(s.peekFront()!=99) {
                        throw new Exception();
                }   

                if(s.dequeueFront()!=99) {
                        throw new Exception();
                }   

                if(s.peekFront()!=98) {
                        throw new Exception();
                }   

                System.out.println("Your deque has the ability to function just like a stack, time for the ultimate test?");

		LLImplOfDequeue<String> d = new LLImplOfDequeue<String>();
		d.enqueueFront("hello");
		d.enqueueFront("yo");
		d.enqueueFront("whatsup");
		d.enqueueBack("uhoh");

		if(d.peekFront()!="whatsup") {
			throw new Exception();
		}

		if(d.peekBack()!="uhoh") {
			throw new Exception();
		}

		if(d.dequeueBack()!="uhoh") {
			throw new Exception();
		}

		if(d.peekBack()!="hello") {
			throw new Exception();
		}

		if(d.dequeueBack()!="hello") {
			throw new Exception();
		}

		if(d.dequeueFront()!="whatsup") {
			throw new Exception();
		}

		d.dequeueBack();

		if(!d.isEmpty()) {
			throw new Exception();
		}

		d.enqueueBack("hello");
		d.dequeueBack();
		
		System.out.println("Nice! You have a functioning deque that's been tried and slightly tested.");
		System.out.println("Shoot an email to morgan.howell95@gmail.com if you'd like to see the array implementation.");
	}
}
