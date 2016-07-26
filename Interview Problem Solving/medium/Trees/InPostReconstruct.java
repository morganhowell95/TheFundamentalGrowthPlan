
//Given inorder and postorder traversal of a tree, construct the binary tree.

//Note:
//You may assume that duplicates do not exist in the tree.

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder.length == 0) {
            return null;
        }
        
        if(inorder.length == 1) {
            return new TreeNode(inorder[0]);
        }
        
        return auxBuildTree(convertToList(inorder), convertToList(postorder), null);
    }
    
    private ArrayList<Integer> convertToList(int[] arr) {
        ArrayList<Integer> convertedArr = new ArrayList<Integer>();
        for(int i: arr) {
            convertedArr.add(i);
        }
        return convertedArr;
    }
    
    private TreeNode auxBuildTree(ArrayList<Integer> inorder, ArrayList<Integer> postorder, TreeNode root) {
        
        if(postorder.size() > 0) {
            //always read post order from right to left, building new nodes from right most element
            root = new TreeNode(postorder.remove(postorder.size()-1));
        } else {
            return null;
        }
        
        int rootLocationIO = inorder.indexOf(root.val);
        
        if(rootLocationIO+1 < inorder.size()) {
            ArrayList<Integer> inorderSub = new ArrayList<Integer>(inorder.subList(rootLocationIO+1, inorder.size()));
            root.right = auxBuildTree(inorderSub, postorder, root.right);
        } 
        
        if(rootLocationIO-1 >= 0) {
            ArrayList<Integer> inorderSub = new ArrayList<Integer>(inorder.subList(0, rootLocationIO));
            root.left = auxBuildTree(inorderSub, postorder, root.left);
        } 
        
        return root;
    } 
    
    
}
