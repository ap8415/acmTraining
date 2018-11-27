package CodeforcesContests.CodeforcesRounds.CF_ROUND_447;

import java.util.*;

public class A {

    public static void main(String[] args) {
        String line = new Scanner(System.in).nextLine();

        int sum = 0;

        char[] qaq = {'Q', 'A', 'Q'};
        int[] left = new int[line.length() + 1];
        int ctr = 0;
        for (int i = 1; i <= line.length(); i++) {
            left[i] = left[i-1];
            if (line.charAt(i-1) == 'Q'){
                left[i]++;
            }
        }

        for (int i = 1; i < line.length() - 1; i++) {
            if (line.charAt(i) == 'A') {
                sum += left[i] * (left[line.length()] - left[i]);
            }
        }

        System.out.println(sum);
    }

}
