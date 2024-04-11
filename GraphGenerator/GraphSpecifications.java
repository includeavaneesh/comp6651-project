package GraphGenerator;

public class GraphSpecifications{
	int numOfNodes;
	double minNodeCountGraph;
	double maxNodeCountGraph;
	double maxAllowedEdgeLength;
	int numNodesLCC;
	String filePath;
	
	public  GraphSpecifications(int numOfNodes, 
			double minNodeCountGraph,
			double maxNodeCountGraph,
			String filePath) {
		this.numOfNodes = numOfNodes;
		this.minNodeCountGraph = minNodeCountGraph;
		this.maxNodeCountGraph = maxNodeCountGraph;
		this.maxAllowedEdgeLength = 0;
		this.numNodesLCC = 0;
		this.filePath = filePath;
	}
	
	@Override
	public String toString() {
		return "-------------------------------------------\n"
				+ "Graph generated with specs n = " + numOfNodes + " and " +
				minNodeCountGraph + ".n <= |VLCC| <= " + 
				maxNodeCountGraph + ".n ------->\n" + 
				"number of nodes in largest connected component = " + numNodesLCC +
				"\nr = " + maxAllowedEdgeLength;
	}
}