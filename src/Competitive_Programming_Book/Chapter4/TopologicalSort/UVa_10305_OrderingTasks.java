package Competitive_Programming_Book.Chapter4.TopologicalSort;

import java.util.*;
import java.io.*;
import java.lang.*;

public class UVa_10305_OrderingTasks {

  static  List<Integer> topoSort = new ArrayList<>();
 static  List<Integer>[] directedEdgeList;
  static int[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            int n = sc.nextInt(); int m = sc.nextInt();
            if (n == 0 && m == 0) return;

            directedEdgeList = new List[n];
            visited = new int[n];
            for (int i = 0; i < n; i++) directedEdgeList[i] = new ArrayList<>();


            Set<Integer> noIncoming = new HashSet<>();
            for (int i = 0; i < n; i++) noIncoming.add(i);

            for (int i = 0; i< m; i++) {
                int from = sc.nextInt() - 1; int to = sc.nextInt() - 1;
                directedEdgeList[from].add(to);
                noIncoming.remove(to);
            }

            for (int source : noIncoming) {
                topologicalSort(source);
            }

            for (int i = topoSort.size() - 1; i > 0; i--) {
                System.out.print((1 + topoSort.get(i)) + " ");
            }
            System.out.print((1 + topoSort.get(0)));

            System.out.println();

            topoSort = new ArrayList<>();

        }
    }

    private static void topologicalSort(int source) {
        if (visited[source] == 1) return;
        visited[source] = 1;

        for (int i : directedEdgeList[source]) {
            topologicalSort(i);
        }
        topoSort.add(source);
    }

}
