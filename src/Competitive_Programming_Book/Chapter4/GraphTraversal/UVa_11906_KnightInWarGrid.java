package Competitive_Programming_Book.Chapter4.GraphTraversal;

import java.io.*;
import java.lang.*;
import java.util.*;

public class UVa_11906_KnightInWarGrid {

    // water fields are -1, visitables are 1, unvisitables are 0 at the end

    static int[][] grid;

    static int[] dirX, dirY;

    static int R,C, D;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int tests = sc.nextInt();
        for (int cases = 1; cases <= tests; cases ++) {

            R = sc.nextInt(); C = sc.nextInt(); int M = sc.nextInt(); int N = sc.nextInt();
            int W = sc.nextInt();

            grid = new int[R][C];
            for (int i = 1; i <= W; i++) grid[sc.nextInt()][sc.nextInt()] = -1;

            if (M == 0) {
                dirX = new int[]{-N, 0, 0, N};
                dirY = new int[]{0, -N, N, 0}; D = 4;
            } else if (N == 0) {
                dirX = new int[]{-M, 0, 0, M};
                dirY = new int[]{0, -M, M, 0}; D = 4;
            } else if (M == N) {
                dirX = new int[]{-M, M, -M, M};
                dirY = new int[]{-M, -M, M, M}; D = 4;
            } else {
                    dirX = new int[]{-M, -M, -N, -N, M, M, N, N};
                    dirY = new int[]{N, -N, M, -M, N, -N, M, -M}; D = 8;
            }
            dfs(0, 0);

//            for (int i = 0; i < R; i++) {
//                System.out.println(Arrays.toString(grid[i]));
//            }


            int even = 0, odd = 0;
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (grid[i][j] != 1) continue;

//                    System.out.println(i + " si " + j);
//                    System.out.println();

                    int which = 0;
                    for (int d = 0; d < D; d++) {
                        int nextX = i + dirX[d];
                        int nextY = j + dirY[d];
                        if (nextX < 0 || nextX >= R || nextY < 0 || nextY >= C) continue;

                        if (grid[nextX][nextY] == 1) which++;
//                        System.out.println(nextX + " si " + nextY);

                    }

                    if (which % 2 == 0) even++; else odd++;
                }
            }

            System.out.println(String.format("Case %d: %d %d", cases, even, odd));
        }
    }

    private static void dfs(int x, int y) {
        grid[x][y] = 1;
//        System.out.println(x + " PULa " + y);
        for (int d = 0; d < D; d++) {
            int nextX = x + dirX[d];
            int nextY = y + dirY[d];

//            System.out.println(nextX + " PULa " + nextY);


            if (nextX < 0 || nextX >= R || nextY < 0 || nextY >= C) continue;

            if (grid[nextX][nextY] != 0) continue;
            dfs(nextX, nextY);
        }

    }

}
