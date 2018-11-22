package ACM_Contests.Pacific_NWRC_2018;

import java.util.*;
import java.lang.*;

public class H {

    static int[] primes;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int x = sc.nextInt();
        primes = new int[1200000];
        Arrays.fill(primes, 1);

        computePrimes(x);

        int steps = 0;
        outer:
        while (x >= 4) {
//            System.out.println(x);
            steps++;
            int p = x, q = 0;
            while (true) {
//                System.out.println(p);
                if (primes[p]==1 && primes[q]==1) {
                    x = p - q; continue outer;
                }
                p--; q++;
            }
        }

        System.out.println(steps);
    }

    private static void computePrimes(int x) {

        primes[0] = 0; primes[1] = 0;
        for (int i = 4; i <= x; i = i + 2) primes[i] = 0;
        for (int i = 6; i <= x; i = i + 3) primes[i] = 0;
        for (int i = 10; i <= x; i = i + 5) primes[i] = 0;
        for (int i = 14; i <= x; i = i + 7) primes[i] = 0;

        boolean isPrime = true;
        for (int i = 7; i <= x; i = i + 2) { // skip the evens.
            if (primes[i] == 0) continue;

            for (int j = 9; j <= (int) Math.sqrt(i); j+= 2) {
                if (isPrime && primes[j] == 1) {
                    if (i % j == 0) {
                        isPrime = false;
                    }
                }

            }
            if (!isPrime) primes[i] = 0;
            isPrime = true;
        }

    }

}
