//Tested on leet code
//https://leetcode.com/problems/plus-one/
public class AddOneToNumInArray {
    public int[] plusOne(int[] digits) {
        
        if(digits[digits.length-1] != 9) {
            digits[digits.length-1]++;
        } else {
            digits = rippleAndCarry(digits);
        }
        
        return digits;
    }
    
    
    public int[] rippleAndCarry(int[] digits) {
        
        int i;
        for(i = digits.length-1; i >= 0; i--) {
            if(digits[i] == 9 && i!=0) {
                digits[i] = 0;
            } else {
                digits[i]++;
                break;
            }
        }
        
        //if for-loop completed  and incremented most significant digit we need to reset and augment the number
        if(i==0 && digits[0] == 10) {
            digits = resetAndAugment(digits);
        }
        return digits;
    }
    
    public int[] resetAndAugment(int [] digits) {
        int[] digitsv2 = new int[digits.length+1];
        digitsv2[0] = 1;
        return digitsv2;
    }
}
