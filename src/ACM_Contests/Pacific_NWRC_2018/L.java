package ACM_Contests.Pacific_NWRC_2018;

import java.util.*;
import java.lang.*;

public class L {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] a = new int[n]; int[] b = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt(); b[i] = sc.nextInt();
        }

        for (int solution = n ;solution >= 0; solution--) {
            int notLiars = 0;
            for (int i = 0; i < n; i++) {
                if (a[i] <= solution && solution <= b[i]) notLiars++;
            }
            if (notLiars == solution) {
                System.out.println(solution); return;
            }
        }

        System.out.println(-1); return;
    }

}
