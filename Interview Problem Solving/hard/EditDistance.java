/*
Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

a) Insert a character
b) Delete a character
c) Replace a character
*/

//The challenge with this problem certainly isn't in the implementation
//make sure you conceptual understand each matrix entry operation and how it relates to optimal substructure
//Efficiency: O(N^2)

public class Solution {
    public int minDistance(String word1, String word2) {
        int[][] memoizeTable =  new int[word1.length()+1][word2.length()+1];
        //fill table with base case of comparing null to each word
        for(int i=0; i<word1.length()+1;i++) {
            memoizeTable[i][0]=i;
        }
        for(int i=0; i<word2.length()+1;i++) {
            memoizeTable[0][i]=i;
        }
        
        //work from left to right, building up optimal substructure from word1->word2
        for(int i=1; i<word1.length()+1;i++) {
            for(int j=1;j<word2.length()+1;j++) {
                if(word1.charAt(i-1) == word2.charAt(j-1)) {
                    memoizeTable[i][j] = memoizeTable[i-1][j-1];
                } else {
                    int minOperations = Math.min(memoizeTable[i-1][j], memoizeTable[i-1][j-1]);
                    minOperations = Math.min(minOperations, memoizeTable[i][j-1]);
                    memoizeTable[i][j] = minOperations+1;
                }
            }
        }
        
        return memoizeTable[word1.length()][word2.length()];
    }
}

