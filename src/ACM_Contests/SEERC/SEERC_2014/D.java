package ACM_Contests.SEERC.SEERC_2014;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class D {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new FileInputStream("D.in"));

        int x = scan.nextInt(); int y = scan.nextInt();
        int n = scan.nextInt();
        for (int ix = 1; ix <= n; ix++) {
            int A = scan.nextInt();
            // tiles 1*A size
            int sum = x + y - 2;
            if (x % A > 2) System.out.println("NO");
            else if (y % A > 2) System.out.println("NO");
            else if (A > x || A > y) System.out.println("NO");
            else System.out.println((sum % A == 0 || A == 2)? "YES" : "NO");
        }
    }
}
