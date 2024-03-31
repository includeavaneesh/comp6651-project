import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Graph {

    public static LinkedList<Integer>[] readGraph(String filePath) {
        LinkedList<Integer>[] adjacencyList = null;
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line;

            // Use HashSet to keep track of unique vertices
            // HashSet<Integer> verticesSet = new HashSet<>();
            int vertices = 0;
            // Read edges and populate verticesSet
            while ((line = reader.readLine()) != null) {
                String[] edge = line.trim().split("\\s+");
                int vertex1 = Integer.parseInt(edge[0]);
                int vertex2 = Integer.parseInt(edge[1]);

                vertices = Math.max(vertices, vertex1);
                vertices = Math.max(vertices, vertex2);
            }

            // Determine the number of vertices
            int numVertices = vertices + 1;
            adjacencyList = new LinkedList[numVertices];

            // Initialize adjacency lists for each vertex
            for (int i = 1; i < numVertices; i++) {
                adjacencyList[i] = new LinkedList<>();
            }

            // Reset reader to read edges again from the beginning
            reader.close();
            reader = new BufferedReader(new FileReader(filePath));

            // Read edges and populate adjacency list
            while ((line = reader.readLine()) != null) {
                String[] edge = line.trim().split("\\s+");
                int vertex1 = Integer.parseInt(edge[0]);
                int vertex2 = Integer.parseInt(edge[1]);

                // Assuming the graph is undirected
                adjacencyList[vertex1].add(vertex2);
                adjacencyList[vertex2].add(vertex1);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return adjacencyList;
    }

    public static void printGraph(LinkedList<Integer>[] adjacencyList) {
        System.out.println("Graph adjacency list:");
        for (int i = 1; i < adjacencyList.length; i++) {
            System.out.print(i + " -> ");
            for (int j : adjacencyList[i]) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Provide the path to the text file containing graph edges
        String filePath = "./EDGES/test.EDGES";

        // Create adjacency list to store the graph
        LinkedList<Integer>[] adjacencyList = readGraph(filePath);

        // Print adjacency list
        printGraph(adjacencyList);
    }
}
