package ACM_Contests.SWERC_2015;

import java.lang.*;
import java.util.*;
import java.io.*;

public class B {

    static class Constraint {
        Set<Integer> suspects;
        int playerNo;
        int reply;

        public Constraint(int playerNo, int reply, int... suspects) {
            this.suspects = new HashSet<>();
            for (int s : suspects) this.suspects.add(s);
            this.playerNo = playerNo;
            this.reply = reply;
        }

        public Constraint(int playerNo, int reply, Set<Integer> suspects) {
            this.suspects = new HashSet<>(suspects);
            this.playerNo = playerNo;
            this.reply = reply;
        }

        public Constraint(Constraint toCpy) {
            this(toCpy.playerNo, toCpy.reply, toCpy.suspects);
        }

        public boolean removeSuspect(int suspect) {
            suspects.remove(suspect);
            return reply <= suspects.size() && reply >= 0;
        }

        public boolean removeSuspectFrom(int suspect, int playerNo) {
            if (suspects.remove(suspect) && playerNo == this.playerNo) {
                reply--;
            }
            return reply <= suspects.size() && reply >= 0;
        }

        public boolean deterministic() {
            return suspects.size() == reply;
        }

        // 0 if not det
        // 1 if susp size = reply
        // 2 if reply =  0
        public int deterministicWrtSet() {
            return suspects.size() == reply ? 1 : (reply == 0 ? 2 : 0); // either suspects go into one set or the other
        }

        @Override
        public String toString() {
            return "Constraint{" +
                    "suspects=" + suspects +
                    ", playerNo=" + playerNo +
                    ", reply=" + reply +
                    '}';
        }
    }

    static List<Constraint> copyOf(List<Constraint> original) {
        List<Constraint> copy = new LinkedList<>();
        for (Constraint c : original) copy.add(new Constraint(c));
        return copy;
    }

    static int sol = 0;

    // suspects from 0...25
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        List<Constraint> overallConstraints = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            String suspects = sc.next();
            overallConstraints.add(new Constraint(sc.nextInt() - 1, sc.nextInt(),
                    suspects.charAt(0) - 'A',
                    suspects.charAt(1) - 'A'));
        }

        for (int i = 0; i < 24; i++) {
            for (int j = i + 1; j < 25; j++) {
                for (int k = j + 1; k < 26; k++) {
                    // i j k is the Vienna circle
                    List<Constraint> constraints = copyOf(overallConstraints);
                    sol += solve(constraints, i, j,k) ? 1 : 0;
                }
            }
        }
        System.out.println(sol);
    }

    private static boolean checkValid(BitSet[] players) {
        BitSet cp = new BitSet(26);
        cp.or(players[0]);
        cp.and(players[1]);
        return cp.cardinality() == 0;
    }

    static List<Integer>[] adjGraph;

    private static boolean solve(List<Constraint> constraints, int i, int j, int k) {

        Iterator<Constraint> it = constraints.iterator();
        BitSet player[] = new BitSet[2];
        player[0] = new BitSet(26);
        player[1] = new BitSet(26);

        while (it.hasNext()) {
            Constraint c = it.next();
            // if removing any suspect causes contradiction return.
            if (!(c.removeSuspect(i) && c.removeSuspect(j) && c.removeSuspect(k))) return false;
            if (c.deterministic()) {
                it.remove();
                for (int x : c.suspects) player[c.playerNo].set(x);
            }
        }

        BitSet new_player[] = new BitSet[2];
        new_player[0] = new BitSet(26); new_player[0].or(player[0]);
        new_player[1] = new BitSet(26); new_player[1].or(player[1]);

        boolean deterministic = true;
        while (deterministic) {

            player[0].or(new_player[0]);
            player[1].or(new_player[1]);
            if (!checkValid(player)) return false;

            BitSet cp0 = new BitSet(26);
            BitSet cp1 = new BitSet(26);

            cp0.or(new_player[0]);
            cp1.or(new_player[1]);
            new_player[0].clear();
            new_player[1].clear();

            deterministic = false;
            it = constraints.iterator();
            while (it.hasNext()) {
                Constraint c = it.next();

                // remove all deterministically assigned suspects, wrt both sets

                int nextSet = cp0.nextSetBit(0);
                while (nextSet != -1) {
                    // nextsetbit means bit(nsb) is set => nsb is suspect
                    if (!c.removeSuspectFrom(nextSet, 0)) return false;
                    // add 1 to keep iterating forward and not get stuck
                    nextSet = cp0.nextSetBit(nextSet+1);
                }

                nextSet = cp1.nextSetBit(0);
                while (nextSet != -1) {
                    // nextsetbit means bit(nsb) is set => nsb is suspect
//                    System.out.println("AICI");
                    if (!c.removeSuspectFrom(nextSet, 1)) return false;
                    // add 1 to keep iterating forward and not get stuck
                    nextSet = cp1.nextSetBit(nextSet+1);
                }

                if (c.deterministicWrtSet() != 0) {
//                    System.out.println("xd");
                    deterministic = true;
                    it.remove();
                    int which = c.deterministicWrtSet();
                    for (int x : c.suspects) new_player[which == 1 ? c.playerNo : 1 - c.playerNo].set(x);
                }
            }
        }

        // Done with the deterministic constraints
        if (constraints.isEmpty()) {
            // if no more constraints and configuration is valid, return true
            return checkValid(player);
        } else {
            // else build graph from remaining constraints which are of the form XY, (player), 1
            // with X, Y not in p0, p1 or vienna
            adjGraph = new List[26];
            for (int a = 0; a < 26; a++) adjGraph[a] = new ArrayList<>();

            for (Constraint c : constraints) {
                Iterator<Integer> newit = c.suspects.iterator();
                int s1 = newit.next(); int s2 = newit.next(); // by construction should have size 2
                adjGraph[s1].add(s2);
                adjGraph[s2].add(s1);
            }

            visited = new int[26];
            valid = true;

            for (int a = 0; a < 26; a++) {
                if (visited[a] == 0) dfs(a, 1);
            }

            return valid;
        }
    }

    static boolean valid;

    private static int dfs(int a, int color) {
        if (visited[a] != 0) return visited[a];

        visited[a] = color;
        for (int b : adjGraph[a]) {
            if (dfs(b, 3 - color) == color) valid = false;
        }

        return visited[a];
    }

    static int[] visited;

}
