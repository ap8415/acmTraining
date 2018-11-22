package ACMCodeLibrary;

import java.util.Arrays;
import java.util.Comparator;
import java.io.*;
import java.util.StringTokenizer;

public class UsefulSnippets {

    public static void main(String[] args) throws FileNotFoundException {

        // Convert array to Stream (Arrays.stream), and back to array (toArray(<type>[]::new))
        char[][] output = new char[1][1];
        String[] outputAsStrings = Arrays.stream(output).map(String::valueOf).toArray(String[]::new);
        //

    }
    /**
     * Compares strings of letters, with ordering given by:
     * letters L1 and L2 are only compared directly if they are the same letter in a different case (e.g a - A, b - B).
     * In that case, the lowercase letter is considered bigger.
     * otherwise, compare lowercase(L1) to lowercase(L2).
     *
     * Example ordering for all permutations of AaB:
     * AaB < ABa < aAB < aBA < BAa < BaA
     *
     * Not bug-tested.
     */
    static Comparator<String> lowerCaseOnlyForSameLetterComparator = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            for (int i = 0; i < Math.min(o1.length(), o2.length()); i++) {
                if (Character.toLowerCase(o1.charAt(i)) < Character.toLowerCase(o2.charAt(i))) {
                    return -1;
                } else if (Character.toLowerCase(o1.charAt(i)) > Character.toLowerCase(o2.charAt(i))) {
                    return 1;
                } else {
                    if (o1.charAt(i) != o2.charAt(i)) return o1.charAt(i) - o2.charAt(i);
                }
            }
            return o1.length() - o2.length();
        }
    };


    // TODO: add implementation of actual next-permutation algorithm. The generation implementation here works as a
    // pseudo-backtracking thing, not an actual intelligent algorithm

    // ALL PERMUTATIONS OF A WORD - no duplicates, sorted

    /**
     *  Returns all the possible permutations of {@code word},
     *  with all duplicates removed, sorted according to natural character order.
     *
     *  The resulting char[] array is completely filled, so we can do stuff like
     *  for (char[] x : permutationsOf(y)) {
     *
     *  }
     */
    static char[][] permutationsOf(char[] word) {

        // Solve case of one-letter word separately
        if (word.length == 1) {
            char[][] words = new char[1][1]; words[0] = word; return words;
        }

        char[][] prevWords = permutationsOf(Arrays.copyOfRange(word, 1, word.length));

        char[][] words = new char[prevWords.length * word.length][word.length];
        int currWord = 0;

        for (char[] smallerWord: prevWords) {
            for (int i = 0; i < word.length; i++) {
                int ctr = 0;
                boolean filled = false;
                char[] goodWord = new char[word.length];
                while (ctr < word.length) {
                    if (ctr == i) {
                        goodWord[ctr] = word[0];
                        filled = true;
                    }
                    else {
                        goodWord[ctr] = smallerWord[ctr - (filled ? 1 : 0)];
                    }
                    ctr++;
                }
                words[currWord] = goodWord; currWord++;
            }
        }

        String[] outputAsStrings = Arrays.stream(words).map(String::valueOf).toArray(String[]::new);

        Arrays.sort(outputAsStrings);

        char[][] finalWords = new char[prevWords.length * word.length][word.length];

        int ctr = 0; String prev = null;
        for (String output : outputAsStrings) {
            if (!output.equals(prev)) {
                prev = output; finalWords[ctr] = output.toCharArray(); ctr++;
            }
        }

        return Arrays.copyOfRange(finalWords, 0, ctr);
    }

    /**
     * Returns the factorial of n, naively calculated.
     * Restrictions: n small (int will overflow)
     * */
    public static int factorial(int n) {
        if (n > 1) return n * factorial(n-1);
        if (n == 1) return 1;
        if (n == 0) return 1;
        return 0;
    }
}
