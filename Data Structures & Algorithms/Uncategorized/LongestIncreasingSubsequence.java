public class Solution {
    public static boolean n2algo = true;
    
    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        
        if(n2algo) {
            return algon2(nums);
        } else {
            return -1;
        }
    }
    
    private int algon2(int[] nums) {
        int[] dpTable = new int[nums.length];
        int max = 1;
        for(int i=0;i<dpTable.length;i++) {
            dpTable[i] = 1;
        }
        
        for(int i=1;i<nums.length;i++) {
            for(int j=0;j<i;j++) {
                if(nums[i] > nums[j]) {
                    dpTable[i] = Math.max(dpTable[i], dpTable[j] + 1);
                    if(dpTable[i] > max) {
                        max = dpTable[i];
                    }
                }
            }
        }
        return max;
    }
}
