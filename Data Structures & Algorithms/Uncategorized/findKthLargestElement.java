public class Solution {
    
    public int findKthLargest(int[] nums, int k) {
        return partition(nums, k, 0, nums.length-1);
    }
    
    private int partition(int[] nums, int k, int beg, int end) {
        swap(nums, (end+beg)/2, end);
        int pivot = _partition(nums, end, beg, end);
        System.out.println("pivot start: " + (end+beg)/2);
        System.out.println("pivot end: " + pivot);
        System.out.println(Arrays.toString(nums));
        if(pivot<nums.length-k) {
            return partition(nums, k, pivot+1, end);
        } else if(pivot>nums.length-k) {
            return partition(nums, k, beg, pivot-1);
        } else {
            return nums[pivot];
        }
    }
    
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private int _partition(int[] nums, int pivot, int beg, int end) {
        int i = beg;
        int j = beg;
        for(;j<end;j++) {
            if(nums[j] < nums[pivot]) {
                swap(nums, i, j);
                i++;
            }
        }
        j=end;
        swap(nums,i,j);
        return i;
    }
    
    
}
