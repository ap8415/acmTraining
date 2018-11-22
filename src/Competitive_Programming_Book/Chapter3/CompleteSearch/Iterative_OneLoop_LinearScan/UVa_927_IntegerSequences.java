package Competitive_Programming_Book.Chapter3.CompleteSearch.Iterative_OneLoop_LinearScan;
import java.math.BigInteger;
import java.util.*;
import java.lang.*;
import java.io.*;

public class UVa_927_IntegerSequences {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int c = sc.nextInt();
        for (int cases = 1; cases <= c; cases++) {
            int def = sc.nextInt();
            int[] coefs = new int[def+1];
            for (int i = 0; i <= def; i++) coefs[i] = sc.nextInt();
            int d = sc.nextInt();
            int k = sc.nextInt();

            int currD = d;
            while (k > 0) {
                k = k - currD;
                if (k > 0) currD += d;
            }

            BigInteger n = BigInteger.valueOf(currD / d);
            BigInteger x = BigInteger.ZERO;
            BigInteger power = BigInteger.ONE;

            for (int i = 0; i <= def; i++) {
                x = x.add(BigInteger.valueOf(coefs[i]).multiply(power));
                power = power.multiply(n);
            }

            System.out.println(x);
        }
    }

}
