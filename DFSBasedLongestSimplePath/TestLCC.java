package DFSBasedLongestSimplePath;

import java.util.LinkedList;

import GraphGenerator.Graph;
import GraphGenerator.LargestConnectedComponent;

public class TestLCC {
    public static void main(String[] args) {
        String filePath = "./EDGES/inf-euroroad.edges";
        Graph graph = new Graph();
        // Create adjacency list to store the graph
        LinkedList<Integer>[] adjacencyList = graph.readGraph(filePath, false);

        LargestConnectedComponent lcc = new LargestConnectedComponent();
        LinkedList<Integer> largestComponent = lcc.largestConnectedComponent(adjacencyList);

        LongestSimplePath dfsSimplePath = new LongestSimplePath();

        dfsSimplePath.DFSLongestSimplePath(adjacencyList, largestComponent);

    }
}
