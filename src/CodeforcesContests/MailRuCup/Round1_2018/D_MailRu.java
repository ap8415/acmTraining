package CodeforcesContests.MailRuCup.Round1_2018;

import java.util.*;
import java.lang.*;


public class D_MailRu {

    static int k;
    static int CST;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long n = sc.nextInt(); k = sc.nextInt(); CST = (1 << k) - 1;

        Map<Integer, Integer> partialSumCounts = new HashMap<>();
        int currentPartial = 0;
        for (int i = 1; i <= n; i++) {
            int next = sc.nextInt();
            currentPartial = currentPartial ^ next;
            partialSumCounts.put(currentPartial, partialSumCounts.getOrDefault(currentPartial, 0) + 1);
        }

        long badSegments = 0;

        // do case 0
        if (partialSumCounts.containsKey(0) || partialSumCounts.containsKey(CST)) {
            badSegments+= formula(partialSumCounts.getOrDefault(0,0) + 1 + partialSumCounts.getOrDefault(CST, 0));
        }

        Set<Integer> existing = new HashSet<>(partialSumCounts.keySet());
        existing.remove(0); existing.remove(CST);

        for (Integer possible : existing) {
            int q1 = partialSumCounts.getOrDefault(possible, 0);
            int q2 = partialSumCounts.getOrDefault(flip(possible), 0);
            if (q1 + q2 != 0) {
                badSegments += formula(q1 + q2);
                partialSumCounts.remove(possible);
                partialSumCounts.remove(flip(possible));
            }
        }

        long maxSegments = (n * (n+1))/ 2;

        System.out.println(maxSegments - badSegments);
    }

    private static long formula(long S) {
        long sol = 0;
        sol += (S * S - S) / 2;
        if (S % 2 ==0) {
            sol -= (S * S) / 4;
        } else {
            sol -= (S * S - 1) / 4;
        }

        return sol;
    }


    private static int flip(int x) {
        return CST - x;
    }
}
