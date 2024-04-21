package AStarBasedLongestSimplePath;

import java.util.LinkedList;
import java.util.Random;

import GraphGenerator.Vertex;

public class LongestSimplePath {
    public int astartLongestSimplePath(LinkedList<Vertex>[] adjacencyList, LinkedList<Integer> largestComponent) {

        AStarLongestSimplePath astar = new AStarLongestSimplePath();

        int maxPathLength = 0;
        int numVerticesInLCC = largestComponent.size();
        int iterations = (int) Math.sqrt(numVerticesInLCC);

        Random random = new Random();

        for (int i = 1; i <= iterations; i++) {
            int randomIndex = random.nextInt(numVerticesInLCC);
            int vertexU = largestComponent.get(randomIndex);
            randomIndex = random.nextInt(numVerticesInLCC);
            int vertexV = largestComponent.get(randomIndex);

            Vertex source = getVertexCoordiantes(adjacencyList, vertexU);
            Vertex destination = getVertexCoordiantes(adjacencyList, vertexV);

            int maxPath = astar.astar(adjacencyList, source, destination);

            maxPathLength = Math.max(maxPathLength, maxPath);
        }

        return maxPathLength;
    }

    public Vertex getVertexCoordiantes(LinkedList<Vertex>[] adjacencyList, int id) {
        for (int i = 0; i < adjacencyList.length; i++) {
            if (adjacencyList[i] == null) {
                continue;
            }
            for (Vertex vertex : adjacencyList[i]) {
                if (vertex.getId() == id) {
                    Vertex source = new Vertex(vertex.getId(), vertex.getX(), vertex.getY());
                    return source;
                }
            }
        }
        return null;
    }
}
