package DFSBasedLongestSimplePath;

import java.util.Iterator;
import java.util.LinkedList;

public class DFSLongestSimplePath {
    private int maxDepth = 0;
    private int vertexAtMaxDepth = 0;

    /**
     * This returns the deepest vertex found by DFS starting at vertex v of a
     * connected component
     * 
     * @param v
     * @param adjacencyList
     * @return
     */
    public int[] DFS(int v, LinkedList<Integer>[] adjacencyList) {
        int numberOfVertices = adjacencyList.length;
        // All nodes are currently not visited
        boolean[] visited = new boolean[numberOfVertices];

        DFSVisit(v, visited, adjacencyList, 0);
        return new int[] { vertexAtMaxDepth, maxDepth };

    }

    /**
     * This is a utility function for DFS to help recurse to deeper nodes
     * 
     * @param v
     * @param visited
     * @param adjacencyList
     * @param depth
     */
    private void DFSVisit(int v, boolean[] visited, LinkedList<Integer>[] adjacencyList, int depth) {
        // Mark the current node as visited
        visited[v] = true;

        if (depth > maxDepth) {
            maxDepth = depth;
            vertexAtMaxDepth = v;
        }

        // Iterate through all its children nodes
        Iterator<Integer> iterator = adjacencyList[v].iterator();
        while (iterator.hasNext()) {
            // Get the child node
            int n = iterator.next();

            // If the child is not visited
            if (!visited[n]) {
                // Visit further in child
                DFSVisit(n, visited, adjacencyList, depth + 1);
            }
        }
    }

}