import java.util.Arrays;
import java.util.List;


public class Dijkstra {

	private Graph graph;
	private Heap<Pair<Integer,Integer>> heap;
	
	public Dijkstra(Graph g) {
		this.graph = g;
		this.heap = new Heap<Pair<Integer,Integer>>(graph.getWeightedGraph().size()*2);
	}
	
	public int[] findShortestPaths(int sourceVertex) {
		int numVertex = graph.getWeightedGraph().size();
		int[] shortestDistances = new int[numVertex];
		//initialize distances:
		shortestDistances[sourceVertex-1] = 0;
		for (int i=1; i<=numVertex; i++) {
			if (i!=sourceVertex) {
				shortestDistances[i-1] = Integer.MAX_VALUE;
			}
			heap.insert(new Pair<Integer, Integer>(i, shortestDistances[i-1]));
		}
		while (!heap.isEmpty()) {
			int tailVertex = heap.extractMin().getLeft();
			List<Pair<Integer,Integer>> neighboursWeights = graph.getNeighboursWeights(tailVertex);
			for (Pair<Integer, Integer> pair : neighboursWeights) {
				int headVertex = pair.getLeft();
				int edgeWeight = pair.getRight();
				int newDist = shortestDistances[tailVertex-1];
				if (newDist!=Integer.MAX_VALUE) {
					newDist += edgeWeight;
				}
				if (newDist < shortestDistances[headVertex-1]) {
					Pair<Integer,Integer> oldValue = new Pair<Integer, Integer>(headVertex, shortestDistances[headVertex-1]);
					shortestDistances[headVertex-1] = newDist;
					Pair<Integer,Integer> newValue = new Pair<Integer, Integer>(headVertex, shortestDistances[headVertex-1]);
					heap.change(oldValue, newValue);
				}
			}
		}
		return shortestDistances;
	}
	
	public int findShortestPath(int sourceVertex, int goalVertex) {
		int numVertex = graph.getWeightedGraph().size();
		int[] shortestDistances = new int[numVertex];
		shortestDistances[sourceVertex-1] = 0;
		for (int i=1; i<=numVertex; i++) {
			if (i!=sourceVertex) {
				shortestDistances[i-1] = Integer.MAX_VALUE;
			}
			heap.insert(new Pair<Integer, Integer>(i, shortestDistances[i-1]));
		}
		while (!heap.isEmpty()) {
			int tailVertex = heap.extractMin().getLeft();
			if (tailVertex == goalVertex) {
				//found goal vertex
				return shortestDistances[goalVertex-1];
			}
			List<Pair<Integer,Integer>> neighboursWeights = graph.getNeighboursWeights(tailVertex);
			for (Pair<Integer, Integer> pair : neighboursWeights) {
				int headVertex = pair.getLeft();
				int edgeWeight = pair.getRight();
				int newDist = shortestDistances[tailVertex-1];
				if (newDist!=Integer.MAX_VALUE) {
					newDist += edgeWeight;
				}
				if (newDist < shortestDistances[headVertex-1]) {
					Pair<Integer,Integer> oldValue = new Pair<Integer, Integer>(headVertex, shortestDistances[headVertex-1]);
					shortestDistances[headVertex-1] = newDist;
					Pair<Integer,Integer> newValue = new Pair<Integer, Integer>(headVertex, shortestDistances[headVertex-1]);
					heap.change(oldValue, newValue);
				}
			}
		}
		return shortestDistances[goalVertex-1]; //couldn't find goal, so distance will be Integer.MAX_VALUE (no path from start to goal)
	}
	
	public static void main(String[] args) {
		Graph g = new Graph();
		g.addWeightedEdge(1, 2, 1);
		g.addWeightedEdge(1, 3, 4);
		g.addWeightedEdge(2, 3, 2);
		g.addWeightedEdge(2, 4, 6);
		g.addWeightedEdge(3, 4, 3);
		Dijkstra d  = new Dijkstra(g);
		System.out.println(Arrays.toString(d.findShortestPaths(1)));
		System.out.println(d.findShortestPath(1, 4));
	}
}
