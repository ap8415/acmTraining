package ACM_Contests.ACPC_2018;

import java.io.*;
import java.lang.*;
import java.util.*;

public class I {

    static class Icecream implements Comparable<Icecream> {
        int calories, happiness;

        public Icecream(int calories, int happiness) {
            this.calories = calories;
            this.happiness = happiness;
        }

        @Override
        public int compareTo(Icecream o) {
            return (calories - o.calories != 0) ? calories - o.calories : o.happiness - happiness;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("icecream.in"));

        int tests= sc.nextInt();
        for (int cases = 1; cases <= tests; cases++) {
            int N= sc.nextInt(); int K = sc.nextInt();

            Icecream[] icecreams = new Icecream[N];
            for (int i = 0; i < N; i++) {
                icecreams[i] = new Icecream(sc.nextInt(), 0);
            }
            for (int i = 0; i < N; i++) {
                icecreams[i].happiness = sc.nextInt();
            }

            Arrays.sort(icecreams);

            int biggest = 0;
            while (biggest < N && icecreams[biggest].calories <= icecreams[K-1].calories) biggest++; // then biggest >= K;

            // last is biggest-1
            int[] happinesses = new int[biggest];
            for (int i = 0; i < biggest; i++) happinesses[i] = icecreams[i].happiness;

            Arrays.sort(happinesses);
            long total = 0;
            for (int i = 0; i < K; i++) {
                total += happinesses[biggest -1 -i];
            }

            System.out.println(String.format("%d %d", icecreams[K-1].calories, total));
        }
    }

}
