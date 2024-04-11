package DFSBasedLongestSimplePath;
import java.util.LinkedList;

import GraphGenerator.Graph;

public class TestLCC {
    public static void main(String[] args) {
        String filePath = "./EDGES/graph_300Nodes.EDGES";
        Graph graph = new Graph();
        // Create adjacency list to store the graph
        LinkedList<Integer>[] adjacencyList = graph.readGraph(filePath, true);

        LongestSimplePath lcc = new LongestSimplePath();
        lcc.DFSLongestSimplePath(adjacencyList);
        // DFS
       
    }
}
