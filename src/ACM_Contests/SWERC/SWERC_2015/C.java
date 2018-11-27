package ACM_Contests.SWERC.SWERC_2015;

import java.lang.*;
import java.util.*;

public class C {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int tests = sc.nextInt();
        for (int i = 1 ; i <= tests; i++) solve(sc);
    }

    private static void solve(Scanner sc) {

        int n = sc.nextInt();
        PriorityQueue<Long> canvasses = new PriorityQueue<>();
        for (int i = 0; i < n; i++) canvasses.add((long) sc.nextInt());

        long total = 0;
        while (canvasses.size() > 1) {
            long a = canvasses.poll(); long b = canvasses.poll();
            canvasses.add(a+b); total += (a+b);
        }
        System.out.println(total); // need to paint alteast once
    }

}
