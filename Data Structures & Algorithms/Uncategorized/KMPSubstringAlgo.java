import java.io.*;
import java.util.*;

class Solution {
  public static void main(String[] args) {
    
    Solution s = new Solution();
    
    System.out.println(s.match("abcabxyz", "aby"));
  }
  
  
  
  private void createPrefixArray(int[] prefixArray, char[] pattern) {
   int j =1;
   int i =0;
   prefixArray[0] = 0;
    
  while(i<pattern.length && j<pattern.length) {
    if(pattern[i] == pattern[j]) {
      prefixArray[j] = i+1;
      i++;
      j++;
    } else {
      if(i == 0) {
       prefixArray[j] = 0;
       j++;
      } else {
       i =  prefixArray[i-1];
      } 
    }  
  }
  }
  
  
  private boolean KMPmatch(int[] prefixArray, char[] pattern, char[] text) {
    
    int i = 0;
    int j = 0;

    while(i<pattern.length && j<text.length) {
      
      if(pattern[i] == text[j]) {
       i++; 
       j++;
      } else {
       if(i == 0) {
        j++; 
       } else {
        i = prefixArray[i-1]; 
       }
      }      
      
    }
    
  return i == pattern.length;
}
  
  
  public boolean match(String text, String pattern) {
    
    if(pattern.length() == 0) {
     return true; 
    }
    
    int[] prefixArray = new int[pattern.length()];
    createPrefixArray(prefixArray, pattern.toCharArray());
    return KMPmatch(prefixArray, pattern.toCharArray(), text.toCharArray());
  }
  
  
}

