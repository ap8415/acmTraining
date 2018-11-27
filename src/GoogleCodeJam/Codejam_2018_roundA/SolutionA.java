package GoogleCodeJam.Codejam_2018_roundA;

import java.util.*;

public class SolutionA {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();

        for (int alfa = 1; alfa <= cases; alfa++) {
            int d = sc.nextInt();
            String robot = sc.next();
            char[] a = new char[robot.length()];
            int power = 0; int charge = 1;
            for (int i = 0; i <= robot.length() - 1; i++) {
                a[i] = robot.charAt(i);
                switch(a[i]) {
                    case 'C': charge *= 2; break;
                    case 'S': power += charge;
                }
            }

            int curr_charge = charge;

            int moves = 0; int ptr = robot.length() - 1;
            while (power > d && ptr > 0) {
                if (a[ptr] == 'S' && a[ptr-1] == 'C') {
                    power -= curr_charge / 2;
                    a[ptr - 1] = 'S'; a[ptr] = 'C';
                    ptr = robot.length() - 1;
                    curr_charge = charge;
                    moves++;
                } else {
                    if (a[ptr] == 'C') curr_charge = curr_charge / 2;
                    ptr--;
                }
            }

            if (ptr == 0) System.out.println("Case #" + alfa + ": IMPOSSIBLE");
            else System.out.println("Case #" + alfa + ": " + moves);

        }
    }

}
