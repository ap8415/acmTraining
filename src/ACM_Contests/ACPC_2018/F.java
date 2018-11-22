package ACM_Contests.ACPC_2018;

import java.io.*;
import java.lang.*;
import java.math.BigInteger;
import java.util.*;

public class F {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("fetiera.in"));
        int tests = sc.nextInt();
        for (int i = 1; i <=tests; i++) solve(sc);

    }

    static double[][][] dp;
    static int k, n;

    private static void solve(Scanner sc) {
        n = sc.nextInt(); k = sc.nextInt();
        dp = new double[n][n][2]; // for each of the nxn cells, get the probabilities wrt k
        int[][] semsema = new int[n][n];
        double expectedValue = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int nextSemsema = sc.nextInt();
                computeDp(i, j);
                expectedValue += dp[i][j][0] * nextSemsema + dp[i][j][1] * (1 - nextSemsema);
            }
        }
        System.out.println(expectedValue);

    }

    private static void computeDp(int i, int j) {
        long containing = 4 * (long)(i+1) * (long)(n - i) * (long)(j+1) * (long)(n - j);
        long total = (long)n * (long)n * (long)(n + 1) * (long)(n + 1);
        double p = (double) containing / (double) total;

        double[][] matrix = new double[2][2];
        matrix[0][0] = 1-p;
        matrix[0][1] = p;
        matrix[1][0] = p;
        matrix[1][1] = 1-p;
        double[][] res = fast_mul(matrix, k);
        dp[i][j][0] = res[0][0];
        dp[i][j][1] = res[0][1];
    }

    private static double[][] fast_mul(double[][] matrix, int k) {
        if (k == 0) {
            double[][] res = new double[2][2]; res[0][0] = 1; res[0][1] = 0; return res;
        }
        if (k == 1) return matrix;
        double[][] rec = fast_mul(matrix, k / 2);
        if (k % 2 == 0) {
            return mul(rec, rec);
        } else {
            return mul(matrix, mul(rec, rec));
        }
    }

    private static double[][] mul(double[][] m1, double[][] m2) {
        double[][] m3 = new double[2][2];
        m3[0][0] = m1[0][0] * m2[0][0] + m1[0][1] * m2[1][0];
        m3[0][1] = m1[0][0] * m2[0][1] + m1[0][1] * m2[1][1];
        m3[1][0] = m1[1][0] * m2[0][0] + m1[1][1] * m2[1][0];
        m3[1][1] = m1[1][0] * m2[0][1] + m1[1][1] * m2[1][1];
        return m3;
    }

}
