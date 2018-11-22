package ACM_Contests.UKIEPC2018;

import java.util.*;
import java.lang.*;

public class F {

    static int[] fibs = new int[30];
  static  int[] thresholds = new int[30];

    static class Frequencies implements Comparable<Frequencies> {
        int frequencies; int value;

        public Frequencies(int frequencies, int value) {
            this.frequencies = frequencies;
            this.value = value;
        }

        @Override
        public int compareTo(Frequencies o) {
            return frequencies == o.frequencies ? Integer.compare(value, o.value) : -Integer.compare(frequencies, o.frequencies);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        init();

        int n = sc.nextInt();

        AVLTreeST<Frequencies> frequenciesBST = new AVLTreeST<>();
        Map<Integer, Integer> frequenciesMap = new HashMap<>();

        int total = 0;
        for (int i = 0; i < n; i++) {
            int currentChar = sc.nextInt();

            int currentFreq = frequenciesMap.getOrDefault(currentChar, 0);
            frequenciesMap.put(currentChar, currentFreq+1);
            frequenciesBST.delete(new Frequencies(currentFreq, currentChar));
            frequenciesBST.put(new Frequencies(currentFreq + 1, currentChar));
            int orderCoef = frequenciesBST.rank(new Frequencies(currentFreq+1, 200_000));

            total += eval(orderCoef);
            System.out.print(total + " ");
        }
    }

    private static void init() {
        fibs[0] = 1; thresholds[0] = 1;
        fibs[1] = 1; thresholds[1] = 2;
        for (int i = 2; i <= 29; i++) {
            fibs[i] = fibs[i-1] + fibs[i-2]; thresholds[i] = thresholds[i-1] + fibs[i];
        }
    }

    private static int eval(int x) {
        int index = 0;
//        System.out.println(x);
        while(thresholds[index] < x) index++;
        return 2 + index;
    }

}
