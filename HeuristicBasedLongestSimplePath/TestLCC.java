package HeuristicBasedLongestSimplePath;

import java.util.LinkedList;

import GraphGenerator.Graph;
import GraphGenerator.LargestConnectedComponent;

public class TestLCC {
    public static void main(String[] args) {
        String filePath = "./EDGES/test2.EDGES";
        Graph graph = new Graph();
        LinkedList<Integer>[] adjacencyList = graph.readGraph(filePath, true);

        LargestConnectedComponent lcc = new LargestConnectedComponent();
        LinkedList<Integer> largestComponent = lcc.largestConnectedComponent(adjacencyList);

        LongestSimplePath longestSimplePath = new LongestSimplePath();
        int longestSimplePathLength = longestSimplePath.maxEdgeLSP(adjacencyList, largestComponent);

        System.out.println("Longest path by Betweenness centrality heuristic: " + longestSimplePathLength);
    }
}
