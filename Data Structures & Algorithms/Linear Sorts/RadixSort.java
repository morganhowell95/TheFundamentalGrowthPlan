//Radix sort is non-comparison sorting algorithm that operations in O(dn) d being the most number of digits contained in each number
//There are multiple flavors of Radix sort, however this is an instance of the least significant digit implementation
//where each digit is sorted into buckets based on consecutive digits (in place) from the least significant digit.
import java.util.Deque;
import java.util.LinkedList;
import java.util.Arrays;

public class RadixSort{
	//0-9 buckets that contain each symbol (number in this case) to be sorted
	Deque<Integer>[] buckets;

	public RadixSort() {
		buckets = new LinkedList[10];
		for(int i=0; i<10; i++){
			buckets[i] = new LinkedList<Integer>();
		}

	}

	public int[] sort(int[] arr) {
		int currentDigit = 10;
		int bucketDenom = 1;
		boolean digitsConsumed = false;
		int maxNumber = Integer.MIN_VALUE;
		
		//non-negative digit sorting
		while(!digitsConsumed) {
			//go through array and arrange least significant digits
			for(int i : arr) {
				if(i > maxNumber) {
					maxNumber = i;
				}
				int bucket = Math.abs(i) % currentDigit;
				bucket /= bucketDenom;
				buckets[bucket].addLast(i);
			}

			if(maxNumber - currentDigit < 0) {
				digitsConsumed = true;
			}

			//go through buckets in proper order to reconstruct array for next passing
			int k = 0;
			for(int i=0;i<10;i++) {
				while(buckets[i].peek() != null) {
					int nextInOrder = buckets[i].removeFirst();
					arr[k] = nextInOrder;
					k++;
				}
			}

			//move to the next significant digit
			currentDigit *= 10;
			bucketDenom *= 10;
		}

		//sorting on the sign, using 0 bucket for negatives and 1 bucket for positive
		for(int i : arr) {
			if(i < 0) {
				//original order needs to be reversed since smaller numbers coorelate to larger negative numbers
				buckets[0].addFirst(i);
			} else {
				buckets[1].addLast(i);
			}
		}

		//restore original array including negatives
		int k = 0;
		while(buckets[0].peek() != null) {
			arr[k] = buckets[0].removeFirst();
			k++;
		}
		while(buckets[1].peek() != null) {
			arr[k] = buckets[1].removeFirst();
			k++;
		}

		return arr;
	}



	public static void main(String[] args) {
		RadixSort s = new RadixSort();
		int[] test = s.sort(new int[]{33,10000,0,5,4,3,2,1,-50,-15,-1234123,12341,12341,23,43432,2342,112,234,10,1003});
		System.out.println(Arrays.toString(test));
	}

}
