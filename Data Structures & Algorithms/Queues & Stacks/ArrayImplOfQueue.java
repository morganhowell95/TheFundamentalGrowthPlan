//A queue is a First In First Out (FIFO) data structure
//Java gives a much more in-depth perspective for the array implementation of both the queue and stack
public class ArrayImplOfQueue<T> {
	private T[] q;
	private int totalSize;
	private int headIndex;
	private int tailIndex;

	//We will assume the initial array has a fixed size so that we can demonstrate the "wrapping" functionality
	//other implementations could involve the array growing (dynamically) after reaching its limit or an ArrayList could
	//be used which would result in a solution similar to the Python one described.
	@SuppressWarnings("unchecked") 
	public ArrayImplOfQueue(int totalSize) {
		this.totalSize = totalSize;
		//Usually this is unsafe, but since we're dealing with
		//an underlying implementation of a queue and enforcing strong typing - all is well
		this.q = (T[]) new Object[totalSize];
		this.headIndex = this.tailIndex = 0;
	}

	/*
 	* Enqueue will input and store data at the back of the queue 
	*
	* @param1: data to be inputted
	* @rvalue: boolean denoting the success of the insertion
	*/
	public boolean enqueue(T data) {
		if(!isFull()){
			//taking the tail index modulo total size allows us to "wrap around" the fixed size array
			q[tailIndex % totalSize] = data;
			tailIndex++;
			return true;
		} else {
			return false;
		}
	}

	
	/*
 	* Dequeue will remove and return data from the head of the queue
	*
	* @rvalue: data at the "front" or head of the list (remember FIFO ds)
	*/
	public T dequeue() {
		if(isEmpty()){
			return null;
		} else {
			//No need to even clear the indices, they will be written over as the queue wraps around the array
			T data = q[headIndex%totalSize];
			headIndex++;
			return data;
		}
	}

	/*
	 * Peek will return data at the head index without actually removing it 
	 *
	 * @rvalue: data at the "front" or head of the list 
	 */
	public T peek(){
		if(isEmpty()) {
			return null;
		} else {
			return q[headIndex%totalSize];
		}
	}

	/*
	 * We know the entire array has been filled if adding one more to the head brings us to the same index as the tail
	 *
	 */
	public boolean isFull() {
		return ((headIndex%totalSize) == (tailIndex%totalSize)+1);
	}


	/*
	 *We know the array is empty if that tail index is equal to the head index
	 *
	 */
	public boolean isEmpty() {
		return (headIndex == tailIndex);
	}

	//Sample run to test the expected functionality
	public static void main(String[] args) throws Exception {
		//Initializing a new queue holding strings of the fixed size 5
		ArrayImplOfQueue<String> q = new ArrayImplOfQueue<String>(5);
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

                boolean didFail = false;
                for(int i=0; i<10; i++) {
                        didFail = !q.enqueue(Integer.toString(i));
                }

                if(!didFail) {
                        throw new Exception();
                }

                if(q.isFull() != true) {
                        throw new Exception();
                }

                if(!q.dequeue().equals("1hello")) {
                        throw new Exception();
                }
		
		System.out.println("Queue works as expected, nice job!");

	}
}
