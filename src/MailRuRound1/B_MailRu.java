package MailRuRound1;

import java.util.*;
import java.io.*;
import java.lang.*;

public class B_MailRu {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // For step n with number x to be successful, I need 0, 1, ... , x-1 to be in the set. Otherwise I can't obtain x from
        // any subset, because why would it not be any of the others?
        // Conversely if those are in the set, we can choose x for step n.

        int n = sc.nextInt();
        int[] steps = new int[n];

        for (int i = 0; i < n; i++) steps[i] = sc.nextInt();

        int highestNumber = -1;
        for (int i = 0; i < n; i++) {
            if (steps[i] > highestNumber + 1) {
                // mistake
                System.out.println(i + 1); return;
            } else {
                highestNumber = Math.max(highestNumber, steps[i]);
            }
        }

        System.out.println(-1);

    }

}
