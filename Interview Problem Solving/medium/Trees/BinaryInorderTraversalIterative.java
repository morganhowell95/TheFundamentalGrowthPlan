/*Given a binary tree, return the inorder traversal of its nodes' values.

*For example:
*Given binary tree [1,null,2,3],
*   1
*    \
*     2
*    /
*   3
*return [1,3,2].

*Note: Recursive solution is trivial, could you do it iteratively?
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
import java.util.Stack;
import java.util.HashSet;

public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> inorder = new ArrayList<Integer>();
        Stack<TreeNode> s = new Stack<TreeNode>();
        HashSet<TreeNode> visited = new HashSet<TreeNode>();
    
        if(root == null) {
            return inorder;
        }
        
        s.push(root);
        while(!s.empty()) {
            //DFS drill into left children
            while(root.left != null && !visited.contains(root.left)) {
                s.push(root.left);
                root = root.left;
            }
            
            //node has no left children or was previously added to the stack
            root = s.pop();
            inorder.add(root.val);
            visited.add(root);
            
            //DFS drill into right children after root has been dealt with
            if(root.right != null && !visited.contains(root.right)) {
                s.push(root.right);
                root = root.right;
            }
        }
        
        //the idea, do DFS with stack to implement iteratively 
        //add left child first
        //when no left child, add node to list, drill into right child
        return inorder;
    }
}
