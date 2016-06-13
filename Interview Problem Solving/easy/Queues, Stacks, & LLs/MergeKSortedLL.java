/*
 * Given an array of K nodes, where each node is the head of a sorted linked list, merge all the nodes 
 * into one linked list in sorted order.
 * Complexity: O(n*k^2) - k searches for every node, where there are k*n total nodes, so total runtime is: k*(k*n) where n
 * is the longest number of nodes in one list, this total runtime is O(n*k^2)
 */
import java.util.ArrayList;

public class MergeKSortedLL<T extends Comparable<T>> {
	public MergeKSortedLL(){}

	public ArrayList<Node<T>> generateArrayOfSortedLinkedLists(){
		ArrayList<Node<T>> nodes = new ArrayList<Node<T>>(); 
		nodes.add(this.new Node<T>((T) new Integer(-50)));
		nodes.add(this.new Node<T>((T) new Integer(1000)));
		nodes.add(this.new Node<T>((T) new Integer(0)));
		nodes.add(this.new Node<T>((T) new Integer(500)));

		Node<T> builderTail = nodes.get(0);
		for(int i=-50; i<450; i = i+20) {
			builderTail.next = this.new Node<T>((T) new Integer(i));
			builderTail = builderTail.next;
		}
		
		builderTail = nodes.get(1);
		for(int i=1000; i<1015; i++) {
			builderTail.next = this.new Node<T>((T) new Integer(i));
			builderTail = builderTail.next;
		}

		
		builderTail = nodes.get(2);
		for(int i=0; i<1800; i=i+75) {
			builderTail.next = this.new Node<T>((T) new Integer(i));
			builderTail = builderTail.next;
		}

		builderTail = nodes.get(3);
		for(int i=500; i<1000; i=i+20) {
			builderTail.next = this.new Node<T>((T) new Integer(i));
			builderTail = builderTail.next;
		}
		return nodes;
	}

	private int findIndexOfSmallestNode(ArrayList<Node<T>> n) {
		int index = -1;
		Node<T> temp = null;
		for(int i=0; i<n.size(); i++) {
			if(n.get(i)!=null && temp == null) {
				temp = n.get(i);
				index = i;
			} else if(n.get(i)!=null && (n.get(i).data.compareTo(temp.data)==-1)) {
				temp = n.get(i);
				index = i;
			}
		}
		return index;
	}

	public Node<T> mergeKSortedLinkedLists(ArrayList<Node<T>> sortedNodes) {
		Node<T> masterList = null;
		Node<T> tailBuilder = null;
		boolean complete = false;

		while(!complete) {
			int smallestIndex = findIndexOfSmallestNode(sortedNodes);
			if(smallestIndex == -1) {
				complete = true;
			} else {
				if(masterList == null){
					masterList = this.new Node<T>(sortedNodes.get(smallestIndex).data);
					tailBuilder = masterList;
				} else {
					tailBuilder.next = this.new Node<T>(sortedNodes.get(smallestIndex).data);
					tailBuilder = tailBuilder.next;
				}
				//increment selected (smallest)  node within sortedNodes up one
				sortedNodes.set(smallestIndex, sortedNodes.get(smallestIndex).next);
			}
		}
		return masterList;
	}

	//internal class that represents each node of the linked list
	public class Node<T extends Comparable<T>> {
		public T data;
		public Node<T> next;

		public Node(T data) {
			this.data = data;
			this.next = null;
		}
	}


	public static void main(String[] args) {
		MergeKSortedLL<Integer> mk = new MergeKSortedLL<Integer>();
		ArrayList<MergeKSortedLL<Integer>.Node<Integer>> nodes = mk.generateArrayOfSortedLinkedLists();
		MergeKSortedLL.Node masterList = mk.mergeKSortedLinkedLists(nodes);
		while(masterList != null) {
			System.out.print(masterList.data + "->");
			masterList = masterList.next;
		}

	}

}
