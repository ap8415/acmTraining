package Competitive_Programming_Book.Chapter4.FloodFill;

import java.io.*;
import java.util.*;
import java.lang.*;

public class UVa_469_WetlandsOfFlorida {

    static class Pair {
        int first, second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    static char[][] grid;

    static int dr[] = {1,1,0,-1,-1,-1, 0, 1}; // trick to explore an implicit 2D grid
    static int dc[] = {0,1,1, 1, 0,-1,-1,-1};
    
    static int R,C;

    public static void main(String[] args) throws FileNotFoundException {
        FileOutputStream testOut = new FileOutputStream("test.out");
        PrintStream out = new PrintStream(testOut);
        System.setOut(out);

        InputStream testIn = new FileInputStream("test.in");
        System.setIn(testIn);

        Scanner sc = new Scanner(System.in);

        int tests = sc.nextInt();
        sc.nextLine(); sc.nextLine();

        for (int cases = 1; cases <= tests; cases++) {
            grid = new char[2000][2000];

            List<Pair> queries = new ArrayList<>();

            boolean doneReading = false;
            int i = 0;
            R=0; C = 0;
            while(!doneReading) {
                String input = sc.nextLine();
                if ("LW".contains(""+input.charAt(0))) {
                    for (int j = 0; j < input.length(); j++) {
                        grid[i][j] = input.charAt(j);
                    }
                    R++;
                    C = Math.max(C,input.length());
                } else {
                    doneReading = true;
                    queries.add(parseQueries(input));
                }
                i++;
            }

            doneReading = false;
            while (!doneReading && sc.hasNext()) {
                String input = sc.nextLine();
                if (!input.equals("")) {
                   queries.add(parseQueries(input));
                } else doneReading = true;
            }

            for (Pair query : queries) {
                System.out.println(floodFill(query.first, query.second));
                recolor(query.first, query.second);
            }

            if (cases < tests) System.out.println();

        }
    }

    private static int floodFill(Integer r, Integer c) {
        if (r < 0 || c < 0 || r >= R || c >= C) return 0;
        if (grid[r][c] != 'W') return 0;
        int ans = 1;
        grid[r][c] = '.';
        for (int d = 0; d < 8; d++) {
            ans += floodFill(r + dr[d], c + dc[d]);
        }
        return ans;
    }

    private static void recolor(Integer r, Integer c) {
        if (r < 0 || c < 0 || r >= R || c >= C) return;
        if (grid[r][c] == '.') {
            grid[r][c] = 'W';
        } else {
            return;
        }
        for (int d = 0; d < 8; d++) {
            recolor(r + dr[d], c + dc[d]);
        }
    }

    public static Pair parseQueries(String input) {
        StringTokenizer tok = new StringTokenizer(input);
        return new Pair(Integer.parseInt(tok.nextToken()) -1, Integer.parseInt(tok.nextToken()) -1);
    }
}
