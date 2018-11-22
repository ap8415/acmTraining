package ACM_Contests.Pacific_NWRC_2018;

import java.util.*;

public class K {

    // int[i] is whether i is in the state. Ignore 0. Length is 10
    static int score(int[] state) {
        int no = 0;
        for (int i = 1; i < 10; i++) {
            if (state[i] == 1) {
                no *= 10; no += i;
            }
        }
        return no;
    }

    static class Roll {
        @Override
        public String toString() {
            return "Roll{" +
                    "value=" + value +
                    ", probability=" + probability +
                    '}';
        }

        int value;
        double probability;

        public Roll(int value, double probability) {
            this.value = value;
            this.probability = probability;
        }

        public static Roll of(int val, double prob) {
            return new Roll(val, prob);
        }
    }

    static double FRAC = ((double)1) / 36;

    static Roll[] rolls = {
            new Roll(2, FRAC), new Roll(3, 2 * FRAC),
            new Roll(4, 3 * FRAC), new Roll(5, 4 * FRAC),
            new Roll(6, 5 * FRAC), new Roll(7, 6 * FRAC),
            new Roll(8, 5 * FRAC), new Roll(9, 4 * FRAC),
            new Roll(10, 3 *FRAC), new Roll(11, 2 * FRAC),
            new Roll(12, FRAC),
    };

    static Map<List<Integer>, Double> dp = new HashMap<>();

    static double expected(List<Integer> state, boolean max) {
        if (state.isEmpty()) return 0.0;
        if (dp.containsKey(state)) return dp.get(state);

        double expected = 0.0;

        for (Roll r: rolls) {
            List<List<Integer>> potential = generateSubsets(state, r.value);
            if (potential.isEmpty()) {
                expected += r.probability * score(listToState(state));
            } else {
                double best = max ? -0.01 : 1_000_000_001.0;
                for (List<Integer> subset : potential) {
                    if (max) {
                        if (best <= expected(subset, true)) {
                            best = expected(subset, true);
                        }
                    } else {
                        if (best >= expected(subset, false)) {
                            best = expected(subset, false);
                        }
                    }
                }
                expected += r.probability * best;
            }
        }
        dp.put(state, expected);
        return expected;
    }

    static List<Integer> stateToList(int[] state) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= 9; i++) if (state[i] == 1) ans.add(i);
        return ans;
    }

    static int[] listToState(List<Integer> state) {
        int[] sol = new int[10];
        for (int s : state) sol[s] = 1;
        return sol;
    }


    static List<List<Integer>> generateSubsets(List<Integer> list, int value) {
        List<List<Integer>> ans = new ArrayList<>();

        List<Integer> copy = new ArrayList<>(list);

        if (value == 0) {
            ans.add(copy);
            return ans;
        } else if (value < 0) {
            return ans;
        }

        if (copy.isEmpty()) return ans;

        int x = copy.get(list.size() - 1);
        copy.remove(list.size() - 1); // potential error

        List<Integer> copy2 = new ArrayList<>(copy);

        List<List<Integer>> ans1 = generateSubsets(copy2, value);
        ans1.forEach(l -> l.add(x));

        ans.addAll(generateSubsets(copy, value - x)); // subsets without x
        ans.addAll(ans1); // subsets with x

        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] state = new int[10];
        String initialState = sc.next();
        int rollVal = sc.nextInt() + sc.nextInt();
        for (int i = 0; i < initialState.length(); i++) state[initialState.charAt(i) - '0'] = 1;

        Roll r = null;
        for (Roll r1: rolls) {
            if (r1.value == rollVal) r = r1;
        }

        double expected;
        List<List<Integer>> potential;

        expected = 1_000_000_001.0;

        potential = generateSubsets(stateToList(state), r.value);
        if (potential.isEmpty()) {
            expected = score(state);
            System.out.println(String.format("%d %.5f", -1, expected));

        } else {
            List<Integer> best = null;
            for (List<Integer> subset : potential) {
                if (expected >= expected(subset, false)) {
                    expected = expected(subset, false);
                    best = subset;
                }
            }
            List<Integer> exp = new ArrayList<>(stateToList(state));
            exp.removeAll(best);
            int[] newst = listToState(exp);
            for (int i = 1; i <= 9; i++) {
                if (newst[i] == 1) System.out.print(i);
            }
            System.out.print(" ");
            System.out.println(String.format("%.5f", expected));
        }

        dp = new HashMap<>();

        expected = -0.01;

        potential = generateSubsets(stateToList(state), r.value);
        if (potential.isEmpty()) {
            expected = score(state);
            System.out.println(String.format("%d %.5f", -1, expected));
        } else {
            List<Integer> best = null;
            for (List<Integer> subset : potential) {
                if (expected <= expected(subset, true)) {
                    expected = expected(subset, true);
                    best = subset;
                }
            }
            List<Integer> exp = new ArrayList<>(stateToList(state));
            exp.removeAll(best);
            int[] newst = listToState(exp);
            for (int i = 1; i <= 9; i++) {
                if (newst[i] == 1) System.out.print(i);
            }
            System.out.print(" ");
            System.out.println(String.format("%.5f", expected));
        }

    }

}
