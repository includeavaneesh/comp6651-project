package HeuristicBasedLongestSimplePath;

import java.util.LinkedList;

public class LongestSimplePath {
    public int maxEdgeLSP(LinkedList<Integer>[] adjacencyList, LinkedList<Integer> largestComponent) {
        Main longestSimplePath = new Main();
        int numVerticesInLCC = largestComponent.size();
        int iterations = (int) Math.sqrt(numVerticesInLCC);
        int longestPathLength = -1;
        for (int i = 1; i <= iterations; i++) {
            int source = longestSimplePath.vertexWithMaximumBetweenness(adjacencyList, largestComponent);
            int destination = longestSimplePath.getRandomDestinationVertex(largestComponent, source);

            int[] deepest = longestSimplePath.getMaximumLengthEdgeSet(adjacencyList, source, destination);
            int deepestNodeLength = deepest[0];

            longestPathLength = Math.max(longestPathLength, deepestNodeLength);
        }
        return longestPathLength;
    }
}
