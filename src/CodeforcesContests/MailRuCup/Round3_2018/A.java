package CodeforcesContests.MailRuCup.Round3_2018;

import java.io.*;
import java.util.*;
import java.lang.*;

public class A {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        BitSet possibleTrams = new BitSet(101);
        possibleTrams.set(1, 101);
        for (int i = 0; i < n; i++) {
            BitSet atSomeStop = new BitSet(101);
            int m = sc.nextInt();
            for (int j = 0; j < m; j++) {
                atSomeStop.set(sc.nextInt());
            }
            possibleTrams.and(atSomeStop);
        }

        int nextSetBit = possibleTrams.nextSetBit(0);
        while (nextSetBit != -1) {
            System.out.print(nextSetBit + " ");
            nextSetBit = possibleTrams.nextSetBit(nextSetBit + 1);
        }
        System.out.println();

    }

}
