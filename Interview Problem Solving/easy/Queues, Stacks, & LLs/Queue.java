public interface Queue<T>{
	public boolean enqueue(T data);
	public T dequeue();
	public T peek();
	public boolean isFull();
	public boolean isEmpty();
}
