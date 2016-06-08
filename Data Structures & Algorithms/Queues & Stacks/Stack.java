public interface Stack<T> {
	public boolean push(T data);
	public T pop();
	public T peek();
	public boolean isEmpty();
	public boolean isFull();
}

