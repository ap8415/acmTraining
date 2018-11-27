package ACMCodeLibrary.GraphAlgorithms;

import java.util.*;

/*
 * Finds the maximum flow in a flow network.
 * @param adjList adjacency list with capacities
 * @param s source
 * @param t sink
 * @return maximum flow
 */
public class EdmondsKarp {
    public static int edmondsKarp(List<WeightedEdge>[] adjList, int s, int t) {
        int n = adjList.length;
        // Residual capacity from u to v is adjList[u][v] - F[u][v]
        List<Integer>[] F = new List[n];
        for (int i = 0; i < n; i++) F[i] = new ArrayList<>();

        while (true) {
            int[] P = new int[n]; // Parent table
            Arrays.fill(P, -1);
            P[s] = s;
            int[] dist = new int[n]; // Capacity of path to node
            dist[s] = Integer.MAX_VALUE;
            // BFS queue
            Queue<Integer> Q = new LinkedList<>();
            Q.offer(s);
            LOOP:
            while (!Q.isEmpty()) {
                int u = Q.poll();
                for (WeightedEdge edge : adjList[u]) {
                    int v = edge.to;
                    // There is available capacity,
                    // and v is not seen before in search
                    if (adjList[u].get(v).residual > 0 && P[v] == -1) {
                        P[v] = u;
                        dist[v] = Math.min(dist[u], adjList[u].get(v).residual - F[u].get(v));
                        if (v != t) {
                            Q.offer(v);
                        } else {
                            // Backtrack search, and write flow
                            while (P[v] != v) {
                                u = P[v];
                                adjList[u].get(v).residual += dist[t];
                                adjList[v].get(u).residual -= dist[t];
                                v = u;
                            }
                            break LOOP;
                        }
                    }
                }
            }
            if (P[t] == -1) { // We did not find a path to t
                int sum = 0;
                for (int x : F[s])
                    sum += x;
                return sum;
            }
        }
    }
}
