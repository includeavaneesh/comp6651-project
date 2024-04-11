package GraphGenerator;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class GraphGenerator {
    // do some code
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

    public ArrayList<Edges<Vertex>> generateEdges(LinkedHashSet<Vertex> vertices, double r) {

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

    /**
     * 
     * @param n
     * @param r
     * @throws IOException
     */
    public void generateGeometricGraph(int n, double r) throws IOException {
    	ArrayList<Edges<Vertex>> edges = generateEdges(generateVertices(n), r);

        try (BufferedWriter file = new BufferedWriter(new FileWriter("./EDGES/test2.EDGES"))) {

            for (Edges<Vertex> E : edges) {
                Vertex u = E.getVertex1();
                Vertex v = E.getVertex2();
                String line = String.format("%d %.2f %.2f %d %.2f %.2f\n", u.getId(), u.getX(), u.getY(), v.getId(), v.getX(), v.getY());
                file.write(line);
            }
        } catch (IOException e) {
            throw e;
        }
    }

    public static void main(String[] args) throws IOException {
        GraphGenerator obj = new GraphGenerator();
        obj.generateGeometricGraph(100, 0.1);
    }
}
