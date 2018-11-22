package codejam2018_round_C;

import java.util.*;

public class A {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int T = scan.nextInt();
        for (int alfa = 1; alfa <= T; alfa++) {

            int N = scan.nextInt();
            int L = scan.nextInt();
            List<String> words = new ArrayList<>();

            int[][] letters = new int[L][26];

            for (int i = 0; i < N; i++) {
                String word = scan.next();
                for (int j = 0; j < L; j++) {
                    letters[j][word.charAt(j) - 'A'] = 1;
                }
                words.add(word);
            }

            words.sort(String::compareTo);
            boolean wordFound = false;
            char[] currentWord = new char[L];

            int[] counter = new int[L];

            for (int i = 0; i < L; i++) {
                int q = 0;
                boolean letterFound = false;
                while (!letterFound) {
                    if (letters[i][q] == 1) {
                        currentWord[i] = (char)('A' + q);
                        counter[i] = q;
                        letterFound = true;
                    } else {
                        q++;
                    }
                }
            }

            // set up first word
            int index = 0;

            while (!wordFound) {
                // backtrack over all possible words

                // check current word
                if (index < words.size() && Arrays.equals(currentWord, words.get(index).toCharArray())) {
                    index++;
                } else {
                    if (!Arrays.equals(currentWord, words.get(0).toCharArray())) {
                    System.out.println("Case #" + alfa + ": " + new String(currentWord));
                    wordFound = true;
                    break;
                    } else {
                        break;
                    }
                }

                int follow = L-1;

                // then skip to next word available
                while (follow != -1) {
                    boolean again = false;
                    boolean first = true;
                    while (first || letters[follow][counter[follow]] == 0) {
                        first = false;
                        counter[follow]++;
                        if (counter[follow] == 26) {
                            counter[follow] = 0;
                            again = true;
                        }
                    }

                    currentWord[follow] = (char)('A' + counter[follow]);
                    if (again) {
                        follow--;
                    } else {
                        follow = -1;
                    }
                }
            }

            if (!wordFound) {
                System.out.println("Case #" + alfa + ": -");
            }

        }
    }

}
