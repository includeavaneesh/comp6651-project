
import java.util.Iterator;
import java.util.LinkedList;

public class LargestConnectedComponent {
    public LinkedList<Integer> largestConnectedComponent(LinkedList<Integer>[] adjacencyList) {
        int numberOfVertices = adjacencyList.length;
        // All nodes are currently not visited
        boolean[] visited = new boolean[numberOfVertices];

        // Variable to store the size of the largest connected component found so far
        int maxComponentSize = 0;
        // Variable to store the starting vertex of the largest connected component
        // found so far
        int startVertex = 1;

        // Traverse through each disconnected component
        for (int i = 1; i < numberOfVertices; i++) {
            if (!visited[i]) {
                int componentSize = DFSVisit(i, visited, adjacencyList);
                if (componentSize > maxComponentSize) {
                    maxComponentSize = componentSize;
                    startVertex = i;
                }
            }
        }

        // Re-run DFS from the starting vertex of the largest connected component
        visited = new boolean[numberOfVertices];
        LinkedList<Integer> largestComponent = new LinkedList<>();
        DFSVisitForComponent(startVertex, visited, adjacencyList, largestComponent);

        return largestComponent;
    }

    private int DFSVisit(int v, boolean[] visited, LinkedList<Integer>[] adjacencyList) {
        // Mark the current node as visited
        visited[v] = true;

        // Initialize the size of this component as 1
        int componentSize = 1;

        // Iterate through all its children nodes
        Iterator<Integer> iterator = adjacencyList[v].iterator();
        while (iterator.hasNext()) {
            // Get the child node
            int n = iterator.next();

            // If the child is not visited
            if (!visited[n]) {
                // Visit further in child
                componentSize += DFSVisit(n, visited, adjacencyList);
            }
        }

        return componentSize;
    }

    private void DFSVisitForComponent(int v, boolean[] visited, LinkedList<Integer>[] adjacencyList,
            LinkedList<Integer> component) {
        // Mark the current node as visited
        visited[v] = true;
        // Add the current node to the component
        component.add(v);

        // Iterate through all its children nodes
        Iterator<Integer> iterator = adjacencyList[v].iterator();
        while (iterator.hasNext()) {
            // Get the child node
            int n = iterator.next();

            // If the child is not visited
            if (!visited[n]) {
                // Visit further in child
                DFSVisitForComponent(n, visited, adjacencyList, component);
            }
        }
    }

    // public static void main(String[] args) {
    // String filePath = "./EDGES/test.EDGES";
    // Graph obj1 = new Graph();
    // // Create adjacency list to store the graph
    // LinkedList<Integer>[] adjacencyList = obj1.readGraph(filePath);

    // // Print adjacency list
    // obj1.printGraph(adjacencyList);

    // // DFS
    // // DFS(1, adjacencyList);
    // LinkedList<Integer> largestComponent =
    // largestConnectedComponent(adjacencyList);
    // System.out.println("Largest connected component: " +
    // largestComponent.size());
    // }
}
