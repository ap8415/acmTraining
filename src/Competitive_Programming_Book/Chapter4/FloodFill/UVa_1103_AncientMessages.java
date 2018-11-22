package Competitive_Programming_Book.Chapter4.FloodFill;

import java.util.*;
import java.lang.*;

public class UVa_1103_AncientMessages {

    static int[][] grid;
    static int H, W;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int cases = 0;

        while (sc.hasNext()) {
            cases++;
            H = sc.nextInt(); W = sc.nextInt();
            if (H == 0 && W == 0) return;
            sc.nextLine();

            grid = new int[H][4 * W];



            for (int i = 0; i < H; i++) {
                String input = sc.nextLine();
                for (int j = 0; j < W; j++) {
                    int x = convert(input.charAt(j));
                    for (int k = 0; k <= 3; k++) grid[i][4*j + k] = (x >> 3-k) % 2;
                }
            }
            W = 4 * W; // replace theoretical width with real width of grid

            for (int j = 0; j < W; j++) {
                if (grid[0][j] == 0) {
                    floodfill(0, j, 0, 2);
                }
            }

            for (int i = 1; i < H - 1; i++) {
                if (grid[i][0] == 0) {
                    floodfill(i, 0, 0, 2);
                }
                if (grid[i][W-1] == 0) {
                    floodfill(i, W-1, 0, 2);
                }
            }

            for (int j = 0; j < W; j++) {
                if (grid[H-1][j] == 0) {
                    floodfill(H-1, j, 0, 2);
                }
            }

//            for (int i = 0; i < H; i++) System.out.println(Arrays.toString(grid[i]));

            // Otherwise outer area is all 1's which is fine for our purposes.

            List<String> outputs = new ArrayList<>();

            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (grid[i][j] == 1) {

                        char output = intToHierogliph(floodfillModified(i, j));
//                        System.out.println(i + " PULA "  + j + " PULAMARE " + output);
                        outputs.add(""+output);
                    }
                }
            }

            Collections.sort(outputs);

            System.out.println(String.format("Case %d: %s", cases,
                    outputs.stream().reduce("", (x, y) -> x + y)));


        }
    }

   static int dr[] = {1,1,0,-1,-1,-1, 0, 1}; // trick to explore an implicit 2D grid
    static int dc[] = {0,1,1, 1, 0,-1,-1,-1}; // S,SE,E,NE,N,NW,W,SW neighbors

    static class Pair {
        int r, c;

        public Pair(int first, int second) {
            this.r = first;
            this.c = second;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }

    static Queue<Pair> bfsQueue = new LinkedList<>();

    static void floodfill(int r, int c, int c1, int c2) { // returns the size of CC

        bfsQueue.offer(new Pair(r, c));
        grid[r][c] = c2; // now recolors vertex (r, c) to c2 to avoid cycling!

        while (!bfsQueue.isEmpty()) {
            Pair curr = bfsQueue.poll();

            for (int d = 0; d < 8; d++)
            {
                Pair curr2 = new Pair(curr.r + dr[d], curr.c + dc[d]);
                if (curr2.r < 0 || curr2.r >= H || curr2.c < 0 || curr2.c >= W) continue; // outside grid
                if (grid[curr2.r][curr2.c] != c1) continue; // does not have color c1
                grid[curr2.r][curr2.c] = c2; // now recolors vertex (r, c) to c2 to avoid cycling!
                bfsQueue.offer(curr2);

            }
        }
        return; // the code is neat due to dr[] and dc[]
    }

    static int floodfillModified(int r, int c) { // returns the size of CC
        if (r < 0 || r >= H || c < 0 || c >= W) return 0; // outside grid

        int ans = 0;


        if (grid[r][c] == 1) {
            grid[r][c] = 2;
            for (int d = 0; d < 8; d++)
                ans += floodfillModified(r + dr[d], c + dc[d]);
        } else if (grid[r][c] == 0) {
            // do regular floodfill
            floodfill(r, c, 0, 2);
            ans++;
        }

        return ans;
    }

    static int convert(char x) {
        return Integer.parseInt(x+ "", 16);
    }

    static char intToHierogliph(int x) {
        if (x == 0) return 'W';
        else if (x == 1) return 'A';
        else if (x == 2) return 'K';
        else if (x == 3) return 'J';
        else if (x == 4) return 'S';
        else if (x == 5) return 'D';
        return ' ';
    }

}
