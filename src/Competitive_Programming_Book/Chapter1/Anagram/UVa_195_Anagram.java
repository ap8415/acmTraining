package Competitive_Programming_Book.Chapter1.Anagram;

import java.util.*;
import java.lang.*;

public class UVa_195_Anagram {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int test = sc.nextInt();

        for (int testCases = 1 ; testCases <= test; testCases++) {
            char[] word = sc.next().toCharArray(); Arrays.sort(word);

            char[][] output = permutationsOf(word);

            String[] outputAsStrings = Arrays.stream(output).map(String::valueOf).toArray(String[]::new);

            Comparator<String> weirdOrderComparator = new Comparator<String>() {
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

            Arrays.sort(outputAsStrings, weirdOrderComparator);
            for (String x : outputAsStrings) System.out.println(x);
        }
    }

    /**
     *  Returns all the possible permutations of {@code word},
     *  including duplicates, in unsorted order.
     *  */
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
