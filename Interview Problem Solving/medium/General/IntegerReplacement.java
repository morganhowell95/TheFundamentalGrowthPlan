/*
Given a positive integer n and you can do operations as follow:

If n is even, replace n with n/2.
If n is odd, you can replace n with either n + 1 or n - 1.
What is the minimum number of replacements needed for n to become 1?
*/

public class Solution {
    public int integerReplacement(int n) {
        //return integerReplacement(n, 0);
        long base = Long.valueOf(n);
        int count = 0;
        while(base!=1) {
            if(base % 2 == 1) {
                if(base != 3 && ((base+1)/2) % 2 == 0) {
                    base+=1;
                    count++;
                } else {
                    base-=1;
                    count++;
                }
            } else {
                base/=2;
                count++;
            }
        }
        
        return count;
    }
 
    private int integerReplacement(int n, int count) {
        if(n==1) {
            return count;
        }

        if(n % 2 == 1) {
            if(n != 3 && ((n+1)/2) % 2 == 0) {
                return integerReplacement(n+1, count+1);
            } else {
                return integerReplacement(n-1, count+1);
            }
        } else {
            return integerReplacement(n/2, count+1);
        }
    }
}
