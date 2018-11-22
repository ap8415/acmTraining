package ACMCodeLibrary.GraphAlgorithms;

import java.lang.*;
import java.util.*;

// Runs toposort either BFS or DFS-style on the input.
// Does not modify any part of the input.
public class TopologicalSort {

    // graph nodes numbered 0..N-1
    // visited = bitset of size N
    // adjList is obvious
    private int N;
    private List<Integer>[] adjacencyList;
    private int[] inDegrees; // I take this as input because adjList only gives outdegrees in cst time
    private BitSet visited_dfs;

    private List<Integer> topologicalOrderDfs;
    private List<Integer> topologicalOrderBfs;

    public TopologicalSort(int N, List<Integer>[] adjacencyList, int[] inDegrees) {
        this.N = N;
        this.adjacencyList = adjacencyList;
        this.inDegrees = inDegrees;
        visited_dfs = new BitSet(N);
        topologicalOrderBfs = new ArrayList<>();
        topologicalOrderDfs = new ArrayList<>();
    }

    // Regular topological sort
    List<Integer> standardTopologicalSort() {
        for (int i = 0; i < N; i++) {
            if (!visited_dfs.get(i)) topologicalSortDfs(i);
        }
        return topologicalOrderDfs;
    }

    private void topologicalSortDfs(int i) {
        visited_dfs.set(i);
        for (int j: adjacencyList[i]) {
            if (!visited_dfs.get(j)) topologicalSortDfs(j);
        }
        topologicalOrderDfs.add(i);
    }

    // Produces vertices in BFS style instead of DFS style.
    // That is, vertices get added to the sort as soon as their indegree (w.r.t the rest of the
    // vertices, which have not been added to the toposort) becomes 0.
    List<Integer> bfsTopologicalSort() {
        int[] inDegreesCpy = Arrays.copyOf(inDegrees, N);

        Queue<Integer> zeroInDegree = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            if (inDegreesCpy[i] == 0) zeroInDegree.offer(i);
        }
        while (!zeroInDegree.isEmpty()) {
            int curr = zeroInDegree.poll();
            for (int next : adjacencyList[curr]) {
                inDegreesCpy[next]--;
                if (inDegreesCpy[next] == 0) zeroInDegree.offer(next);
            }
            topologicalOrderBfs.add(curr);
        }
        return topologicalOrderBfs;
    }

    // Same as above, but uses a priority queue to ensure that between two 0-indegree
    // vertices, the one with lower index is selected.
    List<Integer> bfsTopologicalSortPQ() {
        int[] inDegreesCpy = Arrays.copyOf(inDegrees, N);

        Queue<Integer> zeroInDegree = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            if (inDegreesCpy[i] == 0) zeroInDegree.offer(i);
        }
        while (!zeroInDegree.isEmpty()) {
            int curr = zeroInDegree.poll();
            for (int next : adjacencyList[curr]) {
                inDegreesCpy[next]--;
                if (inDegreesCpy[next] == 0) zeroInDegree.offer(next);
            }
            topologicalOrderBfs.add(curr);
        }
        return topologicalOrderBfs;
    }

}
