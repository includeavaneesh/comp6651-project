package DijkstraBasedLongestSimlePath;

import java.util.*;

public class DijkstraLongestSimplePath {
	private class Vertex implements Comparable<Vertex> {
        int id;
        int d;

        public Vertex(int id, int d) {
            this.id = id;
            this.d = d;
        }

        public int compareTo(Vertex other) {
            return Integer.compare(other.d, this.d); // Modified to sort in descending order
        }
    }

    public int[]  dijkstra(LinkedList<Integer>[] adjacencyList, int source) {
        int numVertices = adjacencyList.length;
        int[] dist = new int[numVertices];
        Arrays.fill(dist, Integer.MIN_VALUE); // Initialize distances to negative infinity
        dist[source] = 0;

        boolean[] visited = new boolean[numVertices]; // Track visited vertices

        PriorityQueue<Vertex> maxHeap = new PriorityQueue<>();
        maxHeap.offer(new Vertex(source, 0)); // Source vertex with distance 0

        int[] longestPathInfo = new int[]{source, 0};  // Array to store the node with the longest path and its length

        while (!maxHeap.isEmpty()) {
            Vertex u = maxHeap.poll();
            int uId = u.id;
            int uDist = u.d;

            if (visited[uId]) continue; // Skip visited vertices
            visited[uId] = true;

            for (int v : adjacencyList[uId]) {
                if (visited[v]) continue; // Skip visited vertices

                // Check if adding edge (u, v) will create a cycle
                if (dist[uId] + 1 > dist[v]) { // Use > for Dijkstra-MAX to find longest paths
                    dist[v] = dist[uId] + 1;
                    maxHeap.offer(new Vertex(v, dist[v]));

                    // Update longest path information if applicable
                    if (dist[v] > longestPathInfo[1]) {
                        longestPathInfo[0] = v; // Update node with the longest path
                        longestPathInfo[1] = dist[v]; // Update length of the longest path
                    }
                }
            }
        }

        return longestPathInfo;
    }
}
