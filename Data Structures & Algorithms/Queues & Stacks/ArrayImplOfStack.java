/*
 * A stack is a Last In First Out (LIFO) data structure
 * The Java implementation of a stack gives a much more comprehensive understanding of the concept as compared to the Pythonic Solution
 */
//**Note: since we implemented the "wrapping" mechanism in the queue example and it would be the same exact implementation for the stack, we are going to experiment with what a "growing" dynamic array would look like

public class ArrayImplOfStack<T> implements Stack<T> {
	private T[] s; 
	private int currentSize;

	@SuppressWarnings("unchecked")
	public ArrayImplOfStack() {
		this.currentSize = 0;
		//default size that will grow dynamically
		this.s = (T[]) new Object[30];
	}
	
	/*
	 * Add data item to end of currentSize
	 *
	 * @rvalue: success of adding element
	 */
	@Override
	public boolean push(T data){
		//grow array if it can't currently hold additional elements
		if(isFull()) {
			growDynamically();
		} 
		s[currentSize++] = data;
		return s[currentSize-1] == data;
	}

	/*
	 * Take last inputted element (LIFO) style and decrement index
	 *
	 * @rvalue: last inputted element
	 *
	 */
	@Override
	public T pop() {
		if(isEmpty()) {
			return null;
		} else {
			return s[--currentSize];
		}
	}

	/*
	 *
	 * Return last element inserted (LIFO) style however do not remove it
	 *
	 * @rvalue: last inputted element
	 */
	@Override
	public T peek() {
		if(isEmpty()) {
			return null;
		} else {
			return s[currentSize-1];
		}
	}

	/*
	* Grow the array by a factor of two
	*
	* @rvalue: return the success state of the grow
	*
	*/
	public void growDynamically() {
		@SuppressWarnings("unchecked")
		T[] t = (T[]) new Object[s.length*2];
		for(int i=0; i<s.length; i++) {
			t[i] = s[i];
		}
		s = t;
	}

	/*
	 * Check whether the Stack has reached its maximum capacity
	 *
	 * @rvalue: boolean deeming if the Stack has maximum number of elements
	 */
	@Override
	public boolean isFull() {
		return currentSize+1 == s.length;
	}

	/*
	 * Check if the Stack contains no data
	 *
	 * @rvalue: boolean with the status of Stack's emptiness
	 */
	@Override
	public boolean isEmpty() {
		return currentSize == 0;
	}


	public static void main(String[] args) throws Exception {
		ArrayImplOfStack<Integer> s = new ArrayImplOfStack<Integer>();

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
