// An algorithm for selecting the Kth order statistic that takes the partitioning concept from quick sort 
//This algo selectes the Kth statistic in O(nlgn) worst case and O(n) expected time
//Make sure to check out the improved algo that takes O(n) worst case
import java.util.Random;
import java.util.Arrays;
public class RandomizedKthOrderStatistic {

	public int chooseK(int k, int[] arr) {
		return chooseKRandomizedPartition(k, arr, 0, arr.length-1);
	}

	public int chooseKRandomizedPartition(int k, int[] arr, int start, int end) {
		if(start <= end) {
			randomizePivot(arr, start, end);
			int pivot = partition(arr, start, end);
			int i = pivot-start+1;
			if(i == k) {
				return arr[pivot];
			} else if(i > k) {
				return chooseKRandomizedPartition(k, arr, start, pivot-1);
			} else {
				return chooseKRandomizedPartition(k-i, arr, pivot+1, end);
			}
		}
		//if integer is not found, we will return min_value
		return Integer.MIN_VALUE;
	}

	private int partition(int[] arr, int start, int end) {
		int pivot = arr[end];
		int k = start-1;
		for(int i=start; i<end; i++) {
			if(arr[i] <= pivot) {
				k++;
				swap(arr, k, i);
			}
		}
		swap(arr, ++k, end);
		return k;
	}

	private void swap(int[] arr, int i1, int i2) {
		int tmp = arr[i1];
		arr[i1] = arr[i2];
		arr[i2] = tmp;
	}

	private void randomizePivot(int[] arr, int start, int end) {
		Random random = new Random();
		double index = Math.abs(random.nextDouble());
		index *= Math.floor(index*100);
		start += index % (end-start+1);
		swap(arr, start, end);
	}


        public static void main(String[] args) {
                RandomizedKthOrderStatistic s = new RandomizedKthOrderStatistic();
                int kthOrder = s.chooseK(5, new int[]{33,10000,0,5,4,3,2,1,-50, 112,234,10,1003});
		System.out.println(kthOrder == 3);
                kthOrder = s.chooseK(11, new int[]{33,10000,0,5,4,3,2,1,-50, 112,234,10,1003});
		System.out.println(kthOrder == 234);
                kthOrder = s.chooseK(1, new int[]{33,10000,0,5,4,3,2,1,-50, 112,234,10,1003});
		System.out.println(kthOrder == -50);
                kthOrder = s.chooseK(13, new int[]{33,10000,0,5,4,3,2,1,-50, 112,234,10,1003});
		System.out.println(kthOrder == 10000);
        }   
}
