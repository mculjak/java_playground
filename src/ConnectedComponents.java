
public class ConnectedComponents {
	
	private Graph graph;
	private BFS bfs;

	public ConnectedComponents(Graph g) {
		graph = g;
		bfs = new BFS(g);
	}
	
	public void findConnectedComponents() {
		boolean[] discovered = new boolean[graph.getGraph().size()];
		int j = 1;
		for (int i = 1; i <= discovered.length-1; i++) {
			if (!discovered[i-1]) {
				System.out.println("Component: "+j);
				j++;
				bfs.searchAll(i, discovered);
			}
		}
		
	}
	public static void main(String[] args) {
		Graph g = new Graph();
		
		//undirected graph:
		g.addEdge(1,3);
		g.addEdge(1,5);
		
		g.addEdge(2,4);
		
		g.addEdge(3,1);
		g.addEdge(3,5);
		
		g.addEdge(4,2);
		
		g.addEdge(5,1);
		g.addEdge(5,3);
		g.addEdge(5,7);
		g.addEdge(5,9);
		
		g.addEdge(6,8);
		g.addEdge(6,10);
		
		
		g.addEdge(7,5);
		
		g.addEdge(8,6);
		g.addEdge(8,10);
		
		g.addEdge(9, 5);
		
		g.addEdge(10,6);
		g.addEdge(10,8);
		
		ConnectedComponents CC = new ConnectedComponents(g);
		CC.findConnectedComponents();
	}

}
