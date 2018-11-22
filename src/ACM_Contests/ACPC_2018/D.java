package ACM_Contests.ACPC_2018;

import java.io.*;
import java.lang.*;
import java.math.BigInteger;
import java.util.*;

public class D {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("dull.in"));
        int tests = sc.nextInt();
        for (int i = 1; i <= tests; i++) solve(sc);
    }

    static class Coordinates implements Comparable<Coordinates> {
        long x, y;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Coordinates that = (Coordinates) o;

            if (x != that.x) return false;
            return y == that.y;
        }

        @Override
        public int hashCode() {
            int result = (int) (x ^ (x >>> 32));
            result = 31 * result + (int) (y ^ (y >>> 32));
            return result;
        }

        public Coordinates(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Coordinates{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        @Override
        public int compareTo(Coordinates o) {
            return -Long.compare(y, o.y);
        }
    }

    private static void solve(Scanner sc) {
        int n = sc.nextInt(); int m = sc.nextInt(); int k = sc.nextInt();

        Coordinates[] sorted = new Coordinates[k];
        for (int i = 0; i < k; i++) {
            sorted[i] = new Coordinates(sc.nextInt(), sc.nextInt());
        }
        Arrays.sort(sorted, new Comparator<Coordinates>() {
            @Override
            public int compare(Coordinates o1, Coordinates o2) {
                return Long.compare(o1.x, o2.x) != 0 ? Long.compare(o1.x, o2.x) : Long.compare(o2.y, o1.y);
            }
        });

        PriorityQueue<Coordinates> alreadySeen = new PriorityQueue<>();
        BigInteger sol = BigInteger.ZERO;
        int howManySoFar = 0;
        for (Coordinates by_x : sorted) {
            int sign = howManySoFar%2==0?1:-1;

            Coordinates second = new Coordinates(n, m+1);
            Coordinates first = null;


            alreadySeen.add(by_x);
            PriorityQueue<Coordinates> tmp = new PriorityQueue<>(alreadySeen);
            while (!tmp.isEmpty()) {
                Coordinates by_y = tmp.poll();
                if (first != null && first.y <= by_x.y) break;

                first = second;
                second = by_y;
//                System.out.println(first + " and "+ second);
                sol = sol.add(BigInteger.valueOf(
                        sign    * (n - by_x.x + 1)
                                * (first.y - second.y)));
                sign = -sign;

                if (by_y.y <= by_x.y) break;
            }
//            System.out.println(sol + " at " + by_x);
            howManySoFar++;
        }
        System.out.println(sol + " " + BigInteger.valueOf(m).multiply(BigInteger.valueOf(n)).subtract(sol));

    }

}
