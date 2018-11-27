package ACM_Contests.SEERC.SEERC_2018;

import java.math.BigInteger;
import java.util.*;
import java.lang.*;

public class B {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt(); int B = sc.nextInt(); int C = sc.nextInt(); long N = sc.nextLong();

        int coef = 0;
        if (A == B && B == C) coef = 1;
        else if (A == B || B == C || C == A) coef = 3;
        else coef = 6;

        BigInteger result = BigInteger.ONE;
        if (N % 2 == 0) {
            long k = N / 2;

            result = result.multiply(BigInteger.valueOf(k));
            result = result.multiply(BigInteger.valueOf(k-1));
            result = result.multiply(BigInteger.valueOf(k+4));
            result = result.divide(BigInteger.valueOf(3));
        } else {
            long k = N / 2;

            result = result.multiply(BigInteger.valueOf(k));
            result = result.multiply(BigInteger.valueOf(k+1));
            result = result.multiply(BigInteger.valueOf(2*k+1));
            result = result.divide(BigInteger.valueOf(6));
        }
        result = result.multiply(BigInteger.valueOf(coef));

        BigInteger modulo = BigInteger.valueOf(1L << 32);
        modulo = modulo.multiply(modulo);
        System.out.println(result.mod(modulo));
    }

}
