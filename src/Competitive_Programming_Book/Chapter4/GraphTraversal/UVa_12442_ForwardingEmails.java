package Competitive_Programming_Book.Chapter4.GraphTraversal;

import java.time.Instant;
import java.util.*;
import java.io.*;
import java.lang.*;

public class UVa_12442_ForwardingEmails {

    private static int[] handled;

    static class Reader {
        static BufferedReader reader;
        static StringTokenizer tokenizer;

        /** call this method to initialize reader for InputStream */
        static void init(InputStream input) {
            reader = new BufferedReader(
                    new InputStreamReader(input) );
            tokenizer = new StringTokenizer("");
        }

        /** get next word */
        static String next() throws IOException {
            while ( ! tokenizer.hasMoreTokens() ) {
                //TODO add check for eof if necessary
                tokenizer = new StringTokenizer(
                        reader.readLine() );
            }
            return tokenizer.nextToken();
        }

        static String nextLine() throws IOException {
            if (!tokenizer.hasMoreTokens()) {
                return reader.readLine(); // if at end of a line, read the next one.
            } else {
                tokenizer = new StringTokenizer(""); // skip to the end of line.
                return null;
            }
        }

        static int nextInt() throws IOException {
            return Integer.parseInt( next() );
        }

        static double nextDouble() throws IOException {
            return Double.parseDouble( next() );
        }
    }

    static int[] dfsVisited;
    static int[] edge;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        Reader.init(System.in);

        int T = Reader.nextInt();

        for (int test = 1; test <= T; test++) {
            int N = Reader.nextInt();

            dfsVisited = new int[N];
            edge = new int[N];
            dp = new int[N];
            handled = new int[N];

            int smallest = -1; int biggestans = 0;

            for (int i = 1; i <= N; i++){
                int from = Reader.nextInt() - 1; int to= Reader.nextInt() - 1;
                edge[from] = to;
            };

            for (int i = 0; i < N; i++) {
                int curr = i;
//                System.out.println("PULAMARE " + i);
                int ans = dfs(curr);

                handleCycles();

                if (ans > biggestans) {
                    smallest = i;
                    biggestans = ans;
                }

            }

            System.out.println(String.format("Case %d: %d", test, smallest + 1));
        }

    }

    static int cycle = -1;

    public static void handleCycles() {
        if (handled[cycle] == 1) return;

        int curr = cycle;
        int ans = 0;
        do {
            ans++;
            curr = edge[curr];
        } while (curr != cycle);

        curr = cycle;
        do {
            dp[curr] = ans;
            curr = edge[curr];
            handled[curr] = 1;
        } while (curr != cycle);
    }

    public static int dfs(int curr) {

//        System.out.println("PULA " + curr);
        if (dp[curr] == 0) {
            if (dfsVisited[curr] == 0) {
                dfsVisited[curr] = 1;
                int val =  1 + dfs(edge[curr]);
                dp[curr] = val;
                return val;
            } else {
                cycle = curr;
                return 0;
            }
        } else {
            return dp[curr];
        }
    }
}
