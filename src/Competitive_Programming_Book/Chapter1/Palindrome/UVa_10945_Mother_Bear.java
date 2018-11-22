package Competitive_Programming_Book.Chapter1.Palindrome;

import java.util.*;
import java.io.*;
import java.lang.*;

public class UVa_10945_Mother_Bear {

    static String GOOD_ANS = "You won't be eaten!";
    static String BAD_ANS = "Uh oh..";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        meme:
        while(sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("DONE")) return;

            String inputLines = Arrays.stream(input.split("[.,?! ]")).reduce("", (x, y) -> x + y).toLowerCase();

            for (int i = 0; i < inputLines.length(); i++) {
                if (inputLines.charAt(i) != inputLines.charAt(inputLines.length() - i - 1)) {
                    System.out.println(BAD_ANS);
                    continue meme;
                }
            }

            System.out.println(GOOD_ANS);
        }
    }

}
