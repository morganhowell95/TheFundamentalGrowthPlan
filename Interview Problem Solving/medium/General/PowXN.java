//implement pow(x,n)
import java.math.BigDecimal;
import java.math.RoundingMode;
public class Solution {
    public double myPow(double x, int n) {
        BigDecimal returnValue = null;
        //memoize the integer multiplied and the number of times the component is a part of this
        ArrayList<Double[]> valuesSeen = new ArrayList<Double[]>();
        HashSet<Double> exponents = new HashSet<Double>();
        valuesSeen.add(new Double[]{x, 1.0});
        exponents.add(x);
        if(n==0) {
            return 1;
        } else if(n==1){
            return x;
        } else if(Math.abs(x) == 1){
            if(n%2==1 && x==-1) {
                return -1;
            } else {
                return 1;
            }
        }else if(Math.abs(Double.valueOf(n).longValue()) > Integer.MAX_VALUE){
            return 0.0;
        }else if(n>0) {
            returnValue = auxMyPow(new BigDecimal(x), Math.abs(n)/1.0, false, valuesSeen, exponents);
        } else {
            returnValue = auxMyPow(new BigDecimal(x), Math.abs(n)/1.0, true, valuesSeen, exponents);
        }
        return returnValue.doubleValue();
    }
    
    public BigDecimal auxMyPow(BigDecimal dec, Double n, boolean isNegative, ArrayList<Double[]> valuesSeen, HashSet<Double> exponents) {
        if(n>1) {
            for(int i=valuesSeen.size()-1;i>=0;i--) {
                if(valuesSeen.get(i)[1]<n){
                    BigDecimal newDecimal = dec.multiply(new BigDecimal(valuesSeen.get(i)[0]));
                    if(!exponents.contains(valuesSeen.get(i)[1]*2)) {
                        valuesSeen.add(new Double[]{newDecimal.doubleValue(), valuesSeen.get(i)[1]*2});
                        exponents.add(valuesSeen.get(i)[1]*2);
                    }
                    return auxMyPow(newDecimal, n-valuesSeen.get(i)[1], isNegative, valuesSeen, exponents);
                }
            }
        } 
        if(isNegative) {
            BigDecimal numerator = new BigDecimal(1.0);
            return numerator.divide(dec, 5, RoundingMode.HALF_DOWN);
        } else {
            return dec;
        }
        
    }
}

