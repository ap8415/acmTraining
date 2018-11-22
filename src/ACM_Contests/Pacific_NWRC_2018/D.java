package ACM_Contests.Pacific_NWRC_2018;

import java.math.BigInteger;
import java.util.*;
import java.lang.*;

public class D {

    static int L;
    static int k;
    static int b;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        k = sc.nextInt();
        b = sc.nextInt();

        BigInteger result = BigInteger.ZERO;
        for (int i = 0; i < b; i++) {
            result = result.add(g(i));
        }

        System.out.println(result.mod(BigInteger.valueOf(1000000009)));
    }

    static BigInteger g(int i) {
        BigInteger result = BigInteger.ZERO;
        BigInteger pwr = BigInteger.valueOf(2).pow(b-i-1);

        BigInteger divded = pwr.divide(BigInteger.valueOf(k));
        int modol = pwr.mod(BigInteger.valueOf(k)).intValue();

        for (int j = 1; j <= k; j++) {
            result = result.add(f(i, j).multiply(divded));
        }

        for (int j = 1; j <= modol; j++) {
            result = result.add(f(i, j));
        }

        return result;
    }


    static BigInteger f(int i, int j) {

        BigInteger first = BigInteger.valueOf(2).pow(i+1).multiply(BigInteger.valueOf(j)).subtract(BigInteger.ONE);
        BigInteger second = BigInteger.valueOf(2).pow(i).multiply(BigInteger.valueOf(2 * j - 1));

        BigInteger firstDivided = first.divide(BigInteger.valueOf(k));
        BigInteger secondDivided = second.divide(BigInteger.valueOf(k)).add(
                second.mod(BigInteger.valueOf(k)).equals(BigInteger.ZERO) ? BigInteger.ZERO : BigInteger.ONE
        );

        return firstDivided.subtract(secondDivided).add(BigInteger.ONE);
    }
}
