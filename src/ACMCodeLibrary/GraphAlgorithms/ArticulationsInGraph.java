package ACMCodeLibrary.GraphAlgorithms;

import java.lang.*;
import java.util.*;

public class ArticulationsInGraph {

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
