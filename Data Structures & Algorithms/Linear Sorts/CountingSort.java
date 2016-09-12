import java.util.Arrays;

public class CountingSort {

	public int[] arr;

	public CountingSort(int[] arr) {
		this.arr = arr;
	}


	public int[] sort(int[] arr) {
		//make array that holds the frequency of elements in original unsorted array
		int max = Integer.MIN_VALUE;
		for(int i: arr) {
			if(i > max) {
				max = i;
			}
		}
		int[] frequencies = new int[max+1];
		for(int i: arr) {
			frequencies[i]++;
		}

		//make a cumulative array out of these frequencies, which represents the index in the final sorted array
		for(int i=1;i<frequencies.length;i++) {
			frequencies[i] += frequencies[i-1];
		}

		//going through original (unsorted) array backwards, we can look up the final index in the cumulative array
		int[] sortedArray = new int[arr.length];
		for(int i=arr.length-1;i>=0;i--) {
			int index = frequencies[arr[i]];
			sortedArray[index-1] = arr[i];
			frequencies[arr[i]]--;
		}

		return sortedArray;
	}

	public int[] sort() {
		return sort(this.arr);
	}

	public static void main(String[] args) {
		CountingSort s = new CountingSort(new int[] {10,9,8,7,5,0,2,1,0,60,1,4,5,30});
		System.out.println(Arrays.toString(s.sort()));
	}
}
