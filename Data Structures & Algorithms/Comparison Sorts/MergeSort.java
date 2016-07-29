//O(nlgn) comparison sort based algorithm that requires O(n) space complexity
public class MergeSort {
  public static void main(String[] args) {
    MergeSort s = new MergeSort();
    int[] test = s.sort(new int[]{5,4,3,2,1,-50});
    System.out.println(Arrays.toString(test));
  }
  
  public int[] sort(int[] arr) {
    if(arr.length>1) {
      //find mid to divide list
      int mid = (arr.length-1)/2;

      //create left subsection
      int[] leftArr = new int[mid+1];
        for(int i=0; i<mid+1; i++){
          leftArr[i] = arr[i];
        }

      //create right subsection
      int[] rightArr = new int[arr.length-(mid+1)];
      for(int i=0; i<arr.length-(mid+1); i++) {
        rightArr[i] = arr[arr.length-1-i];
      }

      //make recursive calls with these sections
      int[] left = sort(leftArr);
      int[] right = sort(rightArr);

      //combine the sorted solutions into final array
      int i=0,j=0,k=0;

      //while both contain sorted entries
      while(i < left.length && j<right.length) {
        if(left[i] < right[j]) {
          arr[k] = left[i];
          i++;
        } else {
          arr[k] = right[j];
          j++;
        }
        k++;
      }

      //cover case where one is exhausted, but other still has entries
      while(i < left.length) {
        arr[k] = left[i];
        i++;
        k++;
      }
      while(j < right.length){
        arr[k] = right[j];
        j++;
        k++;
      }

      return arr;

    } else {
      return new int[]{arr[0]};
    }
  }

}

