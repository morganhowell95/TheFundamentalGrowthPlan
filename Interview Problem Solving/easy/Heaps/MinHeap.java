/*
 * The Min-Heap data structure (priority queue) supports the following operations, along with helper functions (not listed)
 *	insertElement(T data) - (lgn)
 *	extractMin() - O(lgn))
 * 	getMin() - O(1)
 *	deleteNthNode() - O(lgn)
 *	MinHeapify() - O(lgn)
 *	buildMinHeap() - O(n)
 *	heapDecreaseKey() - O(lgn)
 *	heapSort() - O(nlgn)
 *
 * The heapSort function takes an unsorted array and sorts it in-place in O(nlgn) running time efficiency
 */

import java.util.ArrayList;

public class MinHeap<T extends Comparable<T>> {
	public ArrayList<T> heap;
	private int currentSize;

	public MinHeap() {
		heap = new ArrayList<T>();
		currentSize = 0;
		//filler for index 0
		heap.add(null);
	}

	private int getParent(int n) {
		return ((n/2)<heap.size() && n>0) ? (n/2) : -1;
	}

	private int getLeftChild(int n) {
		return ((n*2)<heap.size() && n>0) ? (n*2) : -1;
	}

	private int getRightChild(int n) {
		return ((n*2)+1<heap.size() && n>0) ? (n*2)+1 : -1;
	}
	
	//Swap the data of two indices
	private void swap(int first, int second) {
		T data = heap.get(first);
		heap.set(first, heap.get(second));
		heap.set(second, data);
	}


	/* Percolates the node down ensuring minheap structure for the subtrees of given index 
	 *
	 * @param1: integer that will percolate down
	 * @rvalue: void (however makes minheap structure those subtrees)
	 */
	private void minHeapify(int current) {
		int lc = getLeftChild(current);
		int rc = getRightChild(current);
		//**NOTE, JAVA REVIEW: x.compareTo(y) returns:
		// r<1 if y>x
		// r=0 if y=x
		// r>1 if x>y

		//Swap parent with smallest child
		int min = current;
		if(lc != -1 && heap.get(min).compareTo(heap.get(lc)) > 0) {
			min = lc;
		}
		if(rc != -1 && heap.get(min).compareTo(heap.get(rc)) > 0) {
			min = rc;
		}

		if(min != current) {
			swap(current,min);
			minHeapify(min);
		}
			
	}


	/* Percolates the node up ensure  minheap structure for nodes above (assumes minheap structure held before call) 
	 *
	 * @param1: integer of the node decreasing in value
	 * @param2: new data that will be swapped in
	 * @rvalue: void (however makes minheap structure those subtrees)
	 */
	private void heapDecreaseKey(int indexToDecrease, T data) {
		if(indexToDecrease > 0 && indexToDecrease < heap.size() 
			&& (heap.get(heap.size()-1) == null || heap.get(indexToDecrease).compareTo(data) > 0) ) {

			heap.set(indexToDecrease, data);
			int parent = getParent(indexToDecrease);
			while(parent > 0 && (heap.get(indexToDecrease).compareTo(heap.get(parent)) < 0))  {
				swap(indexToDecrease, parent);
				indexToDecrease = getParent(indexToDecrease);
				parent = getParent(indexToDecrease);
			}
		} 
	}

	/* Inserts elements into the heap while maintaining structure
	 * works by placing maximum element at end and simply replacing then percolating up (utilizing method above)
	 *
	 * @param1: element to be inserted
	 * @rvalue: void (however makes minheap structure those subtrees)
	 */
	public void insert(T data) {
		heap.add(null);
		heapDecreaseKey(heap.size()-1, data);
		currentSize++;
	}


	/* Deletes element at select index by replacing with last one and percolating down
	 *
	 * @param1: targeted index to delete
	 * @rvalue: void (however makes minheap structure those subtrees)
	 */
	public void deleteAtIndex(int index) {
		if(index > 0 && index < heap.size()) {
			T lastElement = heap.get(heap.size()-1);
			heap.set(index, lastElement);
			heap.remove(heap.size()-1);
			minHeapify(index);
			currentSize--;
		}
	}


	/* Returns the root (minimum) element without actually removing it
	 *
	 * @rvalue: Generic minimum data element at root
	 */
	public T getMin() {
		return heap.get(1);
	}

	/* Returns the root (minimum) element and removes it while maintaining heap structure
	 *
	 * @rvalue: Generic minimum data element
	 */
	public T extractMin(){
		T data = heap.get(1);
		heap.set(1, heap.get(heap.size()-1));
		heap.remove(heap.size()-1);
		minHeapify(1);
		currentSize--;
		return data;
	}

	public boolean isEmpty() {
		return currentSize==0;
	}


	public static void main(String[] args) {
		MinHeap<Integer> heap = new MinHeap<Integer>();
		boolean passedCases = true;
		heap.insert(new Integer(100));
		heap.insert(new Integer(0));
		heap.insert(new Integer(500));
		heap.insert(new Integer(-30));
		passedCases &= (heap.getMin() == -30);
		System.out.println("149 - " + passedCases);
		passedCases &= (heap.extractMin() == -30);
		System.out.println("151 - " + passedCases);
		passedCases &= (heap.getMin() == 0);
		System.out.println("153 - " + passedCases);
		passedCases &= (heap.extractMin() == 0);
		System.out.println("154 - " + passedCases);
		heap.insert(new Integer(-500));
		heap.insert(new Integer(1000));
		passedCases &= (heap.extractMin() == -500);
		System.out.println("159 - " + passedCases);
		heap.heapDecreaseKey(2, -800);
		passedCases &= (heap.getMin() == -800);
		System.out.println("162 - " + passedCases);
		heap.deleteAtIndex(1);
		passedCases &= (heap.getMin() == 100);
		System.out.println("165 - " + passedCases);
		System.out.println("Your heap:");
		System.out.println(heap.heap);
		
		String hasPassedCases = (passedCases) ? "Congratulations you made a min heap" : "Sorry - some test cases failed";
		System.out.println(hasPassedCases);
	}
}
