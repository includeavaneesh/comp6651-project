package GraphGenerator;

import java.util.*;

public class Main{	
	public static void main(String str[]) {		
		GraphGenerator g = new GraphGenerator();
		
		List<GraphSpecifications> graphs = new ArrayList<>();		
		graphs.add(new GraphSpecifications(300, 0.9, 0.95, "./EDGES/graph_300Nodes.EDGES"));
		graphs.add(new GraphSpecifications(400, 0.8, 0.9, "./EDGES/graph_400Nodes.EDGES"));
		graphs.add(new GraphSpecifications(500, 0.7, 0.8, "./EDGES/graph_500Nodes.EDGES"));
		
		for (GraphSpecifications graph : graphs) {
			g.generateGraph(graph);
		}		
	}    
}

/*
 * Graph generated and stored in file: ./EDGES/graph_300Nodes.EDGES
-------------------------------------------
Graph generated with specs n = 300 and 0.9.n <= |VLCC| <= 0.95.n ------->
number of nodes in largest connected component = 274
r = 0.0825
Graph generated and stored in file: ./EDGES/graph_400Nodes.EDGES
-------------------------------------------
Graph generated with specs n = 400 and 0.8.n <= |VLCC| <= 0.9.n ------->
number of nodes in largest connected component = 356
r = 0.06875
Graph generated and stored in file: ./EDGES/graph_500Nodes.EDGES
-------------------------------------------
Graph generated with specs n = 500 and 0.7.n <= |VLCC| <= 0.8.n ------->
number of nodes in largest connected component = 397
r = 0.05651733398437499
 */