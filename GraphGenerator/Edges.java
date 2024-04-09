package GraphGenerator;

import java.util.Objects;

public class Edges<T extends Vertex> {
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