/*

One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as #.

     _9_
    /   \
   3     2
  / \   / \
 4   1  #  6
/ \ / \   / \
# # # #   # #
For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.

Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. Find an algorithm without reconstructing the tree.

Each comma separated value in the string must be either an integer or a character '#' representing null pointer.

You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".

Example 1:
"9,3,4,#,#,1,#,#,2,#,6,#,#"
Return true

Example 2:
"1,#"
Return false

Example 3:
"9,#,#,1"
Return false

*/




public class Solution {
    public boolean isValidSerialization(String preorder) {
        
        Deque<Node> stack = new LinkedList<Node>();

        if(preorder.length()==0 || (preorder.length()==1 && preorder.charAt(0)=='#')) {
            return true;
        }
        
        String payload = "";
        
        for(int i=0;i<preorder.length();i++) {
            char c = preorder.charAt(i);
            
            if(c != ',' && i!=preorder.length()-1) {
                    payload += c;
            } else {
                //collect last char
                if(i==preorder.length()-1) {
                    payload += c;
                }
                
                if(payload.equals("#") && stack.isEmpty())  {
                    return false;
                } else if(stack.isEmpty()) {
                    Node insert = new Node(payload);
                    stack.addFirst(insert);
                } else if(!payload.equals("#")){
                    
                    if(stack.getFirst().isComplete()) {
                        return false;
                    } else {
                        stack.getFirst().mark();
                        if(stack.getFirst().isComplete()) {
                            stack.removeFirst();
                        }
                        Node insert = new Node(payload);
                        stack.addFirst(insert);
                    }
                        
                } else {
                    if(stack.getFirst().isComplete()) {
                        return false;
                    } else {
                        stack.getFirst().mark();
                    }
                    
                    if(stack.getFirst().isComplete()) {
                        stack.removeFirst();
                    }
                }
                    
                //check to make sure we haven't exhausted the stack
                if(stack.isEmpty() &&  i!=preorder.length()-1) {
                    return false;
                }
                
                payload = "";
            }
            
        }
        return stack.isEmpty();
    }
    
    public class Node {
        public String val;
        public boolean hasLeft;
        public boolean hasRight;
        public Node(String val) {
            this.val = val;
            hasLeft = false;
            hasRight = false;
        }
        
        public void mark() {
            if(!hasLeft) {
                hasLeft = true;
            } else if(!hasRight) {
                hasRight = true;
            }
        }
        
        public boolean isComplete() {
            return hasLeft && hasRight;
        }
    }
}
