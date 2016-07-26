//find the maximum sum of a contiguous subarray
//this solution is ineffecient but shows the power of a full DP table

public class Solution {
    public int maxSubArray(int[] nums) {
        //table for optimal substructure memoization
        int[][] optSubTable = new int[nums.length][nums.length];
        int i = 0;
        int j = 0;
        int diagonalCount = 1;
        
        if(nums.length == 0 || nums.length == 1) {
            return sumOf(nums);
        }
        
        //population first diagonal with base cases, where length of each considered string is 1
        while(i<nums.length && j<nums.length) {
            optSubTable[i][j] = nums[i];
            i++;
            j++;
        }
        //reset at next diagonal
        i = 0;
        j = diagonalCount;
        
        //population diagonals with recurrence formula:
        // max(optSubTable[i-1][j-1] + nums[i] , optSubTable[i+1][j])
        while(j-i < nums.length) {
            optSubTable[i][j] = Math.max(optSubTable[i][j-1]+nums[j], optSubTable[i+1][j]);
            i++;
            j++;
            if(i >= nums.length || j >= nums.length) {
                i=0;
                diagonalCount++;
                j = diagonalCount;
            }
        }
        
        return allNegativeClean(optSubTable);
    }
    
    public int allNegativeClean(int[][] table) {
        int q = Integer.MIN_VALUE;
        for(int i=0; i<table.length; i++) {
            q = Math.max(q, table[0][i]);
        }
        return q;
    }
    
    public int sumOf(int[] nums) {
        int sum = 0;
        for(int n : nums) {
            sum+= n;
        }
        return sum;
    }
  
  public static void main(String[] args) {
    Solution s = new Solution();
    int[] arr = new int[]{-2,1,-3,4,-1,2,1,-5,4};
    System.out.println(s.maxSubArray(arr));
    
  }
}
