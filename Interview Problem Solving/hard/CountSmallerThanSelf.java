/*
You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Given nums = [5, 2, 6, 1]

To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
Return the array [2, 1, 1, 0].*/


public class Solution {
    public List<Integer> countSmaller(int[] nums) {
        BinaryTree tree = new BinaryTree();
        List<Integer> aggregate = new ArrayList<Integer>(); 
        for(int i=nums.length-1;i>=0;i--) {
            int agg = tree.insert(nums[i]);
            aggregate.add(agg);
        }
        Collections.reverse(aggregate);
        return aggregate;
    }
    
    public class BinaryTree {
        Node root=null;
        
        
        //aggregate of left subtree counts
        public int insert(int val) {
            if(root == null) {
                Node first = new Node(val);
                root = first;
                return 0;
            } else {
                int rCount = insert(val, root, 0);
                return rCount;
            }
        }
        
        private int insert(int val, Node src, int agg) {
            if(val == src.val) {
                src.sizeLeft++;
                src.dupl++;
                return agg+(src.sizeLeft-src.dupl);
            } else {
                if(src.val < val) {
                    agg += src.sizeLeft;
                    if(src.rc == null) {
                        Node insert = new Node(val);
                        src.rc = insert;
                    } else {
                        agg = insert(val, src.rc, agg);
                    }
                } else {
                    src.sizeLeft++;
                    if(src.lc == null) {
                        Node insert = new Node(val);
                        src.lc = insert;
                        return agg;
                    } else {
                        agg = insert(val, src.lc, agg);
                    }
                }
                
                return agg;
            }
            
        }
        
        public class Node {
            int val;
            int sizeLeft;
            Node lc;
            Node rc;
            //number of duplicate vals
            int dupl;
            public Node(int val) {
             this.val = val;
             sizeLeft = 1;
             dupl = 1;
             lc = null;
             rc = null;
            }
        }   
        
    }
    
    
}
