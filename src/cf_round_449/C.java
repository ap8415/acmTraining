package cf_round_449;

import java.util.*;

public class C {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // max exponent possible
        int max = (int) Math.floor(Math.log(1 + 1000000000000000068L / 142) / Math.log(2)) + 1;

        int firstBit = 34;

        String recurse = "What are you doing at the end of the world? Are you busy? Will you save us?";
        String recurse1 = "What are you doing while sending \"";
        String recurse2 = "\"? Are you busy? Will you send \"";
        String recurse3 = "\"?";

        int q = scan.nextInt();
        for (int a = 1; a <= q; a++) {
            int n = scan.nextInt();
            long k = scan.nextLong() - 1;
            boolean done = false;

            // if n too big, then query can be simplified.
            if (n > max) {
                int b = n - max;
                n -= b;
                if (k > 34 * b) {
                    k -= 34 * b;
                } else {
                    System.out.print(recurse1.charAt((int) (k % 34)));
                    continue;
                }
            }

            long lengthPlus68 = ((long) (Math.pow(2, n)) * 143) / 2;

            // recurse until f0
            for (int i = n; i >= 1; i--) {
                long len = lengthPlus68 - 68;
                if (k < recurse1.length()) {
                    System.out.print(recurse1.charAt((int) k));
                    done = true;
                    break;
                } else if (k < recurse1.length() + len) {
                    k -= recurse1.length();
                } else if (k < recurse1.length() + len + recurse2.length()) {
                    System.out.print(recurse2.charAt((int)(k - recurse1.length() - len)));
                    done = true;
                    break;
                } else if (k < recurse1.length() + 2 * len + recurse2.length()) {
                    k-= recurse1.length() + len + recurse2.length();
                } else if (k < recurse1.length() + 2 * len + recurse2.length() + recurse3.length()){
                    System.out.print(recurse3.charAt((int)(k- recurse1.length() - 2 * len - recurse2.length())));
                    done = true;
                    break;
                } else {
                    System.out.print('.');
                    done = true;
                    break;
                }
                lengthPlus68 /= 2;
            }

            if (done) {
                continue;
            } else {
                // means its f0
                if (k < recurse.length()) System.out.print(recurse.charAt((int)k));
                else System.out.print('.');
               // System.out.print(recurse.charAt((int) k));
            }
        }
    }
}
