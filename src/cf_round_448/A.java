package cf_round_448;

import java.util.*;

public class A {

    public static void main(String[] args) {
       Scanner scan = new Scanner(System.in);

       int n = scan.nextInt();
       int[] angles = new int[n];

       for (int i = 0; i < n; i++) {
           angles[i] = scan.nextInt();
       }

       int minsum = 180;

       for (int i = 0; i < n; i++) {
           for (int j = i; j < n; j++) {
               int sum = 0;
               for (int k = i; k <= j; k++) {
                   sum += angles[k];
               }
               minsum = Math.min(minsum, Math.abs(180 - sum));
           }
       }

        System.out.println(2 * minsum);
    }
}
