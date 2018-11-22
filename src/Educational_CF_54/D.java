package Educational_CF_54;

import java.util.*;
import java.io.*;
import java.lang.*;
public class D {

    static class Node implements Comparable<Node> {
        long weight = Long.MAX_VALUE;
        int index;
        Pair shortestPath = null;
        boolean visited = false;

        public Node(int index) {
            this.index = index;
        }

        @Override
        public int compareTo(Node o) {
            return (Long.compare(weight, o.weight) != 0 ? Long.compare(weight, o.weight) : index - o.index);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "weight=" + weight +
                    ", index=" + index +
                    ", shortestPath=" + shortestPath +
                    '}';
        }
    }

    static class Pair implements Comparable<Pair> {
        int from, to;

        public Pair(int from, int to) {
            this.from = from < to ? from : to;
            this.to = from < to ? to : from;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;
            return ((from == pair.from && to == pair.to )|| (from == pair.to && to == pair.from));
        }

        @Override
        public int hashCode() {
            return from + to;
        }

        @Override
        public int compareTo(Pair o) {
            return (this.equals(o)) ? 0 : (
                    from != o.from ? from - o.from : (
                            to - o.to
                    )
            );
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "from=" + from +
                    ", to=" + to +
                    '}';
        }
    }

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

    public static void main(String[] args) throws IOException {

        Reader.init(System.in);

        int n = Reader.nextInt(); int m = Reader.nextInt(); int k = Reader.nextInt();
        List<Integer>[] adjacencyList = new List[n];
        for (int i = 0; i < n; i++) adjacencyList[i] = new ArrayList<Integer>();

        Map<Pair, Integer> edgeMap = new HashMap<>();
        Map<Pair, Integer> weightMap = new HashMap<>();

        for (int i = 1; i <= m; i++) {
            int from = Reader.nextInt() - 1; int to = Reader.nextInt() - 1; int w = Reader.nextInt();
            adjacencyList[from].add(to); adjacencyList[to].add(from);
            edgeMap.put(new Pair(from ,to), i);
            weightMap.put(new Pair(from, to), w);
        }

//        System.out.println(weightMap);

        PriorityQueue<Node> dijk = new PriorityQueue<>();
        Node[] nodes = new Node[n];
        for (int i = 1 ;i < n; i++) {
            Node q = new Node(i);
            nodes[i] = q;
        }
        Node first = new Node(0); first.weight = 0;
        dijk.add(first); nodes[0] = first;

        int visitedTotal = n;

        while (visitedTotal > 0) {
            Node curr = dijk.poll();
//            System.out.println(curr);
            if (nodes[curr.index].visited) continue;
//            System.out.println(" HERE " + curr);

            for (int x : adjacencyList[curr.index]) {
                if (!nodes[x].visited) {
                    Pair edge = new Pair(x, curr.index);
                    long tentative = nodes[curr.index].weight + weightMap.get(edge);
                    if (tentative < nodes[x].weight) {
                        Node newNode = new Node(x);
                        newNode.weight = tentative;
                        newNode.shortestPath = edge;
                        nodes[x] = newNode;
                        dijk.add(newNode);
                    }
                }
            }
            nodes[curr.index].visited = true; visitedTotal--;
        }

        List<Integer>[] nextOptimal = new List[n];
        for (int i = 0; i < n; i++) {
            nextOptimal[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            int from = nodes[i].shortestPath.from == i
                    ? nodes[i].shortestPath.to :
                    nodes[i].shortestPath.from;
            nextOptimal[from].add(i);
        }

        StringBuilder output = new StringBuilder();
        List<Integer> possibleEdges = new LinkedList<>();

        int total = 0;

        Stack<Integer> it = new Stack<>(); it.push(0);
        while (total < k && !it.isEmpty()) {
            int curr = it.pop();
            for (int x : nextOptimal[curr]) {
                if (total < k) {
                    total++; output.append(edgeMap.get(new Pair(curr, x))).append(" ");
                } else {
                    break;
                }
                it.push(x);
            }
        }

        int e = Math.min(k, total);
        System.out.println(e);
        System.out.println(output);
    }

}