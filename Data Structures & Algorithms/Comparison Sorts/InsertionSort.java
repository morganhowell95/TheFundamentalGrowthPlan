//Basic sorting algo that has best case O(n) time and worst case O(n^2) time, very useful for certain scenarios when knowledge is known
//about element distribution, such as in bucket sort
import java.util.Arrays;
public class InsertionSort{
	
	public int[] sort(int[] arr) {
		for(int i=0; i<arr.length-1; i++) {
			int j = i+1;
			while(j>0 && arr[j] < arr[j-1]) {
				swap(arr, j, j-1);
				j--;
			}
		}

		return arr;
	}

	private void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	} 


        public static void main(String[] args) {
                InsertionSort s = new InsertionSort();
                int[] test = s.sort(new int[]{33,10000,0,5,4,3,2,1,-50,-15,-1234123,12341,12341,23,43432,2342,112,234,10,1003});
                System.out.println(Arrays.toString(test));
        }   
}
