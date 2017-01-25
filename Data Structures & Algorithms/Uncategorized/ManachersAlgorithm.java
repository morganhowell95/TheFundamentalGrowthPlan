public class Solution {
    public String longestPalindrome(String s) {
        String augmentedString = augmentString(s);
        //array holding:[center, Plength]
        System.out.println(augmentedString.toString());
        Integer[] centerAndLength = _findPCenterAndLength(augmentedString);
        System.out.println(Arrays.toString(centerAndLength));
        return expandAndFilter(augmentedString, centerAndLength[0], centerAndLength[1]);
    }
    
    private String expandAndFilter(String s, int center, int palindromeLength) {
        int lastIndex = center+palindromeLength;
        int firstIndex = center-palindromeLength;
        String expansion = s.substring(firstIndex, lastIndex+1);
        StringBuilder filteredString = new StringBuilder(expansion);
        filteredString.deleteCharAt(0);
        filteredString.deleteCharAt(filteredString.length()-1);
        while(filteredString.lastIndexOf("#") != -1) {
            filteredString.deleteCharAt(filteredString.lastIndexOf("#"));
        }
        return filteredString.toString();
    }
    
    private String augmentString(String s) {
        StringBuilder augmentedString = new StringBuilder(s);
        for(int i=0;i<augmentedString.length();i+=2) {
            augmentedString.insert(i,"#");
        }
        augmentedString.insert(0,"$");
        augmentedString.append("#@");
        return augmentedString.toString();
    }
    
    private Integer[] _findPCenterAndLength(String s) {
        int center = 0;
        int rightBound = 0;
        int[] pSizes = new int[s.length()];
        int maxPSizeIndex = 0;
        
        for(int i=1;i<s.length()-1;i++) {
            
            //find reflection index
            int reflection = (2*center)-i;
            
            //if the expansion does not surpass the right bound, we know pSizes[i]>= reflection index
            if(i < rightBound) {
                pSizes[i] = Math.min(pSizes[reflection] , rightBound-i);
            }
            
            //continue expansion
            while(s.charAt(i-(pSizes[i]+1)) == s.charAt(i+(pSizes[i]+1))) {
                pSizes[i]++;
            }
            
            //if expansion surpasses the previous right bound, we reset the right bound and the center
            if(i+pSizes[i] > rightBound) {
                center = i;
                rightBound = i + pSizes[i];
            }
             
            if(pSizes[i] > pSizes[maxPSizeIndex]) {
                maxPSizeIndex = i;
            }
            
        }
        
        return new Integer[]{maxPSizeIndex, pSizes[maxPSizeIndex]};
        
    }
}
