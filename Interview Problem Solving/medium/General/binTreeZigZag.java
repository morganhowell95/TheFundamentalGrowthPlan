/*
Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],

*/



/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        //base case
        if(root == null) {
            return new ArrayList<List<Integer>>();
        }
        
        Deque<ZZNode> queue = new LinkedList<ZZNode>();
        queue.addFirst(new ZZNode(root, 0));
        List<List<Integer>> zigZags = new ArrayList<List<Integer>>();
        List<Integer> singleLevel = new ArrayList<Integer>();
        boolean reversed = false;
        
        while(!queue.isEmpty()) {
            ZZNode src = queue.removeLast();
            singleLevel.add(src.node.val);
        
            if(src.node.left != null) {
                queue.addFirst(new ZZNode(src.node.left, src.depth+1));
            }
            
            if(src.node.right != null) {
                queue.addFirst(new ZZNode(src.node.right, src.depth+1));
            }
            
            if(src.depth == 0 || queue.isEmpty() || queue.getLast().depth != src.depth) {
                if(reversed) reverse(singleLevel);
                zigZags.add(deepCopy(singleLevel));
                reversed = !reversed;
                singleLevel.clear();
            } 
        
        }
        
        return zigZags;
    }
    
    public List<Integer> deepCopy(List<Integer> level) {
        List<Integer> deepCopy = new ArrayList<Integer>();
        
        for(Integer i : level) {
            deepCopy.add(i);
        }
        
        return deepCopy;
    }
    
    public void reverse(List<Integer> level) {
        for(int i=0;i<level.size()/2;i++) {
            swap(level, i, level.size()-1-i);
        }
    }
    
    public void swap(List<Integer> level, int i, int j) {
        Integer tmp = level.get(i);
        level.set(i, level.get(j));
        level.set(j, tmp);
    }
    
    
    public class ZZNode {
        public TreeNode node;
        public int depth;
        public ZZNode(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }
}
