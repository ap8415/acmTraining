package CodeforcesContests.MailRuCup.Round1_2018;

import java.util.*;
import java.lang.*;

public class C_MailRu {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] left = new int[n];
        int[] right = new int[n];
        int[] childrenRank = new int[n];

        for (int i = 0; i < n; i++ ) {
            left[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++ ) {
            right[i] = sc.nextInt();
            childrenRank[i] = left[i] + right[i];
        }

        Arrays.sort(childrenRank);

        int currentEqual = 1;

        for (int i = 0; i < n; i++) {
            if (childrenRank[i] == i) {
                currentEqual = 1;
            } else if (childrenRank[i] == i - currentEqual) {
                currentEqual++;
            } else {
                System.out.println("NO"); return;
            }
        }

        // if the ranks make sense, compute and do manual verification.
        int[] childrenVal = new int[n];
        for (int i = 0; i < n; i++) {
            childrenVal[i] = n - left[i] - right[i];
        }

        for (int i = 0; i < n; i++) {
            int l = 0;
            for (int j = 0; j < i; j++) {
                if (childrenVal[j] > childrenVal[i]) l++;
            }
            if (l != left[i]) {
                System.out.println("NO"); return;
            }
            int r = 0;
            for (int j = i+1; j < n; j++) {
                if (childrenVal[j] > childrenVal[i]) r++;
            }
            if (r != right[i]) {
                System.out.println("NO"); return;
            }
        }

        System.out.println("YES");
        for (int i = 0; i < n; i++) System.out.print(childrenVal[i] + " ");

    }

}
