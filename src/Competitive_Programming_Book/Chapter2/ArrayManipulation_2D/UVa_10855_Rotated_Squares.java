package Competitive_Programming_Book.Chapter2.ArrayManipulation_2D;

import java.util.*;
import java.io.*;
import java.lang.*;

public class UVa_10855_Rotated_Squares {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true) {
            int N = sc.nextInt();
            int n = sc.nextInt(); sc.nextLine();
            if (N == 0 && n == 0) return;

            char[][] outer = new char[N][N];
            char[][] inner = new char[n][n];

            for (int i = 0; i < N; i++) {
                String line = sc.nextLine();
                for (int j = 0; j < N; j++) {
                    outer[i][j] = line.charAt(j);
                }
            }

            for (int i = 0; i < n; i++) {
                String line = sc.nextLine();
                for (int j = 0; j < n; j++) {
                    inner[i][j] = line.charAt(j);
                }
            }

            int zero = 0;

            for (int i = 0; i <= N - n; i++) {
                for (int j = 0; j <= N - n; j++) {

                    boolean yes = true;

                    for (int k = 0; k < n; k++) {
                        for (int l = 0; l < n; l++) {
                            if (inner[k][l] != outer[i+k][j+l]) yes = false;
                        }
                    }

                    if (yes) zero++;
                    // topcorner at i,j
                }
            }

            int rotateOnce = 0;

            for (int i = 0; i <= N - n; i++) {
                for (int j = 0; j <= N - n; j++) {

                    boolean yes = true;

                    for (int k = 0; k < n; k++) {
                        for (int l = 0; l < n; l++) {
                            if (inner[k][l] != outer[i+l][j+(n-1)-k]) {
//                                if (yes) {
//                                    System.out.println(inner[k][l] + " vs " + outer[i-k + (n-1)][j+l]);
//                                    System.out.println(i + " "  + j);
//                                }
                                yes = false;
                            }
                        }
                    }

                    if (yes) rotateOnce++;
                    // topcorner at i,j
                }
            }

            int rotateTwice = 0;

            for (int i = 0; i <= N - n; i++) {
                for (int j = 0; j <= N - n; j++) {

                    boolean yes = true;

                    for (int k = 0; k < n; k++) {
                        for (int l = 0; l < n; l++) {
                            if (inner[k][l] != outer[i+(n-1) - k][j + (n-1) - l]) yes = false;
                        }
                    }

                    if (yes) rotateTwice++;
                    // topcorner at i,j
                }
            }

            int rotateThrice = 0;

            for (int i = 0; i <= N - n; i++) {
                for (int j = 0; j <= N - n; j++) {

                    boolean yes = true;

                    for (int k = 0; k < n; k++) {
                        for (int l = 0; l < n; l++) {
                            if (inner[k][l] != outer[i+ (n-1) - l][j+k]) yes = false;
                        }
                    }

                    if (yes) rotateThrice++;
                    // topcorner at i,j
                }
            }

            System.out.println(String.format("%d %d %d %d", zero, rotateOnce, rotateTwice, rotateThrice));
        }
    }

}
