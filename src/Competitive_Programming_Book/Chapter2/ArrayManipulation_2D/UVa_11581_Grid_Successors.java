package Competitive_Programming_Book.Chapter2.ArrayManipulation_2D;

import java.util.*;
import java.lang.*;
import java.io.*;

public class UVa_11581_Grid_Successors {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int test = sc.nextInt(); sc.nextLine(); sc.nextLine();

        testCases:
        for (int cases = 1; cases <= test; cases++) {
            int[][] g = new int[5][5];
            for (int i = 1 ;i <= 3; i++) {
                String line = sc.nextLine();
                for (int j = 1; j <= 3; j++) {
                    g[i][j] = line.charAt(j-1) - '0';
                }
            }
            sc.nextLine();

            List<int[][]> iterations = new ArrayList<>();

            iterations.add(g);
            while (true) {
                g = f(g); int index = -2;
                for (int i =0; i < iterations.size(); i++) {
                    if (Arrays.deepEquals(g, iterations.get(i))) {
                        // then i-1 is the last non-duplicate
                        index = i-1; break;
                    }
                }
                if (index != -2) {
                    // found index
                    System.out.println(index);
                    continue testCases;
                } else {
                    iterations.add(g);
                }
            }

        }
    }


    public static int[][] f(int[][] g) {
        int[][] f_g = new int[5][5];

        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                f_g[i][j] = (g[i-1][j] +g[i+1][j] + g[i][j-1] + g[i][j+1]) % 2;
            }
        }

        return f_g;
    }

}
