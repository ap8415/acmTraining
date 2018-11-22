package Competitive_Programming_Book.Chapter4.BipartiteCheck;

import java.io.*;
import java.lang.*;
import java.util.*;

public class UVa_11080_PlaceTheGuards {

    static List<Integer>[] adjList;
    static int v;
    static int[] visited;
    static int zeroes;
    static int total;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int cases = 1; cases <= t; cases++) {
            v = sc.nextInt(); int e = sc.nextInt();
            adjList = new List[v]; visited = new int[v]; Arrays.fill(visited, -1);
            for (int i = 0; i < v; i++) adjList[i] = new ArrayList<>();
            for (int i = 0; i < e; i++) {
                int from = sc.nextInt(); int to = sc.nextInt();
                adjList[from].add(to); adjList[to].add(from);
            }

            try {
                int ans = 0;
                for (int i = 0; i < v; i++) {
                    if (visited[i] == -1) {
                        zeroes = 0;
                        total = 0;
                        dfs(i, 0);
                        ans += Math.max(1, Math.min(zeroes, total - zeroes)); // a one-element CC will add 1 to the answer
                    }
                }
                System.out.println(ans);
            } catch (Exception ex) {
                System.out.println(-1);
            }


        }
    }

    private static void dfs(int i, int color) throws Exception {
        visited[i] = color;
        if (color == 0) zeroes++;
        total++;
        for (int j : adjList[i]) {
            if (visited[j] == color) throw new Exception();
            else if (visited[j] == -1) dfs(j, 1 - color);
        }
    }

}
