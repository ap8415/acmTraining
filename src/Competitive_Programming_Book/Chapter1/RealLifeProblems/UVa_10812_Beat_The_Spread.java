package Competitive_Programming_Book.Chapter1.RealLifeProblems;

import java.io.*;
import java.lang.*;
import java.util.*;

public class UVa_10812_Beat_The_Spread {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        for (int j = 1; j <= n; j++) {
            int s = sc.nextInt(); int d = sc.nextInt();
            int first = (s + d) / 2;
            int second = (s - d) / 2;
            if (first+second != s) {
                System.out.println("impossible"); continue;
            }
            if (Math.abs(first - second) != d) {
                System.out.println("impossible"); continue;
            }
            if (first < 0 || second < 0) {
                System.out.println("impossible"); continue;
            }

            System.out.println(Math.max(first, second) + " " + Math.min(first, second));
        }
    }

}
