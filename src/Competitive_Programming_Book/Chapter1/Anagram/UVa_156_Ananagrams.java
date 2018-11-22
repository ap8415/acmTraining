package Competitive_Programming_Book.Chapter1.Anagram;

import java.util.*;
import java.io.*;
import java.lang.*;

public class UVa_156_Ananagrams {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // n = 1000 words -> suitable to use n^2 algorithm
        // compare all pairs of words
        // keep bool array isAnanagram[]; if two anagram words are found turn the array values to false for both
        // how we compare the words is : lowercase them, then keep an array for each in which we count the char
        // occurences

        int x = 0; // no of words
        String[] words = new String[1001];
        String[] wordsNotLowercase = new String[1001];
        boolean[] isAnanagram = new boolean[1001]; Arrays.fill(isAnanagram, true);

        while (sc.hasNext()) {
            String str = sc.next();
            if (str.equals("#")) break;
            words[x] = str.toLowerCase();
            wordsNotLowercase[x] = str; x++;
        }

        for (int i = 0; i < x-1; i++) {
            for (int j = i+1; j < x; j++) {
                int[] firstWord = new int[26];
                int[] secondWord = new int[26];
                for (int k = 0; k < words[i].length(); k++) {
                    firstWord[words[i].charAt(k) - 'a']++;
                }
                for (int k = 0; k < words[j].length(); k++) {
                    secondWord[words[j].charAt(k) - 'a']++;
                }
                boolean anagram = true;
                for (int k = 0; k <= 25; k++) {
                    if (firstWord[k] != secondWord[k]) anagram = false;
                }
                if (anagram) {
                    isAnanagram[i] = false; isAnanagram[j] = false;
                }
            }
        }
        int y = 0;
        String[] orderedWords = new String[x];

        for (int i = 0; i < x; i++) {
            if (isAnanagram[i]) {
                orderedWords[y] = wordsNotLowercase[i]; y++;
            }
        }

        Arrays.sort(orderedWords, 0, y);

        for (int i = 0; i < y; i++) {
            System.out.println(orderedWords[i]);
        }

    }
}
