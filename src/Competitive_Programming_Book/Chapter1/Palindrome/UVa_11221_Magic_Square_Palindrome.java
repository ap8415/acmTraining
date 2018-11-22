package Competitive_Programming_Book.Chapter1.Palindrome;

import java.util.*;
import java.io.*;
import java.lang.*;

public class UVa_11221_Magic_Square_Palindrome {

    static String BAD_OUTPUT = "No magic :(";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int tests = sc.nextInt(); sc.nextLine();

        for(int testCases = 1; testCases <= tests; testCases++) {
            System.out.println("Case #"  + testCases+":");
            String[] inputLines = sc.nextLine().split("[ ,.?!()]");
            int tableLength = Arrays.stream(inputLines).map(String::length).reduce(0, (x,y)->x+y);

            int q  = (int) Math.round(Math.sqrt(tableLength));
            if (q * q != tableLength) {
                System.out.println(BAD_OUTPUT);
                continue;
            }

            char[][] palindromeTable = new char[q][q];

            int ii = 0, jj = 0;

            for (String input : inputLines) {
                for (int k = 0; k < input.length(); k++) {
                    palindromeTable[ii][jj] = input.charAt(k);

                    jj++;
                    if (jj == q) {jj = 0; ii++;}
                }
            }

            StringBuilder s1 = new StringBuilder();

            for (int i = 0 ;i < q; i++) {
                for (int j = 0; j < q; j++) {
                    s1.append(palindromeTable[i][j]);
                }
            }
            StringBuilder s2 = new StringBuilder();

            for (int j = 0 ;j < q; j++) {
                for (int i = 0; i < q; i++) {
                    s2.append(palindromeTable[i][j]);
                }
            }

            if (!s2.toString().equals(s1.toString())) {
//                System.out.println(s2);
//                System.out.println(s1);
                System.out.println(BAD_OUTPUT); continue;
            }

            StringBuilder s3 = new StringBuilder();

            for (int i = q-1 ;i >= 0; i--) {
                for (int j = q-1; j >= 0; j--) {
                    s3.append(palindromeTable[i][j]);
                }
            }

            if (!s3.toString().equals(s1.toString())) {
//                System.out.println(s3);
//                System.out.println(s1);
                System.out.println(BAD_OUTPUT); continue;
            }

            StringBuilder s4 = new StringBuilder();

            for (int j = q-1 ;j >= 0; j--) {
                for (int i = q-1; i >= 0; i--) {
                    s4.append(palindromeTable[i][j]);
                }
            }

            if (!s4.toString().equals(s1.toString())) {
//                System.out.println(s4);
//                System.out.println(s1);
                System.out.println(BAD_OUTPUT); continue;
            }

            System.out.println(q);
        }
    }
}
