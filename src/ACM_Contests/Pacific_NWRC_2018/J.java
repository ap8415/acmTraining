package ACM_Contests.Pacific_NWRC_2018;

import java.util.*;
import java.lang.*;


public class J {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); int s = sc.nextInt();
        int maxint = -1;

        for (int i = 0; i < n; i++) {
            int q = sc.nextInt();
            maxint = Math.max(maxint, (q*s)/1000 + ((q*s)%1000==0 ? 0 : 1));
        }

        System.out.println(maxint);
    }

}
