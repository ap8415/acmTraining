package Competitive_Programming_Book.Chapter2.ArrayManipulation_2D;

import java.util.*;
import java.io.*;
import java.lang.*;

public class UVa_12187_Brothers {

    public static void main(String[] args) throws FileNotFoundException {
                Scanner sc = new Scanner(System.in);

        while(true) {
            int heirs = sc.nextInt(); int rows = sc.nextInt();
            int columns = sc.nextInt(); int battles = sc.nextInt();

            if (heirs == 0 && rows == 0 && columns == 0 && battles == 0) return;

            int[][] current = new int[rows][columns];
            int[][] next = new int[rows][columns];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    current[i][j] = sc.nextInt();
                }
            }

            for (int round = 1; round <= battles; round++) {
                for (int j = 0; j < rows; j++) Arrays.fill(next[j], -1);

                for (int i = 0; i < rows - 1; i++) {
                    for (int j = 0; j < columns; j++) {
                        // current[i][j] vs current[i+1][j]
                        if ((current[i][j] + 1) % heirs == (current[i+1][j] % heirs)) {
                            next[i+1][j] = current[i][j];
                        }

                        // current[i][j] vs current[i+1][j]
                        if ((current[i][j]% heirs) == (current[i+1][j] + 1) % heirs) {
                            next[i][j] = current[i+1][j];
                        }
                    }
                }

                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns - 1; j++) {
                        // current[i][j+1] vs current[i][j]
                        if ((current[i][j] + 1) % heirs == (current[i][j+1] % heirs)) {
                            next[i][j+1] = current[i][j];
                        }

                        // current[i][j] vs current[i+1][j]
                        if ((current[i][j]% heirs) == (current[i][j+1] + 1) % heirs) {
                            next[i][j] = current[i][j+1];
                        }
                    }
                }

                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        // current[i][j+1] vs current[i][j]
                        if (next[i][j] == -1) next[i][j] = current[i][j];
                    }
                }

                current = next; next = new int[rows][columns];

            }

            StringBuilder answer = new StringBuilder();

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    answer.append(current[i][j]);
                    if (j < columns - 1) answer.append(" ");
                }
                answer.append('\n');
            }

            System.out.print(answer.toString());


        }
    }

}
