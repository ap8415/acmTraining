package Competitive_Programming_Book.Chapter1.Palindrome;

import java.util.*;
import java.lang.*;

public class UVa_10018_Reverse_And_Add {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); for (int i = 1 ; i <= n ;i++) {
            long number = sc.nextInt();
            int iterations = 0;
            while (reverse(number) != number) {
                number += reverse(number);
                iterations++;
            }
            System.out.println(iterations + " " + number);
        }
    }

    static long reverse(long x) {
        if (x < 10) return x;
        return x % 10 * (long)Math.pow(10, digits(x/10)) + reverse(x / 10);
    }

    static int digits(long x) {
        if (x < 10) return 1;
        return  1 + digits(x / 10);
    }
}
