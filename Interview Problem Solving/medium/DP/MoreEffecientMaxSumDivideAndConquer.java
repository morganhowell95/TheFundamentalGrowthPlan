//find max sum of contiguous subarray
//This solution is more efficient than the full out DP table, it is NLOGN where the DP table is N^2

public class Solution {
    public int maxSubArray(int[] nums) {
        return auxMaxSub(nums, 0, nums.length-1);
    }
    
    public int auxMaxSub(int[] nums, int start, int end) {
        if(start >= end) {
            return nums[end];
        }
        
        int mid = (start+end)/2;
        int left_max = auxMaxSub(nums, start, mid);
        int right_max = auxMaxSub(nums, mid+1, end);
        
        int sum_left = Integer.MIN_VALUE;
        int sum = Integer.MIN_VALUE;
        int sum_right = Integer.MIN_VALUE;

        for(int i= mid+1; i<=end; i++) {
            if(sum == Integer.MIN_VALUE) {
                sum = 0;
            }
            sum+=nums[i];
            sum_left = Math.max(sum_left, sum);
        }
        
        sum = Integer.MIN_VALUE;
        for(int i= mid; i>=start; i--) {
            if(sum == Integer.MIN_VALUE) {
                sum = 0;
            }
            sum+=nums[i];
            sum_right = Math.max(sum_right, sum);
        }
        
        int solution = Math.max(left_max, right_max);
        
        if(sum_right != Integer.MIN_VALUE && sum_left != Integer.MIN_VALUE) {
            solution = Math.max(solution, sum_right + sum_left);
        }
        return solution;
    }
    
}
