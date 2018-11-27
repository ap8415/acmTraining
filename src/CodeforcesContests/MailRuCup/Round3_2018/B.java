package CodeforcesContests.MailRuCup.Round3_2018;

import java.io.*;
import java.util.*;
import java.lang.*;

public class B {

   static int[] memo;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); int m = sc.nextInt();
        // the question essentially asks how many answers we can find
        // for i^2 + j^2 divisible by m
//        memo = new int[m]; // array of count of residues
//        for (int i = 0; i < m; i++) {
//            memo[(i * i) % m]++;
//        }
        long total = 0; // we can store n^2 in long
        // n splits into n/m *m + n%m
        long bigSqTotal = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                bigSqTotal += ((i*i + j*j) % m == 0) ? 1 : 0;
            }
        }
        bigSqTotal *= (n/m);
        bigSqTotal *= (n/m); // big box
        total += bigSqTotal;

        long bigRectangleTotal = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 1; j <= n%m; j++) {
                bigRectangleTotal += ((i*i + j*j) % m == 0) ? 1 : 0;
            }
        }

        bigRectangleTotal *= (n/m);
        bigRectangleTotal *= 2;
        total += bigRectangleTotal;

        long smallSquareTotal = 0;
        for (int i = 1; i <= n%m; i++) {
            for (int j = 1; j <= n%m; j++) {
                smallSquareTotal += ((i*i + j*j) % m == 0) ? 1 : 0;
            }
        }

        total += smallSquareTotal;
        System.out.println(total);
    }

}
