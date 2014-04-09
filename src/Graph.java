import java.util.ArrayList;
import java.util.List;


public class Graph {
	
	private List<List<Integer>> graph; //set or hashmap perhaps?
	private List<List<Pair<Integer, Integer>>> weightedGraph;
	
	public Graph() {
		graph = new ArrayList<List<Integer>>();
		weightedGraph = new ArrayList<List<Pair<Integer, Integer>>>();
	}
	
	public void addEdge(int v1, int v2) {
		List<Integer> neighbours;
		if (v1<graph.size()) {
			neighbours = graph.get(v1);
		} else {
			neighbours = new ArrayList<Integer>();
			for (int i = graph.size(); i <= v1; i++) {
				graph.add(neighbours);
			}
		}
		neighbours.add(v2);
	}
	
	public void addWeightedEdge(int v1, int v2, int weight) {
		List<Pair<Integer,Integer>> neighbours;
		Pair<Integer, Integer> entry = new Pair<Integer, Integer>(v2,weight);
		if (v1<weightedGraph.size()) {
			neighbours = weightedGraph.get(v1);
		} else {
			neighbours = new ArrayList<Pair<Integer,Integer>>();
			for (int i = weightedGraph.size(); i <= v1; i++) {
				weightedGraph.add(neighbours);
			}
		}
		neighbours.add(entry);
	}
	
	public List<Integer> getNeighbours(int vertex) {
		if (vertex>=graph.size())  {
			return new ArrayList<Integer>();
		}
		List<Integer> neighbours = graph.get(vertex);
		return neighbours;
	}
	
	public List<Pair<Integer,Integer>> getNeighboursWeights(int vertex) {
		if (vertex>=weightedGraph.size())  {
			return new ArrayList<Pair<Integer,Integer>>();
		}
		List<Pair<Integer,Integer>> neighbours = weightedGraph.get(vertex);
		return neighbours;
	}
	
	public List<List<Integer>> getGraph() {
		return this.graph;
	}
	
	public List<List<Pair<Integer,Integer>>> getWeightedGraph() {
		return this.weightedGraph;
	}
}
