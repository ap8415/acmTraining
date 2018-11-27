package ACM_Contests.SWERC.SWERC2014;

import java.io.*;
import java.util.*;
import java.lang.*;

/**
 * class D
 **/
public class D {
    /**
     * number of vertices
     **/
    private int V;
    /**
     * preorder number counter
     **/
    private int preCount;
    /**
     * low number of v
     **/
    private int[] low;
    /**
     * to check if v is visited
     **/
    private boolean[] visited;
    /**
     * to store given graph
     **/
    private List<Integer>[] graph;
    /**
     * to store all scc
     **/
    private List<List<Integer>> sccComp;
    private Stack<Integer> stack;
    private Stack<State> dfsStack;

    /**
     * function to get all strongly connected components
     **/
    public List<List<Integer>> getSCComponents(List<Integer>[] graph) {
        V = graph.length;
        this.graph = graph;
        low = new int[V];
        visited = new boolean[V];
        stack = new Stack<>();
        dfsStack = new Stack<>();
        sccComp = new ArrayList<>();
        for (int v = 0; v < V; v++) {
            if (!visited[v]) {
                dfsStack.push(new State(v));
                dfs();
            }
        }
        return sccComp;
    }

    /**
     * function dfs
     **/
    public void dfs() {
        while (dfsStack.size() > 0) {
            State curState = dfsStack.pop();
            int v = curState.node;
            int min, w;
            if (curState.nodeToVisit == -1) {
                low[v] = preCount++;
                visited[v] = true;
                stack.push(v);
                min = low[v];
                curState.nodeToVisit++;
            } else {
                min = curState.min;
                w = graph[v].get(curState.nodeToVisit);
                if (low[w] < min) {
                    min = low[w];
                }
                curState.nodeToVisit++;
            }

            boolean dfsContinue = false;
            for (int i = curState.nodeToVisit; i < graph[v].size(); i++) {
                w = graph[v].get(i);
                if (!visited[w]) {
                    curState.min = min;
                    curState.nodeToVisit = i;
                    dfsStack.push(curState);
                    dfsStack.push(new State(w));
                    dfsContinue = true;
                    break;
                }
                if (low[w] < min) {
                    min = low[w];
                }
            }
            if (dfsContinue) continue;

            if (min < low[v]) {
                low[v] = min;
                continue;
            }
            List<Integer> component = new ArrayList<>();
            do {
                w = stack.pop();
                component.add(w);
                low[w] = V;
            } while (w != v);
            sccComp.add(component);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        int V = scan.nextInt();
        /** make graph **/
        List<Integer>[] g = new List[V];
        for (int i = 0; i < V; i++) {
            g[i] = new ArrayList<>();
        }
        int E = scan.nextInt();
        Set<Integer>[] links = new Set[V];
        for (int i = 0; i < V; i++) {
            links[i] = new HashSet<>();
        }
        /** all edges **/
        for (int i = 0; i < E; i++) {
            int x = scan.nextInt();
            int y = scan.nextInt();
            g[x].add(y);
            links[x].add(y);
            links[y].add(x);
        }


        D tarjan = new D();
        /** print all strongly connected components **/
        List<List<Integer>> scComponents = tarjan.getSCComponents(g);
        for (List<Integer> scc : scComponents) {
            if (scc.size() < 2) {
                System.out.println("NO");
                return;
            } else if (scc.size() >= 3) {
                for (int x : scc) {
                    if (links[x].size() < 2) {
                        System.out.println("NO");
                        return;
                    }
                }
            }
        }
        System.out.println("YES");
    }

    private static class State {
        int node;
        int nodeToVisit;
        int min;

        State(int node) {
            this.node = node;
            nodeToVisit = -1;
        }
    }
}

