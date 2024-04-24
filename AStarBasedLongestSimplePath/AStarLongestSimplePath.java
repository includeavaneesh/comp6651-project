package AStarBasedLongestSimplePath;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Set;

import GraphGenerator.Vertex;

class GeometricVertex {
    int id;
    double x;
    double y;
    double d;
    double h;
    double f;

    public GeometricVertex(int id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.d = Double.NEGATIVE_INFINITY;
        this.h = 0;
        this.f = Double.NEGATIVE_INFINITY;
    }
}

public class AStarLongestSimplePath {

    public static LinkedList<GeometricVertex>[] calculateEuclideanHeuristic(LinkedList<Vertex>[] adjacencyList,
            GeometricVertex source,
            GeometricVertex destination) {
        LinkedList<GeometricVertex>[] finalAdjacencyList = new LinkedList[adjacencyList.length];
        source.d = 0;
        source.h = Math.sqrt(
                Math.pow(destination.x - source.x, 2) + Math.pow(destination.y - source.y, 2));
        for (int i = 0; i < adjacencyList.length; i++) {
            if (adjacencyList[i] == null) {
                finalAdjacencyList[i] = null;
                continue;
            }

            finalAdjacencyList[i] = new LinkedList<>();
            for (Vertex vertex : adjacencyList[i]) {
                if (vertex != null) {
                    GeometricVertex vertexWithHeuristic = new GeometricVertex(vertex.getId(), vertex.getX(),
                            vertex.getY());
                    if (vertex.getId() == source.id) {
                        vertexWithHeuristic.d = 0;
                    }
                    vertexWithHeuristic.h = Math.sqrt(
                            Math.pow(destination.x - vertex.getX(), 2) + Math.pow(destination.y - vertex.getY(), 2));
                    finalAdjacencyList[i].add(vertexWithHeuristic);
                }
            }
        }
        return finalAdjacencyList;
    }

    public int astar(LinkedList<Vertex>[] adjacencyList, Vertex src, Vertex dest) {
        GeometricVertex source = new GeometricVertex(src.getId(), src.getX(), src.getY());
        GeometricVertex destination = new GeometricVertex(dest.getId(), dest.getX(), dest.getY());
        LinkedList<GeometricVertex>[] adjacencyLinkedListsWithHeuristic = calculateEuclideanHeuristic(adjacencyList,
                source,
                destination);

        int numVertices = adjacencyList.length;
        int[] dist = new int[numVertices];
        Arrays.fill(dist, Integer.MIN_VALUE);
        dist[source.id] = 0;
        source.f = source.h + source.d;

        boolean[] visited = new boolean[numVertices];
        int longestPath = 0;

        Set<GeometricVertex> S = new HashSet<>();
        PriorityQueue<GeometricVertex> maxHeap = new PriorityQueue<>((v1, v2) -> Double.compare(v2.f, v1.f));

        maxHeap.add(source);

        while (!maxHeap.isEmpty()) {
            GeometricVertex currentVertex = maxHeap.poll();
            S.add(currentVertex);
            int uId = currentVertex.id;

            if (currentVertex == destination) {
                break;
            }

            if (visited[uId])
                continue;

            visited[uId] = true;

            for (int i = 0; i < adjacencyLinkedListsWithHeuristic[currentVertex.id].size(); i++) {
                GeometricVertex neighbor = adjacencyLinkedListsWithHeuristic[currentVertex.id].get(i);

                if (dist[currentVertex.id] + 1 > neighbor.h) {
                    dist[neighbor.id] = dist[currentVertex.id] + 1;

                    if (dist[neighbor.id] > longestPath) {
                        longestPath = dist[neighbor.id];
                    }
                    if (S.contains(neighbor)) {
                        S.remove(neighbor);
                        maxHeap.add(neighbor);
                    } else {
                        neighbor.f = dist[neighbor.id];
                        maxHeap.add(neighbor);
                    }
                }
            }
        }
        return longestPath;
    }
}
