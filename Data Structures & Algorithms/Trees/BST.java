/* Standard implementation of a binary search tree (BST) with insert, search, and delete operations that all run in O(lgn) time */
public class BST<T extends Comparable<T>> {
	private Node<T> root;
	
	public BST() {
		root = null;
	}

	//insert data element while maintaining BST order properties
	public void insert(T data) {
		insert(data, root);
	}

	public void insert(T data, Node<T> root) {

		if(root == null) {
			root = new Node<T>(data);
		} else if(root.data.compareTo(data) > 0) {
			//if element being inserted is smaller, drill into left subtree
			if(root.lc != null) {
				insert(data, root.lc);
			} else {
				root.lc = new Node<T>(data);
			}
		} else {
			//if element being inserted is equal or larger, drill into right subtree
			if(root.rc != null) {
				insert(data, root.rc);
			} else {
				root.rc = new Node<T>(data);
			}
		}
	}


	//search for given target value, if it doesnt exist return null
	public Node<T> search(T data) {
		return search(data, root);
	}

	public Node<T> search(T data, Node<T> root) {
		if(root == null) {
			return null;
		} else if(root.data.equals(data)) {
			return root;
		} else if(root.data.compareTo(data) > 0) {
			//if current nodes data is bigger than select node drill into left subtree 
			return search(data, root.lc);
		} else {
			return search(data, root.rc);
		}
	}

	//delete first find of the target value, returns deleted node if found otherwise returns null; doing it iteratively for practice
	//but recursively (just as we did before) is also a fine approach
	public Node<T> deleteValue(T data) {
		boolean isComplete = false;
		boolean isLeftOfParent = false;
		Node<T> currentNode = root;
		Node<T> nodeToDelete = null;

		//find parent of node we're going to delete
		while(!isComplete) {
			if(currentNode.data.compareTo(data) > 0) {
				//if current node is bigger than target, search left subtree
				if(currentNode == null) {
					return null;
				} else if(currentNode.lc.data == data) {
					nodeToDelete = currentNode.lc;
					isComplete = true;
					isLeftOfParent = true;
				} else {
					currentNode = currentNode.lc
				}

			} else if(currentNode.data.compareTo(data) < 0) {
				//if current node is less than target, search right subtree
				if(currentNode == null) {
					return null;
				} else if(currentNode.rc.data == data) {
					nodeToDelete = currentNode.rc;
					isComplete = true;
				} else {
					currentNode = currentNode.rc;
				}
			}
		}

		//case 1: the node to be delete has no children
		if((nodeToDelete.lc == null) && (nodeToDelete.rc==null)) {
			if(isLeftOfParent) {
				currentNode.lc = null;
			} else {
				currentNode.rc = null;
			}
		}

		//case 2: the node to delete has one child, keep in mind that "^" is XOR in Java
		if( (nodeToDelete.lc != null) ^ (nodeToDelete.rc != null) ) {
			if(isLeftOfParent) {
				currentNode.lc = (nodeToDelete.lc != null) ? nodeToDelete.lc : nodeToDelete.rc;
			} else {	
				currentNode.rc = (nodeToDelete.lc != null) ? nodeToDelete.lc : nodeToDelete.rc;
			}
		}
		
		
		//case 3: the node to delete has two children
		//This case can be especially tricky, but by finding the predecessor (or sucessor) 
		if((nodeToDelete.lc!= null) && (nodeToDelete.rc!=null)) {
			Node<T> predecessor = findPredecessor(nodeToDelete);
			if(isLeftOfParent) {
				currentNode.lc = predecessor;
				Node<T> leftSubOfPred = predecessor.lc;
				predecessor.lc = nodeToDelete.lc;
				//predecessor by definition is guaranteed to not have a right subtree
				predecessor.rc = nodeToDelete.rc;
				//place predecessor's old (potential) left subtree as the right subtree of Pred's new left subtree
				predecessor.lc.rc = leftSubOfPred;
			} else {
				currentNode.rc = predecessor;
				Node<T> leftSubOfPred = predecessor.lc;
				predecessor.rc = nodeToDelete.rc;
				predecessor.lc = nodeToDelete.lc;
				predecessor.lc.rc = leftSubOfPred;
			}
		}
		
		return nodeToDelete;
	}

	//node must have both a left and right subtree, returns null if this condition is not met
	private Node<T> findPredecessor(Node<T> node) {
		if((node.lc != null) && (node.rc != null)) {
			node = node.lc;
			while(node.rc != null) {
				node = node.rc;
			}
			return node;
		} else {
			return null;
		}
	}

	public class Node<T extends Comparable<T>> implements Comparable<T>{
		public Node<T> lc;
		public Node<T> rc;
		public T data;

		public Node(T data) {
			this.data = data;
			this.lc = null;
			this.rc = null;
		}

		@Override
		public int compareTo(Node<T> otherNode) {
			return this.data.compareTo(otherNode.data);
		}
	}


}
