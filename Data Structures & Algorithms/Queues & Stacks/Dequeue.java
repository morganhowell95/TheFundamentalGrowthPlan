/* 
 * a double-ended queue (dequeue, often abbreviated to deque, pronounced deck) is an abstract data type that generalizes a queue, for which elements can be added to or removed from either the front (head) or back (tail).
 */

public interface Dequeue<T> {
	public T dequeueFront();
	public T dequeueBack();
	public T peekFront();
	public T peekBack();
	public boolean enqueueFront(T data);
	public boolean enqueueBack(T data);
	public boolean isEmpty();
	public boolean isFull();
}
