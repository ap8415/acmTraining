package Competitive_Programming_Book.Chapter4.GraphTraversal;

import java.io.*;
import java.lang.*;
import java.util.*;

public class UVa_11831_StickerCollector {

    static char[][] grid;

    static int[] D = {3, 0, 1, 2};
    static int[] E = {1, 2, 3, 0};

    static int[] XF = {-1, 0, 1, 0};
    static int[] YF = {0, -1, 0, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int robotX = -1, robotY = -1;
        int orientation = -1; // can be 0,1,2,3

        while (sc.hasNext()) {
            int N = sc.nextInt(); int M = sc.nextInt(); int S = sc.nextInt();
            if (N == 0 && M == 0 && S == 0) return;

            grid = new char[N][M];

            for (int i = 0; i < N; i++) {
                String inp = sc.next();
                for (int j = 0; j < M; j++) {
                    char x = inp.charAt(j);
                    if (x == 'N') {
                        robotX = i; robotY = j; orientation = 0; x = '.';
                    } else if (x == 'L') {
                        robotX = i; robotY = j; orientation = 3;x = '.';
                    } else if (x == 'S') {
                        robotX = i; robotY = j; orientation = 2;x = '.';
                    } else if (x == 'O') {
                        robotX = i; robotY = j; orientation = 1;x = '.';
                    }
                    grid[i][j] = x;
                }
            }

            int ans = 0;

            String sequenceOfMoves = sc.next();
            for (int i = 0; i < sequenceOfMoves.length(); i++) {
                char move = sequenceOfMoves.charAt(i);
                if (move == 'D') {
                    orientation = D[orientation];
                } else if (move == 'E') {
                    orientation = E[orientation];
                } else {
//                    System.out.println(robotX + " PULA " + robotY);
                    int nextX = robotX + XF[orientation];
                    int nextY = robotY + YF[orientation];
                    if (!(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M)) {
                        if (grid[nextX][nextY] != '#') {
                            robotX = nextX; robotY = nextY;
                            if (grid[nextX][nextY] == '*') {
                                grid[nextX][nextY] = '.'; ans++;
                            }
                        }
                    }

                }
            }

            System.out.println(ans);

        }
    }

}
