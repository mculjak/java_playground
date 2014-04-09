
public class DenseGraph {
	
	private int[][] graphMatrix;
	private boolean isDirected;
	
	
	public DenseGraph(int numNodes, boolean isDirected) {
		this.isDirected = isDirected;
		graphMatrix = new int[numNodes][numNodes];
		for (int i = 0; i < graphMatrix.length; i++) {
			for (int j = 0; j < graphMatrix.length; j++) {
				graphMatrix[i][j] = 0;
			}
		}
	}
	
	public void addEdge(int i, int j) {
		graphMatrix[i][j]++;
		if (isDirected) {
			graphMatrix[j][i]--;
		} else {
			graphMatrix[j][i]++;
		}
	}
	
	public void addWeightedEdge(int i, int j, int weight) {
		graphMatrix[i][j]+=weight;
		if (isDirected) {
			graphMatrix[j][i]-=weight;
		} else {
			graphMatrix[j][i]+=weight;
		}
	}
	
	public int getEdge(int i, int j) {
		return graphMatrix[i][j];
	}
}
