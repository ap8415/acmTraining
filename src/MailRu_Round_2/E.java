package MailRu_Round_2;

import java.util.*;
import java.io.*;
import java.lang.*;

public class E {

    static int n, s, m, k;
    static int[] left, values, valuesSorted;

    static int BIG;

    static int[] next;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt(); s = sc.nextInt(); m = sc.nextInt(); k = sc.nextInt();

        left = new int[n];
        BIG = 2 * n;

        Arrays.fill(left, BIG);

        values = new int[n];
        valuesSorted = new int[n];
        for (int i = 0; i < n; i++) {
            int val = sc.nextInt();
            values[i] = val; valuesSorted[i] = val;
        }

        Arrays.sort(valuesSorted);

        for (int i = 0; i < s; i++) {
            int leftSeg = sc.nextInt() - 1; int rightSeg = sc.nextInt() - 1;
            left[rightSeg] = Math.min(leftSeg, left[rightSeg]);
        }

        System.out.println(binarySearch(k, n));
    }

    private static int binarySearch(int lowerBound, int upperBound) {

//        System.out.println("BINARY CU " + lowerBound + " SI " + upperBound);
        int middle = (lowerBound + upperBound) / 2;
        if (lowerBound == upperBound) {
            if (possible(middle)) {
                return valuesSorted[lowerBound - 1];
            } else {
                return -1;
            }
        }

        if (possible(middle)) {
//            System.out.println("POSIBIL " + middle);
            return binarySearch(lowerBound, middle);
        } else {
            return binarySearch(middle+1, upperBound);
        }
    }

    static int[][] dp;
    private static boolean possible(int middle) {
        dp = new int[n+1][m+1];
        for (int i= 0; i <= n; i++) Arrays.fill(dp[i] , -1);

        int q = f(n, m, valuesSorted[middle - 1]);
//        System.out.println(Arrays.deepToString(dp));
        return q >= k;
    }

    private static int f(int n, int m, int middleVal) {
        if (m == 0) return 0;
        else if (n == 0) return 0;

        if (dp[n][m] == -1) {
            if (left[n-1] == BIG) {
                dp[n][m] = f(n-1, m, middleVal);
            } else {
                // shift all lefts
                if (left[n-1] - 1 >= 0) {
                    int aux = left[left[n - 1] - 1];
                    for (int i = left[n - 1]; i < n; i++) {
                        left[left[n - 1] - 1] = Math.min(left[left[n - 1] - 1], left[i]);
                    }
                    dp[n][m] = Math.max(f(n - 1, m, middleVal), f(left[n - 1], m - 1, middleVal) + g(n - 1, middleVal));
                    left[left[n - 1] - 1] = aux;
                } else {
                    dp[n][m] = Math.max(f(n - 1, m, middleVal), f(left[n - 1], m - 1, middleVal) + g(n - 1, middleVal));
                }
            }
        }

        return dp[n][m];
    }

    private static int g(int R, int val) {
        int L = left[R];

        int ans = 0;

        for (int i = L; i <= R; i++) {
            ans += (val >= values[i]) ? 1 : 0;
        }

        return ans;
    }
}
