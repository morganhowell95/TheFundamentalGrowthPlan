/*
 * Couple different ways to reverse a linked list
 */

public class CommonLinkedListOperations<T> {

	public CommonLinkedListOperations(){}

	//reverse linked list revursively given the root node
	public Node<T> reverseViaRecursion(Node<T> n, Node<T> prev) {
		if(n != null) {
			Node<T> nextNode = n.next;
			n.next = prev;
			return reverseViaRecursion(nextNode, n);
		} else {
			return prev;
		}
	}
	
	//reverse linked list iteratively given root node
	public Node<T> reverseViaIteration(Node<T> n) {
		Node<T> tail = n;
		Node<T> prev = null;
		while(tail != null) {
			Node<T> nextNode = tail.next;
			tail.next = prev;
			prev = tail;
			tail = nextNode;
		}
		return prev;
	}

	/* Deletes all occurrences of nodes that hold the target value
	 *
	 *@param1: linked list
	 *@param2: target value to delete
	 *@rvalue: linked list not containing any of the nodes that previously had the target value
	 */
	public Node<T> deleteNodeFromList(Node<T> n, T target) {
		Node<T> lagNode = n;
		Node<T> currentNode = null;

		//ensure that the first node is not null or the target
		if(lagNode == null) {
			return null;
		} else if(lagNode.data == target){
			return deleteNodeFromList(lagNode.next, target);
		} else {
			//proceed to scan through the linked list 
			currentNode = lagNode.next;
			while(currentNode!=null) {
				if(currentNode.data == target) {
					lagNode.next = currentNode.next;
					return deleteNodeFromList(n, target);
				}
				lagNode = lagNode.next;
				currentNode = currentNode.next;
			}
		}
		return n;
	}


	public static class Node<T>{
		public T data;
		public Node<T> next;

		public Node(T data) {
			this.data = data;
			this.next = null;
		}
	}

	//Minimal Testing
	public static void main(String[] args) {
		CommonLinkedListOperations<Integer> s = new CommonLinkedListOperations<Integer>();
		Node<Integer> root = new Node<Integer>(-1);
		Node<Integer> tail = root;
		for(int i=0;i<55;i++) {
			tail.next = new Node<Integer>(i);
			tail = tail.next;
		}

		Node<Integer> head = root;
		System.out.println("The original linked list");
		while(head!=null) {
			System.out.print(head.data + "->" );
			head = head.next;
		}
		
		System.out.println("\n\n--------------------------\n\n");
		Node<Integer> newReversedRoot = s.reverseViaRecursion(root, null);
		head = newReversedRoot;
		System.out.println("The linked list reversed");
		while(head!=null) {
			System.out.print(head.data + "->" );
			head = head.next;
		}

		System.out.println("\n\n--------------------------\n\n");
		head = s.reverseViaIteration(newReversedRoot);
		System.out.println("The reversed linked list reversed.... Back to the original list");
		while(head!=null) {
			System.out.print(head.data + "->" );
			head = head.next;
		}
		System.out.println("\n\n--------------------------\n\n");

		System.out.println("Without the value 5 deleted");
		root = new Node<Integer>(-1);
		tail = root;
		for(int j=0;j<10;j++){
			for(int i=1;i<6;i++) {
				tail.next = new Node<Integer>(i);
				tail = tail.next;
			}
		}
		head = root;
		while(head!=null) {
			System.out.print(head.data + "->");
			head = head.next;
		}
		System.out.println("\n\n----------------------\n\n");
		System.out.println("With the target value deleted:");
		Node<Integer> newLLWithoutValue = s.deleteNodeFromList(root, 5);
		head = root;
		while(head!=null) {
			System.out.print(head.data + "->");
			head = head.next;
		}
	}
}
