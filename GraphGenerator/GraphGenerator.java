package GraphGenerator;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GraphGenerator {
	LargestConnectedComponent lccCalculator;
	
	public GraphGenerator() {
		lccCalculator = new LargestConnectedComponent();
	}

    public LinkedHashSet<Vertex> generateVertices(int n) {
        LinkedHashSet<Vertex> vertices = new LinkedHashSet<Vertex>(n);
        for (int vertexID = 1; vertexID <= n; vertexID++) {
            double x = Math.random();
            double y = Math.random();
            Vertex newVertex = new Vertex(vertexID, x, y);
            vertices.add(newVertex);
        }
        return vertices;
    }

    public ArrayList<Edges<Vertex>> generateEdges(LinkedHashSet<Vertex> vertices, double r) 
    {
        ArrayList<Edges<Vertex>> edges = new ArrayList<>();
        for (Vertex u : vertices) {
            for (Vertex v : vertices) {
                double distanceUV = (Math.pow((u.getX() - v.getX()), 2) + Math.pow((u.getY() - v.getY()), 2));
                Edges<Vertex> newEdge = new Edges<>(u, v);
                if (u != v && distanceUV <= Math.pow(r, 2) && !edges.contains(newEdge)) {
                    edges.add(newEdge);
                }
            }
        }
        return edges;
    }
    
    public void generateGraphFile(ArrayList<Edges<Vertex>> edges, String filePath) throws IOException 
    {
        try (BufferedWriter file = new BufferedWriter(new FileWriter(filePath))) 
        {
            for (Edges<Vertex> E : edges) 
            {
                Vertex u = E.getVertex1();
                Vertex v = E.getVertex2();
                String line = String.format("%d %.2f %.2f %d %.2f %.2f\n", u.getId(), u.getX(), u.getY(), v.getId(), v.getX(), v.getY());
                file.write(line);
            }
        }
        catch (IOException e) 
        {
            throw e;
        }
    }

	public void generateGraph(GraphSpecifications graph) {
        double lowerBoundR = 0.01;
        double upperBoundR = 0.1;
        int numOfNodes = graph.numOfNodes;
        double minNodeCountGraph = graph.minNodeCountGraph * numOfNodes;
        double maxNodeCountGraph = graph.maxNodeCountGraph * numOfNodes;
        ArrayList<Edges<Vertex>> bestEdges = null;

        while (true) 
        {        	
            double mid = (lowerBoundR + upperBoundR) / 2;
            ArrayList<Edges<Vertex>> edges = generateEdges(generateVertices(numOfNodes), mid);
            LinkedList<Integer>[] adjacencyList = generateAdjacencyList(edges, numOfNodes);
            LinkedList<Integer> largestComponent = lccCalculator.largestConnectedComponent(adjacencyList);
            int componentSize = largestComponent.size();
            
            if (componentSize >= minNodeCountGraph && 
            		componentSize <= maxNodeCountGraph) 
            {
            	bestEdges = edges;
                graph.maxAllowedEdgeLength = mid;
                graph.numNodesLCC = componentSize;
                break;
            } 
            else if (componentSize >= minNodeCountGraph) 
            {
                upperBoundR = mid - 0.01;
            } 
            else 
            {
                lowerBoundR = mid + 0.01;
            }
        }

        if (graph.maxAllowedEdgeLength != -1 && bestEdges != null) 
        {
            try 
            {
            	generateGraphFile(bestEdges, graph.filePath);
                System.out.println("Graph generated and stored in file: " + graph.filePath);
                System.out.println(graph);
            } 
            catch (IOException e) 
            {
                System.err.println("Error writing to file: " + e.getMessage());
            }
        } 
        else 
        {
            System.out.println("No graph found within the specified range of connected component size.");
        }		
	}
	
	@SuppressWarnings("unchecked")
	private LinkedList<Integer>[] generateAdjacencyList(ArrayList<Edges<Vertex>> edges, int n) {
        LinkedList<Integer>[] adjacencyList = new LinkedList[n + 1];

        for (int i = 1; i <= n; i++) 
        {
            adjacencyList[i] = new LinkedList<>();
        }

        for (Edges<Vertex> edge : edges) 
        {
            int u = edge.getVertex1().getId();
            int v = edge.getVertex2().getId();
            adjacencyList[u].add(v);
            adjacencyList[v].add(u);
        }

        return adjacencyList;
    }
}
