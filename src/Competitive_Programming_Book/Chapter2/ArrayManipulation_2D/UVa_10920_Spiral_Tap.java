package Competitive_Programming_Book.Chapter2.ArrayManipulation_2D;

import java.io.*;
import java.lang.*;
import java.util.*;

public class UVa_10920_Spiral_Tap {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true) {
            int sz = sc.nextInt(); int p = sc.nextInt();

            if (sz == 0 && p == 0)  return;

            int posI = (sz + 1) / 2; int posJ = posI;

            p--;
            int cnt = 1;
            while (p > 0) {
                // up cnt
                if (p >= cnt) {
                    posI += cnt; p -= cnt;
                } else {
                    posI += p; p = 0;
                }

                if (p == 0) continue;
                // left cnt

                if (p >= cnt) {
                    posJ -= cnt; p -= cnt;
                } else {
                    posJ -= p; p = 0;
                }

                if (p == 0) continue;
                // down cnt+1
                cnt++;

                if (p >= cnt) {
                    posI -= cnt; p -= cnt;
                } else {
                    posI -= p; p = 0;
                }

                if (p == 0) continue;
                // right cnt+1

                if (p >= cnt) {
                    posJ += cnt; p -= cnt;
                } else {
                    posJ += p; p = 0;
                }

                cnt++;
            }

            System.out.println(String.format("Line = %d, column = %d.", posI, posJ));
        }
    }

}
