package Competitive_Programming_Book.Chapter1.Anagram;

import java.util.*;
import java.io.*;
import java.lang.*;

public class UVa_454_Anagrams {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);

        int tests = sc.nextInt(); sc.nextLine(); sc.nextLine();

        for (int cases = 1; cases <= tests; cases++) {

            String[] inputs = new String[100];

            String x = sc.nextLine(); int k = 0;
            while (!Objects.equals(x, "")) {
                inputs[k] = x; k++; if (sc.hasNext()) {
                    x = sc.nextLine();
                } else {
                    x = "";
                }
            }

            Arrays.sort(inputs, 0, k);

            for (int i = 0; i < k-1; i++){
                for (int j = i+1; j < k; j++) {
                    // In the problem description it doesn't feel like non-letters are included but apparently they
                    // are; so we use a char-spanning array to make comparisons.
                    int[] firstWord = new int[800];
                    int[] secondWord = new int[800];
                    for (int k1 = 0; k1 < inputs[i].length(); k1++) {
                        if (inputs[i].charAt(k1) != ' ') firstWord[inputs[i].charAt(k1)]++;
                    }
                    for (int k1 = 0; k1 < inputs[j].length(); k1++) {
                        if (inputs[j].charAt(k1) != ' ') secondWord[inputs[j].charAt(k1)]++;
                    }
                    boolean anagram = true;
                    for (int k1 = 0; k1 < 800; k1++) {
                        if (firstWord[k1] != secondWord[k1]) anagram = false;
                    }

                    if (anagram) System.out.println(inputs[i] + " = " + inputs[j]);
                }
            }
            if (tests > cases) System.out.println();
        }
    }

}
