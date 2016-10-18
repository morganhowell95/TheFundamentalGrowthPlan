/*

Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.



Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].*/

public class Solution {
    public List<String> letterCombinations(String digits) {
        //number mapping
        HashMap<Character, String> mapping = new HashMap<Character, String>();
        mapping.put('2', "abc");
        mapping.put('3', "def");
        mapping.put('4', "ghi");
        mapping.put('5', "jkl");
        mapping.put('6', "mno");
        mapping.put('7', "pqrs");
        mapping.put('8', "tuv");
        mapping.put('9', "wxyz");
        List<String> rVals = new ArrayList<String>();
        letterCombinations(digits, mapping, rVals, "", 0);
        return rVals;
    }
    
    
    private void letterCombinations(String digits, HashMap<Character, String>  mapping, 
        List<String> rVals, String bucket, int currentIndex) {
        if(currentIndex>=digits.length()) {
            if(bucket.length()>0) {
                rVals.add(bucket);
            }
        } else {
            char[] strIter = mapping.get(digits.charAt(currentIndex)).toCharArray();
            for(char letter : strIter) {
                //letterCombinations(digits.substring(currentIndex+1, digits.length()), mapping, rVals, 
                  //bucket + letter, currentIndex+1, top);
                letterCombinations(digits, mapping, rVals, 
                    bucket + letter, currentIndex+1);
            }
        }
        
    }
}
