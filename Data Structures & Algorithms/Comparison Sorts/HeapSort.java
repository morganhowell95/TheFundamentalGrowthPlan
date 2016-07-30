/* Utilizing a max-heap to do O(nlgn) in-place sorting of a given array. We could have implemented this in the min heap example as well by reversing the final array. Conceptually, the functions are exactly the same as the max heap example (file in this same directory) including the complexity analyses */
import java.util.Arrays;
public class HeapSort<T extends Comparable<T>> {
	private T[] container;
	private int heapSize;

	public HeapSort() {
		container = null;
	}

	//returns left child if it exists
	private int getLeftChild(int i) {
		return ((i*2<=heapSize) ? i*2 : -1); 
	}

	//return right child if it exists
	private int getRightChild(int i) {
		return ((i*2)+1<=heapSize) ? (i*2)+1 : -1;
	}

	//swaps the indices of two given heap values
	private void swap(int v1, int v2) {
		T data = container[v1-1];
		container[v1-1] = container[v2-1];
		container[v2-1] = data;
	}

	//percolates the heap index down to maintain max-heap structure
	private void maxHeapify(int i) {
		if(container != null) {
			int lc = getLeftChild(i);
			int rc = getRightChild(i);
			int max;
			if(lc != -1 && (container[i-1].compareTo(container[lc-1]) < 0) ) {
				max = lc;	
				
			} else {
				max = i;
			}
			
			if(rc != -1 && (container[max-1].compareTo(container[rc-1]) < 0 )) {
				max = rc;	
			}

			if(i!=max) {
				swap(i, max);
				maxHeapify(max);
			}
		}
	}

	//heap is empty even if elements live in the array because we track the heap within the array
	private boolean isEmpty() {
		return	this.heapSize == 0;
	}

	//takes out the root and replaces it with the last element (smallest) in the heap, then percolates that down
	private T extractMax() {
		T data = container[0];
		container[0] = container[heapSize-1];
		heapSize--;
		maxHeapify(1);
		return data;
	}

	/* Selects the last non leaf and heapifys from there down... No need to start at the leaves because a node alone
	 * is already in the max heap structure, so only the nodes which have subtrees need to be heapified O(n) */	
	private T[] buildMaxHeap() {
		int lastNonLeaf = (container.length/2);
		for(int i=lastNonLeaf; i>0; i--) {
			maxHeapify(i);
		}

		return container;
	}


	/* Turns unsorted array into a heap O(nlgn) then repeatedly extracts max to create a sorted array O(n)
	 * Runtime analysis: O(nlgn)
	 */
	public T[] sort(T[] unsorted) {
		//note that we are not redeclaring a new array; this algorithm is in-place, heap below is a pointer
		heapSize = unsorted.length;
		container = unsorted;
		int itemsInputted = 0;
		buildMaxHeap();
		while(!isEmpty()) {
			T data = extractMax();
			container[(container.length-1)-itemsInputted] = data;
			itemsInputted++;
		}

		return container;
	}


	public static void main(String[] args) {
		HeapSort<Integer> h = new HeapSort<Integer>();
		Integer[] unsorted = {500,400,300,-300,800,1,2,3,100000,-1000000,93,43,-1023,984};
		Integer[] sorted = h.sort(unsorted);
		Integer[] sorted2 = Arrays.copyOf(unsorted,unsorted.length);
		Arrays.sort(sorted2);
		boolean passedTest = (Arrays.deepEquals(sorted,sorted2));
		String passedMessage = passedTest ? "Congrats you did heap sort" : "boo - you failed";
		System.out.println(passedMessage);
	}
}
