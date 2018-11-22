package ACM_Contests.Pacific_NWRC_2018;

import java.math.BigInteger;
import java.util.*;
import java.lang.*;

public class C {

    static int[] available;
    static int max;
    static List<Integer> levelsInOrder;
    static Map<Integer, Integer> avail;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); int k = sc.nextInt();

        avail = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int diff = sc.nextInt();
            avail.put(diff, avail.getOrDefault(diff, 0) + 1);
        }

        levelsInOrder = new ArrayList<>();
        levelsInOrder.addAll(avail.keySet());
        Collections.sort(levelsInOrder);

        max = levelsInOrder.size();

        BigInteger result = f(0, k);
        System.out.println(result.mod(BigInteger.valueOf(998244353)).longValue());

    }

    static BigInteger f(int curr, int k) {
        if (k == 0) return BigInteger.valueOf(1);
        if (curr >= max) return BigInteger.valueOf(0);
        return f(curr+1, k-1).multiply(
                BigInteger.valueOf(avail.get(levelsInOrder.get(curr)))
        ).add(f(curr+1, k));
    }



}
