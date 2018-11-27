package ACM_Contests.NWERC.NWERC2012;

import java.util.*;
import java.lang.*;

public class B {

    static Outcome[][][][][] dp;
    static int[] iS = new int[5];
    static int n, k;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            solve(sc);
        }
    }

    public static void solve(Scanner sc) {

        n = sc.nextInt(); k = sc.nextInt();
        int remaining = k;
        for (int i = 0; i < n; i++) {
            iS[i] = sc.nextInt();
            remaining -= iS[i];
        }

        // dp[x1][x2][x3][x4][x5]
        // is for when a1 =
        dp = new Outcome[remaining+2][][][][];
        for (int a1 = 0; a1 <= remaining; a1++) {
            int b1 = n >= 1 ? remaining - a1 : 0;
            dp[a1] = new Outcome[b1+2][][][];
            for (int a2 = 0; a2 <= b1; a2++) {
                int b2 = n >= 2 ? b1 - a2 : 0;
                dp[a1][a2] = new Outcome[b2+2][][];
                for (int a3 = 0; a3 <= b2; a3++) {
                    int b3 = n >= 3 ? b2 - a3 : 0;
                    dp[a1][a2][a3] = new Outcome[b3+2][];
                    for (int a4 = 0; a4 <= b3; a4++) {
                        int b4 = n >= 4 ? b3 - a4 : 0;
                        dp[a1][a2][a3][a4] = new Outcome[b4 + 2];
                    }
                }
            }
        }

        initial_total = iS[0] + iS[1] + iS[2] + iS[3] + iS[4];

        Outcome result = f(new int[5]);
        double[] res = new double[5];
        res[0] = 100 * result.a1;
        res[1] = 100 * result.a2;
        res[2] = 100 * result.a3;
        res[3] = 100 * result.a4;
        res[4] = 100 * result.a5;

        for (int i = 0; i < n; i++) {
            System.out.println(String.format("pub %d: %.02f ", i+1, res[i]) + "%");
        }

    }

    static class Outcome {
        public double a1, a2, a3, a4, a5;

        Outcome multiply(double p) {
            Outcome o = new Outcome();
            o.a1=  a1; o.a2= a2; o.a3 = a3; o.a4 = a4; o.a5 = a5;
            o.a1 *= p; o.a2 *= p; o.a3 *= p; o.a4 *= p; o.a5 *= p;
            return o;
        }

        void add(Outcome o) {
            a1 += o.a1;
            a2 += o.a2;
            a3 += o.a3;
            a4 += o.a4;
            a5 += o.a5;
        }

        @Override
        public String toString() {
            return "Outcome{" +
                    "a1=" + a1 +
                    ", a2=" + a2 +
                    ", a3=" + a3 +
                    ", a4=" + a4 +
                    ", a5=" + a5 +
                    '}';
        }
    }

    static int initial_total;

    private static Outcome f(int[] A) {
//        System.out.println(String.format("Baietii mei: %d %d %d %d %d", A[0], A[1], A[2], A[3], A[4]));
        // Get totals
        int total = A[0] + A[1] + A[2] + A[3] + A[4];
        total += initial_total;

        if (dp[A[0]][A[1]][A[2]][A[3]][A[4]] != null) return dp[A[0]][A[1]][A[2]][A[3]][A[4]];

        // if at end-state, find the likeliest pubs probabilistically
        if (total >= k) {
            Set<Integer> biggest = new HashSet<>(); int biggestSize = -1;
            for (int i = 0; i < n; i++) {
                if (A[i] + iS[i] > biggestSize) {
                    biggestSize = A[i] + iS[i];
                    biggest.clear();
                }

                if (A[i] + iS[i] >= biggestSize) {
                    biggest.add(i);
                }
            }

            double prob = 1.0 / biggest.size();
            Outcome out = new Outcome();
            if (biggest.contains(0)) out.a1 = prob;
            if (biggest.contains(1)) out.a2 = prob;
            if (biggest.contains(2)) out.a3 = prob;
            if (biggest.contains(3)) out.a4 = prob;
            if (biggest.contains(4)) out.a5 = prob;

            dp[A[0]][A[1]][A[2]][A[3]][A[4]] = out;
//            System.out.println("mema " + dp[A[0]][A[1]][A[2]][A[3]][A[4]].a1);
            return out;
        }

        // else go down the DP tree

        dp[A[0]][A[1]][A[2]][A[3]][A[4]] = new Outcome();

        double P1 = (double)(A[0] + iS[0]) / total;
        if (P1 > 0) {
            int[] B = Arrays.copyOf(A, 5);
            B[0]++;
            dp[A[0]][A[1]][A[2]][A[3]][A[4]].add(f(B).multiply(P1));
        }
        double P2 = (double)(A[1] + iS[1]) / total;
        if (P2 > 0) {
            int[] B = Arrays.copyOf(A, 5);
            B[1]++;
            dp[A[0]][A[1]][A[2]][A[3]][A[4]].add(f(B).multiply(P2));
        }
        double P3 = (double)(A[2] + iS[2]) / total;
        if (P3 > 0) {
            int[] B = Arrays.copyOf(A, 5);
            B[2]++;
            dp[A[0]][A[1]][A[2]][A[3]][A[4]].add(f(B).multiply(P3));
        }
        double P4 = (double)(A[3] + iS[3]) / total;
        if (P4 > 0) {
            int[] B = Arrays.copyOf(A, 5);
            B[3]++;
            dp[A[0]][A[1]][A[2]][A[3]][A[4]].add(f(B).multiply(P4));
        }
        double P5 = (double)(A[4] + iS[4]) / total;
        if (P5 > 0) {
            int[] B = Arrays.copyOf(A, 5);
            B[4]++;
            dp[A[0]][A[1]][A[2]][A[3]][A[4]].add(f(B).multiply(P5));
        }

        return dp[A[0]][A[1]][A[2]][A[3]][A[4]];
    }

}
