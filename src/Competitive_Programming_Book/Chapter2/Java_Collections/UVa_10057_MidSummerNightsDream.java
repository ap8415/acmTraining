package Competitive_Programming_Book.Chapter2.Java_Collections;

import java.util.*;
import java.lang.*;
import java.io.*;

public class UVa_10057_MidSummerNightsDream {

   static  int n;

    public static class Stdev implements Comparable<Stdev> {
        int A;
        long stdev;
        int inNumbers; long left, right;

        public Stdev(int a, long stdev, int inNumbers, long left) {
            A = a;
            this.stdev = stdev;
            this.inNumbers = inNumbers;
            this.left = left;
            this.right = n- left - inNumbers;
        }

        @Override
        public int compareTo(Stdev o) {
            return stdev != o.stdev ? (stdev > o.stdev ? 1 : -1) : A - o.A;
        }

        @Override
        public String toString() {
            return A + " " + stdev + " " + inNumbers;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

//        FileOutputStream testOut = new FileOutputStream("test.out");
//        PrintStream out = new PrintStream(testOut);
//        System.setOut(out);
//
//        InputStream testIn = new FileInputStream("test.in");
//        System.setIn(testIn);

        Scanner sc = new Scanner(System.in);

        while(sc.hasNext()) {
            n = sc.nextInt();
            int[] numbers = new int[66000];
            for (int i = 0; i < n; i++) numbers[sc.nextInt()]++;

            List<Stdev> stdevs = new ArrayList<>();

            long left = 0;

            for (int i = 0; i <= 65536; i++) {
                if (numbers[i] == 0) continue;
                long stdev = 0;
                for (int j = 0 ; j <= 65536; j ++) {
                    if (numbers[j] != 0) stdev += Math.abs(i - j) * numbers[j];
                }
                stdevs.add(new Stdev(i, stdev, numbers[i], left));
                left += numbers[i];
            }

            Collections.sort(stdevs);
//            System.out.println(stdevs);
//
            int minA = stdevs.get(0).A;
            long minval = stdevs.get(0).stdev;

            long inNumbers = 0;

            int index = 0; int L = -1; int R = -1;
            while (index < stdevs.size() && stdevs.get(index).stdev == minval) {
                inNumbers += stdevs.get(index).inNumbers;

                    if (stdevs.get(index).left == n / 2) R = stdevs.get(index).A;
                    if (stdevs.get(index).right == n / 2) L = stdevs.get(index).A;

                index++;
            }

            if (L != -1 && R != -1) index += Math.max(Math.abs(L-R) -1, 0);
            // k-1/2 k/2

            System.out.println(String.format("%d %d %d", minA, inNumbers, index));


        }
    }

}
