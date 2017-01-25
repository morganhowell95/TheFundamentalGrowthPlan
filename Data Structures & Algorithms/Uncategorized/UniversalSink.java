/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
    public int findCelebrity(int n) {
        
        SortedSet<Integer> potentialCelebrities = new TreeSet<Integer>();
        SortedSet<Integer> nextBatch = new TreeSet<Integer>();
        for(int i=0;i<n;i++) {
            potentialCelebrities.add(i);
            nextBatch.add(i);
        }
        Integer currentCandidate = 0;
        boolean extraStep = true;
        
        while(potentialCelebrities.size() > 1 || extraStep) {
            
            if(potentialCelebrities.size() == 1) {
                extraStep = false;
            }

            for(Integer candidate : potentialCelebrities) {
                if(knows(currentCandidate, candidate)) {
                    nextBatch.remove(currentCandidate);
                    currentCandidate = candidate;
                    break;
                } else {
                    nextBatch.remove(candidate);
                }
            }
            potentialCelebrities = new TreeSet<Integer>(nextBatch);
            
           
        }
        
        
        return potentialCelebrities.size() == 1 ? potentialCelebrities.first() : -1;
    }
}
