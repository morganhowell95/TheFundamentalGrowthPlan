import java.util.ArrayList;

//Tree based implementation of disjoint sets.... Useful for a variety of operations, including finding the connected components of a graph.
//The analysis is quite complex, but this implementation (which is an improvement from the LL implementation) achieves next to linear O(n)
// or more accurately super-linear time complexity by making use of union by rank and path compression (during finhd operations).

public class DisjointSet<T>{
	private ArrayList<Node<T>> DSForest;

	public DisjointSet() {
		this.DSForest = new ArrayList<Node<T>>();
	}

	//create a completey new set separate from the others, where the only node references itself as the parent
	public Node<T> makeSet(T data) {
		Node<T> newNode = this.new Node<T>(data);
		newNode.parent = newNode;
		DSForest.add(newNode);
		return newNode;
	}

	//union two sets, keeping track of rank and adjusting appropriately
	public void union(Node<T> n1, Node<T> n2){
		link(find(n1), find(n2));
	}

	//links two sets and adjusts the rank accordingly
	private void link(Node<T> r1, Node<T> r2){
		if(r1.rank > r2.rank) {
			r2.parent = r1;
			r1.rank++;
		} else {
			r1.parent = r2;
			r2.rank++;
		}
	}

	//Given a reference to a node, find its representative node (the ultimate parent) and enforce path-compression for effeciency
	public Node<T> find(Node<T> node) {
		if(node != node.parent) {
			node.parent = find(node.parent);
		}
		return node.parent;
	}

	public class Node<T>{
		public Node<T> parent;
		public T data;
		public int rank;

		public Node(T data) {
			this.data = data;
			this.rank = 0;
		}

	}

	public static void main(String[] args) throws Exception {
		DisjointSet<Integer> set = new DisjointSet<Integer>();
		DisjointSet<Integer>.Node<Integer> node5 = set.makeSet(5);
		DisjointSet<Integer>.Node<Integer> node6 = set.makeSet(6);
		DisjointSet<Integer>.Node<Integer> node10 = set.makeSet(10);
		DisjointSet<Integer>.Node<Integer> node7 = set.makeSet(7);

		if(set.find(node5).data != 5) {
			throw new Exception("Make set failed");
		}

		set.union(node5, node6);
		set.union(node6, node7);

		if(set.find(node7).data != 6) {
			throw new Exception("union failed");
		}

		System.out.println("Hooray - your disjoint set forest seems to be working, but write better testers to make sure");
	}
}
