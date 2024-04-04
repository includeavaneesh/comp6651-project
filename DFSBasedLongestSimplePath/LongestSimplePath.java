package DFSBasedLongestSimplePath;
import java.util.LinkedList;
import java.util.Random;

public class LongestSimplePath {
    public void DFSLongestSimplePath(LinkedList<Integer>[] adjacencyList) {
        LargestConnectedComponent lcc = new LargestConnectedComponent();
        LinkedList<Integer> largestComponent = lcc.largestConnectedComponent(adjacencyList);

        DFSLongestSimplePath dfs = new DFSLongestSimplePath();

        int maxPathLength = 0;

        int numVerticesInLCC = largestComponent.size();
        int iterations = (int) Math.sqrt(numVerticesInLCC);

        Random random = new Random();

        for (int i = 1; i <= iterations; i++) {
            int randomIndex = random.nextInt(numVerticesInLCC);
            int vertexU = largestComponent.get(randomIndex);

            int vertexV = dfs.DFS(vertexU, adjacencyList)[0];
            int depthV = dfs.DFS(vertexU, adjacencyList)[1];
            int depthW = dfs.DFS(vertexV, adjacencyList)[1];

            maxPathLength = Math.max(maxPathLength, Math.max(depthV,depthW));
        }

        System.out.println("longest path: " + maxPathLength);
    }
}
