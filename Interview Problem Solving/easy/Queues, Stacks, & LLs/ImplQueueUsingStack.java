/* Implement a fully functional Queue using only stacks */

public class ImplQueueUsingStack<T> implements Queue<T> {
	private LLImplOfStack<T> enqueueStorage;
	private LLImplOfStack<T> dequeueStorage;

	public ImplQueueUsingStack() {
		enqueueStorage = new LLImplOfStack<T>();
		dequeueStorage = new LLImplOfStack<T>();
	}

	@Override
	public boolean enqueue(T data) {
		enqueueStorage.push(data);
		return true;
	}

	@Override
	public T dequeue() {
		if(isEmpty()) {
			return null;
		} else if(dequeueStorage.isEmpty()) {
			refillDequeueStorage();
		}
		//ensuring that are dequeue is not empty, we can go ahead and pop, which effectively acts as a queue
		return dequeueStorage.pop();
	}

	@Override
	public T peek() {	
		if(isEmpty()) {
			return null;
		} else if(dequeueStorage.isEmpty()) {
			refillDequeueStorage();
	}
		return dequeueStorage.peek();
	}

	//fill contents of dequeue storage with all elements within enqueue storage
	//This part with inevitable have O(n) for the n elements that are sitting in enqueue storage
	private void refillDequeueStorage() {
		while(!enqueueStorage.isEmpty()){
			T data = enqueueStorage.pop();
			dequeueStorage.push(data);
		}
	}


	@Override
	public boolean isEmpty() {
		return (enqueueStorage.isEmpty() && dequeueStorage.isEmpty()); 
	}

	@Override
	public boolean isFull() {
		return false;
	}


	public static void main(String[] args) throws Exception {
		ImplQueueUsingStack<String> q  = new ImplQueueUsingStack<String>();
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

                System.out.println("Queue works as expected and you made it out of stacks!");
	}
}
