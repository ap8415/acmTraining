package Competitive_Programming_Book.Chapter2.ArrayManipulation_1D;

import java.io.*;
import java.util.*;
import java.lang.*;

public class UVa_12150_Pole_Position {

    public static void main(String[] args) {
        Scanner sc=  new Scanner(System.in);

        while (true) {
            int n = sc.nextInt();
            if (n ==0) return;

            boolean valid = true;

            int[] grid = new int[n];

            for (int i = 1; i <= n; i++) {
                int c = sc.nextInt(); int delta = sc.nextInt();
                int initialPosition = i + delta;
                if (initialPosition > n || initialPosition < 1) {
                    valid = false;
                } else {
                    if (grid[initialPosition - 1] != 0) {
                        valid = false;
                    } else {
                        grid[initialPosition - 1] = c;
                    }
                }
            }

            if (!valid) {
                System.out.println(-1);
            } else {
                for (int i = 0; i < n-1; i++) System.out.print(grid[i] + " ");
                System.out.print(grid[n-1]);
                System.out.println();
            }

        }
    }

}
