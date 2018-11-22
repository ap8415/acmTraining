package cf_round_448;

import java.util.*;

public class D {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        char[] low = scan.nextLine().toCharArray();
        char[] high = scan.nextLine().toCharArray();

        int[] freq = new int[26];

        for (int i = 0; i < low.length; i++) {
            freq[low[i] - 'a'] ++;
        }

        System.out.println(fixedPrefix(low, 0, true, freq));
        System.out.println(fixedPrefix(high, 0, false, freq));



    }

    private static long fixedPrefix(char[] prefixed, int begin, boolean higherThan, int[] freq) {
        return 0;
    }
}
