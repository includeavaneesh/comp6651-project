import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Objects;

class Vertex {
    double x;
    double y;
    int id;

    public Vertex(int id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return Integer.toString(id);
    }

}

class Edges<T extends Vertex> {
    private T vertex1;
    private T vertex2;

    public Edges(T vertex1, T vertex2) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }

    public T getVertex1() {
        return vertex1;
    }

    public void setVertex1(T vertex1) {
        this.vertex1 = vertex1;
    }

    public T getVertex2() {
        return vertex2;
    }

    public void setVertex2(T vertex2) {
        this.vertex2 = vertex2;
    }

    @Override
    public String toString() {
        return vertex1 + " " + vertex2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Edges<?> pair = (Edges<?>) o;
        return (Objects.equals(vertex1, pair.vertex1) &&
                Objects.equals(vertex2, pair.vertex2))
                || (Objects.equals(vertex1, pair.vertex2) &&
                        Objects.equals(vertex2, pair.vertex1));
    }

}

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

        try (BufferedWriter file = new BufferedWriter(new FileWriter("./EDGES/test.EDGES"))) {
            String toWrite = "";

            for (Edges<Vertex> E : edges) {
                toWrite += E.toString() + "\n";
            }
            file.write(toWrite);
            file.close();
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

    public static void main(String[] args) throws IOException {
        GraphGenerator obj = new GraphGenerator();
        obj.generateGeometricGraph(10, 0.5);
    }
}
