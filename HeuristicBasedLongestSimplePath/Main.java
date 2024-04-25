package HeuristicBasedLongestSimplePath;

import java.util.*;

//betweenness centrality heuristic
public class Main {

    public boolean relaxEdges(int vertex1, int vertex2, Map<Integer, Integer> centralityDistance,
            Map<Integer, Integer> predecessor, boolean vertexBool) {
        if (centralityDistance.get(vertex2) < centralityDistance.get(vertex1) + 1) {
            centralityDistance.put(vertex2, centralityDistance.get(vertex1) + 1);
            predecessor.put(vertex2, vertex1);
            vertexBool = true;
        }

        return vertexBool;
    }

    public int[] getMaximumLengthEdgeSet(LinkedList<Integer>[] adjacencyList, int sourceVertex, int destinationVertex) {
        int n = adjacencyList.length;
        Map<Integer, Integer> centralityDistance = new HashMap<>();
        Map<Integer, Integer> predecessor = new HashMap<>();
        Map<Integer, Integer> neighbor = new HashMap<>();
        for (int i = 1; i < n; i++) {
            centralityDistance.put(i, Integer.MIN_VALUE);
            predecessor.put(i, Integer.MIN_VALUE);
            neighbor.put(i, adjacencyList[i].size());

        }

        centralityDistance.put(sourceVertex, 0);

        Set<Integer> S = new HashSet<>();
        PriorityQueue<int[]> priorityQue = new PriorityQueue<>(Comparator.comparingInt(a -> -a[0]));
        priorityQue.add(new int[] { centralityDistance.get(sourceVertex), sourceVertex });
        int furthestVertexLength = 0;
        int furthestVertex = Integer.MIN_VALUE;

        while (!priorityQue.isEmpty()) {
            int[] pair = priorityQue.poll();
            int currentVertex = pair[1];

            S.add(currentVertex);
            for (int i : adjacencyList[currentVertex]) {
                if (!S.contains(i)) {
                    boolean check = relaxEdges(currentVertex, i, centralityDistance, predecessor, false);
                    if (check) {
                        for (Iterator<int[]> iterator = priorityQue.iterator(); iterator.hasNext();) {
                            int[] elements = iterator.next();
                            if (elements[1] == i) {
                                iterator.remove();
                            }
                        }
                        priorityQue.add(new int[] { -centralityDistance.get(i) - (neighbor.get(i) / 10)
                                - (int) (Math.sqrt(Math.pow((destinationVertex - i), 2)) * 10), i });
                    }
                    if (centralityDistance.get(i) > furthestVertexLength) {
                        furthestVertexLength = centralityDistance.get(i);
                        furthestVertex = i;
                    }
                }
            }
        }

        return new int[] { furthestVertexLength, furthestVertex };
    }

    public int vertexWithMaximumBetweenness(LinkedList<Integer>[] adjacencyList, LinkedList<Integer> largestComponent) {
        Map<Integer, Double> betweennessCentrality = calculateBetweennessCentrality(adjacencyList, largestComponent);
        int vertexWithMaximumBetweenness = -1;
        double maxBetweenness = Double.MIN_VALUE;
        for (int vertex : largestComponent) {
            double betweenness = betweennessCentrality.getOrDefault(vertex, 0.0);
            if (betweenness > maxBetweenness) {
                maxBetweenness = betweenness;
                vertexWithMaximumBetweenness = vertex;
            }
        }
        return vertexWithMaximumBetweenness;
    }

    public Map<Integer, Double> calculateBetweennessCentrality(LinkedList<Integer>[] adjacencyList,
            LinkedList<Integer> largestComponent) {
        int numVertices = adjacencyList.length - 1;
        Map<Integer, Double> betweennessCentrality = new HashMap<>();

        for (int vertex : largestComponent) {
            betweennessCentrality.put(vertex, 0.0);
        }

        for (int s : largestComponent) {
            LinkedList<Integer> stack = new LinkedList<>();
            Stack<Integer> queue = new Stack<>();
            @SuppressWarnings("unchecked")
            LinkedList<Integer>[] predecessors = new LinkedList[numVertices + 1];
            double[] centrality = new double[numVertices + 1];
            double[] distance = new double[numVertices + 1];
            Arrays.fill(distance, -1);
            Arrays.fill(centrality, 0);
            distance[s] = 0;
            centrality[s] = 1;
            queue.push(s);
            while (!queue.isEmpty()) {
                int v = queue.pop();
                stack.push(v);
                for (int neighbor : adjacencyList[v]) {
                    if (distance[neighbor] < 0) {
                        queue.push(neighbor);
                        distance[neighbor] = distance[v] + 1;
                    }
                    if (distance[neighbor] == distance[v] + 1) {
                        centrality[neighbor] += centrality[v];
                        if (predecessors[neighbor] == null) {
                            predecessors[neighbor] = new LinkedList<>();
                        }
                        predecessors[neighbor].add(v);
                    }
                }
            }
            double[] delta = new double[numVertices + 1];
            while (!stack.isEmpty()) {
                int w = stack.pop();
                if (predecessors[w] != null) {
                    for (int predecessor : predecessors[w]) {
                        delta[predecessor] += (centrality[predecessor] / centrality[w]) * (1 + delta[w]);
                    }
                }
                if (w != s) {
                    betweennessCentrality.put(w, betweennessCentrality.get(w) + delta[w]);
                }
            }
        }

        return betweennessCentrality;
    }

    public int getRandomDestinationVertex(LinkedList<Integer> largestComponent, int source) {
        Random random = new Random();
        int numVerticesInLCC = largestComponent.size();
        int randomIndex;
        do {
            randomIndex = random.nextInt(numVerticesInLCC);
        } while (largestComponent.get(randomIndex) == source); // Ensure destination is not the same as source
        return largestComponent.get(randomIndex);
    }
}
