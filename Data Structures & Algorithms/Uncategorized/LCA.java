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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        String seqP = findSeq(root, p);
        String seqQ = findSeq(root, q);
        
        String smallerSeq = seqP.length() < seqQ.length() ? seqP : seqQ;
        String largerSeq = seqP.length() < seqQ.length() ? seqQ : seqP;
        String commonSequence = "";
        for(int i=0; i<smallerSeq.length();i++) {
            if(smallerSeq.charAt(i) == largerSeq.charAt(i)) {
                commonSequence += smallerSeq.charAt(i);
            } else {
                break;
            }
        }
        
        TreeNode LCA = seqToNode(root, commonSequence);
        return LCA;
    }
    
    private String findSeq(TreeNode root, TreeNode target) {
        return _findSeq(root, target, "");
    }
    
    private TreeNode seqToNode(TreeNode root, String sequence) {
        TreeNode target  = root;
        
        for(Character c : sequence) {
            switch(c) {
                case '0':
                    target = target.left;
                    break;
                case '1':
                    target = target.right;
                    break;
                default:
                    break;
            }
        }
        
        return target;
        
    }
    
    private String _findSeq(TreeNode current, TreeNode target, String base) {
        if(current == target) {
            return base;
        } else {
            if(current.left != null) {
                return _findSeq(current.left, target, base + "0");
            }
            
            if(current.right != null) {
                return _findSeq(current.right, target, base + "1");
            }
        }
        return base; //not found
    }
    
    
}
