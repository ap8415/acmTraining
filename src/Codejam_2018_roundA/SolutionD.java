package Codejam_2018_roundA;

import java.util.*;

public class SolutionD {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int test = sc.nextInt();

        here_label:
        for (int alfa = 1; alfa <= test; alfa++) {

            int area = sc.nextInt();
            int i = 2; // y-coord of sq
            int squares = area / 9 + (area % 9 == 0 ? 0 : 1);
            int[][] filled = new int[3][3];
            for (int e = 1; e <= squares; e++) {
                boolean aaa = true;
                while (aaa) {

                    // fill square i
                    System.out.println("2 " + i);
                    System.out.flush();
                    int x = sc.nextInt();
                    int y = sc.nextInt();
                    if (x == 0 && y == 0) {
                        continue here_label;
                    }
                    filled[x - 1][y - i + 1] = 1;

                    if (check(filled)) {
                        i += 3;
                        filled = new int[3][3];
                        aaa = false;
                    }
                }
            }
        }

    }

    private static boolean check(int[][] filled) {
        boolean result = true;
        for (int i = 0; i <=2; i++) {
            for (int j = 0; j <=2; j++) {
                result &= (filled[i][j] == 1);
            }
        }
        return result;
    }

}
