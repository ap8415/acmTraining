package cf_round_448;

import java.util.*;

public class B {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        long x = scan.nextInt();
        int k = scan.nextInt();

        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = scan.nextInt();
        }

        if (k == 0) {
            long pairs = 0;
            HashMap<Long , Long > below = new HashMap<>();
            HashMap<Long , Long > third = new HashMap<>();
            for (int i = 0; i < n; i++) {

                if (a[i] % x != 0) {
                    if (!below.containsKey(a[i] / x)) {
                        below.put(a[i] / x, 1L);
                    } else {
                        below.put(a[i] / x, 1 + below.get(a[i] / x));
                    }

                    if (!third.containsKey((long)a[i])) {
                        third.put((long)a[i], 1L);
                    } else {
                        third.put((long)a[i], 1 + third.get((long)a[i]));
                    }
                }
            }

            for (Long i : third.keySet()) {
                pairs += third.get(i) * (third.get(i) - 1) / 2;
            }

            for (Long b : below.keySet()) {
                pairs += below.get(b) * (below.get(b) + 1) / 2;
            }

            System.out.println(pairs);
            return;
        }

        if (k == 1) {

        }

        HashMap<Long , Long > below = new HashMap<>();
        HashMap<Long , Long > above = new HashMap<>();

        for (int i = 0; i < n; i++) {
            if (!below.containsKey(a[i] / x)) {
                below.put(a[i] / x, 1L);
            } else {
                below.put(a[i] / x, 1 + below.get(a[i] / x));
            }

            if (!above.containsKey((a[i] % x == 0 ? 0 : 1) + (a[i] / x))) {
                above.put((a[i] % x == 0 ? 0 : 1) + (a[i] / x), 1L);
            } else {
                above.put((a[i] % x == 0 ? 0 : 1) + (a[i] / x), 1 + above.get((a[i] % x == 0 ? 0 : 1) + (a[i] / x)));
            }
        }

        long pairs = 0;

        for (Long s: above.keySet()) {
            s += k - 1;
            if (below.containsKey(s)) {
                pairs += below.get(s) * above.get(s - k + 1);
            }
        }

        System.out.println(pairs);
    }
}