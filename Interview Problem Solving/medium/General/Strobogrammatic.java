/*A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

For example,
Given n = 2, return ["11","69","88","96"].*/


public class Solution {
    public List<String> findStrobogrammatic(int n) {
        List<String> entries = new ArrayList<String>();
        findStrobogrammatic(n, entries, "");
        return entries;
    }
    
    private void findStrobogrammatic(int n, List<String> entries, String progress){
        if(n == 0) {
            if(progress.length()>0) {
                entries.add(progress);
            }
        } else if(n == 1) {
            String firstHalf = progress.substring(0, progress.length()/2);
            String secondHalf = progress.substring(progress.length()/2, progress.length());
            String[] onePossibles = new String[] {"0","1","8"};
            for(String s: onePossibles) {
                entries.add(firstHalf + s + secondHalf);
            }
        } else {
            String firstHalf = progress.substring(0, progress.length()/2);
            String secondHalf = progress.substring(progress.length()/2, progress.length());
            String[] twoPossibles;
            if(progress.length()>0) {
                twoPossibles = new String[] {"00", "11", "69", "88", "96"};
            } else {
                twoPossibles = new String[] {"11", "69", "88", "96"};
            }
            
            for(String s: twoPossibles) {
                findStrobogrammatic(n-2, entries, firstHalf + s + secondHalf);
            }
            
        } 
    }
    
    
}
