package cf_round_520;

import java.util.*;
import java.io.*;
import java.lang.*;
import java.util.Arrays;

public class D {

    static int[] primes;
    static int[] spf;

    static long[] dp;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sieve(n);

        dp = new long[n + 1];

        for (int i = 2; i <= n; i++){
            if (primes[i] == 0) {
                dp[i] = i + 1;
            }
            else {
                int j = i;
                while (j % spf[i] == 0) {
                    j = j / spf[i];
                }
                if (j == 1) {
                    dp[i] = dp[i/spf[i]] * spf[i] + 1;
                } else {
                    dp[i] = dp[j] * dp[i/j];
                }
            }
        }

        int ans = 0;
        for (int i = n; i >= 2; i--) {
            if (primes[i] != 0) {
                ans += 4 * (dp[i] - i - 1);
            }
        }

        System.out.println(ans);
    }

    static void sieve(int n) {
        primes = new int[n+1];
        spf = new int[n+1];
        int leastUnmarked = 2;
        while (leastUnmarked <= n) {
            for (int i = 2; i <= n / leastUnmarked; i++) {
                if (primes[i * leastUnmarked] == 0) {
                    primes[i * leastUnmarked] = -1; spf[i * leastUnmarked] = leastUnmarked;
                }
            }
            leastUnmarked++;
            while (leastUnmarked <= n && primes[leastUnmarked] == -1) leastUnmarked++;
        }
    }

}
