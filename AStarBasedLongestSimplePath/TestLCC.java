package AStarBasedLongestSimplePath;

import java.util.LinkedList;

import GraphGenerator.Graph;
import GraphGenerator.LargestConnectedComponent;
import GraphGenerator.Vertex;

public class TestLCC {
    public static void main(String[] args) {
        String filePath = "./EDGES/graph_300Nodes.EDGES";
        Graph graph = new Graph();

        LinkedList<Integer>[] adjacencyListForLargestComponent = graph.readGraph(filePath, true);
        LinkedList<Vertex>[] adjacencyList = graph.readGeometricGraphWithCoordinates(filePath);

        LargestConnectedComponent lcc = new LargestConnectedComponent();
        LinkedList<Integer> largestComponent = lcc.largestConnectedComponent(adjacencyListForLargestComponent);

        LongestSimplePath longestSimplePath = new LongestSimplePath();
        int longestSimplePathLength = longestSimplePath.astartLongestSimplePath(adjacencyList, largestComponent);

        System.out.println("longest path by AStar: " + longestSimplePathLength);
    }
}
