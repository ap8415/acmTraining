package Competitive_Programming_Book.Chapter4.TopologicalSort;

import java.util.*;
import java.io.*;
import java.lang.*;

public class UVa_872_Ordering {


    static int[] incoming;

    static List<Integer>[] directedAdjList;
    static int[] visited;
    static int[] tempVisited;
    static Map<Integer, Character> backToNames = new HashMap<>();


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int tests = sc.nextInt();sc.nextLine();
        for (int cases = 1; cases <= tests; cases++) {
            Map<Character, Integer> variableMap = new TreeMap<>();
            sc.nextLine();
            String[] variables = sc.nextLine().split(" "); int varIndex = 0;
            for (String var : variables) {
                variableMap.put(var.charAt(0), varIndex);
                backToNames.put(varIndex, var.charAt(0));
                varIndex++;
            }
            String[] constraints = sc.nextLine().split(" ");
            incoming = new int[varIndex];
            directedAdjList = new List[varIndex];
            for (int i = 0; i < varIndex; i++) directedAdjList[i] = new ArrayList<>();
            for (String constraint : constraints) {
                char from = constraint.charAt(0);
                char to = constraint.charAt(2);
                incoming[variableMap.get(to)]++;
                directedAdjList[variableMap.get(from)].add(variableMap.get(to));
            }
            
            visited = new int[varIndex];
            tempVisited = new int[varIndex];
            
            boolean topoSort = true;
            
            for (int i = 0; i < varIndex; i++) {
                if (visited[i] == 0) topoSort &= checkAcyclic(i);
            }

            if (!topoSort) {
                System.out.println("NO");
            } else {
                Set<Integer> noIncoming = new HashSet<>();
                for (int i = 0; i < varIndex; i++) {
                    if (incoming[i] == 0) noIncoming.add(i);
                }
                back(noIncoming, new ArrayList<>());
            }
            if (cases < tests) System.out.println();
        }
    }
    private static void back(Set<Integer> noIncoming, List<Integer> order) {
        if (noIncoming.isEmpty()) {
            for (int t = 0; t < order.size() - 1; t++) {
                System.out.print(backToNames.get(order.get(t)) + " ");
            }
            System.out.print(backToNames.get(order.get(order.size() - 1)));
            System.out.println();
        }

        for (int i : noIncoming) {
            Set<Integer> backCopy = new HashSet<>();
            backCopy.addAll(noIncoming);
            backCopy.remove(i);
            for (int j : directedAdjList[i]) {
                incoming[j] --;
                if (incoming[j] == 0) backCopy.add(j);
            }
            List<Integer> orderCopy = new ArrayList<>();
            orderCopy.addAll(order); orderCopy.add(i);
            back(backCopy, orderCopy);
            for (int j : directedAdjList[i]) {
                incoming[j] ++;
            }
        }
    }

    private static boolean checkAcyclic(int i) {
        if (visited[i] == 1) return true;
        if (tempVisited[i] == 1) return false;
        boolean result = true;
        tempVisited[i] = 1;
        for (int j: directedAdjList[i]) result &= checkAcyclic(j);
        tempVisited[i] = 0; visited[i]  =1;
        return result;
    }

}
