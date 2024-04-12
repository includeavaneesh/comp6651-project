package EvaluationMetrics;

import java.util.LinkedList;

import GraphGenerator.Graph;
import GraphGenerator.LargestConnectedComponent;

public class TestMetrics {
    public static void main(String[] args) {
        EvaluationMetrics evaluationMetrics = new EvaluationMetrics();
        String filePath = "./EDGES/inf-euroroad.edges";
        Graph graph = new Graph();
        // Create adjacency list to store the graph
        LinkedList<Integer>[] adjacencyList = graph.readGraph(filePath, false);

        LargestConnectedComponent lcc = new LargestConnectedComponent();
        LinkedList<Integer> largestComponent = lcc.largestConnectedComponent(adjacencyList);

        System.out.println("|V_LCC|: " + evaluationMetrics.numberOfNodesInLCC(largestComponent));
        System.out.println("Delta V_LCC: " + evaluationMetrics.maxDegree(adjacencyList, largestComponent));
        System.out.println("kBar V_LCC: " + evaluationMetrics.averageDegree(adjacencyList, largestComponent));
        // Add LSPcode according to use
    }
}
