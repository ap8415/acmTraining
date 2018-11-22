package Competitive_Programming_Book.Chapter2.ArrayManipulation_2D;

import java.util.*;
import java.lang.*;
import java.io.*;

public class UVa_12398_NumPuzz_I {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);

        int caseNo = 1;

        while(sc.hasNextLine()) {
            int[][] output = new int[3][3];

            String input = sc.nextLine();
            for (int i = input.length() - 1; i >= 0; i--) {
                switch (input.charAt(i)) {
                    case 'a':
                        output[0][0]++;
                        output[0][1]++;
                        output[1][0]++;
                        break;
                    case 'b':
                        output[0][0]++;
                        output[0][1]++;
                        output[0][2]++;
                        output[1][1]++;
                        break;
                    case 'c':
                        output[0][1]++;
                        output[0][2]++;
                        output[1][2]++;
                        break;
                    case 'd':
                        output[1][0]++;
                        output[0][0]++;
                        output[2][0]++;
                        output[1][1]++;
                        break;
                    case 'e':
                        output[1][0]++;
                        output[1][1]++;
                        output[1][2]++;
                        output[0][1]++;
                        output[2][1]++;
                        break;
                    case 'f':
                        output[1][2]++;
                        output[0][2]++;
                        output[2][2]++;
                        output[1][1]++;
                        break;
                    case 'g':
                        output[2][0]++;
                        output[2][1]++;
                        output[1][0]++;
                        break;
                    case 'h':
                        output[2][0]++;
                        output[2][1]++;
                        output[2][2]++;
                        output[1][1]++;
                        break;
                    case 'i':
                        output[2][1]++;
                        output[2][2]++;
                        output[1][2]++;
                        break;
                }
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    output[i][j] = output[i][j] % 10;
                }
            }

            System.out.println("Case #" + caseNo+":"); caseNo++;
            System.out.println(String.format("%d %d %d", output[0][0], output[0][1], output[0][2]));
            System.out.println(String.format("%d %d %d", output[1][0], output[1][1], output[1][2]));
            System.out.println(String.format("%d %d %d", output[2][0], output[2][1], output[2][2]));
        }
    }

}
