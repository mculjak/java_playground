import java.util.Arrays;
import java.util.List;


public class DFS {
	
	private Graph graph;
	private Stack stack;
	
	public DFS(Graph g) {
		this.graph = g;
		int capacity = 2*g.getGraph().size();
		this.stack = new Stack(capacity);
	}
	
	public boolean search(int startVertex, int goalVertex) {
		boolean[] discovered = new boolean[graph.getGraph().size()];
		Arrays.fill(discovered,false);
		stack.put(startVertex);
		discovered[startVertex-1] = true; //numbering of edges begins from 1
		while (!stack.isEmpty()) {
			int vertex = stack.pop();
			System.out.println("Visiting vertex: "+vertex);
			if (vertex == goalVertex) {
				return true;
			}
			List<Integer> neighbours = graph.getNeighbours(vertex);
			for (Integer n : neighbours) {
				if (!discovered[n-1]) {
					stack.put(n);
					discovered[n-1] = true;
				}
			}
		}
		return false; //if stack empty, couldn't find goalVertex	
	}
	
	public boolean searchRecursively(int startVertex, int goalVertex, boolean[] discovered) {
		discovered[startVertex-1] = true;
		System.out.println("Visiting vertex: "+startVertex);
		if (startVertex == goalVertex) {
			return true;
		}
		List<Integer> neighbours = graph.getNeighbours(startVertex);
		for (Integer n : neighbours) {
			if (!discovered[n-1]) {
				return searchRecursively(n, goalVertex, discovered);
			}
		}
		return false;
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
		
		DFS dfs = new DFS(g);
		System.out.println(dfs.search(6,1));
		
		boolean[] discovered = new boolean[g.getGraph().size()];
		Arrays.fill(discovered, false);
		System.out.println(dfs.searchRecursively(1, 6, discovered));
		//search() i searchRecursively() obilaze različite vrhove jer koriste različite poretke
		//susjednih vrhova (iterativan kreće od najvećeg broja, a rekurzivan od najmanjeg)
	}
}
