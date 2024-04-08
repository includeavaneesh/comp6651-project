package EvaluationMetrics;

import java.util.LinkedList;

public class EvaluationMetrics {
    public int numberOfNodesInLCC(LinkedList<Integer> LCCVertexList) {
        return LCCVertexList.size();
    }

    public int maxDegree(LinkedList<Integer>[] graph, LinkedList<Integer> lccVertices) {
        int maxDegree = 0;

        // Iterate over vertices in the LCC
        for (int vertex : lccVertices) {
            int degree = graph[vertex].size(); // Calculate degree of the vertex

            // Update maxDegree if necessary
            if (degree > maxDegree) {
                maxDegree = degree;
            }
        }

        return maxDegree;
    }

    public double averageDegree(LinkedList<Integer>[] graph, LinkedList<Integer> lccVertices) {
        int degreeSum = 0;
        for (int vertex : lccVertices)
            degreeSum += graph[vertex].size();

        return (double) (degreeSum / lccVertices.size());
    }

}
