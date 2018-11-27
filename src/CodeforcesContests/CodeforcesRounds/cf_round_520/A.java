package CodeforcesContests.CodeforcesRounds.cf_round_520;

import java.util.*;
import java.lang.*;

public class A {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); int currentAns = 0; int ans = 0;
        int prev = 0;
        for (int i = 0 ; i < n; i++) {
            int curr = sc.nextInt();
//            System.out.println(curr - prev);
            if (curr - prev == 1) {
                currentAns++;
            } else {
                ans = Math.max(currentAns - 1, ans);
                currentAns = 0;
            }
            prev = curr;
//            System.out.println(curr);
        }

        if (currentAns != 0) {
            if (prev == 1000) {
                ans = Math.max(currentAns, ans);
            } else {
                ans = Math.max(currentAns - 1, ans);
            }
        }

        System.out.println(ans);
    }

}
