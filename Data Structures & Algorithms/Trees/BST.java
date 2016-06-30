/* Standard implementation of a binary search tree (BST) with insert, search, and delete operations that all run in O(lgn) time */
public class BST<T extends Comparable<T>> {
	public Node<T> root;
	
	public BST() {
		this.root = null;
	}

	//insert data element while maintaining BST order properties
	public void insert(T data) {
		insert(data, this.root);
	}

	public void insert(T data, Node<T> root) {
		if(root == null) {
			this.root = new Node<T>(data);
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
	public Node<T> delete(T data) {
		return delete(this.root, data);
	}


	private Node<T> delete(Node<T> root, T data) {

		if(root == null) {
			return null;
		} else if(root.data.compareTo(data) > 0) {
			root.lc = delete(root.lc, data);
		} else if(root.data.compareTo(data) < 0) {
			root.rc = delete(root.rc, data);

		//we have found root containing data
		} else {
			//case 1: node has no children
			if(root.rc == null && root.lc == null) {
				root = null;
			//case 2: node has one child
			} else if (root.rc == null) {
				root = root.lc;
			} else if(root.lc == null) {
				root = root.rc;
			//case 3: node has two children
			} else {
				//equally valid to have also found the successor (smallest node of right subtree)
				Node<T> pred = findPredecessor(root);
				root.data = pred.data;
				root.lc = delete(root.lc, pred.data);
			}

		}
		return root;
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
	
	//print nodes in-order 
	public void printInOrder(Node<T> node){
		if(node.lc != null) {
			printInOrder(node.lc);
		}

		System.out.println(node.data);

		if(node.rc != null) {
			printInOrder(node.rc);
		}
	}

	public class Node<T extends Comparable<T>> implements Comparable<Node<T>>{
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

	public static void main(String[] args) {
		//Example from: https://en.wikipedia.org/wiki/Tree_traversal
		BST<Integer> bst = new BST<Integer>();
		bst.insert(15);
		bst.insert(10);
		bst.insert(4);
		bst.insert(13);
		bst.insert(12);
		bst.insert(14);
		bst.insert(25);
		bst.insert(22);
		bst.insert(17);
		bst.insert(27);
		bst.insert(30);
		bst.insert(29);
		bst.insert(31);
		bst.insert(26);
		bst.printInOrder(bst.root);
		System.out.println("\n\n\n -------------- after 25 deletion:---------------  \n\n\n");
		bst.delete(25);
		bst.printInOrder(bst.root);
	}
}
