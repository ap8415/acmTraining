package ACM_Contests.Pacific_NWRC_2018;

import java.util.Scanner;

public class A {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int k = sc.nextInt();
        String mine = sc.next(); String his = sc.next();
        int n = mine.length();
        int ones = 0;
        for (int i = 0; i < n; i++) {
            ones += mine.charAt(i) == his.charAt(i) ? 1 : 0;
        }
        System.out.println(n - Math.abs(k - ones));
    }
}
