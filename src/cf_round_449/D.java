package cf_round_449;

import java.util.*;

public class D {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt(); int m = scan.nextInt(); int c = scan.nextInt();

        int[] sols = new int[n];
        int left = -1; int right = n;

        int cst = c % 2 == 0 ? c/2 : (c+1)/2;

        while(right - left > 1) {
            int next = scan.nextInt();

            boolean set = false;

            if (next > cst) {
                // Put on right
                for (int i = n-1; i >= right; i--) {
                    if (next > sols[i]) {
                        sols[i] = next;
                        set = true;
                        System.out.println(i+1);
                        break;
                    }
                }
                if (!set) {
                    sols[right - 1] = next;
                    right--;
                    System.out.println(right+1);
                }
            } else {
                // Put on left
                for (int i = 0; i <= left; i++) {
                    if (next < sols[i]) {
                        sols[i] = next;
                        set = true;
                        System.out.println(i+1);
                        break;
                    }
                }
                if (!set) {
                    sols[left + 1] = next;
                    left++;
                    System.out.println(left+1);
                }
            }

        }
    }

}
