package ACM_Contests.SEERC_2014;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class H {

        public static void main(String[] args) throws FileNotFoundException {
            Scanner scan = new Scanner(new FileInputStream("H.in"));

            int m = scan.nextInt();
            int n = scan.nextInt();

            int[] freq = new int[30000];
            for (int i = 0; i <= m; i++ ){
                for (int j = i; j <= m; j++) {
                    freq[i*i+j*j]++;
                }
            }

            int total = 0;
            total += (n-2) * (m+1);

            for (int i = 0; i <= m; i++) {
                total += freq[i*i];
            }

            System.out.println(total);
        }

}
