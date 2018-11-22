package Educational_CF_54;


import java.util.*;
import java.io.*;
import java.lang.*;


public class C {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for (int cases = 1 ; cases <= t; cases++) {
            int d = sc.nextInt();
            if (d > 0 && d < 4) {
                System.out.println("N");
            } else {
                double sol1 = (d + Math.sqrt(d * d - 4 * d)) / 2;
                double sol2 = (d - Math.sqrt(d * d - 4 * d)) / 2;
                System.out.println(String.format("Y %.9f %.9f", sol1, sol2));
            }
        }

    }

}
