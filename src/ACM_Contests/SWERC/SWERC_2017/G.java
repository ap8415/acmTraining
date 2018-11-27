package ACM_Contests.SWERC.SWERC_2017;

import java.lang.*;
import java.util.*;

public class G {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); int M = sc.nextInt();
        // nodes 0->N-1 are the bottles
        // nodes N -> N+M-1 are the couriers
        // nodes N+M -> 2N+M-1 are the restaurant N times
        Map<Integer, Double>[] adjacencyList = new Map[2*N+M];

        for (int i = 0; i < 2*N+M; i++) adjacencyList[i] = new HashMap<>();

        int[][] bottles = new int[N][2];
        for (int i = 0; i < N; i++) {
            bottles[i][0] = sc.nextInt();
            bottles[i][1] = sc.nextInt();
        }

        int[][] couriers = new int[M][2];
        for (int i = 0; i < M; i++) {
            couriers[i][0] = sc.nextInt();
            couriers[i][1] = sc.nextInt();
        }

        int[] restaurant = new int[2];
        restaurant[0] = sc.nextInt(); restaurant[1] = sc.nextInt();

        // add weighted edge both ways between couriers and bottles
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                adjacencyList[j].put(i+N, manhattan(couriers[i], bottles[j]));
                adjacencyList[i+N].put(j, manhattan(couriers[i], bottles[j]));
            }
        }

        // add weighted edge both ways between bottles and restaurants
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                adjacencyList[j].put(i+N+M, manhattan(restaurant, bottles[j]));
                adjacencyList[i+N+M].put(j, manhattan(restaurant, bottles[j]));
            }
        }



    }

    static double manhattan(int[] p1, int[] p2) {
        return Math.sqrt((p1[0] - p2[0]) * (p1[0] - p2[0])  +  (p1[1] - p2[1]) * (p1[1] - p2[1]));
    }

}
