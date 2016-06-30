/* A collection of common graph searching algorithms, including DFS, BFS, and utility functions that these common searches provide */
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;

public class GraphSearch {
	private Graph graph;
	private int time;

	public GraphSearch(){
		this.graph = new Graph();
	}

	private void generateNewGraphOfType(int selection) {
		switch(selection) {
			//makes good example for DFS demo (CLRS pg. 605)
			case 1:
				graph.clearGraph();
				graph.insert("u");
				graph.insert("v");
				graph.insert("w");
				graph.insert("x");
				graph.insert("y");
				graph.insert("z");
				graph.makeEdge("u","v");
				graph.makeEdge("u","x");
				graph.makeEdge("v","y");
				graph.makeEdge("w","y");
				graph.makeEdge("w","z");
				graph.makeEdge("x","y");
				graph.makeEdge("y","x");
				graph.makeEdge("z","z");
				break;
			//makes good example for BFS demo
			case 2:
				graph.clearGraph();
				//remember BFS can run on undirected graphs just fine (CLRS pg. 596)
				graph.insert("v");
				graph.insert("r");
				graph.insert("s");
				graph.insert("w");
				graph.insert("t");
				graph.insert("x");
				graph.insert("y");
				graph.insert("u");
				graph.makeUEdge("v","r");
				graph.makeUEdge("r","s");
				graph.makeUEdge("s","w");
				graph.makeUEdge("t","w");
				graph.makeUEdge("t","x");
				graph.makeUEdge("w","x");
				graph.makeUEdge("t","u");
				graph.makeUEdge("x","u");
				graph.makeUEdge("y","u");
				graph.makeUEdge("x","y");
				break;
			//makes good example for Dijkstra (remember no negatively weighted edges and must be directed graph; CLRS pg. 659) 
			case 3:
				graph.clearGraph();
				graph.insert("s");
				graph.insert("t");
				graph.insert("y");
				graph.insert("x");
				graph.insert("z");
				graph.makeWDEdge("s","t", 10);
				graph.makeWDEdge("s","y", 5);
				graph.makeWDEdge("t","y", 2);
				graph.makeWDEdge("t","x", 1);
				graph.makeWDEdge("y","t", 3);
				graph.makeWDEdge("y","x", 9);
				graph.makeWDEdge("y","z", 2);
				graph.makeWDEdge("x","z", 4);
				graph.makeWDEdge("z","s", 7);
				graph.makeWDEdge("z","x", 6);
				break;
		}

	}

	private void outputDFSOfCurrentGraph(String source) {
		if(!graph.isEmpty()) {
			time = 0;
			HashMap<String, Vertex> g = graph.adjList;
			//We may want to specify a node or "source" in which to kick off DFS on
			System.out.println("top of dfs tree");
			recursiveDFS(g.get(source));
			System.out.println("\n------------------");

			for(String s : g.keySet()){
				Vertex src = g.get(s);
				if(src.color == 'w') {
					System.out.println("top of dfs tree");
					recursiveDFS(src);
					System.out.println("\n-------------------");
				}
			}
			graph.clearGraph();
		}
	}

	private void recursiveDFS(Vertex source) {
		source.color = 'g';
		source.dTime = ++time;
		System.out.print(source.label + "->");
		for(Edge e : source.adjVertices) {
			if(e.v2.color == 'w') {
				e.v2.parent = source;
				recursiveDFS(e.v2);
			}
		}
		source.fTime = ++time;
		source.color = 'b';
	}

	public void BFS(String source) {
		LLImplOfQueue<Vertex> q = new LLImplOfQueue<Vertex>();
		Vertex src = graph.adjList.get(source);
		q.enqueue(src);
		src.weight = 0;
		//access nodes in a breadth-first manner while the queue is not empty
		while(!q.isEmpty()) {
			Vertex v = q.dequeue();
			//g indicates discovered status
			v.color = 'g';
			System.out.println("node: " + v.label + "  distance from src:" + v.weight);
			for(Edge adj : v.adjVertices) {
				Vertex next = adj.v2;
				if(next.color == 'w') {
					next.color = 'g';
					next.parent = v;
					next.weight = next.parent.weight + 1;
					q.enqueue(next);
				}
			}
			//b indicates that we are done with this node
			v.color = 'b';
		}

	}

	//to be used only after calling BFS which preps the graph to have shortest paths generated
	public void printShortestPath(String currentNode, String source) {

		if(currentNode == null || source == null) {
			System.out.println("path does not exist!");
		}

		Vertex current = graph.adjList.get(currentNode);
		Vertex src = graph.adjList.get(source);
		if(src == current) {
			System.out.print(src + "->");
		} else if(current != null) {
			printShortestPath(current.parent.label, source);
			System.out.print(current.label + "->");
		}
	}

	//Dijkstra's famous single-source shortest-paths algorithm O(Elg(V))
	//maintains min-heap of vertices and relaxes surrounding nodes until heap is empty
	public void dijkstraSP(String source) {
		MinHeap<Vertex> heap = new MinHeap<Vertex>();
		//must prep src node so it's not accidently relaxed or rediscovered
		Vertex src = graph.adjList.get(source);
		src.color = 'g';
		src.weight = 0;
		heap.insert(src);
		System.out.print("\nHEAP ORDER:   ");
		while(!heap.isEmpty()) {
			Vertex v = heap.extractMin();
			System.out.print(v.label + "->");
			for(Edge e : v.adjVertices) {
				relax(e);
				if(e.v2.color == 'w') {
					heap.insert(e.v2);
					//once we insert a vertex into the heap, we don't want to insert it again
					e.v2.color = 'g';
				}
			}
			v.color = 'b';
		}
	}

	public void relax(Edge e) {
		if(e.v2.weight > e.v1.weight + e.weight) {
			e.v2.weight = e.v1.weight + e.weight;
			e.v2.parent = e.v1;
		}
	}


	public class Graph {
		public HashMap<String, Vertex> adjList;

		public Graph() {
			adjList = new HashMap<String, Vertex>();
		}

		public void insert(String label) {
			adjList.put(label, new Vertex(label));
		}

		public void makeEdge(String v1, String v2, int weight) {
			if(adjList.containsKey(v1) && adjList.containsKey(v2)) {
				Edge newEdge = new Edge(adjList.get(v1), adjList.get(v2), weight);
				adjList.get(v1).adjVertices.add(newEdge);
			}
		}

		public void makeEdge(String v1, String v2) {
			makeEdge(v1, v2 ,1);
		}

		public void makeUEdge(String v1, String v2) {
			makeEdge(v1, v2, 1);
			makeEdge(v2, v1, 1);
		}

		public void makeWDEdge(String v1, String v2, int weight) {
			makeEdge(v1, v2, weight);
		}

		public void clearGraph() {
			adjList.clear();
		}

		public boolean isEmpty() {
			return adjList.isEmpty();
		}
	}


	public class Vertex implements Comparable<Vertex> {
		//indicator we use for visited/discovery
		public char color;
		public int dTime;
		public int fTime;
		//integer for relaxing and accumulation searching
		public int weight;
		public String label;
		//collection of all adjacent nodes
		public HashSet<Edge> adjVertices;
		public Vertex parent;
		//label must be distinct

		public Vertex(String label) {
			this.label = label;
			color = 'w';
			dTime = 0;
			fTime = 0;
			weight = Integer.MAX_VALUE;
			this.adjVertices = new HashSet<Edge>();
			parent = null;
		}

		public String toString() {
			return label;
		}
		
		@Override
		public int compareTo(Vertex other) {
			if(weight > other.weight) {
				return 1;
			} else if(weight == other.weight) {
				return 0;
			} else {
				return -1;
			}
		}
	}

	public class Edge implements Comparable<Edge> {
		public Vertex v1;
		public Vertex v2;
		public int weight;
		public String label = "(%1s,%2s)";

		public Edge(Vertex v1, Vertex v2, int weight) {
			this.v1 = v1;
			this.v2 = v2;
			this.weight = weight;
		}

		public Edge(Vertex v1, Vertex v2) {
			this(v1, v2, 1);
		}

		public String toString() {
			return String.format(label, v1.label, v2.label);
		}

		public int compareTo(Edge e) {
			if(weight > e.weight) {
				return 1;
			} else if(weight == e.weight) {
				return 0;
			} else {
				return -1;
			}
		}
	}


	public static void main(String[] args) {
		GraphSearch g = new GraphSearch();
		g.generateNewGraphOfType(1);
		g.outputDFSOfCurrentGraph("u");
		System.out.println("\n\n");
		g.generateNewGraphOfType(2);
		g.BFS("s");
		g.printShortestPath("y","s");
		g.generateNewGraphOfType(3);
		g.dijkstraSP("s");
		g.printShortestPath("x","s");
		System.out.println("\n\n-----------------------------\n\n");
	}
}
