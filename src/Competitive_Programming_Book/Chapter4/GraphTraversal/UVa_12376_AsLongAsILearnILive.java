package Competitive_Programming_Book.Chapter4.GraphTraversal;

import java.util.*;
import java.io.*;
import java.lang.*;

public class UVa_12376_AsLongAsILearnILive {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Integer>[] adjList;
        int[] vals;
        int[] visited;

        int T = sc.nextInt();
        for (int cases = 1 ; cases <= T; cases ++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            adjList = new ArrayList[n];
            for (int i = 0; i < n; i++) adjList[i] =  new ArrayList<Integer>();
            vals = new int[n];
            visited = new int[n];
            for (int i = 0; i < n; i++) vals[i] = sc.nextInt();

            for (int i = 1; i <= m; i++) {
                adjList[sc.nextInt()].add(sc.nextInt());
            }

//            System.out.println(Arrays.toString(adjList));

            int total = 0;
            int curr = 0;
            while (true) {
//                System.out.println(curr);
                visited[curr] = 1;

                int maxval = -1; int best = -1;
                for (int q : adjList[curr]) {
                    if (visited[q] == 0 && vals[q] > maxval) {
                        maxval = vals[q];
                        best = q;
                    }
                }
                if (best != -1) {
                    curr = best; total += maxval;
                } else {
                    break;
                }
            }

            System.out.println(String.format("Case %d: %d %d", cases, total, curr));
        }
    }

}
