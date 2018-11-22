package Competitive_Programming_Book.Chapter4.Articulations;

import java.util.*;
import java.io.*;

public class UVa_10765_DovesAndBombs {

    static class Reader {
        static BufferedReader reader;
        static StringTokenizer tokenizer;

        /** call this method to initialize reader for InputStream */
        static void init(InputStream input) {
            reader = new BufferedReader(
                    new InputStreamReader(input) );
            tokenizer = new StringTokenizer("");
        }

        static boolean hasNext() throws IOException {
            String nextLine = reader.readLine();
            if (nextLine == null) return false;
            else {
                tokenizer = new StringTokenizer(nextLine);
                return true;
            }
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

    static class PigeonValue implements Comparable<PigeonValue> {
        int stationIndex, pigeonValue;

        public PigeonValue(int stationIndex, int pigeonValue) {
            this.stationIndex = stationIndex;
            this.pigeonValue = pigeonValue;
        }

        @Override
        public int compareTo(PigeonValue o) {
            return -(pigeonValue != o.pigeonValue ? pigeonValue - o.pigeonValue : o.stationIndex - stationIndex);
        }

        @Override
        public String toString() {
            return String.format("%d %d", stationIndex, pigeonValue);
        }
    }

    public static void main(String[] args) throws IOException {

        Reader.init(System.in);
        while (Reader.hasNext()) {
            int n = Reader.nextInt(); int m = Reader.nextInt();
            if (n == 0 && m == 0) return;
            int from, to;
            List<Integer>[] adjList = new List[n]; for (int i = 0; i < n; i++) adjList[i] = new ArrayList<>();
            while(true) {
                from = Reader.nextInt(); to = Reader.nextInt();
                if (from == -1 && to == -1) break;

                adjList[from].add(to); adjList[to].add(from);
            }

            ArticulationsInGraph articulations = new ArticulationsInGraph(n, adjList);
            PriorityQueue<PigeonValue> sortedValues = new PriorityQueue<>();
            for (int i = 0; i < n; i++) sortedValues.add(new PigeonValue(i, articulations.numberOfCC(i)));

            for (int i = 0; i < m; i++) {
                System.out.println(sortedValues.poll());
            }
            System.out.println();
        }
    }

    static class ArticulationsInGraph {

        // graph nodes numbered 0..N-1
        private int N;
        private List<Integer>[] adjacencyList;
        // dfs[i] = iteration of dfs at which node i was encountered
        private int[] dfs;
        // lowestDfs[i] =  lowest dfs that can be reached by dfs from
        // node i.
        private int[] lowestDfs;
        // 1 if is articulation point, 2 otherwise.
        private int[] CC;
        private int iterationNo = 0;
        private int totalCC; // no of connected components of the graph

        // Graph is not assumed to be connected, the dfs is run on all connected
        // components.
        public ArticulationsInGraph(int N, List<Integer>[] adjacencyList) {
            this.N = N;
            this.adjacencyList = adjacencyList;
            this.CC = new int[N];
            this.dfs = new int[N];
            this.lowestDfs = new int[N];
            totalCC = 0;
            for (int i = 0; i < N; i++) {
                if (dfs[i] == 0) {
                    articulationDfsEntry(i);
                    totalCC++;
                }
            }
            for (int i = 0; i < N; i++) CC[i] += (totalCC - 1);
        }

        public boolean isArticulationPoint(int i) {
            return CC[i] > 1;
        }

        // return the number of CC's that would result from removing node i
        public int numberOfCC(int i) {
            return CC[i];
        }

        // starts an articulation point dfs at 'first'.
        void articulationDfsEntry(int first) {
            dfs[first] = 1;
            lowestDfs[first] = 1;
            iterationNo = 2;
            for (int j : adjacencyList[first]) {
                if (dfs[j] == 0) {
                    int lowestFromSubtree = articulationPointsDfs(j, first);
                    if (lowestFromSubtree >= 1) CC[first]++;
                }
            }
            iterationNo = 1;
        }

        // Runs a depth-first search that sets the dfs and lowest_dfs for each node
        private int articulationPointsDfs(int i, int parent) {
            dfs[i] = iterationNo;
            int lowest = dfs[i];
            CC[i]++;

            for (int j : adjacencyList[i]) {
                if (j == parent) continue;

                iterationNo++;
                int lowestFromSubtree;
                if (dfs[j] == 0) {
                    lowestFromSubtree = articulationPointsDfs(j, i);
                    if (lowestFromSubtree >= dfs[i]) CC[i]++;
                } else {
                    lowestFromSubtree = dfs[j];
                }
                lowest = Math.min(lowest, lowestFromSubtree);
            }

            lowestDfs[i] = lowest;
            return lowestDfs[i];
        }

    }


}
