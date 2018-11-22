package ACM_Contests.SWERC_2017;

import java.math.BigInteger;
import java.time.Instant;
import java.util.*;
import java.lang.*;

public class C {

    static int N;
    static long M;

    static int combine(int[] a, int[] b) {
        int count = 1;
        for (int i = 0; i < N; i++) {
            if (a[i] == 0 && b[i] == 0) count *= 2;
        }

//        System.out.println(Arrays.toString(a) + " combine " + Arrays.toString(b) + " = " + count);

        return count;
    }

    static int resolve(int[] a, int[] b) {
        int firstOneA = N + 1, firstOneB = N + 1;
        for (int i = 0; i < N; i++) {
            if (a[i] == 1) {
                firstOneA = i;break;
            }
        }

        for (int i = 0; i < N; i++) {
            if (b[i] == 1) {
                firstOneB = i;break;
            }
        }

        int sol = 0;
        if (firstOneA == N+1 && firstOneB == N+1) return 1;

        if (firstOneB > firstOneA) {
            if (firstOneA == N-1 || a[firstOneA + 1] == 0) {
                return 0;
            } else {
                int[] cp = Arrays.copyOf(a, N);
                cp[firstOneA] = 0; cp[firstOneA + 1] = 0;
                sol += resolve(cp, b);
            }
        } else if (firstOneB < firstOneA) {
            if (firstOneB == N-1 || b[firstOneB + 1] == 0) {
                return 0;
            } else {
                int[] cp = Arrays.copyOf(b, N);
                cp[firstOneB] = 0; cp[firstOneB+1] = 0;
                sol += resolve(a, cp);
            }
        } else {
            int[] cp1 = Arrays.copyOf(a, N); int[] cp2 = Arrays.copyOf(b, N);
            cp1[firstOneA] = 0;  cp2[firstOneB] = 0;
            sol += resolve(cp1, cp2);

            if ( (firstOneB != N-1 && b[firstOneB + 1] == 1) && (firstOneA != N-1 && a[firstOneA + 1] == 1)) {
                int[] cp3 = Arrays.copyOf(a, N); int[] cp4 = Arrays.copyOf(b, N);
                cp3[firstOneA] = 0;  cp4[firstOneB] = 0;
                cp3[firstOneA + 1] = 0;  cp4[firstOneB + 1] = 0;
                sol += resolve(cp3, cp4);
            }
        }


//        System.out.println(Arrays.toString(a) + " resolve " + Arrays.toString(b) + " = " + sol);
        return sol;
    }

    static List<int[]> edgeStates = new ArrayList<>();
    static List<int[]> allStates = new ArrayList<>();

    private static void generateEdgeStates(int i, int[] curr) {
        if (i >= N-1) {
            edgeStates.add(curr); return;
        }
        int[] cp1 = Arrays.copyOf(curr, N);
        generateEdgeStates(i+1, cp1);
        int[] cp2 = Arrays.copyOf(curr, N); cp2[i] = 1; cp2[i+1] = 1;
        generateEdgeStates(i+2, cp2);
    }

    private static void generateAllStates(int i, int[] curr) {
        if (i >= N) {
            allStates.add(curr); return;
        }
        int[] cp1 = Arrays.copyOf(curr, N);
        generateAllStates(i+1, cp1);
        int[] cp2 = Arrays.copyOf(curr, N); cp2[i] = 1;
        generateAllStates(i+1, cp2);
    }

//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        N = sc.nextInt();
//        M = sc.nextLong();
//
//        N = 6;
//
//        lessThan(0, new int[]{0, 0, 0, 1, 0, 1}).forEach(a -> System.out.println(Arrays.toString(a)));
//    }
//
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Instant now = Instant.now();
        N = sc.nextInt(); M = sc.nextLong();

        dp_f = new HashMap<>();
        dp_g = new HashMap<>();

        generateEdgeStates(0, new int[N]);
        generateAllStates(0, new int[N]);

//        System.out.println(edgeStates);

        BigInteger result = BigInteger.ZERO;
        int[] big = new int[N]; Arrays.fill(big, 1);

        for (int[] i : allStates) {
            for(int[] j : allStates) {
                result = result.add(f(M, i, j));
            }
        }

        System.out.println(result.mod(BigInteger.valueOf(1_000_000_000)));

//        System.out.println(dp_f);

//        System.out.println("TIME " + (Instant.now().toEpochMilli() - now.toEpochMilli()));
    }

    static HashMap<Long, HashMap<Integer, HashMap<Integer, BigInteger>>> dp_f;
    static HashMap<Long, HashMap<Integer, HashMap<Integer, BigInteger>>> dp_g;


    static int val(int[] state) {
        int ans = 0;
        for (int i = 0; i < N; i++) {
            ans *= 2; ans += state[i];
        }
         return ans;
    }

    static int[] neg(int[] state) {
        int[] newst = new int[N];
        for (int i = 0; i < N; i++) newst[i] = 1 - state[i];
        return newst;
    }

    static List<int[]> lessThan(int lowest, int[] given) {
        if (lowest >= N) {
            List<int[]> meme = new ArrayList<>(); meme.add(new int[N]); return meme;
        }
        List<int[]> ans = lessThan(lowest+1, given);
        if (given[lowest] == 1) {
            List<int[]> ans2 = lessThan(lowest+1, given);
            ans2.forEach(good -> good[lowest] = 1);
            ans.addAll(ans2);
        }
        return ans;
    }

    static int[] fib = {1, 1, 2, 3, 5, 8, 13, 21, 34};

    static int h(int[] first, int[] second) {
        int counter = 0; int total = 1;
        for (int i = 0; i <= N; i++) {
            if (i < N && first[i] == 0 && second[i] == 0) {
                counter++;
            } else {
                total *= (fib[counter]);
                counter = 0;
            }
        }
        return total;
    }

    static BigInteger g(long width, int[] left, int[] right) {
        try {
            BigInteger g_dp = dp_g.get(width).get(val(left)).get(val(right));
            if (g_dp == null) throw new NullPointerException();
            return g_dp;
        } catch (NullPointerException e) {
            BigInteger g_dp = BigInteger.ZERO;
            for (int[] possible : lessThan(0, neg(left))) {
                g_dp = g_dp.add(f(width, possible, right).multiply(BigInteger.valueOf(h(possible, left))));
            }

            if (g_dp.compareTo(BigInteger.valueOf(1_000_000_000)) >= 0)
                g_dp = g_dp.mod(BigInteger.valueOf(1_000_000_000));
            dp_g.computeIfAbsent(width, k->new HashMap<>())
                    .computeIfAbsent(val(left), k -> new HashMap<>())
                    .put(val(right), g_dp);
//            System.out.println(String.format("%d, <=%s, %s --> %s",
//                    width, Arrays.toString(left),  Arrays.toString(right), g_dp));
            return g_dp;
        }
    }


    static BigInteger f(long width, int[] left, int[] right) {
//        System.out.println(" INTRARE " + width + " PENIS " + Arrays.toString(left) + " " + Arrays.toString(right));

        try {
            BigInteger dpSol = dp_f.get(width).get(val(left)).get(val(right));
            if (dpSol == null) throw new NullPointerException();
//            System.out.println("Sol: " + dpSol + " width " + width + " PENIS " + Arrays.toString(left) + " " + Arrays.toString(right));
            return dpSol;
        } catch (NullPointerException e) {
            // not found in dp_f
            BigInteger dpSol = BigInteger.ZERO;
            if (width == 1) {
                if (Arrays.equals(left, right)) {
                    for (int[] possible : edgeStates) {
                        if (Arrays.equals(possible, right)) dpSol = BigInteger.ONE;
                    }
                }
            } else if (width == 2) {
                    dpSol = dpSol.add(BigInteger.valueOf(resolve(left, right)));
            } else {
                for (int[] middle : allStates) {
                    BigInteger L = f((width + 1) / 2, left, middle);
                    BigInteger R = g((width + 2) / 2, middle, right);
                    dpSol = dpSol.add(L.multiply(R));
                }
            }

            if (dpSol.compareTo(BigInteger.valueOf(1_000_000_000)) >= 0)
                dpSol = dpSol.mod(BigInteger.valueOf(1_000_000_000));
            dp_f.computeIfAbsent(width, k->new HashMap<>())
                    .computeIfAbsent(val(left), k -> new HashMap<>())
                    .put(val(right), dpSol);
//            System.out.println(String.format("%d, %s, %s --> %s",
//                    width, Arrays.toString(left),  Arrays.toString(right), dpSol));
            return dpSol;
        }
    }

    private static int threes(int[] left, int[] right, int[] middle) {
        int curr = -1;
        for (int i = 0; i < N; i++) {
            if (middle[i] == 1) {
                curr = i;
                break;
            }
        }

        if (curr == -1) {
            int cnt = 0;
            for (int[] edgeState : edgeStates) {
                if (Arrays.equals(edgeState, left))
                {
                    cnt++;
                }
                if (Arrays.equals(edgeState, right))
                {
                    cnt++;
                }
            }
            return cnt == 2 ? 1 : 0;
        }

        int count = 0;

        if (middle[curr] == right[curr]) {
            int[] leftcp = Arrays.copyOf(left, N);
            int[] midcp = Arrays.copyOf(middle, N);
            int[] rightcp = Arrays.copyOf(right, N);
            midcp[curr] = 0; rightcp[curr] = 0;
            count += threes(leftcp, rightcp, midcp);
        }
        if (middle[curr] == left[curr]) {
            int[] leftcp = Arrays.copyOf(left, N);
            int[] midcp = Arrays.copyOf(middle, N);
            int[] rightcp = Arrays.copyOf(right, N);
            midcp[curr] = 0; leftcp[curr] = 0;
            count += threes(leftcp, rightcp, midcp);
        }
        if (curr < N-1 && middle[curr] == middle[curr+1]) {
            int[] leftcp = Arrays.copyOf(left, N);
            int[] midcp = Arrays.copyOf(middle, N);
            int[] rightcp = Arrays.copyOf(right, N);
            midcp[curr] = 0; midcp[curr+1] = 0;
            count += threes(leftcp, rightcp, midcp);
        }
        return count;
    }

}
