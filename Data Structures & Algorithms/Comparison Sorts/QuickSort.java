//In-place sorting algorithm with worst case O(n^2) and expected run-time O(nlgn)
//Quicksort can be improved by choosing a randomized partition element
//Modified quicksort is O(nlgn) worst case

import java.util.Arrays;
public class QuickSort{
	public int[] sort(int [] arr) {
		return quickSort(arr, 0, arr.length-1);
	}

	private int[] quickSort(int[] arr, int start, int end) {
		if(start < end) {	
			int partition = nonRandomPartition(arr, start, end);
			quickSort(arr, start, partition-1);
			quickSort(arr, partition+1, end);
		}
		return arr;
	}

	//choose a partition (in this instance the last element of the section) and arrange elements around partition in linear time
	private int nonRandomPartition(int[] arr, int start, int end) {
		//choose partition element
		int partition = arr[end];
		int j = start-1;

		//all elements lower than the partition will be placed left of the partition
		for(int i=start;i<end;i++) {
			if(arr[i]<=partition) {
				j++;
				swap(arr, j, i);
			}
		}

		//finally the actual partition will 
		swap(arr, j+1, end);
		return j+1;
	}

	//swap values at two indices of the given array
	private void swap(int[] arr, int i1, int i2) {
		int tmp = arr[i2];
		arr[i2] = arr[i1];
		arr[i1] = tmp;
	}



  public static void main(String[] args) {
    QuickSort s = new QuickSort();
    int[] test = s.sort(new int[]{33,10000,0,5,4,3,2,1,-50});
    System.out.println(Arrays.toString(test));
  }


}
