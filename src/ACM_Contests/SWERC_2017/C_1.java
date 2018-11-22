import java.time.Instant;
import java.util.*;
import java.lang.*;

public class C_1 {

    static int N;
    static long M;

    static HashMap<Integer, HashMap<Integer, Integer>> combineMemo = new HashMap<>();


    static int combine(int[] a, int[] b) {

        try {
            Integer val = combineMemo.get(val(a)).get(val(b));
            if (val == null) throw new NullPointerException();
            return val;
        } catch (NullPointerException e) {
            int count = 1;
            for (int i = 0; i < N; i++) {
                if (a[i] == 0 && b[i] == 0) count *= 2;
            }
            combineMemo.computeIfAbsent(val(a), k->new HashMap<>()).put(val(b), count);
            return count;
        }

    }

    static HashMap<Integer, HashMap<Integer, Integer>> resolveMemo = new HashMap<>();

    static int resolve(int[] a, int[] b) {
        try {
            Integer val = resolveMemo.get(val(a)).get(val(b));
            if (val == null) throw new NullPointerException();
            return val;
        } catch (NullPointerException e) {
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
            resolveMemo.computeIfAbsent(val(a), k->new HashMap<>()).put(val(b), sol);
            return sol;
        }
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

    static long MODUL = 1_000_000_000;

    public static void main(String[] args) {

        Instant now = Instant.now();
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextLong();

        dp = new HashMap<>(); memoDp = new HashMap<>();


        generateEdgeStates(0, new int[N]);
        generateAllStates(0, new int[N]);

//        System.out.println(edgeStates);

        long result = 0;
        for (int[] i: allStates) {
            for (int[] j : allStates) {
                result = result + divAndConq(M, i, j); result = result % MODUL;
//                System.out.println(divAndConq(M, i , j));
            }
        }

        System.out.println(result);
        System.out.println(Instant.now().toEpochMilli() - now.toEpochMilli());
        System.out.println(dp.get(M).keySet());
        System.out.println("Nr de calcule: " + pulamea);
    }

    static HashMap<Long, HashMap<Integer, HashMap<Integer, Long>>> dp;

    static int val(int[] state) {
        int ans = 0;
        for (int i = 0; i < N; i++) {
            ans *= 2; ans += state[i];
        }
        return ans;
    }

    static Set<Long> penal = new HashSet<>();

    static int pulamea = 0;

    static long divAndConq(long width, int[] left, int[] right) {
//        System.out.println(" INTRARE " + width + " PENIS " + Arrays.toString(left) + " " + Arrays.toString(right));

        try {
            Long dpSol = dp.get(width).get(val(left)).get(val(right));
            if (dpSol == null) throw new NullPointerException();
//            System.out.println("Sol: " + dpSol + " width " + width + " PENIS " + Arrays.toString(left) + " " + Arrays.toString(right));
            return dpSol;
        } catch (NullPointerException e) {
            pulamea++;
            // not found in dp
            long dpSol = 0;
            if (width == 1) {
                if (Arrays.equals(left, right)) {
                    for (int[] possible: edgeStates) {
//                        System.out.println("Posibila: " + Arrays.toString(possible));
                        if (Arrays.equals(possible, left)) dpSol = 1;
                    }
                }
            } else if (width == 2) {
                dpSol = resolve(left, right);// * combine(left, right);
            }
            else if (width == 3) {
                for (int[] possible: allStates) {
                    dpSol = dpSol + threes(left, right, possible);
                    dpSol = dpSol % MODUL;
                }
            }
            else {
                for (int[] right_0 : allStates) {
                    long L = divAndConq(width/2, left, right_0);
                    dpSol = dpSol + L * memo((width+ 1) / 2, right_0, right);
                    dpSol = dpSol % MODUL;
                }
            }
            dpSol = dpSol % MODUL;
            dp.computeIfAbsent(width, k->new HashMap<>())
                    .computeIfAbsent(val(left), k -> new HashMap<>())
                    .put(val(right), dpSol);
            return dpSol;
        }
    }

    static HashMap<Long, HashMap<Integer, HashMap<Integer, Long>>> memoDp;


    static long memo(long width, int[] right_0, int[] right) {
        try {
            Long memoSol = memoDp.get(width).get(val(right_0)).get(val(right));
            if (memoSol == null) throw new NullPointerException();
//            System.out.println("Sol: " + dpSol + " width " + width + " PENIS " + Arrays.toString(left) + " " + Arrays.toString(right));
            return memoSol;
        } catch (NullPointerException e) {
//            pulamea++;
            long memoSol = 0;
            for (int[] left_0 : allStates) {
                long R = divAndConq(width, left_0, right);
                long C = combine(right_0, left_0);
                memoSol += R * C; memoSol %= MODUL;
            }
            memoDp.computeIfAbsent(width, k->new HashMap<>())
                    .computeIfAbsent(val(right_0), k -> new HashMap<>())
                    .put(val(right), memoSol);
            return memoSol;
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