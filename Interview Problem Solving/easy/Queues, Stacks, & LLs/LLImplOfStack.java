/*
 * Linked list implementation of a stack, which is a Last In First Out (LIFO) data structure.
 * The java solution offers a more comprehensive understanding of the underlying functionality as compared
 * to the Pythonic solution. All operations are done in constant O(1) time.
 */

public class LLImplOfStack<T> implements Stack<T> {
	private Node head;

	public LLImplOfStack() {
		head = null;
	}

	/* Adds the element to the back (head) of the list which is the next to be removed in a LIFO fashion.
	 * Makes more conceptual sense to think of the list being built "backwards": head->x->x->x->tail
	 * 
	 * @param1: arbitrary data to be stored
	 * @rvalue: return successful storage
	 */
	@Override
	public boolean push(T data) {
		if(isEmpty()) {
			head = this.new Node<T>(data);
		} else {
			Node currentHead = head;
			head = this.new Node<T>(data);
			head.next = currentHead;
		}
		return true;
	}

	/* Pops (or removes) element from the head of the list which is the most recently inputted element then
	 * the pointer will increment "forward" in the list (if you think of the list being build backwards with head
	 * incrementing forward upon a removal). All operations are done in constant time.
	 *
	 * @rvalue: data sitting at head of list (at the back)
	 */
	@Override
	public T pop() {
		if(isEmpty()) {
			return null;
		} else {
			T data = (T) head.data;
			head = head.next;
			return (T) data;
		}
	}

	/* View the last inputted element at the back of the list without incermenting the pointers
	 *
	 * @rvalue: data sitting at the head of list (at the back)
	 */
	@Override
	public T peek() {
		if(isEmpty()) {
			return null;
		} else {
			return (T) head.data;
		}
	}

	/* Check to see if the list has been populated yet
	 *
	 * @rvalue: boolean that says whether the list is empty or not
	 */
	@Override
	public boolean isEmpty() {
		return head == null;
	}

	/* Check to see if the list is full, shouldn't happen anytime soon with the linked list implementation */
	@Override
	public boolean isFull() {
		return false;
	}


	//Inner-class representing a node of the linked list where the arbitrary data is stored
	public class Node<T> {
		public T data;
		public Node next;

		public Node(T data) {
			this.data = data;
			this.next = null;
		}
	}

 	public static void main(String[] args) throws Exception {
                LLImplOfStack<Integer> s = new LLImplOfStack<Integer>();

                //crude tests are better than no tests
                if(!s.isEmpty()) {
                        throw new Exception();
                }

                for(int i=0; i<100; i++) {
                        s.push(i);
                }

                if(s.isEmpty()) {
                        throw new Exception();
                }

                if(s.isFull()) {
                        throw new Exception();
                }

                if(s.peek()!=99) {
                        throw new Exception();
                }

                if(s.pop()!=99) {
                        throw new Exception();
                }

                if(s.peek()!=98) {
                        throw new Exception();
                }

                System.out.println("Stack works exactly as expected, but you should write some better testers.");
        }


}
