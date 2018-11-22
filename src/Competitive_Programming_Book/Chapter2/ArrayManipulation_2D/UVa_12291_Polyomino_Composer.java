package Competitive_Programming_Book.Chapter2.ArrayManipulation_2D;

import java.util.*;
import java.lang.*;
import java.util.*;

public class UVa_12291_Polyomino_Composer {

    // Brute force solution, time m^3 * n^3?
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        nextTest:
        while(true) {
            int n = sc.nextInt(); int m = sc.nextInt();
            if (n == 0 && m == 0) return;
            sc.nextLine();

            int[][] large = new int[n][n];
            int rowNMax = 0; int rowNMin = n-1;
            int colNMax = 0; int colNMin = n-1;

            for (int i = 0; i < n; i++) {
                String next = sc.nextLine();
                for (int j = 0; j < next.length(); j++) {
                    if (next.charAt(j) == '*') {
                        large[i][j] = 1;
                        rowNMax = Math.max(i, rowNMax);
                        rowNMin = Math.min(i, rowNMin);
                        colNMax = Math.max(j, colNMax);
                        colNMin = Math.min(j, colNMin);
                    }
                }
            }

            int[][] largeReal = new int[rowNMax - rowNMin + 1][colNMax - colNMin + 1];
            for (int i = 0; i <= rowNMax - rowNMin; i++) {
                for (int j = 0; j <= colNMax - colNMin; j++) {
                    largeReal[i][j] = large[i + rowNMin][j + colNMin];
                }
            }

            int[][] small = new int[m][m];
            int rowMMax = 0; int rowMMin = m-1;
            int colMMax = 0; int colMMin = m-1;

            for (int i = 0; i < m; i++) {
                String next = sc.nextLine();
                for (int j = 0; j < next.length(); j++) {
                    if (next.charAt(j) == '*') {
                        small[i][j] = 1;
                        rowMMax = Math.max(i, rowMMax);
                        rowMMin = Math.min(i, rowMMin);
                        colMMax = Math.max(j, colMMax);
                        colMMin = Math.min(j, colMMin);
                    }
                }
            }

            int[][] smallReal = new int[rowMMax - rowMMin + 1][colMMax - colMMin + 1];
            for (int i = 0; i <= rowMMax - rowMMin; i++) {
                for (int j = 0; j <= colMMax - colMMin; j++) {
                    smallReal[i][j] = small[i + rowMMin][j + colMMin];
                }
            }

            int large_row = rowNMax - rowNMin + 1;
            int large_col = colNMax - colNMin + 1;

            int small_row = rowMMax - rowMMin + 1;
            int small_col = colMMax - colMMin + 1;

//            System.out.println(large_row + " " + large_col + " " + small_col + " " + small_row);

            for (int i_0 = 0; i_0 <= large_row - small_row; i_0++) {
                for (int j_0 = 0; j_0 <= large_col - small_col; j_0++) {

                    for (int i_1 = 0; i_1 <= large_row - small_row; i_1++) {
                        for (int j_1 = 0; j_1 <= large_col - small_col; j_1++) {
                            int[][] cmp = new int[large_row][large_col];

                            for (int i = 0; i < small_row; i++) {
                                for (int j = 0; j < small_col; j++) {
                                    cmp[i + i_0][j + j_0] += smallReal[i][j];
                                    cmp[i + i_1][j + j_1] += smallReal[i][j];
                                }
                            }

                            if (Arrays.deepEquals(cmp, largeReal)) {
                                System.out.println(1);
                                continue nextTest;
                            }

                        }
                    }

                }
            }
            // i 0 j 1 + i 1 j 0

            System.out.println(0);
        }
    }

}
