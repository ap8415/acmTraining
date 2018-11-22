package Competitive_Programming_Book.Chapter4.TopologicalSort;

import java.lang.*;
import java.util.*;

public class UVa_11060_Beverages {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = 0;

        while (sc.hasNext()) {cases++;
            int N = sc.nextInt();
            HashMap<String, Integer> avBev = new HashMap<>();
            String[] nrToBev = new String[N];
            for (int i = 0; i < N; i++) {
                String next = sc.next();
                avBev.put(next, i);
                nrToBev[i] = next;
            }

            List<Integer>[] adjList = new List[N];
            int[] inDegrees = new int[N];
            for (int i = 0; i < N;i++) adjList[i] = new ArrayList<>();

            int M = sc.nextInt();
            for (int i = 0; i < M; i++) {
                int from = avBev.get(sc.next());
                int to = avBev.get(sc.next());
                inDegrees[to]++;
                adjList[from].add(to);
            }

            for (int i = 0; i < N;i++) Collections.sort(adjList[i]);

            TopologicalSort tps = new TopologicalSort(N, adjList, inDegrees);
            List<Integer> sol = tps.bfsTopologicalSortPQ();

            System.out.print("Case #" + cases+": Dilbert should drink beverages in this order:");
            for (int i = 0; i <= sol.size() - 1; i++) {
                System.out.print(" " + nrToBev[sol.get(i)]);
            }
            System.out.println(".");
            System.out.println();
                    }
    }



    static class TopologicalSort {

        public TopologicalSort(int N, List<Integer>[] adjacencyList, int[] inDegrees) {
            this.N = N;
            this.adjacencyList = adjacencyList;
            this.inDegrees = inDegrees;
            visited_dfs = new BitSet(N);
        }

        // graph nodes numbered 0..N-1
        // visited = bitset of size N
        // adjList is obvious
        private int N;
        private List<Integer>[] adjacencyList;
        private int[] inDegrees; // I take this as input because adjList only gives outdegrees in cst time
        private BitSet visited_dfs;

        private Queue<Integer> topologicalOrderDfs;
        private List<Integer> topologicalOrderBfs;

        // Regular topological sort.
        // Returns the result as an arraylist instead of queue, but can be optimized away.
        List<Integer> topologicalSort() {
            topologicalOrderDfs = new LinkedList<>();
            for (int i = 0; i < N; i++) {
                if (!visited_dfs.get(i)) topologicalSortDfs(i);
            }
            return new ArrayList<>(topologicalOrderDfs);
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
            topologicalOrderBfs = new ArrayList<>();
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
            topologicalOrderBfs = new ArrayList<>();
            int[] inDegreesCpy = Arrays.copyOf(inDegrees, N);

            PriorityQueue<Integer> zeroInDegree = new PriorityQueue<>();
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

}
