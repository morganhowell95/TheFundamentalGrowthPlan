import java.util.HashMap;
import java.util.Arrays;

public class KMostFrequentIntegers {
    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> ht = new HashMap<Integer, Integer>();
        
        //make hash table of frequencies int num: int frequencies
        for(int num : nums) {
            if(ht.containsKey(num)) {
                int currentFreq = ht.get(num);
                ht.put(num, currentFreq+1); 
            } else {
                ht.put(num, 1); 
            }
        }
        
        //counting sort is able to sort in linear time, since frequency at worst will be N we also only have N space complexity
        FreqEnc [] frequencies = new FreqEnc[ht.size()];
        //fill array with frequencies
        int i=0;
        for(Integer ks : ht.keySet()) {
            FreqEnc freObj = this.new FreqEnc(ht.get(ks), ks);
            frequencies[i] = freObj;
            i++;
        }
        //sorted these frequencies via counting sort
        frequencies = linearSort(frequencies);
        
        List<Integer> finalList = new ArrayList<Integer>();
        //grab the last k elements, which are the k most frequent elements
        for(int j=frequencies.length-1; j>=frequencies.length-k; j--) {
            finalList.add(frequencies[j].num);
        }
        
        //TOTAL RUN TIME:
        // Make hash map of frequencies:        O(N) with O(1) average constant insertion/look-up time
        // Create frequency list of hash keys:  O(N)
        // Compound frequency list to cum sum:  O(N)
        // linear counting sort of frequencies: O(N)  (worst case is every element is the same, which can only happen N times)
        // grabbing the last K elements of:     O(K)
        //FINAL TOTAL:                          O(N) - better than O(nlgn)!        
        return finalList;
    }
    
    public FreqEnc[] linearSort(FreqEnc[] frequencies) {
        //max frequency will be the size of our working buffer
        int max = Integer.MIN_VALUE;
        for(FreqEnc f : frequencies) {
            if(f.frequency > max) {
                max = f.frequency;
            }
        }
        
        int[] workingMem = new int[max+1];
        //fill working buffer with counts of frequency
        for(FreqEnc f : frequencies) {
            workingMem[f.frequency]++; 
        }
        
        
        //cumulative total working mem array, which is position of elements in sorted array
        for(int i=2; i < workingMem.length; i++) {
            workingMem[i] += workingMem[i-1];
        }
        
        //dissecting the cumulative totals, along with scanning original array backwards, to construct new sorted array holding 
        //the original objecte
        FreqEnc[] sortedFreq = new FreqEnc[frequencies.length];
        for(int i=frequencies.length-1;i>=0;i--) {
            FreqEnc f = frequencies[i];
            sortedFreq[workingMem[f.frequency]-1] = f;
            workingMem[f.frequency]--;
        }
        return sortedFreq;
    }
    
    public class FreqEnc {
        public int frequency;
        public int num;
        public FreqEnc(int frequency, int num) {
            this.frequency = frequency;
            this.num = num;
        }
    }
}
