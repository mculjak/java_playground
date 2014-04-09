import java.util.Arrays;
import java.util.List;


public class BFS {
	
	private Graph graph;
	private Queue queue;
	
	public BFS(Graph g) {
		this.graph = g;
		int capacity = 2*g.getGraph().size(); //capacity is double the number of vertices in graph
		this.queue = new Queue(capacity);
	}
	
	public boolean search(int startVertex, int goalVertex) {
		boolean[] discovered = new boolean[graph.getGraph().size()];
		Arrays.fill(discovered,false);
		queue.put(startVertex);
		discovered[startVertex-1] = true; //numbering of edges begins from 1
		while (!queue.isEmpty()) {
			int vertex = queue.pop();
			System.out.println("Visiting vertex: "+vertex);
			if (vertex == goalVertex) {
				return true;
			}
			List<Integer> neighbours = graph.getNeighbours(vertex);
			for (Integer n : neighbours) {
				if (!discovered[n-1]) {
					queue.put(n);
					discovered[n-1] = true;
				}
			}
		}
		return false; //if queue empty, couldn't find goalVertex	
	}
	
	public void searchAll(int startVertex, boolean[] discovered) {
		queue.put(startVertex);
		discovered[startVertex-1] = true; //numbering of edges begins from 1
		while (!queue.isEmpty()) {
			int vertex = queue.pop();
			System.out.println("Visiting vertex: "+vertex);
			List<Integer> neighbours = graph.getNeighbours(vertex);
			for (Integer n : neighbours) {
				if (!discovered[n-1]) {
					queue.put(n);
					discovered[n-1] = true;
				}
			}
		}
	}

	public static void main(String[] args) {
		Graph g = new Graph();
		g.addEdge(1,2);
		g.addEdge(1,3);
		g.addEdge(2,4);
		g.addEdge(3,4);
		g.addEdge(3,5);
		g.addEdge(4,5);
		g.addEdge(4,6);
		g.addEdge(5,6);
		
		BFS bfs = new BFS(g);
		System.out.println(bfs.search(1,6));		
	}
}
