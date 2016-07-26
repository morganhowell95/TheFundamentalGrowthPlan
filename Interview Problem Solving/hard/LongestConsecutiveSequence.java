/*
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.
*/
import java.util.HashMap;
public class Solution {
    public int longestConsecutive(int[] nums) {
        
        //base case
        if(nums.length == 0) {
            return 0;
        }
        if(nums.length == 1) {
            return 1;
        }
        
        //hash all numbers, and map to itself as the parent, representing a disjoint set
        HashMap<Integer, Node<Integer>> occurrences = new HashMap<Integer, Node<Integer>>();
        for(int n : nums) {
            occurrences.put(n, this.new Node<Integer>(n));
        }
        
        //use disjoint set forest to begin unioning numbers that are consecutive, taking notice of rank and path compression
        DisjointSetForest df = this.new DisjointSetForest(occurrences);
        // for(Node<Integer> n : occurrences.values()) {
        //     df.addDSet(n);
        // }
        
        for(Node<Integer> n: occurrences.values()) {
            if(occurrences.containsKey(n.data+1)) {
                df.union(occurrences.get(n.data), occurrences.get(n.data+1));
            }
            if(occurrences.containsKey(n.data-1)) {
                df.union(occurrences.get(n.data), occurrences.get(n.data-1));
            }
        }
        
        return df.getMax();
    }
    
    public class DisjointSetForest {
        int max;
        HashMap<Integer, Node<Integer>> nodeRefs;
        
        public DisjointSetForest(HashMap<Integer, Node<Integer>> nodeRefs){
            max = Integer.MIN_VALUE;
            this.nodeRefs = nodeRefs;
        }
        
        //return representative node and enforce path compression for effeciency
        public Node<Integer> find(Node<Integer> n) {
            if(n == n.parent) {
                return n;
            } else {
                n.parent = find(n.parent);
            }
            return n.parent;
        }
        
        //union two disjoint sets and enforce rank
        public void union(Node<Integer> n1, Node<Integer> n2) {
            if(find(n1) != null && find(n2) != null && find(n1) != find(n2)) {
                link(find(n1), find(n2));
            }
        }
        
        //link two sets given the representative nodes
        public void link(Node<Integer> n1, Node<Integer> n2) {
            Node<Integer> parent = null;
            
            //link higher rank to lower rank to reduce lookup time
            if(n1.rank > n2.rank) {
                n2.parent = n1;
                parent = n1;
            } else {
                n1.parent = n2;
                parent = n2;
            }
            
            //deal with adjusted ranks and record max
            parent.rank++;
            if(parent.rank > max) {
                max = parent.rank;
            }
        }
        
        //set with the most nodes
        public int getMax() {
            return max;
        }
    }
    
    
    //node representation of a single numerical instance
    public class Node<T> {
        public int rank;
        public Node parent;
        public T data;
        
        public Node(T data) {
            this.data = data;
            this.parent  = this;
            this.rank = 1;
        }
    } 
}
