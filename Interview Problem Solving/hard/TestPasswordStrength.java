/*
A password is considered strong if below conditions are all met:

It has at least 6 characters and at most 20 characters.
It must contain at least one lowercase letter, at least one uppercase letter, and at least one digit.
It must NOT contain three repeating characters in a row ("...aaa..." is weak, but "...aa...a..." is strong, assuming other conditions are met).
Write a function strongPasswordChecker(s), that takes a string s as input, and return the MINIMUM change required to make s a strong password. If s is already strong, return 0.

Insertion, deletion and replace of any one character are all considered as one change.

Update (2016-10-14): Fixed test cases. */




public class Solution {
    
    public enum Condition {
      HAS_UPPER, HAS_LOWER, HAS_DIG
    }
    
    public static int MAX_LEN = 20;
    
    public int strongPasswordChecker(String s) {
        
        StringBuilder mutStr = new StringBuilder(s);
        boolean[] conditions = new boolean[Condition.values().length];
        int repCount = 0;
        int totalChanges = 0;
        char curChar = '\0';
        int i = 0;
        
        //preprocess third rep routine
        while(i<mutStr.length() && mutStr.length()>2) {
            //update repetition count
            boolean thirdCycle = false;
            if(curChar == '\0' || curChar != mutStr.charAt(i)) {
                repCount=1;
            } else {
                repCount++;
                if(repCount == 3) {
                    thirdCycle = true;
                    repCount = 0;
                }
            }
            curChar = mutStr.charAt(i);
            updateSensitivity(curChar, conditions);
            
            //string is too long
            if(mutStr.length()>20) {
                if(thirdCycle) {
                    mutStr.replace(i, i+1, "*");
                } 
                
            //string is too short
            } else if(mutStr.length()<6) {
                if(thirdCycle) {
                    mutStr.insert(i, '*');
                }
            
            //string has valid length
            } else {
                if(thirdCycle) {
                    //we can greedily replace 
                    mutStr.replace(i, i+1, "*");
                }
            }
            
         i++;
        }
        
        //given wild cards in the string, adjust sensitivity accordingly
        for(i=0;i<mutStr.length();i++) {
            if(mutStr.charAt(i) == '*' && mutStr.length() <= MAX_LEN) {
                insertArbitraryCond(conditions);
                totalChanges++;
            } else if(mutStr.charAt(i) == '*' && mutStr.length() > MAX_LEN && needsChar(conditions)){
                insertArbitraryCond(conditions);
                totalChanges++;
            } else if(mutStr.charAt(i) == '*' && mutStr.length() > MAX_LEN && !needsChar(conditions)) {
                mutStr.deleteCharAt(i);
                totalChanges++;
            }
        }
        
        //after preprocessing we can check size constraints and see if any needed chars are not present
        if(mutStr.length()>20) {
            totalChanges += mutStr.length() - 20;
        } else if(mutStr.length()<6) {
            for(i=mutStr.length();i<6;i++) {
                totalChanges++;
                insertArbitraryCond(conditions);
            }
        }
        
    
        
        //it's possible that after the preprocessing and size readjustment, there are still some chars that need to be included
        for(i=0;i<conditions.length;i++) {
            if(!conditions[i]) {
                totalChanges++;
            }
        }
        
        return totalChanges;
        
    }
    
    public boolean needsChar(boolean[] conditions) {
        for(int i=0; i<conditions.length; i++) {
            if(!conditions[i]) {
                return true;
            }
        }
        return false;
    }
    
    
    //when we insert a character we can assume that we can also fulfill a needed char condition
    public void insertArbitraryCond(boolean[] conditions) {
        for(int i=0; i<conditions.length; i++) {
            if(!conditions[i]) {
                conditions[i] = true;
                break;
            }
        }
    }
    
    //given a character we should update the sensitivity conditions
    public void updateSensitivity(char current, boolean[] conditions) {
        if(current >= 'a' && current <= 'z') {
            conditions[Condition.HAS_LOWER.ordinal()] = true;
        } else if(current >= 'A' && current <= 'Z') {
            conditions[Condition.HAS_UPPER.ordinal()] = true;
        } else if(current >= '0' && current <= '9') {
            conditions[Condition.HAS_DIG.ordinal()] = true;
        }
    }
    
}
