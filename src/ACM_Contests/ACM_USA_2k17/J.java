package ACM_Contests.ACM_USA_2k17;

import java.util.*;

public class J {

    public static void main(String[] args) {
        Scanner pl = new Scanner(System.in);
        int n = pl.nextInt();
        int m = pl.nextInt();

        int[][] mines = new int[2][n];

        int[][] paths = new int[n][n];

        int stepsUntilZero = 0;

        for (int i = 0; i < n; i++) {
            mines[0][i] = pl.nextInt();
            mines[1][i] = pl.nextInt();
            int steps = ((mines[0][i] % mines[1][i] == 0) ? 0 : 1) + mines[0][i] / mines[1][i];
            stepsUntilZero = Math.max(steps, stepsUntilZero);
        }

        if (m == 0) {System.out.println(mines[0][0]); return;}

        for (int i = 0; i < m; i++) {
            int a = pl.nextInt(); int b = pl.nextInt();
            int ddd = pl.nextInt();
            paths[a -1 ][b-1] = ddd; paths[b-1][a-1] = ddd;
        }

        int[][] dp = new int[150 + stepsUntilZero][n];


        // found DP steps.
        // do dp
        // if doesnt work, make paths a hashmap instead
        for (int i = 0; i < stepsUntilZero; i++) {
            int rev = stepsUntilZero - i;

            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (paths[j][k] != 0) {
                        dp[i+paths[j][k]][k] = Math.max(
                                dp[i+paths[j][k]][k],
                                dp[i][j] + Math.max(mines[0][k] - (rev - paths[j][k]) * mines[1][k], 0));
                    }
                }

                dp[i][j] = Math.max(dp[i][j], Math.max(mines[0][j] - rev * mines[1][j], 0));
            }



        }


        for (int j = 0; j < n; j++) {
            dp[stepsUntilZero][j] = Math.max(dp[stepsUntilZero][j], Math.max(mines[0][j], 0));
        }

        System.out.println(dp[stepsUntilZero][0]);


    }

}
