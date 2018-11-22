package cf_round_448;

import java.util.*;

public class C {

    static int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67};
    static int cst = 1000000007;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();


        Map<Integer, Integer> present = new HashMap<>();

//        int[] present2 = new int[71];
//
//        for (int i = 1; i <= n; i++) {
//            int k = powerFree(scan.nextInt());
//            present2[k]++;
//        }

        for (int i = 1; i <= n; i++) {
            int k = powerFree(scan.nextInt());
            present.put(k, (present.get(k) != null ? present.get(k) : 0) + 1);
        }

        ArrayList<HashMap<Integer, Integer>> dp2 = new ArrayList<>(70);

        long[] dp3 = new long[1<<19 + 1];

        long[] dp4 = new long[1<<19 + 1];

        long[] aux;

        int cnt = -1;

        Set<Integer> keyset = new HashSet<>();

        for (Integer i: present.keySet()) {
            cnt++;
            int i1 = toBinary(i);
//            System.out.println(i);

            if (cnt == 0) {
                int var2 = modConstPower(present.get(i) - 1);
                dp3[i1] += var2;
                dp3[0] += var2;
                keyset.add(0); keyset.add(toBinary(i));
//                dp2.get(0).put(i, var2);
//                dp2.get(0).put(1, var2 + ((i == 1) ? var2 : 0));
                continue;
            }

            Set<Integer> keyset2 = new HashSet<>();

            for (Integer j: keyset) dp4[j] = 0;

            // CHANGE INT TO LONG
            for (Integer j: keyset) {
                int var2 = modConstPower(present.get(i) - 1);
                dp4[i1 ^ j] += var2 * dp3[j]; dp4[i1 ^ j] %= cst;
                dp4[j] += var2 * dp3[j]; dp4[j] %= cst;

                keyset2.add(j); keyset2.add(i1 ^ j);

//                System.out.println(dp4[0]);System.out.println(dp4[1]);

//                int var1 = dp2.get(cnt).get(cmmmc(i, j)) != null ? dp2.get(cnt).get(cmmmc(i, j)) : 0;
//                int var2 = 1 << (present.get(i) - 1);
//                int var3 = dp2.get(cnt - 1).get(j) != null ? dp2.get(cnt - 1).get(j) : 0;
//                dp2.get(cnt).put(cmmmc(i, j), (var1 + var2 * var3) % cst);
//                int var4 = var2;
//                int var5 = dp2.get(cnt).get(j) != null ? dp2.get(cnt).get(j) : 0;
//                dp2.get(cnt).put(j, (var5 + var4 * var3) % cst);
            }
//            System.out.println(keyset);

            keyset = keyset2;
            aux = dp3;
            dp3 = dp4;
            dp4 = aux;
        }
        System.out.println(dp3[0] - 1);
    }

    private static int modConstPower(int i) {
        long result = 1;
        while (i > 0) {
            int c = i >= 30 ? 30 : i;
            result = result << c;
            i -= c;
            result %= cst;
        }
        return (int)result;
    }

    static int toBinary(int powerFreeNumber) {
        int cnt = -1;
        int toReturn = 0;
        while (powerFreeNumber != 1) {
            cnt++;
            if (powerFreeNumber % primes[cnt] == 0) {
                toReturn += 1 << cnt;
                powerFreeNumber /= primes[cnt];
            }
        }
        return toReturn;
    }

    // if TLE, cache powerFree function.
    static int powerFree(int i) {
        int i1 = i;
        for (int j = 2; j < Math.abs(Math.sqrt(i+1)); j++) {
            if (i % (j * j) == 0) return powerFree(i / (j * j));
        }
        return i;
    }

    static int cmmmc(int i, int j){
        int i1 = i; int j1 = j;
        while (j > 0) {
            int aux = j; j = i % j; i = aux;
        }
        return (i1 * j1) / (i * i);
    }

}
