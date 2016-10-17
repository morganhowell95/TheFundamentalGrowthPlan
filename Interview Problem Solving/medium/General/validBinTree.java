
/*

Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
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
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, new Long[]{Long.MIN_VALUE, Long.MAX_VALUE});
    }
    
    public boolean isValidBST(TreeNode root, Long[] bounds) {
        if(root == null) return true;
        if(root != null && root.val <= bounds[0]) return false;
        if(root != null && root.val >= bounds[1]) return false;
        if(root != null) {
            if(root.left != null) {
                if (root.left.val == root.val) return false;
            }
            if(root.right != null) {
                if(root.right.val == root.val) return false;
            }
        }
        return isValidBST(root.left, new Long[]{bounds[0], Long.valueOf(root.val)}) &&
                isValidBST(root.right, new Long[]{Long.valueOf(root.val), bounds[1]});
    }
}
