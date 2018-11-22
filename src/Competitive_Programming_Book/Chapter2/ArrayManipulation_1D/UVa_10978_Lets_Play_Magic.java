package Competitive_Programming_Book.Chapter2.ArrayManipulation_1D;

import java.io.*;
import java.lang.*;
import java.util.*;

public class UVa_10978_Lets_Play_Magic {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int n = sc.nextInt();
            if (n == 0 ) return;

            String[] vals = new String[n];
            int currPos = -1;
            for (int i = 0; i < n; i++) {
                String card = sc.next();
                String word = sc.next();
                int len = word.length();
                while (len > 0) {
                    currPos++;
                    while (vals[currPos % n] != null) currPos++;
                    len--;
                }
                vals[currPos % n] = card;
            }

            for (int i = 0; i < n - 1; i++) System.out.print(vals[i] + " ");
            System.out.println(vals[n-1]);
        }

    }

}
