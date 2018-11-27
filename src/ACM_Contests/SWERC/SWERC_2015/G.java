package ACM_Contests.SWERC.SWERC_2015;

import java.lang.*;
import java.util.*;

public class G {

    static int[][] dp; static int p,k;
    static int[] current_pile;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        p = sc.nextInt();
        k = sc.nextInt();
        int[][] piles = new int[p][];
        for (int i = 0; i < p; i++) {
            int pile_size = sc.nextInt();
            piles[i] = new int[pile_size];
            for (int j =0; j < pile_size; j++) piles[i][j] = sc.nextInt();
        }

        int total = 0;
        for (int i = 0; i < p; i++) {
            current_pile = piles[i];
            total += solve() ? 0 : 1;
        }

        System.out.println(total % 2 == 0 ? "Bob will win." : "Alice can win.");

    }

    private static boolean solve() {
        dp = new int[current_pile.length + 1][2]; // dp(x) = x things left
        can_win_a(current_pile.length); // a wins
        can_force_loss = new int[current_pile.length + 1][2];
        can_force_loss_a(current_pile.length);
        System.out.println("dp: " + Arrays.deepToString(dp));
        System.out.println("can force loss: " + Arrays.deepToString(can_force_loss));
        return dp[current_pile.length][0] == 1;
    }

    static int[][] can_force_loss;

    private static int can_force_loss_a(int curr) {
        if (curr == 0) return 1; // a already lost here
        if (can_force_loss[curr][0] == 0) {
            can_force_loss[curr][0] = 1;
            for (int i = 0; i <= k; i++) {
                int next = curr - i;
                if (next < 1) continue; // cannot remove all cards
                next = next - current_pile[next-1];
                if (next < 0) continue;  // can have 0 cards but not negative
                can_force_loss[curr][0] = 2; // if we have any move that a can make, then b must
                // not be able to force a loss from it.

                if (can_force_loss_b(next) == 1) {
                    can_force_loss[curr][0] = 1;
                    break;
                }
            }
        }
        return can_force_loss[curr][0];

        // if b can't force a loss from one of the moves, then a can make that move.
        // if from any move a makes b can force a loss, then we return false.
        // if a can make no moves we return true wlog as a has already lost.
    }

    private static int can_force_loss_b(int curr) {
        if (curr == 0) return 2;
        if (can_force_loss[curr][1] == 0) {
            can_force_loss[curr][1] = 2;
            for (int i = 0; i <= k; i++) {
                int next = curr - i;
                if (next < 1) continue; // cannot remove all cards
                next = next - current_pile[next-1];
                if (next < 0) continue;  // can have 0 cards but not negative
                can_force_loss[curr][1] = 2; // if we have any move that a can make, then b must
                // not be able to force a loss from it.

                if (can_force_loss_a(next) == 2) {
                    can_force_loss[curr][1] = 1;
                    break;
                }
            }
        }
        return can_force_loss[curr][1];

    }

    private static int can_win_a(int curr) {
        if (curr == 0) return 2;
        if (dp[curr][0] == 0) {
            dp[curr][0] = 2;
            for (int i = 0; i <= k; i++) {
                int next = curr - i;
                if (next < 1) continue; // cannot remove all cards
                next = next - current_pile[next-1];
                if (next < 0) continue;  // can have 0 cards but not negative

                if (can_win_b(next) == 1) {
                    System.out.println(next);
                    dp[curr][0] = 1; break;
                }
            }
            // if no situation where a wins, then b wins
        }
        return dp[curr][0];
    }

    private static int can_win_b(int curr) {
        if (curr == 0) return 1;
        if (dp[curr][1] == 0) {
            dp[curr][1] = 1;
            for (int i = 0; i <= k; i++) {
                int next = curr - i;
                if (next < 1) continue;
                next = next - current_pile[next-1];
                if (next < 0) continue;
                // above are illegal moves

                if (can_win_a(next) == 2) {
                    dp[curr][1] = 2; break;
                }
            }
            // if no situation where a wins, then b wins
        }
        return dp[curr][1];
    }
}
