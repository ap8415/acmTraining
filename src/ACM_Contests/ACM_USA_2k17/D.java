package ACM_Contests.ACM_USA_2k17;

import java.util.*;

public class D {

    static int[][][] grid;
    static int jmp;
    static int n;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        n = s.nextInt();
        jmp = s.nextInt();

        s.nextLine();

        grid = new int[n][n][2];

        for (int i = 0; i < n; i++) {
            String ln = s.nextLine();
            for (int j = 0; j < n; j++) {
                grid[i][j][0] = ln.charAt(j) == '.' ? 0 : 1;
            }
        }

        solve(0,0);

        System.out.println(grid[0][0][1] == Integer.MAX_VALUE ? -1 : grid[0][0][1]);
//        for (int i = 0; i < n; i++) {
//            System.out.println();
//            for (int j = 0; j < n; j++) {
//                System.out.print(grid[i][j][1]+" ");
//            }
//        }

    }

    // only solve for valids
    static int solve(int i, int j) {

//       System.out.println(i + "   " + j);
        if (i == n-1 && j == n-1) return 0;
//        System.out.println("nonzero");

        if (grid[i][j][1] != 0) return grid[i][j][1];

        if (grid[i][j][0] == 1) {
            grid[i][j][1] = Integer.MAX_VALUE; return Integer.MAX_VALUE;
        }

        int mini = Integer.MAX_VALUE;

        for (int k = Math.min(jmp, n-i-1); k >= 1; k--) {
            if (grid[i+k][j][0] == 0 && solve(i+k, j) != Integer.MAX_VALUE) {
                mini = Math.min(mini, 1 + solve(i+k, j));
//                if (i ==0 & j ==0) System.out.println(mini);


            }
        }

        int minj = Integer.MAX_VALUE;

        for (int k = Math.min(jmp, n-j-1); k >= 1; k--) {
            if (grid[i][j+k][0] == 0 && solve(i, j+k) != Integer.MAX_VALUE) {

                minj = Math.min(minj, 1 + solve(i, j+k));
            }
        }

        int sol = Math.min(mini, minj);

            grid[i][j][1] = sol;
            return sol;
    }

}
