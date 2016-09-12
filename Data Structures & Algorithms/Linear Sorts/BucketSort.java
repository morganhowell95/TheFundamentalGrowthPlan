//Bucket sort is a sorting algorithm that takes advantage of the distribution of the given data set to make sorting happen in O(n+k) average time, where k is the number of buckets created
//The sorting algo is meant to work with numbers that are uniformly distributed (randomly) over [0,1)
import java.util.Arrays;

public class BucketSort {
	Node[] buckets;

	public double[] sort(double[] arr) {
		createBuckets(0, 9);

		for(double i : arr) {
			int bucket = (int) Math.floor(10.0*i);
			insertInBucket(bucket, i);
		}
		sortEachBucket();
		double[] sorted = cocatenateBuckets(arr.length);
		return sorted;
	}

	private void createBuckets(int firstBucket, int lastBucket) {
		buckets = new Node[lastBucket-firstBucket+1];
	}

	private double[] cocatenateBuckets(int length) {
		double[] sorted = new double[length];
		int k = 0;
		for(int i=0; i<buckets.length; i++) {
			while(buckets[i] != null) {
				Node n = buckets[i];
				sorted[k] = n.data;
				k++;
				buckets[i] = n.next;
			}
		}
		return sorted;
	}

	private void insertInBucket(int bucket, double element) {
		if(buckets[bucket] == null) {
			Node insert = new Node(element);
			buckets[bucket]= insert;
		} else {
			Node insert = new Node(element);
			Node current = buckets[bucket];
			insert.next = current;
			current.prev = insert;
			buckets[bucket] = insert;
		}
	}

	//insertion sort for linked lists
	private void sortEachBucket() {
		for(int i=0; i<buckets.length; i++) {
			Node k = buckets[i];
			while(k != null && k.next!=null) {
				k = k.next;
				Node n = k;
				while(n != null && n.prev != null && n.prev.data > n.data) {
					//if swapping head, must change array index reference as well
					if(n.prev == buckets[i]) {
						buckets[i] = n;
					}
					swap(n, n.prev);
					n = n.prev;
				}
			}
		}
	}

	//swapping two doubly-linked nodes
	private void swap(Node n1, Node n2) {
		Node next = n1.next;
		Node prev = n2.prev;
		if(prev != null) {
			prev.next = n1;
		}
		if(next != null) {
			next.prev = n2;
		}
		n1.prev = prev;
		n1.next = n2;
		n2.next = next;
		n2.prev = n1;
	}

        public static void main(String[] args) {
                BucketSort s = new BucketSort();
		//example pulled from CLRS pg.201
                double[] test = s.sort(new double[]{.78,.17,.39,.26,.72,.94,.21,.12,.23,.68});
                System.out.println(Arrays.toString(test));
        }   

	public class Node {
		public Node prev;
		public Node next;
		public double data;

		public Node(double data) {
			this.data = data;
		}
	}
}
