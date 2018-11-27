package CodeforcesContests.EducationalCodeforcesRounds.EducationalCodeforcesRound53;

import java.util.*;
import java.lang.*;

public class A {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        String s = sc.next();

        for (int i = 0; i < n; i++) {
            for (int j = i+1; j <= n; j++) {
                String s1 = s.substring(i, j);
                if (diverse(s1)) {
                    System.out.println("YES");
                    System.out.println(s1); return;
                }
            }
        }
        System.out.println("NO");
    }

    private static boolean diverse(String s1) {
        int[] freq = new int[26];
        for (int i = 0; i < s1.length(); i++) freq[s1.charAt(i) - 'a']++;
        for (int i =0; i < 26; i++) {
            if (freq[i] > s1.length() / 2) return false;
        }
        return true;
    }

}
