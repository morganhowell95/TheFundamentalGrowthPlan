//https://leetcode.com/problems/valid-perfect-square/
public class IsNumberPerfectSquare {
    public boolean isPerfectSquare(int num) {
        long lower = 1;
        long upper = (int) Math.ceil(num/3.0);
        return binSearch(lower, upper, num);
    }
    
    public boolean binSearch(long lower, long upper, int num) {
        long mid = (long) Math.ceil((upper+lower)/2.0);
        System.out.println(mid);
        if(lower <= upper) {
            if(mid*mid > num) {
                return binSearch(lower, mid-1, num);
            } else if(mid*mid < num) {
                return binSearch(mid+1, upper, num);
            } else {
                return true;
            }
        } else {
            return false;
        }
        
    }
}
