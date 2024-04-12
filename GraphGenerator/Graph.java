package GraphGenerator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Graph {
	
	/*
	 * Reads graph and creates adjacency matrix of vertex ID.
	 * When reading graph without coordinate information for vertex, set isGeometric to false
	 */
    @SuppressWarnings("unchecked")
    public LinkedList<Integer>[] readGraph(String filePath, boolean isGeometric) {
        LinkedList<Integer>[] adjacencyList = null;
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line;
            
            // Use ArrayList to temporarily store edges
            ArrayList<String[]> edgesList = new ArrayList<>();
            
            // Read edges and populate edgesList
            while ((line = reader.readLine()) != null) {
                String[] edge = line.trim().split("\\s+");
                edgesList.add(edge);
            }

            // Use HashSet to keep track of unique vertices
            // HashSet<Integer> verticesSet = new HashSet<>();
            int vertices = 0;
            // Read edges and populate verticesSet
            for  (String[] edge : edgesList) {
            	int vertex1, vertex2;
            	if (isGeometric) {
            		vertex1 = Integer.parseInt(edge[0]);
                    vertex2 = Integer.parseInt(edge[3]);
            	}
            	else {
            		vertex1 = Integer.parseInt(edge[0]);
                    vertex2 = Integer.parseInt(edge[1]);
            	}               
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

            // Read edges and populate adjacency list
            for (String[] edge : edgesList) {
            	int vertex1, vertex2;
            	if (isGeometric) {
            		vertex1 = Integer.parseInt(edge[0]);
                    vertex2 = Integer.parseInt(edge[3]);
            	}
            	else {
            		vertex1 = Integer.parseInt(edge[0]);
                    vertex2 = Integer.parseInt(edge[1]);
            	} 

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
    
    /*
	 * Reads the graph and creates adjacency matrix of vertex with all the details.
	 * Can be used for A* algorithm if position coordinate are required
	 */
    @SuppressWarnings("unchecked")
    public LinkedList<Vertex>[] readGeometricGraphWithCoordinates(String filePath) {
        LinkedList<Vertex>[] adjacencyList = null;
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line;
            
            // Use ArrayList to temporarily store edges
            ArrayList<String[]> edgesList = new ArrayList<>();
            
            // Use HashMap to store the mapping between vertex IDs and Vertex objects
            HashMap<Integer, Vertex> vertexMap = new HashMap<>();
            
            int vertices = 0;
            // Read edges and populate edgesList
            while ((line = reader.readLine()) != null) {
                String[] edge = line.trim().split("\\s+");
                edgesList.add(edge);
                
                int vertex1 = Integer.parseInt(edge[0]);
                int vertex2 = Integer.parseInt(edge[3]);
                double x1 = Double.parseDouble(edge[1]);
                double y1 = Double.parseDouble(edge[2]);
                double x2 = Double.parseDouble(edge[4]);
                double y2 = Double.parseDouble(edge[5]);
                
                if (!vertexMap.containsKey(vertex1)) {
                    Vertex newVertex = new Vertex(vertex1, x1, y1);
                    vertexMap.put(vertex1, newVertex);
                }
                
                if (!vertexMap.containsKey(vertex2)) {
                    Vertex newVertex = new Vertex(vertex2, x2, y2);
                    vertexMap.put(vertex2, newVertex);
                }
                
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

            // Read edges and populate adjacency list
            for (String[] edge : edgesList) {
            	int vertex1ID = Integer.parseInt(edge[0]);
                int vertex2ID = Integer.parseInt(edge[3]);
                
                Vertex vertex1 = vertexMap.get(vertex1ID);
                Vertex vertex2 = vertexMap.get(vertex2ID);

                // Assuming the graph is undirected
                adjacencyList[vertex1ID].add(vertex2);
                adjacencyList[vertex2ID].add(vertex1);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return adjacencyList;
    }

    public void printGraph(LinkedList<Vertex>[] adjacencyList) {
        System.out.println("Graph adjacency list:");
        for (int i = 1; i < adjacencyList.length; i++) {
            System.out.print(i + " -> ");
            for (Vertex vertex : adjacencyList[i]) {
                System.out.print(vertex.id + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Provide the path to the text file containing graph edges
         String filePath = "./EDGES/graph_300Nodes.EDGES";

        // // Create adjacency list to store the graph
         
         Graph graph = new Graph();
         // LinkedList<Integer>[] adjacencyList = graph.readGraph(filePath, true);
         
         LinkedList<Vertex>[] adjacencyList = graph.readGeometricGraphWithCoordinates(filePath);

        // // Print adjacency list
         graph.printGraph(adjacencyList);
    }
}
