package ACM_Contests.SWERC.SWERC_2015;

import java.lang.*;
import java.util.*;

public class E {

    static long MODULO = 2147483647L;
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        s = new int[N+1];
        for (int i = 0; i <= N; i++) s[i] = sc.nextInt();
        dp = new long[N+1][N+2]; // prevheads from 1 ... N+1

        for (int i = 0; i < N+1; i++) Arrays.fill(dp[i], -1);

        System.out.println(f(1, s[0]));
//        System.out.println(Arrays.deepToString(dp));
    }

    static long f(int n, int p) {
        if (n >= N) return 1;
        if (dp[n][p] == -1) {
            dp[n][p] =
                    f(n+1, p) * overlap(p, s[n], s[n+1], true) // segm p .. s[n+1]
                            + f(n+1, s[n]) * overlap(p, s[n], s[n+1], false); // s[n]...s[n+1]
            dp[n][p] %= MODULO;
        }
        return dp[n][p];

    }

    static int overlap(int x1, int x2, int y, boolean xd) {
        if (xd) {
          return x1 < x2 ? (y > x1 ? 1 : 0) : (y < x1 ? 1 : 0);
        } else {
            return overlap(x2, x1, y, true);
        }
    }

    static long[][] dp;
    static int[] s;

}
