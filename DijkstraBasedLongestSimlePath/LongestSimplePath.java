package DijkstraBasedLongestSimlePath;

import java.util.*;

public class LongestSimplePath {
	public int dijkstraLongestSimplePath(LinkedList<Integer>[] adjacencyList, LinkedList<Integer> largestComponent) {
        DijkstraLongestSimplePath dijkstraLongestSimplePath = new DijkstraLongestSimplePath();

        int maxPathLength = 0;
        int numVerticesInLCC = largestComponent.size();
        int iterations = (int) Math.sqrt(numVerticesInLCC);

        Random random = new Random();

        for (int i = 1; i <= iterations; i++) {
            int randomIndex = random.nextInt(numVerticesInLCC);
            int vertexU = largestComponent.get(randomIndex);

            int vertexV = dijkstraLongestSimplePath.dijkstra(adjacencyList, vertexU)[0];
            int depthV = dijkstraLongestSimplePath.dijkstra(adjacencyList, vertexU)[1];
            int depthW = dijkstraLongestSimplePath.dijkstra(adjacencyList, vertexV)[1];

            maxPathLength = Math.max(maxPathLength, Math.max(depthV,depthW));
        }

        //System.out.println("longest path: " + maxPathLength);
        return  maxPathLength;
    }





    /*public static void main(String[] args) {
        String filePath = "./EDGES/test.EDGES";
        // Create adjacency list to store the graph
        Graph graph = new Graph();
        LinkedList<Integer>[] adjacencyList = graph.readGraph(filePath);
        LargestConnectedComponent lc = new LargestConnectedComponent();
        LinkedList<Integer> largestComponent = lc.largestConnectedComponent(adjacencyList);

        int numVerticesInLCC = largestComponent.size();
        int iterations = (int) Math.sqrt(numVerticesInLCC);
        Random random = new Random();
        int maxPathLength = 0;
        for (int i = 1; i <= iterations; i++) {
            int randomIndex = random.nextInt(numVerticesInLCC);
            int vertexU = largestComponent.get(randomIndex);

            int vertexV = dijkstra(adjacencyList, vertexU)[0];
            int depthV = dijkstra(adjacencyList, vertexU)[1];
            int depthW = dijkstra(adjacencyList, vertexV)[1];

            maxPathLength = Math.max(maxPathLength, Math.max(depthV,depthW));
        }

        System.out.println("longest path: " + maxPathLength);
    }*/
}
